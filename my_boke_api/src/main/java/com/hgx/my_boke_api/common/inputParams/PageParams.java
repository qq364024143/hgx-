package com.hgx.my_boke_api.common.inputParams;

import java.io.Serializable;

public class PageParams<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cpage;//当前页数
	private int pageSize;//一页多少条
	private T params;//查询条件
	public PageParams() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageParams(int cpage, int pageSize, T params) {
		super();
		this.cpage = cpage;
		this.pageSize = pageSize;
		this.params = params;
	}
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public T getParams() {
		return params;
	}
	public void setParams(T params) {
		this.params = params;
	}
	
	
}
