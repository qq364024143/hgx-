package com.hgx.my_boke_eurekaclient.comment.controller;

import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hgx.my_boke_api.comment.controller.CommentController;
import com.hgx.my_boke_api.comment.entity.CommentVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;
import com.hgx.my_boke_eurekaclient.comment.service.CommentService;

/**
 * 博客评论
 * @author hgx
 *
 */
@RestController
public class CommentControllerImpl implements CommentController{
	
	private Logger log = LoggerFactory.getLogger(CommentControllerImpl.class);
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 分页查询博客评论
	 */
	@Override
	public ResponseResult<List<CommentVo>> findPageForCommentList(
			@RequestBody PageParams<String> pageParams) {
		log.info("分页查询评论，入参为："+JSONObject.toJSONString(pageParams));
		ResponseResult<List<CommentVo>> result = new ResponseResult<List<CommentVo>>();
		
		//验证参数
		if(pageParams==null){
			result.setCode("500");
			result.setMessage("参数为null");
			return result;
		}
		
		if(StringUtils.isEmpty(pageParams.getParams())){
			result.setCode("500");
			result.setMessage("bokeId为null");
			return result;
		}
		
		int cpage = 1;
		if(pageParams.getCpage()>0){
			cpage = pageParams.getCpage();
		}
		int pageSize = 0;
		if(pageParams.getPageSize()<=0){
			pageSize = 5;
		}else{
			pageSize = pageParams.getPageSize();
		}
		
		try{
			//设置分页
			PageHelper.startPage(cpage, pageSize);
			List<CommentVo> commentList = commentService.findPageForCommentList(pageParams.getParams());
			PageInfo<CommentVo> pageInfo = new PageInfo<CommentVo>(commentList);
			
			result.setCode("200");
			result.setMessage("分页查询bokeid="+pageParams.getParams()+"评论成功");
			result.setData(pageInfo.getList());
		}catch(Exception e){
			e.printStackTrace();
			log.info("分页查询评论异常，原因："+ExceptionUtils.getStackTrace(e));
			result.setCode("500");
			result.setMessage("分页查询评论异常，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 保存评论
	 */
	@Override
	public ResponseResult<Integer> saveComment(@RequestBody CommentVo comment) {
		
		log.info("保存评论："+JSONObject.toJSONString(comment));
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		try{
			int b = commentService.saveComment(comment);
			result.setCode("200");
			result.setMessage("保存评论成功");
			result.setData(b);
		}catch(Exception e){
			e.printStackTrace();
			log.info("保存评论失败，原因："+ExceptionUtils.getStackTrace(e));
			result.setCode("500");
			result.setMessage("保存评论失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 统计博客评论数
	 */
	@Override
	public ResponseResult<Integer> countBokeCommentTotal(@RequestParam("bokeId")String bokeId) {
		log.info("统计博客评论数,bokeId:"+bokeId);
		ResponseResult<Integer> result = new ResponseResult<Integer>();
		try{
			int commentTotal = commentService.countBokeCommentTotal(bokeId);
			result.setCode("200");
			result.setData(commentTotal);
			result.setMessage("统计博客评论数成功");
		}catch(Exception e){
			e.printStackTrace();
			log.info("统计博客评论数失败，bokeId："+bokeId);
			result.setCode("500");
			result.setMessage("统计博客评论数失败，bokeId："+bokeId);
		}
		return result;
	}

	/**
	 * 根据id和类型查找评论
	 */
	@Override
	public ResponseResult<CommentVo> findCommentByIdAndType(String commentId,
			String commentType) {
		ResponseResult<CommentVo> result = new ResponseResult<CommentVo>();
		log.info("根据id和类型查找评论，参数：[commentId:"+commentId+",commentType:"+commentType+"]");
		try{
			CommentVo commentVo = commentService.findCommentByIdAndType(commentId, commentType);
			result.setCode("200");
			result.setMessage("根据id和类型查找评论成功");
			result.setData(commentVo);
		}catch(Exception e){
			e.printStackTrace();
			result.setCode("500");
			result.setMessage("根据id和类型查找评论失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	/**
	 * 分页查询评论的回复
	 */
	@Override
	public ResponseResult<PageResult<CommentVo>> findPageForCommentReplys(
			@RequestParam("rootCommentId")String rootCommentId, @RequestParam("cpage")Integer cpage, @RequestParam("limit")Integer limit) {
		ResponseResult<PageResult<CommentVo>> result = new ResponseResult<PageResult<CommentVo>>();
		log.info("分页查询评论的回复，参数为：[cpage:"+cpage+",rootCommentId:"+rootCommentId+",limit:"+limit);
		if(cpage==null||cpage==0){
			cpage = 1;
		}
		if(limit == null|| limit<5){//每页最下值不能低于5
			limit = 5;
		}
		
		try{
			PageHelper.startPage(cpage, limit);
			List<CommentVo> replys = commentService.findPageForCommentReplys(rootCommentId);
			PageInfo<CommentVo> pageInfo = new PageInfo<CommentVo>(replys);
			PageResult<CommentVo> pageResult = new PageResult<CommentVo>();
			pageResult.setCpage(cpage);
			pageResult.setData(pageInfo.getList());
			pageResult.setTotal(pageInfo.getTotal());
			pageResult.setPageSize(limit);
			
			result.setCode("200");
			result.setMessage("分页查询评论的回复成功");
			result.setData(pageResult);
		}catch(Exception e){
			e.printStackTrace();
			log.info("分页查询评论的回复失败，原因："+ExceptionUtils.getStackTrace(e));
			result.setCode("500");
			result.setMessage("分页查询评论的回复失败，原因："+ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

}
