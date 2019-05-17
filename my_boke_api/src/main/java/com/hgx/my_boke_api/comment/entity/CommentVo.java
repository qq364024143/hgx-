package com.hgx.my_boke_api.comment.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 评论实体,一对多，一个评论多个回复
 * @author hgx
 *
 */
public class CommentVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String commentId;//评论ID
	private String commentContent;//评论内容
	private String bokeId;//博客Id
	private String commentUserId;//评论用户
	private String rootCommentId;//根评论Id
	private String commentType;//评论类型，0博客的评论，1评论的评论
	private String parentCommentId;//父类评论ID
	private String parentCommentUserId;//父类评论用户ID
	private String commentTime;//评论时间
	private String commentUserNickname;//评论用户昵称
	private String commentUserUserPicture;//评论用户头像
	private String parentCommentUserNickname;//父类评论用户昵称
	private String parentCommentUserUserPicture;//父类评论用户头像
	private int replyTotal;//根评论才有的回复总数
	
	private List<CommentVo> replys = new ArrayList<CommentVo>();//评论回复集合

	public CommentVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentVo(String commentId, String commentContent, String bokeId,
			String commentUserId, String rootCommentId, String commentType,
			String parentCommentId, String parentCommentUserId,
			String commentTime, String commentUserNickname,
			String commentUserUserPicture, String parentCommentUserNickname,
			String parentCommentUserUserPicture, List<CommentVo> replys) {
		super();
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.bokeId = bokeId;
		this.commentUserId = commentUserId;
		this.rootCommentId = rootCommentId;
		this.commentType = commentType;
		this.parentCommentId = parentCommentId;
		this.parentCommentUserId = parentCommentUserId;
		this.commentTime = commentTime;
		this.commentUserNickname = commentUserNickname;
		this.commentUserUserPicture = commentUserUserPicture;
		this.parentCommentUserNickname = parentCommentUserNickname;
		this.parentCommentUserUserPicture = parentCommentUserUserPicture;
		this.replys = replys;
	}

	
	public int getReplyTotal() {
		return replyTotal;
	}

	public void setReplyTotal(int replyTotal) {
		this.replyTotal = replyTotal;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getBokeId() {
		return bokeId;
	}

	public void setBokeId(String bokeId) {
		this.bokeId = bokeId;
	}

	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getRootCommentId() {
		return rootCommentId;
	}

	public void setRootCommentId(String rootCommentId) {
		this.rootCommentId = rootCommentId;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}

	public String getParentCommentUserId() {
		return parentCommentUserId;
	}

	public void setParentCommentUserId(String parentCommentUserId) {
		this.parentCommentUserId = parentCommentUserId;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getCommentUserNickname() {
		return commentUserNickname;
	}

	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}

	public String getCommentUserUserPicture() {
		return commentUserUserPicture;
	}

	public void setCommentUserUserPicture(String commentUserUserPicture) {
		this.commentUserUserPicture = commentUserUserPicture;
	}

	public String getParentCommentUserNickname() {
		return parentCommentUserNickname;
	}

	public void setParentCommentUserNickname(String parentCommentUserNickname) {
		this.parentCommentUserNickname = parentCommentUserNickname;
	}

	public String getParentCommentUserUserPicture() {
		return parentCommentUserUserPicture;
	}

	public void setParentCommentUserUserPicture(String parentCommentUserUserPicture) {
		this.parentCommentUserUserPicture = parentCommentUserUserPicture;
	}

	public List<CommentVo> getReplys() {
		return replys;
	}

	public void setReplys(List<CommentVo> replys) {
		this.replys = replys;
	}

	@Override
	public String toString() {
		return "CommentVo [commentId=" + commentId + ", commentContent="
				+ commentContent + ", bokeId=" + bokeId + ", commentUserId="
				+ commentUserId + ", rootCommentId=" + rootCommentId
				+ ", commentType=" + commentType + ", parentCommentId="
				+ parentCommentId + ", parentCommentUserId="
				+ parentCommentUserId + ", commentTime=" + commentTime
				+ ", commentUserNickname=" + commentUserNickname
				+ ", commentUserUserPicture=" + commentUserUserPicture
				+ ", parentCommentUserNickname=" + parentCommentUserNickname
				+ ", parentCommentUserUserPicture="
				+ parentCommentUserUserPicture + ", replyTotal=" + replyTotal
				+ ", replys=" + replys + "]";
	}

	
	
	
}
