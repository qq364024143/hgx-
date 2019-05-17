package com.hgx.my_boke_api.comment.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hgx.my_boke_api.comment.entity.CommentVo;
import com.hgx.my_boke_api.common.inputParams.PageParams;
import com.hgx.my_boke_api.common.responseResult.PageResult;
import com.hgx.my_boke_api.common.responseResult.ResponseResult;

/**
 * 博客评论,@RestController不能写到接口上
 * @author hgx
 *@RequestMapping("")不要写到feign接口上面，会被映射为springmvc的controller
 */

public interface CommentController {
	
	/**
	 * 分页查询博客评论，通过bokeID
	 * @param pageParams
	 * @return
	 */
	@RequestMapping(value="comment/findPageForCommentList",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<List<CommentVo>> findPageForCommentList(@RequestBody PageParams<String> pageParams);
	
	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="saveComment" ,method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseResult<Integer> saveComment(@RequestBody CommentVo comment);
	
	/**
	 * 统计博客评论数
	 * @param bokeId
	 * @return
	 */
	@RequestMapping(value="countBokeCommentTotal",method=RequestMethod.GET)
	public ResponseResult<Integer> countBokeCommentTotal(@RequestParam("bokeId")String bokeId);
	/**
	 * 通过commentId和commentType查询评论
	 * @param commentId
	 * @param commentType
	 * @return
	 */
	@RequestMapping(value = "findCommentByIdAndType",method=RequestMethod.GET)
	public ResponseResult<CommentVo> findCommentByIdAndType(@RequestParam("commentId")String commentId, @RequestParam("commentType")String commentType);
	
	/**
	 * 分页查询评论的回复（全部回复）
	 * @param rootCommentId
	 * @param cpage
	 * @param limit
	 * @return
	 */
	@RequestMapping(value = "findPageForCommentReplys", method = RequestMethod.GET)
	public ResponseResult<PageResult<CommentVo>> findPageForCommentReplys(@RequestParam("rootCommentId")String rootCommentId,@RequestParam("cpage")Integer cpage, @RequestParam("limit")Integer limit);
}
