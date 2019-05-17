package com.hgx.my_boke_api.boke.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 收藏的博客实体
 * @author hgx
 *
 */
public class MarkBokeVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String markId;
	private String sourceFrom;
	private String netAddress;
	private String markDescript;
	private String markTitle;
	private String userId;
	private List<String> markKeywords;
	private String mysqlFullTextIndexs;
	public MarkBokeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MarkBokeVo(String markId, String sourceFrom, String netAddress,
			String markDescript, String markTitle, String userId,
			List<String> markKeywords) {
		super();
		this.markId = markId;
		this.sourceFrom = sourceFrom;
		this.netAddress = netAddress;
		this.markDescript = markDescript;
		this.markTitle = markTitle;
		this.userId = userId;
		this.markKeywords = markKeywords;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSourceFrom() {
		return sourceFrom;
	}
	public void setSourceFrom(String sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public String getNetAddress() {
		return netAddress;
	}
	public void setNetAddress(String netAddress) {
		this.netAddress = netAddress;
	}
	public String getMarkDescript() {
		return markDescript;
	}
	public void setMarkDescript(String markDescript) {
		this.markDescript = markDescript;
	}
	public String getMarkTitle() {
		return markTitle;
	}
	public void setMarkTitle(String markTitle) {
		this.markTitle = markTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getMarkKeywords() {
		return markKeywords;
	}
	public void setMarkKeywords(List<String> markKeywords) {
		this.markKeywords = markKeywords;
	}
	public String getMysqlFullTextIndexs() {
		return mysqlFullTextIndexs;
	}
	public void setMysqlFullTextIndexs(String mysqlFullTextIndexs) {
		this.mysqlFullTextIndexs = mysqlFullTextIndexs;
	}
	@Override
	public String toString() {
		return "MarkBokeVo [markId=" + markId + ", sourceFrom=" + sourceFrom
				+ ", netAddress=" + netAddress + ", markDescript="
				+ markDescript + ", markTitle=" + markTitle + ", userId="
				+ userId + ", markKeywords=" + markKeywords
				+ ", mysqlFullTextIndexs=" + mysqlFullTextIndexs + "]";
	}
	
	
	
}
