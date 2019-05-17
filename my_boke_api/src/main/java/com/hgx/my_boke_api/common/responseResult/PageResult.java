package com.hgx.my_boke_api.common.responseResult;

import java.util.ArrayList;
import java.util.List;


/**
 * 作为接口的的响应实体（公共的服务端与客户端使用）
 * @author hgx
 *
 * @param <T>
 */
public class PageResult<T> {

	private int cpage;//当前页数
	private int pageNums;//总页数 
	private long total;//总条数
	private int pageSize;//一页多少条
	private List<T> data = new ArrayList<T>();//查询的数据
	public PageResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageResult(int cpage, int pageNums, int total, int pageSize,
			List<T> data) {
		super();
		this.cpage = cpage;
		this.pageNums = pageNums;
		this.total = total;
		this.pageSize = pageSize;
		this.data = data;
	}
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getPageNums() {
		return (int)(total%pageSize==0?total/pageSize:((total/pageSize)+1));
	}
	private void setPageNums(int pageNums) {
		this.pageNums = pageNums;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "PageResult [cpage=" + cpage + ", pageNums=" + pageNums
				+ ", total=" + total + ", pageSize=" + pageSize + ", data="
				+ data + "]";
	}
	
	
	
}
