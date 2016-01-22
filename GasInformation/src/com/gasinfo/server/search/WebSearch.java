package com.gasinfo.server.search;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import jeasy.analysis.MMAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.RangeFilter;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.BooleanClause.Occur;

import com.gasinfo.server.search.WebSearch;

import com.gasinfo.config.Configuration;

public class WebSearch {
	
	//配置路径
	Configuration rc = new Configuration("daoconfig.properties");
	String rootURL = rc.getValue("RootURL");
	String RootURL_exIndex = rc.getValue("RootURL_exIndex");
	String RootURL_exSelectedWeb = rc.getValue("RootURL_exSelectedWeb");
	String RootURL_project = rc.getValue("RootURL_project");
	
	private String RootURL = rootURL;//"E:\\My Eclipse";
	
	
	//搜索接口
	public ArrayList<HashMap<String, String>> Search(String searchkey,
			String area, String B_time, String E_time,IndexSearcher searcher , int No_sel) throws Exception {
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		BufferedReader bis = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(
								new File(
										RootURL_project + "\\words.txt"))));
		MMAnalyzer.addDictionary(bis);

		String contentORtitle = "title";
		if(No_sel==1){
			contentORtitle = "content";
		}
		
		QueryParser queryParser = new QueryParser(contentORtitle, new MMAnalyzer());
		queryParser.setDefaultOperator(QueryParser.OR_OPERATOR);
		Query query1 = queryParser.parse(searchkey);

		
		
		Term t2 = new Term(contentORtitle, area);
		Query query2 = new TermQuery(t2);
		
		RangeFilter filter;

		Hits hits;
		
		Sort sort = new Sort();
		SortField sf = new SortField("time",SortField.STRING,true);
		sort.setSort(sf);

		if ((B_time==null||B_time=="") && (area==null||area=="")) {

			hits = searcher.search(query1,sort);
		} else if ((B_time == null||B_time == "") && (area != null||area != "")) {

			BooleanQuery query = new BooleanQuery();
			query.add(query1, Occur.MUST);
			query.add(query2, Occur.MUST);
			hits = searcher.search(query,sort);
		} else if ((B_time != null||B_time != "") && (area ==null ||area == "")) {

			if (E_time==null||E_time=="") {
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				int date = c.get(Calendar.DATE);
				E_time = year + "-" + month + "-" + date;
			}
			filter = new RangeFilter("time", B_time, E_time, true, true);
			hits = searcher.search(query1, filter,sort);
		} else if ((B_time !=null||B_time !="") && (area !=null||area !="")) {

			BooleanQuery query = new BooleanQuery();
			query.add(query1, Occur.MUST);
			query.add(query2, Occur.MUST);
			if (E_time == "" || E_time == null) {
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				int date = c.get(Calendar.DATE);
				E_time = year + "-" + month + "-" + date;
			}
			filter = new RangeFilter("time", B_time, E_time, true, true);
			hits = searcher.search(query, filter,sort);
		} else {
			return null;
		}
		
		
		for (int i = 0, j = 0; j <= 100 && i < hits.length(); i++) {
			HashMap<String, String> timemap = new HashMap<String, String>();
			if (hits.doc(i).get("title").length() > 5
					&& hits.doc(i).get("time").length() == 10
					&& hits.doc(i).get("time").indexOf('2') == 0) {
				
				timemap.put("source", hits.doc(i).get("source"));
				timemap.put("title",
						this.HighLight(hits.doc(i).get("title"), searchkey));
				timemap.put("html", hits.doc(i).get("html"));
				timemap.put("time", hits.doc(i).get("time"));
				list.add(timemap);
			}
			j++;
		}
		searcher.close();
		
		ArrayList<HashMap<String, String>> list_QC = new ArrayList<HashMap<String, String>>();
		
		for(int i=0;i<list.size();i++){
			if(!list_QC.contains(list.get(i))){
				list_QC.add(list.get(i));
			}
		}
		
		return list_QC;
  
	}

	// 高亮显示
	String HighLight(String title, String searchkey) throws Exception {

		String[] words = new MMAnalyzer().segment(searchkey, " ").split(" ");
		for (int i = 0; i < words.length; i++) {
			if (title.indexOf(words[i]) != -1) {
				title = title.replace(words[i], "<font color=\"red\">"
						+ words[i] + "</font>");
			}
		}
		return title;
	}
	
	//创建搜索
	public ArrayList<HashMap<String, String>> WebSearch(String searchkey,
			String area, String B_time, String E_time,int No , int No_sel) throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String PATH = null;
        
        switch(No){
        case -1: PATH = RootURL_exIndex+"\\IndexAll";break;
        case 1: PATH = RootURL_exIndex+"\\GongZuoDongTaiIndex";break;
        case 2: PATH = RootURL_exIndex+"\\KuangChanGuanLiIndex";break;
        case 3: PATH = RootURL_exIndex+"\\ZhengCeFaGuiIndex";break;
        case 4: PATH = RootURL_exIndex+"\\YiZouReDianIndex";break;   
        case 5: PATH = RootURL_exIndex+"\\GuoJiHeZuoIndex";break;
        case 6: PATH = RootURL_exIndex+"\\KeJiJinZhanIndex";break;
        case 7: PATH = RootURL_exIndex+"\\TuPianXinWenIndex";break;
        case 8: PATH = RootURL_exIndex+"\\LingDaoDongTaiIndex";break;
        case 9: PATH = RootURL_exIndex+"\\TongJiShuJuIndex";break;
        case 10: PATH = RootURL_exIndex+"\\KanTanKaiFaIndex";break;
        case 11: PATH = RootURL_exIndex+"\\YouJiaXinXiIndex"; break;  
        }
		
		IndexSearcher searcher = new IndexSearcher(PATH);
		list=this.Search(searchkey, area, B_time, E_time, searcher , No_sel);
		return list;
	}

	public static void main(String[] args) throws Exception {
		String title = "英国石油公司希望参与中国页岩气开发";
		String searchKey = "石油";
		title = new WebSearch().HighLight(title, searchKey);
		
		WebSearch websearch = new WebSearch();
		ArrayList<HashMap<String, String>> searchlist = new ArrayList<HashMap<String, String>>();
		
		searchlist = websearch.WebSearch(searchKey, null, null, null, 0,-1);
		System.out.print(title);

	}
	
}
