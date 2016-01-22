package com.gasinfo.util;

/**
 * 分页中页面对象，属性包括当前第几页，每页消息条数，总页数，总消息条目数
 * @author Administrator
 *
 */
public class PageRoll {
	/**
	 * 当前第几页，默认为1
	 */
	private int currPage =1;
	/**
	 * 每页消息条目数
	 */
	private int pageSize =10;
	/**
	 * 总页面数
	 */
	private int pageCount;
	/**
	 * 总消息条目数
	 */
	private int totalCount;
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		if(totalCount%pageSize==0){
			pageCount = totalCount/pageSize;
		}else{
			pageCount =totalCount/pageSize+1;
		}
		return pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	}
