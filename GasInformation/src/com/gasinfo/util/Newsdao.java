package com.gasinfo.util;
import java.util.ArrayList;
import java.util.HashMap;

public interface Newsdao{
    //后台动态热点和动态参考的清空功能
	public void deleteData(int Module);
	//前台的分页功能
	public ArrayList<News> visiblePage(PageRoll pageRoll, int Module) ;
	//首页看到的20条和前台每个模块的一周热点
	public ArrayList<News> getIndexNews(String indexname);
	//后台的修改功能
	public void Update(News news);
	//后台的单个删除
	public void delete(int id,int Module);
	//后台的分页功能
	public ArrayList<News> pageList(PageRoll pageRoll,int Module);
	/**
	 * 获取指定页码的工作动态参考新闻数据，因该模块数据涉及分栏，以及分栏排序，故不与其他模块分页同接口,
	 * 同时考虑此模块是参考文件生成的工作目录，故数据量不大，实现方式与其他模块不同
	 * @param pageRoll 要显示的页面对象
	 * @return
	 */
	public ArrayList<News> pageListOfDtck(PageRoll pageRoll);
    //后台的批量删除
	public void deleteStr(int[] id,int Module);
	//后台更新入库功能
	public void addNews(News news,int moduleId);

	//对于每个标题可以点进去看正文，链接的功能
	public News findById(int id,int Module);
	//后台的批量下载功能
	public ArrayList<News> getMoreNews(String[] newsId,int moduleId);
    //根据标题找到相应的正文，也相当于链接的功能
	public News findByTitle(String title,int Module);
	//后台的批量删除功能
	public void deleteMore(String[] id,int Module);
	//后台的可见不可见功能
	public void isVisble(int newsId, int moduleId,int visible);
	//前台的点击量功能，在前台每次点进去一篇文章，点击量就会加一
	public void countVisit(int newsId, int moduleId);
	/**
	 * 获取动态参考中指定分栏的排序columnOrder
	 * @param columnName 分栏名
	 * @return
	 */
	public int getColumnOrder(String columnName);
	/**
	 * 获取工作动态参考新闻的分栏信息
	 * @return 分栏信息数组
	 */
	public String[] getColumnsName();
	/**
	 * 获取工作动态参考新闻的分栏信息
	 * @return 分栏信息数组
	 */
	public boolean modifyColumnName(int newsID,String toColumnName);
	/**
	 * 向工作动态参考和一周热点参考插入新闻
	 * @param newsId 被选消息的ID
	 * @param Module 被选消息所属模块
	 * @param toModule 插入到指定模块，12位动态参考，13为一周热点
	 * @param columnName 新闻消息所属分栏信息
	 */
	public void addTobook(String[] newsId, int Module,int toModule,String columnName);
	
	/**
	  * 向工作动态参考和一周热点参考插入新闻时，将所选新闻插入热点新闻表，前台每一个模块都有一个热点新闻栏目
	  * @param newsId 被选消息的ID
	  * @param module 被选消息所属模块
	  * @param toModule 插入到指定模块，12位动态参考，13为一周热点
	 */
	public void addTobookdb(String[] newsId, int module, int toModule,String time);
	//后台在动态参考和动态热点有生成动态热点和生成动态参考的功能
	public ArrayList<News> downloadAll(int Module);
	//对于数据库中热词表中没东西的时候要用到这个
	public void firstExtractWords(int moduleId);
	//前台每个模块都有一个热词分析内容
	public ArrayList<HashMap<String,Integer>> getHotword(int moduleId);
	//后台的业务定制中，有一个更新热词的功能
	public void updateHotword();
	//后台的业务定制中，热词频数分析一进去显示的图
	public ArrayList<HashMap<String, Object>>getWordandCount(int moduleId);
	//后台的业务定制中，热词增量分析一进去显示的图
	public ArrayList<HashMap<String, Object>>getZlfxChart(ArrayList<HashMap<String, Object>> wordlist);
	//后台的业务定制中，热词频数分析中可以选择时间，模块
	public ArrayList<HashMap<String, Object>>getWordandCount(int moduleId,String begintime,String endtime);
	//后台的业务定制中，热词增量分析中可以选择时间，模块
	public ArrayList<HashMap<String, Object>>getZlfxChart(String endtime,ArrayList<HashMap<String, Object>> wordlist);
	/**
	 * 后台的动态参考和动态热点有一个上移下移置顶的操作
	 */
	public void upDownStick(int moduleId,String updown,int newsId,int updownId);
	/**
	 * 工作动态参考模块的新闻上移、下移、置顶操作
	 * @param updown 操作类型：up/down/stick
	 * @param newsId 操作的新闻ID
	 * @param columnOrder 所属分栏类别
	 * @param updownId 操作时比较用的ID
	 */
	public void upDownStickOfDtck(String updown,int newsId,int columnOrder,int updownId);
	//后台的每个模块都有一个检索功能
	public ArrayList<News> dataBaseRetrieve(String beginTime, String endTime,
			String oilField, String basin, String normal,String company,int moduleId,int titleOrContent);
	/**
	 * 关键词筛选后的检索方法
	 * @param beginTime
	 * @param endTime
	 * @param oilField
	 * @param basin
	 * @param normal
	 * @param company
	 * @param moduleId
	 * @param titleOrContent
	 * @return
	 */
	public ArrayList<News> dataBaseRetrieve2(String beginTime, String endTime,String oilField, String basin, String normal, String company,
			int moduleId, int titleOrContent);
	//后台的热词趋势分析
	public ArrayList<HashMap<String, Object>>getkeywordChart(String keyword,String begintime,String endtime);
	
	public void updateModuleInf() throws Exception;
	
	public void updateblacklist(int blackOrWhite);
	
	public ArrayList<News> titleSearch(int moduleId,String title);
	//分页获取所有用户
	public ArrayList<PhoneUser> getAllUser(PageRoll pageRoll);
	//分页获取部门用户
	public ArrayList<PhoneUser> getDepartmentUser(PageRoll pageRoll,String department);
	//改变用户角色
	public void changeRole(int userID,String role,String department);
	//获取所有组功能
	public ArrayList<Group> getAllGroup();
	//获取通知公告到数据库
	public void setNotification(String time,ArrayList<Integer> groups,String content,String publisher,String department,String title);
	
	/**
	 * 后台上传文件录入数据库
	 * @param title
	 * 			上传文件的标题
	 * @param department
	 * 			上传文件者的部门
	 * @param module
	 * 			上传文件至哪个模块（1表示热点、2表示专题、3表示数据）
	 * @param url
	 * 			上传文件在服务器端的Url
	 * @param picName
	 * 			图片名
	 * @return
	 */
	public String uploadFileInputStorage(String title,String department,int module,String url,String picName);
	
	/**
	 * 通过类别获取上传文件列表
	 * @param type
	 * @return
	 */
	public ArrayList<HashMap<String, Object>> getFileByType(int type); 
}

