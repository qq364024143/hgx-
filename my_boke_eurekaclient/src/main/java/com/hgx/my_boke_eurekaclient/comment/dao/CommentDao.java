package com.hgx.my_boke_eurekaclient.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hgx.my_boke_api.comment.entity.CommentVo;

@Repository
public interface CommentDao {
	
	/**
	 * 根据博客ID分页查询评论
	 * @param bokeId
	 * @return
	 */
	public List<CommentVo> findPageForCommentList(@Param("bokeId")String bokeId);
	
	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	public int saveComment(CommentVo comment);
	
	/**
	 * 根据博客id统计博客评论数，commentType=0的
	 * @param bokeId
	 * @return
	 */
	public int countBokeCommentTotal(@Param("bokeId")String bokeId);
	
	/**
	 * 根据类型和id查询评论
	 * @param rootCommentId
	 * @param commentType
	 * @return
	 */
	public CommentVo findCommentByIdAndType(@Param("commentId")String commentId,@Param("commentType")String commentType);
	
	/**
	 * 通过rootCommentId分页查询回复
	 * @param rootCommentId
	 * @return
	 */
	public List<CommentVo> findPageForCommentReplys(@Param("rootCommentId")String rootCommentId);
}
