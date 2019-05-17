package com.hgx.hgxboke_ui.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hgx.hgxboke_ui.feign.CommentFeign;
import com.hgx.my_boke_api.comment.entity.CommentVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;

@RequestMapping("comment")
@Controller
public class CommentController {

	@Autowired
	private CommentFeign commentFeign;

	/**
	 * 分页查询评论
	 * @param pageParams
	 * @return
	 */
	@RequestMapping(value="findPageForCommentList",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<CommentVo> findPageForCommentList(@RequestBody PageParams<String> pageParams){
		ResponseResult<List<CommentVo>>  result = commentFeign.findPageForCommentList(pageParams);
		if("200".equals(result.getCode())){
			return result.getData();
		}else{
			return new ArrayList<CommentVo>();
		}
	}

	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="saveComment",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseResult<Integer> saveComment(@RequestBody CommentVo comment,HttpServletRequest request){
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		comment.setCommentTime(fmt.format(new Date()));
		comment.setCommentId(UUID.randomUUID().toString().replace("-", ""));
		comment.setCommentUserId(request.getSession().getAttribute("userInfo")==null?"":(String)request.getSession().getAttribute("userInfo"));//从session中取
		return commentFeign.saveComment(comment);
	}

	/**
	 * 跳转到查询评论下的所有回复页面
	 * @param rootCommentId
	 * @return
	 */
	@RequestMapping("allReply")
	public String forwardToCommentAllReplyJsp(@RequestParam("commentId")String commentId,HttpServletRequest request){
		ResponseResult<CommentVo> result = commentFeign.findCommentByIdAndType(commentId, "0");
		if("200".equals(result.getCode())&&result.getData()!=null){
			request.setAttribute("rootComment", result.getData());
		}
		return "comment_all_reply";
	}

	/**
	 * 分页查询评论的回复
	 * @param rootCommentId
	 * @param cpage
	 * @param limit
	 * @return
	 */
	@RequestMapping("findPageForCommentReplys")
	@ResponseBody
	public PageResult<CommentVo> findPageForCommentReplys(@RequestParam("rootCommentId")String rootCommentId,@RequestParam("cpage")Integer cpage, @RequestParam("limit")Integer limit){
		ResponseResult<PageResult<CommentVo>> result = commentFeign.findPageForCommentReplys(rootCommentId, cpage, limit);
		return result.getData();
	}
	
	/**
	 * 获取博客评论数，用于重新渲染分页
	 * @param bokeId
	 * @return
	 */
	@RequestMapping("getBokeCommentCount")
	@ResponseBody
	public ResponseResult<Integer> getBokeCommentCount(String bokeId){
		ResponseResult<Integer> result = commentFeign.countBokeCommentTotal(bokeId);
		return result;
	}
}
