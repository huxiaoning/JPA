package org.hxn.app.util;

public class Pager<T> {

	private Integer pageNum;// 请求参数:当前页码,构造分页对象时使用

	private Integer pageSize;// 请求参数:每页显示数据量,构造分页对象时使用

	private Integer firstResult;// jpa分页api参数:通过计算赋值

	private Integer maxResults;// jpa分页api参数:通过计算赋值

	public Pager() {

	}

	public Pager(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.firstResult = (pageNum - 1) * pageSize;
		this.maxResults = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

}
