package com.gasinfo.action;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;

import com.gasinfo.util.DaoFactory;
import com.gasinfo.util.Newsdao;
import com.gasinfo.util.NewsdaoJdbcImpl;
import com.opensymphony.xwork2.ActionContext;


public class ChartAction implements ServletRequestAware,Action {
	private HttpServletRequest request;
	public void setServletRequest(HttpServletRequest hsr) {
		request = hsr;
	}
	private JFreeChart chart;

	
	public String jfreeChart() {
		String endtime=request.getParameter("endtime");
		String begintime=request.getParameter("begintime");
		try{
			this.chart=createChart(0,begintime,endtime);
		}catch (Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String zlfxchart() {	
		String endtime=request.getParameter("endtime");
		try{
			this.chart = zlfxChart(endtime);	
		}catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String onewordtrend() throws UnsupportedEncodingException{
		String keyword=request.getParameter("keyword");
		keyword =new String(keyword.getBytes("iso-8859-1"),"utf-8");
		String endtime=request.getParameter("endtime");
		String beginetime=request.getParameter("begintime");
		try{
		this.chart=onewordTrend(keyword,endtime,beginetime);
		}catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	public JFreeChart getChart() {
		return chart;
	}
	
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	//创建柱状图
	public JFreeChart createChart(int moduleId,String begintime,String endtime) {
		ArrayList<HashMap<String, Object>> wordList=null;
		Newsdao jdbcImpl=DaoFactory.getInstance().getNewsDao();
		wordList = jdbcImpl.getWordandCount(moduleId,begintime,endtime);
		HttpSession session=request.getSession();
		session.setAttribute("getwords", wordList);
		String[]word = new String[wordList.size()];
		double[][]count = new double[wordList.size()][1];
		for (int i = 0; i < wordList.size(); i++) {
			word[i] = wordList.get(i).get("word").toString();
			count[i][0] = Double.parseDouble(wordList.get(i).get("count").toString());
		}
		String[] columnKeys = { ""};

		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				 word , columnKeys , count);
		JFreeChart chart = ChartFactory.createBarChart3D("高频词频数分析", "高频词",
				"频数", dataset, PlotOrientation.VERTICAL, true, true, true);
		
		// 设置标题字体，可以处理乱码问题
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 14));

		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
		customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		customBarRenderer.setBaseItemLabelsVisible(true); 
		// X轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置设置X轴上的文字
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		// 设置X轴的标题文字
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

		// Y轴
		ValueAxis numberaxis = plot.getRangeAxis();
		// 设置设置Y轴上的文字
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		// 设置Y轴的标题文字
		numberaxis.setLabelFont(new Font("宋体", Font.BOLD, 14));

		// 这句代码解决了底部汉字乱码的问题
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		return chart;
	}
	
	
	public JFreeChart zlfxChart(String endtime) {
		HttpSession session=request.getSession();
		ArrayList<HashMap<String, Object>> wordList2=(ArrayList)session.getAttribute("getwords");
		ArrayList<HashMap<String, Object>> wordList=null;
		Newsdao jdbcImpl=DaoFactory.getInstance().getNewsDao();
		if(endtime==""||endtime==null){
			wordList=jdbcImpl.getZlfxChart(wordList2);
		}else{
			wordList = jdbcImpl.getZlfxChart(endtime,wordList2);
		}
		String[]word = new String[wordList.size()];
		double[][]count = new double[wordList.size()][1];
		for (int i = 0; i < wordList.size(); i++) {
			word[i] = wordList.get(i).get("word").toString();
			count[i][0] = Double.parseDouble(wordList.get(i).get("count").toString());
		}
		String[] columnKeys = { ""};

		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
				 word , columnKeys , count);
		JFreeChart chart = ChartFactory.createBarChart3D("高频词增量分析图", "高频词",
				"增量", dataset, PlotOrientation.VERTICAL, true, true, true);
		
		// 设置标题字体，可以处理乱码问题
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 14));

		CategoryPlot plot = chart.getCategoryPlot();
		BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer(); 
		customBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		customBarRenderer.setBaseItemLabelsVisible(true); 
		// X轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置设置X轴上的文字
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		// 设置X轴的标题文字
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));

		// Y轴
		ValueAxis numberaxis = plot.getRangeAxis();
		// 设置设置Y轴上的文字
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		// 设置Y轴的标题文字
		numberaxis.setLabelFont(new Font("宋体", Font.BOLD, 14));

		// 这句代码解决了底部汉字乱码的问题
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		return chart;
	}
	
	//创建折线图
		public JFreeChart onewordTrend(String keyword,String endtime,String beginetime){
			ArrayList<HashMap<String, Object>> onewordlist=new ArrayList<HashMap<String,Object>>();
			Newsdao newsdao=DaoFactory.getInstance().getNewsDao();
			onewordlist=newsdao.getkeywordChart(keyword,beginetime,endtime);
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(int i=0;i<onewordlist.size();i++){
				dataset.setValue(Integer.valueOf(onewordlist.get(i).get("count").toString()),keyword,onewordlist.get(i).get("time").toString());
			}
			JFreeChart chart = ChartFactory.createLineChart(  
				       "高频词趋势分析",                    // 标题  
				       "时间",                      // 横坐标  
				       "媒体关注度",                     // 纵坐标  
				       dataset,                    // 数据  
				       PlotOrientation.VERTICAL,   // 竖直图表  
				       true,                       // 是否显示legend  
				       false,                      // 是否显示tooltip  
				       false                       // 是否使用url链接  
				   );  
			  CategoryPlot plot = (CategoryPlot) chart.getPlot();
			   chart.getTitle().setFont(new Font("宋体", Font.BOLD, 15));
			   chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
			   CategoryAxis domainAxis = plot.getDomainAxis();   
			   /*------设置X轴坐标上的文字-----------*/ 
			   domainAxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 11));   
			   /*------设置X轴的标题文字------------*/ 
			   domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));   
			   NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();   
			   /*------设置Y轴坐标上的文字-----------*/ 
			   numberaxis.setTickLabelFont(new Font("黑体", Font.PLAIN, 12));   
			    
			   /*------设置Y轴的标题文字------------*/ 
			   numberaxis.setLabelFont(new Font("黑体", Font.PLAIN, 12));
			return chart;
		}

}
