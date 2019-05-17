package com.hgx.my_boke_eurekaclient.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hgx.my_boke_api.comment.entity.CommentVo;
import com.hgx.my_boke_eurekaclient.comment.dao.CommentDao;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	/**
	 * 分页查询评论
	 * @param bokeId
	 * @return
	 */
	public List<CommentVo> findPageForCommentList(String bokeId){
		return commentDao.findPageForCommentList(bokeId);
	}
	
	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	public int saveComment(CommentVo comment){
		return commentDao.saveComment(comment);
	}
	
	/**
	 * 统计博客评论数 用于分页插件
	 * @param bokeId
	 * @return
	 */
	public int countBokeCommentTotal(String bokeId){
		return commentDao.countBokeCommentTotal(bokeId);
	}
	
	/**
	 * 根据id和类型查找评论
	 * @param commentId
	 * @param commentType
	 * @return
	 */
	public CommentVo findCommentByIdAndType(String commentId, String commentType){
		return commentDao.findCommentByIdAndType(commentId, commentType);
	}
	
	/**
	 * 通过rootCommentId分页查询回复
	 * @param rootCommentId
	 * @return
	 */
	public List<CommentVo> findPageForCommentReplys(String rootCommentId){
		return commentDao.findPageForCommentReplys(rootCommentId);
	}
}
