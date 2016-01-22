package com.gasinfo.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.gasinfo.util.*;



public class manageNewsRetrievalAction  implements ServletRequestAware , Action{
	private HttpServletRequest request;
	private String moduleId;
	private String retrievalCount;
	private  static int countRetrieval=0;
	public String getRetrievalCount() {
		return retrievalCount;
	}
	public void setRetrievalCount(String retrievalCount) {
		this.retrievalCount = retrievalCount;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	//修改检索条件，因为加入了关键词
	public String manageNewsRetrieval() {
		String checkbox1 = request.getParameter("startTime");
		String checkbox2 = request.getParameter("endTime");
		String checkbox3 = request.getParameter("oilfield");
		String checkbox4 = request.getParameter("company");
		String checkbox5 = request.getParameter("basin");
		String checkbox6 = request.getParameter("regular");
		String titleOrCoent=request.getParameter("relative");
		//关键词筛选，Output控制状态
		String checkbox8 = request.getParameter("Output");
		int indexModule = Integer.valueOf(moduleId);
		Newsdao Newsdao = DaoFactory.getInstance().getNewsDao();
		if(checkbox8.equals("2")||checkbox8.equals("0")){
			
		
		ArrayList<News> pagezone =Newsdao.dataBaseRetrieve(checkbox1, checkbox2, checkbox3, checkbox5, checkbox6, checkbox4, indexModule,Integer.valueOf(titleOrCoent));
		if(pagezone.size()==0){
			return ERROR;
		}
		PageRoll pageRoll =new PageRoll();
		pageRoll.setTotalCount(pagezone.size());
		ArrayList<News> pagelist =new ArrayList<News>();
			if(pageRoll.getCurrPage()!=pageRoll.getPageCount()){
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getCurrPage()*pageRoll.getPageSize();i++){
					pagelist.add(pagezone.get(i));
				}
			}else{
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getTotalCount();i++){
					pagelist.add(pagezone.get(i));
				}
			}
		countRetrieval++;
		HttpSession session=request.getSession();
		session.setAttribute(String.valueOf(countRetrieval), pagezone);
		request.setAttribute("indexModuleId",(Object)Integer.valueOf(indexModule));
		request.setAttribute("moduleIdcount",(Object)Integer.valueOf(countRetrieval));
		request.setAttribute("m_research", pagelist);
		request.setAttribute("pageRoll", pageRoll);
		
		}else{
			ArrayList<News> pagezone =Newsdao.dataBaseRetrieve2(checkbox1, checkbox2, checkbox3, checkbox5, checkbox6, checkbox4, indexModule,Integer.valueOf(titleOrCoent));
			if(pagezone.size()==0){
				return ERROR;
			}
			PageRoll pageRoll =new PageRoll();
			pageRoll.setTotalCount(pagezone.size());
			ArrayList<News> pagelist =new ArrayList<News>();
				if(pageRoll.getCurrPage()!=pageRoll.getPageCount()){
					for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getCurrPage()*pageRoll.getPageSize();i++){
						pagelist.add(pagezone.get(i));
					}
				}else{
					for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getTotalCount();i++){
						pagelist.add(pagezone.get(i));
					}
				}
			countRetrieval++;
			HttpSession session=request.getSession();
			session.setAttribute(String.valueOf(countRetrieval), pagezone);
			request.setAttribute("indexModuleId",(Object)Integer.valueOf(indexModule));
			request.setAttribute("moduleIdcount",(Object)Integer.valueOf(countRetrieval));
			request.setAttribute("m_research", pagelist);
			request.setAttribute("pageRoll", pageRoll);
		}
		return SUCCESS;	
	}
	public String retrievalPage() {
		String ModuleIdcount=request.getParameter("moduleIdcount");
		String moduleId=request.getParameter("moduleId");
		request.setAttribute("indexModuleId",moduleId);
		request.setAttribute("moduleIdcount",ModuleIdcount);
		HttpSession session=request.getSession();
		ArrayList<News> pagezone=null;
		pagezone=(ArrayList<News>)session.getAttribute(ModuleIdcount);
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll =new PageRoll();
		if(currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		pageRoll.setTotalCount(pagezone.size());
		ArrayList<News> pagelist =new ArrayList<News>();
			if(pageRoll.getCurrPage()!=pageRoll.getPageCount()){
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getCurrPage()*pageRoll.getPageSize();i++){
					pagelist.add(pagezone.get(i));
				}
			}else{
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getTotalCount();i++){
					pagelist.add(pagezone.get(i));
				}
			}
			request.setAttribute("m_research", pagelist);
			request.setAttribute("pageRoll", pageRoll);	
		return SUCCESS;
	}
	
	public String manageNewstitleRetrieval() throws UnsupportedEncodingException {
		String title = request.getParameter("titleKeyWord");
		title =new String(title.getBytes("iso-8859-1"),"utf-8");
		int indexModule = Integer.valueOf(moduleId);
		Newsdao Newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<News> pagezone =Newsdao.titleSearch(indexModule, title);
		if(pagezone.size()==0){
			return ERROR;
		}
		PageRoll pageRoll =new PageRoll();
		pageRoll.setTotalCount(pagezone.size());
		ArrayList<News> titlelist =new ArrayList<News>();
			if(pageRoll.getCurrPage()!=pageRoll.getPageCount()){
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getCurrPage()*pageRoll.getPageSize();i++){
					titlelist.add(pagezone.get(i));
				}
			}else{
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getTotalCount();i++){
					titlelist.add(pagezone.get(i));
				}
			}
		countRetrieval++;
		HttpSession session=request.getSession();
		session.setAttribute("title"+String.valueOf(countRetrieval), pagezone);
		request.setAttribute("indextitleModuleId",(Object)Integer.valueOf(indexModule));
		request.setAttribute("moduleIdcount",(Object)Integer.valueOf(countRetrieval));
		request.setAttribute("title_research", titlelist);
		request.setAttribute("pageRoll", pageRoll);
		return SUCCESS;			
	}
	public String retrievaltitlePage() {
		String ModuleIdcount=request.getParameter("moduleIdcount");
		String moduleId=request.getParameter("moduleId");
		request.setAttribute("indextitleModuleId",moduleId);
		request.setAttribute("moduleIdcount",ModuleIdcount);
		HttpSession session=request.getSession();
		ArrayList<News> pagezone=null;
		pagezone=(ArrayList<News>)session.getAttribute("title"+ModuleIdcount);
		String currpage = request.getParameter("currPage");
		PageRoll pageRoll =new PageRoll();
		if(currpage != null) {
			pageRoll.setCurrPage(Integer.parseInt(currpage));
		}
		pageRoll.setTotalCount(pagezone.size());
		ArrayList<News> titlelist =new ArrayList<News>();
			if(pageRoll.getCurrPage()!=pageRoll.getPageCount()){
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getCurrPage()*pageRoll.getPageSize();i++){
					titlelist.add(pagezone.get(i));
				}
			}else{
				for(int i=(pageRoll.getCurrPage()-1)*pageRoll.getPageSize();i<pageRoll.getTotalCount();i++){
					titlelist.add(pagezone.get(i));
				}
			}
			request.setAttribute("title_research", titlelist);
			request.setAttribute("pageRoll", pageRoll);	
		return SUCCESS;
	}
}
