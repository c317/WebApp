package com.gasinfo.server.search;

public class PageRoll {
	
	private int currPage =1;
	private int pageSize =15;
	private int pageCount;
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
	public int getTatalCount() {
		return totalCount;
	}
	public void setTatalCount(int totalCount) {
		this.totalCount = totalCount;
	}


}
