package com.gasinfo.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.gasinfo.server.indexdatebase.Words;
import com.gasinfo.util.JdbcUtils;
import com.gasinfo.util.News;
import com.gasinfo.util.DaoException;
import com.gasinfo.util.PageRoll;
import com.gasinfo.wordsanalysis.GetWords;

public class NewsdaoJdbcImpl implements Newsdao, UsersDao {

	public ArrayList<News> findIndex(String zone1, String zone2, String zone3,
			String normal1, String normal2, int moduleId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		News news = null;
		String sql = null;
		if (normal1 == null && normal2 == null) {
			switch (moduleId) {
			case 1:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gzdt where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 2:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kcgl where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 3:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Zcfg where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 4:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yzrd where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 5:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gjhz where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 6:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kjjz where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 7:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tpxw where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 8:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Lddt where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 9:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tjsj where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 10:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Ktkf where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 11:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yjxx where oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			}
		} else if (zone1 == null && zone2 == null && zone3 == null) {
			switch (moduleId) {
			case 1:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gzdt where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 2:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kcgl where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 3:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Zcfg where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 4:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yzrd where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 5:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gjhz where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 6:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kjjz where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 7:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tpxw where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 8:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Lddt where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 9:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tjsj where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 10:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Ktkf where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			case 11:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yjxx where type in("
						+ normal1 + "," + normal2 + ")) temp";
				break;
			}
		} else {
			switch (moduleId) {
			case 1:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gzdt where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 2:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kcgl where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 3:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Zcfg where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 4:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yzrd where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 5:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Gjhz where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 6:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Kjjz where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 7:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tpxw where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 8:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Lddt where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 9:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Tjsj where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 10:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Ktkf where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			case 11:
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from Yjxx where type in("
						+ normal1
						+ ","
						+ normal2
						+ ")"
						+ " and oilField in("
						+ "'"
						+ zone1
						+ "',"
						+ "'"
						+ zone2
						+ "',"
						+ "'"
						+ zone3
						+ "')) temp";
				break;
			}
		}

		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				news.setRownumber(rs.getInt("rownumber"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				datas.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}

	public void deleteData(int Module) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		String sql = null;
		switch (Module) {
		case 12:
			sql = "truncate table Dtrd";
			break;
		case 13:
			sql = "truncate table Dtck";
			break;
		default:
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<News> findZone(String zone1, String zone2, String zone3,
			String normal1, String normal2, String tableName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		News news = null;
		String sql = null;
		if (normal1 == null && normal2 == null) {
			if (tableName.equals("Gzdt")) {
				sql = "select * from Gzdt where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Gjhz")) {
				sql = "select * from Gjhz where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Kcgl")) {
				sql = "select * from Kcgl where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Ktkf")) {
				sql = "select * from Ktkf where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Lddt")) {
				sql = "select * from Lddt where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Tpxw")) {
				sql = "select * from Tpxw where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Kjjz")) {
				sql = "select * from Kjjz where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Tjsj")) {
				sql = "select * from Tjsj where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Yjxx")) {
				sql = "select * from Yjxx where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Yzrd")) {
				sql = "select * from Yzrd where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			} else if (tableName.equals("Zcfg")) {
				sql = "select * from Zcfg where zone in(" + "'" + zone1 + "',"
						+ "'" + zone2 + "'," + "'" + zone3 + "')";
			}
		} else if (zone1 == null && zone2 == null && zone3 == null) {
			if (tableName.equals("Gzdt")) {
				sql = "select * from Gzdt where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Gjhz")) {
				sql = "select * from Gjhz where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Kcgl")) {
				sql = "select * from Kcgl where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Ktkf")) {
				sql = "select * from Ktkf where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Lddt")) {
				sql = "select * from Lddt where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Tpxw")) {
				sql = "select * from Tpxw where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Kjjz")) {
				sql = "select * from Kjjz where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Tjsj")) {
				sql = "select * from Tjsj where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Yjxx")) {
				sql = "select * from Yjxx where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Yzrd")) {
				sql = "select * from Yzrd where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			} else if (tableName.equals("Zcfg")) {
				sql = "select * from Zcfg where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')";
			}
		} else {
			if (tableName.equals("Gzdt")) {
				sql = "select * from Gzdt where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Gjhz")) {
				sql = "select * from Gjhz where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Kcgl")) {
				sql = "select * from Kcgl where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Ktkf")) {
				sql = "select * from Ktkf where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Lddt")) {
				sql = "select * from Lddt where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Tpxw")) {
				sql = "select * from Tpxw where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Kjjz")) {
				sql = "select * from Kjjz where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Tjsj")) {
				sql = "select * from Tjsj where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Yjxx")) {
				sql = "select * from Yjxx where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Yzrd")) {
				sql = "select * from Yzrd where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			} else if (tableName.equals("Zcfg")) {
				sql = "select * from Zcfg where normal in(" + "'" + normal1
						+ "'," + "'" + normal2 + "')" + " and zone in(" + "'"
						+ zone1 + "'," + "'" + zone2 + "'," + "'" + zone3
						+ "')";
			}
		}

		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				datas.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}

	public ArrayList<News> getMoreNews(String[] newsId, int moduleId) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		String sql = null;
		News news = null;
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 0; i < newsId.length; i++) {
				news = new News();
				switch (moduleId) {
				case 1:
					sql = "select * from Gzdt where id=" + newsId[i];
					break;
				case 2:
					sql = "select * from Kcgl where id=" + newsId[i];
					break;
				case 3:
					sql = "select * from Zcfg where id=" + newsId[i];
					break;
				case 4:
					sql = "select * from Yzrd where id=" + newsId[i];
					break;
				case 5:
					sql = "select * from Gjhz where id=" + newsId[i];
					break;
				case 6:
					sql = "select * from Kjjz where id=" + newsId[i];
					break;
				case 7:
					sql = "select * from Tpxw where id=" + newsId[i];
					break;
				case 8:
					sql = "select * from Lddt where id=" + newsId[i];
					break;
				case 9:
					sql = "select * from Tjsj where id=" + newsId[i];
					break;
				case 10:
					sql = "select * from Ktkf where id=" + newsId[i];
					break;
				case 11:
					sql = "select * from Yjxx where id=" + newsId[i];
					break;
				default:
					sql = null;
				}
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				if (rs.next()) {
					news.setTitle(rs.getString("title"));
					news.setSiteSource(rs.getString("SiteSource"));
					news.setOriginSource(rs.getString("originSource"));
					news.setPubTime(rs.getString("pubTime"));
					news.setContent(rs.getString("content"));
					datas.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}
	@Override
	public ArrayList<News> pageListOfDtck(PageRoll pageRoll) {
		ArrayList<News> pagelist = new ArrayList<News>();
		News news = null;
		String sql = "select updown,id,module,title,pubTime,originSource,siteSource,visible,columnName,columnOrder from Dtck";
		Connection conn = null;
		Statement st;
		ResultSet rs;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				news = new News();
				news.setUpdownId(rs.getInt("updown"));
				news.setId(rs.getInt("id"));
				news.setModule(rs.getInt("module"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				news.setColumnName(rs.getString("columnName"));
				news.setColumnOrder(rs.getInt("columnOrder"));
				pagelist.add(news);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(conn);
		}
		
		List<News> newsList = null;
		pageRoll.setTotalCount(pagelist.size());
		//先按栏目排序，栏目相同的时候，再按updownId属性排序，排序方式都为升序
		SortListUtil.sort(pagelist, new String[] { "columnOrder", "updownId" }, new String[] {SortListUtil.ASC,SortListUtil.ASC});
		int curentPageNum = pageRoll.getCurrPage();
		if(pageRoll.getPageSize()>pagelist.size()){
			newsList = pagelist;
		}else if(curentPageNum*pageRoll.getPageSize()<=pagelist.size()){
			newsList =  pagelist.subList((curentPageNum-1)*pageRoll.getPageSize(), curentPageNum*pageRoll.getPageSize());
		}else{
			newsList =  pagelist.subList((curentPageNum-1)*pageRoll.getPageSize(), pagelist.size());
		}
		return new ArrayList<News>(newsList);
	}
	public ArrayList<News> pageList(PageRoll pageRoll, int Module) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<News> pagelist = new ArrayList<News>();
		News news = null;
		String sql1 = null;
		String sql2 = null;
		switch (Module) {
		case 1:
			sql1 = "select count(title) from Gzdt where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Gzdt where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 2:
			sql1 = "select count(title) from Kcgl where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Kcgl where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 3:
			sql1 = "select count(title) from Zcfg where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Zcfg where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 4:
			sql1 = "select count(title) from Yzrd where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yzrd where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 5:
			sql1 = "select count(title) from Gjhz where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Gjhz where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 6:
			sql1 = "select count(id) from Kjjz where Output!=-1";
			sql2 = "select temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Kjjz where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 7:
			sql1 = "select count(title) from Tpxw where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Tpxw where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 8:
			sql1 = "select count(title) from Lddt where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Lddt where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 9:
			sql1 = "select count(title) from Tjsj where Output!=-1";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Tjsj where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 10:
			sql1 = "select count(title) from Ktkf where Output!=-1";
			sql2 = "select temp.module,temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Ktkf where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 11:
			sql1 = "select count(title) from Yjxx where Output!=-1";
			sql2 = "select temp.module, temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yjxx where Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 12:
			sql1 = "select count(title) from Dtrd";
			sql2 = "select  temp.module,temp.rownumber,temp.id,temp.updown,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.siteSource from "
					+ "(select ROW_NUMBER() over(order by updown ASC) rownumber,* from Dtrd) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		default:
			sql1 = null;
			sql2 = null;
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next()) {
				Integer count = rs.getInt(1);
				pageRoll.setTotalCount(count);
			}
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize());
			ps.setInt(2, pageRoll.getCurrPage() * pageRoll.getPageSize());
			rs = ps.executeQuery();
			while (rs.next()) {
				news = new News();
				if (Module == 12) {//一周热点参考
					news.setUpdownId(rs.getInt("updown"));
				}
				news.setId(rs.getInt("id"));
				news.setModule(rs.getInt("module"));
				news.setRownumber(rs.getInt("rownumber"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				pagelist.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return pagelist;
	}

	public void AddtoHotword(ArrayList<HashMap<String, Integer>> datas) {
		Connection conn = null;
		Statement ps = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Boolean lean = false;
		String sql = "select name from Hotword";
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			for (HashMap<String, Integer> data : datas) {
				for (java.util.Map.Entry<String, Integer> entry : data
						.entrySet()) {
					rs = ps.executeQuery(sql);
					lean = false;
					while (rs.next()) {
						if (rs.getString("name").equals(entry.getKey())) {
							String usql = "update Hotword set count=count+"
									+ entry.getValue() + ",psmcount=psmcount+"
									+ entry.getValue() + ",pswcount=pswcount+"
									+ entry.getValue() + "where name =" + "'"
									+ entry.getKey() + "'";
							conn = JdbcUtils.getConnection();
							ps = conn.createStatement();
							ps.executeUpdate(usql);
							lean = true;
						}
					}
					if (!lean) {
						String isql = "insert into Hotword(name) values(?)";
						conn = JdbcUtils.getConnection();
						st = conn.prepareStatement(isql);
						st.setString(1, entry.getKey());
						st.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<HashMap<String, Integer>> HotwordTop10(String sql) {
		HashMap<String, Integer> data = null;
		ArrayList<HashMap<String, Integer>> datas = new ArrayList<HashMap<String, Integer>>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				data = new HashMap<String, Integer>();
				data.put(rs.getString("name"), rs.getInt("count"));
				datas.add(data);
			}
			return datas;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<News> getIndexNews(String indexname) {
		News news = null;
		ArrayList<News> datas = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		if (indexname.equals("zcfg")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Zcfg where visible=0) temp where temp.rownumber<=20";
		} else if (indexname.equals("yzrd")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Yzrd where visible=0) temp where temp.rownumber<=15";
		} else if (indexname.equals("ktkf")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Kcgl where visible=0) temp where temp.rownumber<=20";
		} else if (indexname.equals("gzdt")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Gzdt where visible=0) temp where temp.rownumber<=10";
		} else if (indexname.equals("lddt")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Lddt where visible=0) temp where temp.rownumber<=20";
		} else if (indexname.equals("tjsj")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Tjsj where visible=0) temp where temp.rownumber<=20";
		} else if (indexname.equals("tpxw")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Tpxw where visible=0) temp where temp.rownumber<=15";
		} else if (indexname.equals("yjxx")) {
			sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Yjxx where visible=0) temp where temp.rownumber<=10";
		}
		//zm
			else if (indexname.equals("gzdtrd")){
				sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Dtrddb where visible=0 and Modulesource = 1) temp where temp.rownumber<=50";
			} else if (indexname.equals("zcfgrd")){
				sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Dtrddb where visible=0 and Modulesource = 3) temp where temp.rownumber<=50";
			} else if (indexname.equals("kcglrd")){
				sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Dtrddb where visible=0 and Modulesource = 2) temp where temp.rownumber<=50";
			} else if (indexname.equals("ktkfrd")){
				sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Dtrddb where visible=0 and Modulesource = 10) temp where temp.rownumber<=50";
			} else if (indexname.equals("kjjzrd")){
				sql = "select * from (select ROW_NUMBER() over(order by pubTime DESC) rownumber,* from Dtrddb where visible=0 and Modulesource = 6) temp where temp.rownumber<=50";
			} 
		//zm
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setOriginSource(rs.getString("originSource"));
				news.setPubTime(rs.getString("pubTime"));
				news.setContent(rs.getString("content"));
				datas.add(news);
			}
			return datas;
		} catch (Exception e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	private News Mappingnews(ResultSet rs) throws SQLException {
		News news;
		news = new News();
		news.setId(rs.getInt("id"));
		// news.setName(rs.getString("name"));
		// news.setCname(rs.getString("cname"));
		// news.setGrade(rs.getInt("grade"));
		return news;
	}

	public void delete(int id, int Module) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		try {
			switch (Module) {
			case 1:
				sql = "delete from Gzdt where id=" + id;
				break;
			case 2:
				sql = "delete from Kcgl where id=" + id;
				break;
			case 3:
				sql = "delete from Zcfg where id=" + id;
				break;
			case 4:
				sql = "delete from Yzrd where id=" + id;
				break;
			case 5:
				sql = "delete from Gjhz where id=" + id;
				break;
			case 6:
				sql = "delete from Kjjz where id=" + id;
				break;
			case 7:
				sql = "delete from Tpxw where id=" + id;
				break;
			case 8:
				sql = "delete from Lddt where id=" + id;
				break;
			case 9:
				sql = "delete from Tjsj where id=" + id;
				break;
			case 10:
				sql = "delete from Ktkf where id=" + id;
				break;
			case 11:
				sql = "delete from Yjxx where id=" + id;
				break;
			case 12:
				sql = "delete from Dtrd where id=" + id;
				break;
			case 13:
				sql = "delete from Dtck where id=" + id;
				break;
			default:
				sql = null;
				break;
			}
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}

	}

	public void deleteStr(int[] id, int Module) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			if (Module == 1) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Gzdt where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 2) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Kcgl where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 3) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Zcfg where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 4) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Yzrd where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 5) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Gjhz where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 6) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Kjjz where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 7) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Tpxw where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 8) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Lddt where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 9) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Tjsj where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 10) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Ktkf where id=" + id[i];
					st.executeUpdate(sql);
				}
			} else if (Module == 11) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Yjxx where id=" + id[i];
					st.executeUpdate(sql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public void addNews(News news, int moduleId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String tableName = null;
		try {
			switch (moduleId) {
			case 1:
				tableName = "Gzdt";
				break;
			case 2:
				tableName = "Kcgl";
				break;
			case 3:
				tableName = "Zcfg";
				break;
			case 4:
				tableName = "Yzrd";
				break;
			case 5:
				tableName = "Gjhz";
				break;
			case 6:
				tableName = "kjjz";
				break;
			case 7:
				tableName = "Tpxw";
				break;
			case 8:
				tableName = "Lddt";
				break;
			case 9:
				tableName = "Tjsj";
				break;
			case 10:
				tableName = "Ktkf";
				break;
			case 11:
				tableName = "Yjxx";
				break;
			}
			sql = "insert into "
					+ tableName
					+ "(title,siteSource,originSource,pubTime,content,oilField,webWeight,charset,basin,company,oil,Output) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, news.getTitle());
			ps.setString(2, news.getSiteSource());
			ps.setString(3, news.getOriginSource());
			ps.setString(4, news.getPubTime());
			ps.setString(5, news.getContent());
			ps.setString(6, news.getOilField());
			ps.setInt(7, news.getWebWeight());
			ps.setString(8, news.getCharset());
			ps.setString(9, news.getBasin());
			ps.setString(10, news.getCompany());
			ps.setString(11, news.getOil());
			ps.setInt(12, news.getOutput());
			ps.executeUpdate();
		} catch (Exception e) {
			return;
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public void deleteMore(String[] id, int Module) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			if (Module == 1) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Gzdt where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 2) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Kcgl where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 3) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Zcfg where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 4) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Yzrd where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 5) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Gjhz where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 6) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Kjjz where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 7) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Tpxw where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 8) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Lddt where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 9) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Tjsj where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 10) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Ktkf where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 11) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Yjxx where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 12) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Dtrd where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			} else if (Module == 13) {
				for (int i = 0; i < id.length; i++) {
					sql = "delete from Dtck where id=" + Integer.valueOf(id[i]);
					st.executeUpdate(sql);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public News findById(int id, int Module) {
		String sql = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		News news = null;
		switch (Module) {
		case 1:
			sql = "select * from Gzdt where id=" + id;
			break;
		case 2:
			sql = "select * from Kcgl where id=" + id;
			break;
		case 3:
			sql = "select * from Zcfg where id=" + id;
			break;
		case 4:
			sql = "select * from Yzrd where id=" + id;
			break;
		case 5:
			sql = "select * from Gjhz where id=" + id;
			break;
		case 6:
			sql = "select * from Kjjz where id=" + id;
			break;
		case 7:
			sql = "select * from Tpxw where id=" + id;
			break;
		case 8:
			sql = "select * from Lddt where id=" + id;
			break;
		case 9:
			sql = "select * from Tjsj where id=" + id;
			break;
		case 10:
			sql = "select * from Ktkf where id=" + id;
			break;
		case 11:
			sql = "select * from Yjxx where id=" + id;
			break;
		case 12:
			sql = "select * from Dtrd where id=" + id;
			break;
		case 13:
			sql = "select * from Dtck where id=" + id;
			break;
			//zm
		case 100:
			sql = "select * from Dtrddb where id=" + id; 
			break;
			//zm
		default:
			sql = null;
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			if (rs.next()) {
				news = new News();
				if (Module == 7) {
					news.setImagePath(rs.getString("imagePath"));
				}
				news.setModule(rs.getInt("module"));
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setOriginSource(rs.getString("originSource"));
				news.setPubTime(rs.getString("pubTime"));
				news.setContent(rs.getString("content"));
				news.setOilField(rs.getString("oilField"));
				news.setTotalWeight(rs.getInt("totalWeight"));
				news.setTotalVisits(rs.getInt("totalVisits"));
				news.setWebWeight(rs.getInt("webWeight"));
				news.setVisible(rs.getInt("Visible"));
				news.setCharset(rs.getString("charset"));
				news.setType(rs.getInt("type"));
				news.setBasin(rs.getString("basin"));
				news.setCompany(rs.getString("company"));
				news.setOil(rs.getString("oil"));
				news.setOutput(rs.getInt("Output"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return news;
	}

	public void Update(News news) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String sqlupdate = null;
		String tableName = null;
		try {
			if (news.getModule() == news.getToModule()) {
				switch (news.getModule()) {
				case 1:
					tableName = "Gzdt";
					break;
				case 2:
					tableName = "Kcgl";
					break;
				case 3:
					tableName = "Zcfg";
					break;
				case 4:
					tableName = "Yzrd";
					break;
				case 5:
					tableName = "Gjhz";
					break;
				case 6:
					tableName = "kjjz";
					break;
				case 7:
					tableName = "Tpxw";
					break;
				case 8:
					tableName = "Lddt";
					break;
				case 9:
					tableName = "Tjsj";
					break;
				case 10:
					tableName = "Ktkf";
					break;
				case 11:
					tableName = "Yjxx";
					break;
				case 12:
					tableName = "Dtrd";
					break;
				case 13:
					tableName = "Dtck";
					break;
				default:
					tableName = null;
					break;
				}
				sql = "update "
						+ tableName
						+ " set title=?,pubTime=?,originSource=?,content=?,oilField=?,totalWeight=?,type=? where id =?";
				conn = JdbcUtils.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getPubTime());
				ps.setString(3, news.getOriginSource());
				ps.setString(4, news.getContent());
				ps.setString(5, news.getOilField());
				ps.setInt(6, news.getTotalWeight());
				ps.setInt(7, news.getType());
				ps.setInt(8, news.getId());
				ps.executeUpdate();
			} else {
				switch (news.getModule()) {
				case 1:
					sql = "delete from Gzdt where id=" + news.getId();
					break;
				case 2:
					sql = "delete from Kcgl where id=" + news.getId();
					break;
				case 3:
					sql = "delete from Zcfg where id=" + news.getId();
					break;
				case 4:
					sql = "delete from Yzrd where id=" + news.getId();
					break;
				case 5:
					sql = "delete from Gjhz where id=" + news.getId();
					break;
				case 6:
					sql = "delete from Kjjz where id=" + news.getId();
					break;
				case 8:
					sql = "delete from Lddt where id=" + news.getId();
					break;
				case 9:
					sql = "delete from Tjsj where id=" + news.getId();
					break;
				case 10:
					sql = "delete from Ktkf where id=" + news.getId();
					break;
				case 11:
					sql = "delete from Yjxx where id=" + news.getId();
					break;
				case 12:
					sql = "delete from Dtrd where id=" + news.getId();
					break;
				case 13:
					sql = "delete from Dtck where id=" + news.getId();
					break;
				default:
					sql = null;
					break;
				}
				switch (news.getToModule()) {
				case 1:
					tableName = "Gzdt";
					break;
				case 2:
					tableName = "Kcgl";
					break;
				case 3:
					tableName = "Zcfg";
					break;
				case 4:
					tableName = "Yzrd";
					break;
				case 5:
					tableName = "Gjhz";
					break;
				case 6:
					tableName = "kjjz";
					break;
				case 7:
					tableName = "Tpxw";
					break;
				case 8:
					tableName = "Lddt";
					break;
				case 9:
					tableName = "Tjsj";
					break;
				case 10:
					tableName = "Ktkf";
					break;
				case 11:
					tableName = "Yjxx";
					break;
				case 12:
					tableName = "Dtrd";
					break;
				case 13:
					tableName = "Dtck";
					break;
				default:
					tableName = null;
					break;
				}
				sqlupdate = "insert into "
						+ tableName
						+ "(title,siteSource,originSource,pubTime,content,totalVisits,oilField,totalWeight,webWeight,visible,charset,type,basin,company,oil,Output) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				conn = JdbcUtils.getConnection();
				ps = conn.prepareStatement(sqlupdate);
				ps.setString(1, news.getTitle());
				ps.setString(2, news.getSiteSource());
				ps.setString(3, news.getOriginSource());
				ps.setString(4, news.getPubTime());
				ps.setString(5, news.getContent());
				ps.setInt(6, news.getTotalVisits());
				ps.setString(7, news.getOilField());
				ps.setInt(8, news.getTotalWeight());
				ps.setInt(9, news.getWebWeight());
				ps.setInt(10, news.getVisible());
				ps.setString(11, news.getCharset());
				ps.setInt(12, news.getType());
				ps.setString(13, news.getBasin());
				ps.setString(14, news.getCompany());
				ps.setString(15, news.getOil());
				ps.setInt(16, news.getOutput());
				ps.executeUpdate();
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}

	}

	public News findByTitle(String title, int Module) {
		String sql = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tableName = null;
		News news = null;
		switch (Module) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		case 12:
			tableName = "Dtrd";
			break;
		case 13:
			tableName = "Dtck";
			break;
		default:
			sql = null;
			break;
		}
		sql = "select * from " + tableName + " where title=?";
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			rs = ps.executeQuery();
			if (rs.next()) {
				news = new News();
				if (Module == 7) {
					news.setImagePath(rs.getString("imagePath"));
				}
				news.setModule(rs.getInt("module"));
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setOriginSource(rs.getString("originSource"));
				news.setPubTime(rs.getString("pubTime"));
				news.setContent(rs.getString("content"));
				news.setOilField(rs.getString("oilField"));
				news.setTotalWeight(rs.getInt("totalWeight"));
				news.setWebWeight(rs.getInt("webWeight"));
				news.setVisible(rs.getInt("Visible"));
				news.setCharset(rs.getString("charset"));
				news.setType(rs.getInt("type"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return news;
	}

	public void isVisble(int newsId, int moduleId, int visible) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		String sql = null;
		String tableName = null;
		switch (moduleId) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "Kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		}
		if (visible == 0) {
			sql = "update " + tableName + " set Visible=1 where id=" + newsId;
		} else {
			sql = "update " + tableName + " set Visible=0 where id=" + newsId;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public void countVisit(int newsId, int moduleId) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		String sql = null;
		switch (moduleId) {
		case 1:
			sql = "update Gzdt set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 2:
			sql = "update Kcgl set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 3:
			sql = "update Zcfg set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 4:
			sql = "update Yzrd set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 5:
			sql = "update Gjhz set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 6:
			sql = "update Kjjz set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 7:
			sql = "update Tpxw set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 8:
			sql = "update Lddt set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 9:
			sql = "update Tjsj set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 10:
			sql = "update Ktkf set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		case 11:
			sql = "update Yjxx set totalVisits=totalVisits+1 where id="
					+ newsId;
			break;
		
		default:
			sql = null;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			ps.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}
	
	public void addTobookdb(String[] newsId, int Module, int toModule) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<News> bookList = new ArrayList<News>();
		News news = null;
		String sql = null;
		String Moudle =Module+"";
		String updown = null;
		switch (toModule) {
		case 12:
			sql = "insert into Dtrddb(title,Modulesource,siteSource,originSource,pubTime,content,oilField,totalWeight,webWeight,visible,charset,type,basin,company,oil,Output) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			updown = "update Dtrddb set updown=id where updown is NULL";
			break;
		case 13:
			sql = "insert into Dtckdb(title,Modulesource,siteSource,originSource,pubTime,content,oilField,totalWeight,webWeight,visible,charset,type,basin,company,oil,Output) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			updown = "update Dtckdb set updown=id where updown is NULL";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 0; i < newsId.length; i++) {
				news = new News();
				news = findById(Integer.valueOf(newsId[i]), Module);
				bookList.add(news);
			}
			for (int i = 0; i < bookList.size(); i++) {
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, bookList.get(i).getTitle());
					ps.setString(2,Moudle);
					ps.setString(3, bookList.get(i).getSiteSource());
					ps.setString(4, bookList.get(i).getOriginSource());
					ps.setString(5, bookList.get(i).getPubTime());
					ps.setString(6, bookList.get(i).getContent());
					ps.setString(7, bookList.get(i).getOilField());
					ps.setInt(8, bookList.get(i).getTotalWeight());
					ps.setInt(9, bookList.get(i).getWebWeight());
					ps.setInt(10, bookList.get(i).getVisible());
					ps.setString(11, bookList.get(i).getCharset());
					ps.setInt(12, bookList.get(i).getType());
					ps.setString(13, news.getBasin());
					ps.setString(14, news.getCompany());
					ps.setString(15, news.getOil());
					ps.setInt(16, news.getOutput());
					ps.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			ps = conn.prepareStatement(updown);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}
	@Override
	public int getColumnOrder(String columnName) {
		int columnOrder = 0;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select columnOrder from Columns where name = '"+columnName+"'");
			while (rs.next()) {
				columnOrder = (int) rs.getObject("columnOrder");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return columnOrder;
	}
	@Override
	public String[] getColumnsName() {
		ArrayList<String> cols = new ArrayList<String>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select name from Columns order by columnOrder ");
			while (rs.next()) {
				cols.add((String) rs.getObject("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		String columns[] = new String[cols.size()];
		return cols.toArray(columns);
	}
	@Override
	public boolean modifyColumnName(int newsID,String toColumnName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int result = 0;
		int toColumnOrder = 0;//被修改后的分栏排序
		try {
			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("select columnOrder from Columns where name = '"+toColumnName+"'");
			while (rs.next()) {
				toColumnOrder = rs.getInt("columnOrder");
			}
			result = st.executeUpdate("update dtck set columnOrder=" + toColumnOrder + ",columnName= '"+toColumnName+"' where id = "+newsID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		if(result==0){
			return false;
		}else{
			return true;
		}
	}
	public void addTobook(String[] newsId, int Module, int toModule,String columnName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ArrayList<News> bookList = new ArrayList<News>();
		News news = null;
		String sql = null;
		String updown = null;
		int columnOrder=0;
		switch (toModule) {
		case 12:
			sql = "insert into Dtrd(title,siteSource,originSource,pubTime,content,oilField,totalWeight,webWeight,visible,charset,type,basin,company,oil,Output) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			updown = "update Dtrd set updown=id where updown is NULL";
			break;
		case 13:
			columnOrder = getColumnOrder(columnName);
			sql = "insert into Dtck(title,siteSource,originSource,pubTime,content,oilField,totalWeight,webWeight,visible,charset,type,basin,company,oil,Output,columnName,columnOrder) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			updown = "update Dtck set updown=id where updown is NULL";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 0; i < newsId.length; i++) {
				news = new News();
				news = findById(Integer.valueOf(newsId[i]), Module);
				bookList.add(news);
			}
			for (int i = 0; i < bookList.size(); i++) {
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, bookList.get(i).getTitle());
					ps.setString(2, bookList.get(i).getSiteSource());
					ps.setString(3, bookList.get(i).getOriginSource());
					ps.setString(4, bookList.get(i).getPubTime());
					ps.setString(5, bookList.get(i).getContent());
					ps.setString(6, bookList.get(i).getOilField());
					ps.setInt(7, bookList.get(i).getTotalWeight());
					ps.setInt(8, bookList.get(i).getWebWeight());
					ps.setInt(9, bookList.get(i).getVisible());
					ps.setString(10, bookList.get(i).getCharset());
					ps.setInt(11, bookList.get(i).getType());
					ps.setString(12, news.getBasin());
					ps.setString(13, news.getCompany());
					ps.setString(14, news.getOil());
					ps.setInt(15, news.getOutput());
					if(toModule==13){//动态参考模块需要添加分栏名，以及分栏名在所有分栏中的排序位置
						ps.setString(16, columnName);
						ps.setInt(17, columnOrder);
					}
					ps.executeUpdate();
				} catch (Exception e) {
					continue;
				}
			}
			ps = conn.prepareStatement(updown);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<News> downloadAll(int Module) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		ArrayList<News> createBook = new ArrayList<News>();
		News news = null;
		String sql = null;
		switch (Module) {
		case 12:
			sql = "select * from Dtrd";
			break;
		case 13:
			sql = "select * from Dtck";
			break;
		default:
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				news = new News();
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setContent(rs.getString("content"));
				if(Module==13){//动态参考模块需要添加分栏名，以及分栏名在所有分栏中的排序位置
					news.setColumnName(rs.getString("columnName"));
					news.setColumnOrder(rs.getInt("columnOrder"));
				}
				createBook.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		if(Module==13){//先按栏目排序，栏目相同的时候，再按updownId属性排序，排序方式都为升序
			SortListUtil.sort(createBook, new String[] { "columnOrder", "updownId" }, new String[] {SortListUtil.ASC,SortListUtil.ASC});
		}
		return createBook;
	}

	public ArrayList<News> visiblePage(PageRoll pageRoll, int Module) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<News> pagelist = new ArrayList<News>();
		News news = null;
		String sql1 = null;
		String sql2 = null;
		switch (Module) {
		case 1:
			sql1 = "select count(title) from Gzdt where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Gzdt where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 2:
			sql1 = "select count(title) from Kcgl where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Kcgl where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 3:
			sql1 = "select count(title) from Zcfg where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Zcfg where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 4:
			sql1 = "select count(title) from Yzrd where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yzrd where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 5:
			sql1 = "select count(title) from Gjhz where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Gjhz where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 6:
			sql1 = "select count(title) from Kjjz where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Kjjz where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 7:
			sql1 = "select count(title) from Tpxw where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Tpxw where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 8:
			sql1 = "select count(title) from Lddt where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Lddt where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 9:
			sql1 = "select count(title) from Tjsj where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Tjsj where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 10:
			sql1 = "select count(title) from Ktkf where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Ktkf where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 11:
			sql1 = "select count(title) from Yjxx where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yjxx where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		case 15:
			sql1 = "select count(title) from Yjxx where visible=0 and Output!=-1";
			sql2 = "select temp.rownumber,temp.id,temp.title,temp.pubTime,temp.originSource,temp.visible,temp.totalVisits from "
					+ "(select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber,* from Yjxx where visible=0 and Output!=-1) temp "
					+ "where temp.rownumber>? and temp.rownumber<=?";
			break;
		default:
			sql1 = null;
			sql2 = null;
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			if (rs.next()) {
				Integer count = rs.getInt(1);
				pageRoll.setTotalCount(count);
			}
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize());
			ps.setInt(2, pageRoll.getCurrPage() * pageRoll.getPageSize());
			rs = ps.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setRownumber(rs.getInt("rownumber"));
				news.setId(rs.getInt("id"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setTotalVisits(rs.getInt("totalVisits"));
				news.setVisible(rs.getInt("visible"));
				pagelist.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return pagelist;
	}

	public void firstExtractWords(int moduleId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String allTitie = "";
		String tableName = null;
		String beginTime = null;
		String endTime = null;
		switch (moduleId) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "Kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 1; i < 2; i++) {
				allTitie = "";
				switch (i) {
				case 1:
					beginTime = "2015-02-01";
					endTime = "2015-03-01";
					break;

				}
				ps = conn.prepareStatement("select title from " + tableName
						+ " where Pubtime>=" + "'" + beginTime
						+ "' and Pubtime<" + "'" + endTime + "'");
				rs = ps.executeQuery();
				while (rs.next()) {
					allTitie = allTitie + rs.getString("title") + "\n";
				}
				System.out.println(allTitie);
				// allTitie =
				// allTitie.replaceAll("[\\pZ|\n\\pN|[a-z][A-Z]]","");
				// GetWords getWords = new GetWords();
				// ArrayList<HashMap<String, Object>> titleWords =
				// getWords.excute(0,allTitie);
				// String
				// sql="insert into hotWord(word,count,begintime,endtime,moduleId) values(?,?,?,?,?)";
				// ps=conn.prepareStatement(sql);
				// for(int j=0;j<titleWords.size();j++){
				// ps.setString(1,titleWords.get(j).get("Str").toString());
				// ps.setInt(2,
				// Integer.valueOf(titleWords.get(j).get("Count").toString()));
				// ps.setString(3,beginTime);
				// ps.setString(4,endTime);
				// ps.setInt(5, moduleId);
				// ps.executeUpdate();
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	// 画图的函数，改写数字可以调现实的词的个数
	public ArrayList<HashMap<String, Object>> getWordandCount(int moduleId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<HashMap<String, Object>> wordList = new ArrayList<HashMap<String, Object>>();
		switch (moduleId) {
		case 0:
			sql = "select top 15 * from (select word,sum(count) as count from (select word,count from hotWord)hotwordchart group by hotwordchart.word)hu order by hu.count DESC";
			break;
		default:
			sql = "select top 15 * from (select word,sum(count) as count from (select word,count from hotWord where moduleId="
					+ moduleId
					+ ")hotwordchart group by hotwordchart.word)hu order by hu.count DESC";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> word = new HashMap<String, Object>();
				word.put("word", rs.getString("word"));
				word.put("count", rs.getInt("count"));
				wordList.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return wordList;
	}

	public ArrayList<HashMap<String, Object>> getZlfxChart(
			ArrayList<HashMap<String, Object>> wordlist) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		ArrayList<HashMap<String, Object>> nowwordList = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> word = null;
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 0; i < wordlist.size(); i++) {
				int i1 = 0;
				int i2 = 0;
				sql = "select count from hotWord where word=? and DATEDIFF(MONTH,begintime,GETDATE())=1";
				sql2 = "select count from hotWord where word=? and DATEDIFF(MONTH,begintime,GETDATE())=2";
				ps = conn.prepareStatement(sql);
				ps.setString(1, wordlist.get(i).get("word").toString());
				rs = ps.executeQuery();
				if (rs.next()) {
					i1 = rs.getInt("count");
				}
				ps = conn.prepareStatement(sql2);
				ps.setString(1, wordlist.get(i).get("word").toString());
				rs = ps.executeQuery();
				if (rs.next()) {
					i2 = rs.getInt("count");
				}
				if (i1 - i2 == 0) {

				} else {
					word = new HashMap<String, Object>();
					word.put("word", wordlist.get(i).get("word").toString());
					word.put("count", i1 - i2);
					nowwordList.add(word);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return nowwordList;
	}

	public ArrayList<HashMap<String, Integer>> getHotword(int moduleId) {
		HashMap<String, Integer> data = null;
		ArrayList<HashMap<String, Integer>> datas = new ArrayList<HashMap<String, Integer>>();
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		String sql = null;
		switch (moduleId) {
		case 1:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=1)temp group by word)hotword order by hotword.count DESC";
			break;
		case 2:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=2)temp group by word)hotword order by hotword.count DESC";
			break;
		case 3:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=3)temp group by word)hotword order by hotword.count DESC";
			break;
		case 4:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=4)temp group by word)hotword order by hotword.count DESC";
			break;
		case 5:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=5)temp group by word)hotword order by hotword.count DESC";
			break;
		case 6:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=6)temp group by word)hotword order by hotword.count DESC";
			break;
		case 7:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=7)temp group by word)hotword order by hotword.count DESC";
			break;
		case 8:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=8)temp group by word)hotword order by hotword.count DESC";
			break;
		case 9:
			sql = "select top 7 * from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=9)temp group by word)hotword order by hotword.count DESC";
			break;
		case 10:
			sql = "select top 7* from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=10)temp group by word)hotword order by hotword.count DESC";
			break;
		case 11:
			sql = "select top 7* from (select temp.word,SUM(temp.count) as count from (select word,count,moduleId from hotWord where DATEDIFF(MONTH,begintime,GETDATE())<=2 and moduleId=11)temp group by word)hotword order by hotword.count DESC";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				data = new HashMap<String, Integer>();
				data.put(rs.getString("word"), rs.getInt("count"));
				datas.add(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}

	public void updateHotword() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tableName = null;
		String begintime = null;
		String endtime = null;
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 1; i <= 11; i++) {
				String allTitie = "";
				switch (i) {
				case 1:
					tableName = "Gzdt";
					break;
				case 2:
					tableName = "Kcgl";
					break;
				case 3:
					tableName = "Zcfg";
					break;
				case 4:
					tableName = "Yzrd";
					break;
				case 5:
					tableName = "Gjhz";
					break;
				case 6:
					tableName = "Kjjz";
					break;
				case 7:
					tableName = "Tpxw";
					break;
				case 8:
					tableName = "Lddt";
					break;
				case 9:
					tableName = "Tjsj";
					break;
				case 10:
					tableName = "Ktkf";
					break;
				case 11:
					tableName = "Yjxx";
					break;
				}
				String queryDate = "select MAX(begintime) as begintime,MAX(endtime) as endtime from Hotword where moduleId="
						+ i;
				ps = conn.prepareStatement(queryDate);
				rs = ps.executeQuery();
				if (rs.next()) {
					begintime = rs.getString("begintime");
					endtime = rs.getString("endtime");
				}
				if (begintime != null && endtime != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					if (date.getTime() >= sdf.parse(begintime).getTime()
							&& date.getTime() < sdf.parse(endtime).getTime()) {
						String sql = "delete from hotWord where moduleId=" + i
								+ " and DATEDIFF(MONTH,begintime,GETDATE())=0";
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
						ps = conn.prepareStatement("select title from "
								+ tableName + " where Pubtime>=" + "'"
								+ begintime + "' and Pubtime<" + "'" + endtime
								+ "'");
						rs = ps.executeQuery();
						while (rs.next()) {
							allTitie = allTitie + rs.getString("title") + "\n";
						}
						allTitie = allTitie.replaceAll(
								"[\\pZ|\n\\pN|[a-z][A-Z]]", "");
						GetWords getWords = new GetWords();
						ArrayList<HashMap<String, Object>> titleWords = getWords
								.excute(0, allTitie);
						String insertsql = "insert into hotWord(word,count,begintime,endtime,moduleId) values(?,?,?,?,?)";
						ps = conn.prepareStatement(insertsql);
						for (int j = 0; j < titleWords.size(); j++) {
							ps.setString(1, titleWords.get(j).get("Str")
									.toString());
							ps.setInt(
									2,
									Integer.valueOf(titleWords.get(j)
											.get("Count").toString()));
							ps.setString(3, begintime);
							ps.setString(4, endtime);
							ps.setInt(5, i);
							ps.executeUpdate();
						}
					} else if (date.getTime() >= sdf.parse(endtime).getTime()) {
						Calendar c1 = Calendar.getInstance();
						Calendar c2 = Calendar.getInstance();
						c1.setTime(date);
						c2.setTime(sdf.parse(endtime));
						int differmonth = c1.get(Calendar.MONTH)
								- c2.get(Calendar.MONTH);
						while (differmonth >= 0) {
							differmonth--;
							Calendar c4 = Calendar.getInstance();
							Calendar c3 = Calendar.getInstance();
							c4.setTime(sdf.parse(begintime));
							c4.add(Calendar.MONTH, 1);
							sdf.format(c4.getTime());
							begintime = sdf.format(c4.getTime());

							c3.setTime(sdf.parse(endtime));
							c3.add(Calendar.MONTH, 1);
							sdf.format(c3.getTime());
							endtime = sdf.format(c3.getTime());
							ps = conn.prepareStatement("select title from "
									+ tableName + " where Pubtime>=" + "'"
									+ begintime + "' and Pubtime<" + "'"
									+ endtime + "'");
							rs = ps.executeQuery();
							while (rs.next()) {
								allTitie = allTitie + rs.getString("title")
										+ "\n";
							}
							allTitie = allTitie.replaceAll(
									"[\\pZ|\n\\pN|[a-z][A-Z]]", "");
							GetWords getWords = new GetWords();
							ArrayList<HashMap<String, Object>> titleWords = getWords
									.excute(0, allTitie);
							String insertsql = "insert into hotWord(word,count,begintime,endtime,moduleId) values(?,?,?,?,?)";
							ps = conn.prepareStatement(insertsql);
							for (int j = 0; j < titleWords.size(); j++) {
								ps.setString(1, titleWords.get(j).get("Str")
										.toString());
								ps.setInt(
										2,
										Integer.valueOf(titleWords.get(j)
												.get("Count").toString()));
								ps.setString(3, begintime);
								ps.setString(4, endtime);
								ps.setInt(5, i);
								ps.executeUpdate();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<HashMap<String, Object>> getWordandCount(int moduleId,
			String begintime, String endtime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		ArrayList<HashMap<String, Object>> wordList = new ArrayList<HashMap<String, Object>>();
		switch (moduleId) {
		case 0:
			sql = "select top 15 * from (select word,sum(count) as count from (select word,count from hotWord where DATEDIFF(MONTH,begintime,?)<=0 and DATEDIFF(MONTH,endtime,?)>=0)hotwordchart group by hotwordchart.word)hu order by hu.count DESC";
			break;
		default:
			sql = "select top 15 * from (select word,sum(count) as count from (select word,count from hotWord where moduleId="
					+ moduleId
					+ " and begintime>? and endtime<?)hotwordchart group by hotwordchart.word)hu order by hu.count DESC";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, begintime);
			ps.setString(2, endtime);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> word = new HashMap<String, Object>();
				word.put("word", rs.getString("word"));
				word.put("count", rs.getInt("count"));
				wordList.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return wordList;
	}

	public ArrayList<HashMap<String, Object>> getZlfxChart(String endtime,
			ArrayList<HashMap<String, Object>> wordlist) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String sql2 = null;
		HashMap<String, Object> word = null;
		ArrayList<HashMap<String, Object>> timewordList = new ArrayList<HashMap<String, Object>>();
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 0; i < wordlist.size(); i++) {
				int i1 = 0;
				int i2 = 0;
				sql = "select count from hotWord where word=? and DATEDIFF(MONTH,begintime,?)=0";
				sql2 = "select count from hotWord where word=? and DATEDIFF(MONTH,begintime,?)=1";
				ps = conn.prepareStatement(sql);
				ps.setString(1, wordlist.get(i).get("word").toString());
				ps.setString(2, endtime);
				rs = ps.executeQuery();
				while (rs.next()) {
					i1 = i1 + rs.getInt("count");
				}
				ps = conn.prepareStatement(sql2);
				ps.setString(1, wordlist.get(i).get("word").toString());
				ps.setString(2, endtime);
				rs = ps.executeQuery();
				while (rs.next()) {
					i2 = i2 + rs.getInt("count");
				}
				if (i1 - i2 == 0) {

				} else {
					word = new HashMap<String, Object>();
					word.put("word", wordlist.get(i).get("word").toString());
					word.put("count", i1 - i2);
					timewordList.add(word);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return timewordList;
	}

	public void upDownStick(int moduleId, String updown, int newsId,int updownId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tableName = null;
		int temp = -9999;
		int tempId = -9999;
		try {
			switch (moduleId) {
			case 12:
				tableName = "Dtrd";
				break;
			case 13:
				tableName = "Dtck";
				break;
			default:
				tableName = null;
				break;
			}
			conn = JdbcUtils.getConnection();
			if (updown.equals("up")) {
				String sql= "select top 1 * from (select * from " + tableName + " where updown<" + updownId + ")temp order by temp.updown DESC";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getInt("updown");
					tempId = rs.getInt("id");
					String exchange1 = "update " + tableName + " set updown=" + temp + " where id=" + newsId;
					ps = conn.prepareStatement(exchange1);
					ps.executeUpdate();
					String exchange2 = "update " + tableName + " set updown=" + updownId + " where id=" + tempId;
					ps = conn.prepareStatement(exchange2);
					ps.executeUpdate();
				}
			} else if (updown.equals("down")) {
				String sql = "select top 1 * from (select * from " + tableName + " where updown>" + updownId + ")temp order by temp.updown ASC";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					// 该信息下方的updown和id
					temp = rs.getInt("updown");
					tempId = rs.getInt("id");
					// 把该信息的updown换成他下方信息的updown
					String exchange1 = "update " + tableName + " set updown="
							+ temp + " where id=" + newsId;
					ps = conn.prepareStatement(exchange1);
					ps.executeUpdate();
					// 把该信息下方的信息的updown换成该信息的updown
					String exchange2 = "update " + tableName + " set updown="
							+ updownId + " where id=" + tempId;
					ps = conn.prepareStatement(exchange2);
					ps.executeUpdate();
				}
			} else if (updown.equals("stick")) {
				String sql = "select MIN(updown) as updown from " + tableName
						+ "";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getInt("updown");
					if (temp != updownId) {
						String exchange1 = "update " + tableName
								+ " set updown=" + temp + "-1 where id="
								+ newsId;
						ps = conn.prepareStatement(exchange1);
						ps.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}
	@Override
	public void upDownStickOfDtck(String updown, int newsId, int columnOrder,int updownId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int temp = -9999;
		int tempId = -9999;
		try {			
			conn = JdbcUtils.getConnection();
			if (updown.equals("up")) {
				String sql= "select top 1 * from ( select * from Dtck where updown<" + updownId + " and columnOrder="+columnOrder+")temp order by temp.updown DESC";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getInt("updown");
					tempId = rs.getInt("id");
					String exchange1 = "update Dtck set updown=" + temp + " where id=" + newsId;
					ps = conn.prepareStatement(exchange1);
					ps.executeUpdate();
					String exchange2 = "update Dtck set updown=" + updownId + " where id=" + tempId;
					ps = conn.prepareStatement(exchange2);
					ps.executeUpdate();
				}
			} else if (updown.equals("down")) {
				String sql = "select top 1 * from (select * from Dtck where updown>" + updownId + " and columnOrder="+columnOrder+")temp order by temp.updown ASC";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					// 该信息下方的updown和id
					temp = rs.getInt("updown");
					tempId = rs.getInt("id");
					// 把该信息的updown换成他下方信息的updown
					String exchange1 = "update Dtck set updown="
							+ temp + " where id=" + newsId;
					ps = conn.prepareStatement(exchange1);
					ps.executeUpdate();
					// 把该信息下方的信息的updown换成该信息的updown
					String exchange2 = "update Dtck set updown="
							+ updownId + " where id=" + tempId;
					ps = conn.prepareStatement(exchange2);
					ps.executeUpdate();
				}
			} else if (updown.equals("stick")) {
				String sql = "select MIN(updown) as updown from Dtck where columnOrder="+columnOrder;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getInt("updown");
					if (temp != updownId) {
						String exchange1 = "update Dtck set updown=" + temp + "-1 where id=" + newsId;
						ps = conn.prepareStatement(exchange1);
						ps.executeUpdate();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<News> dataBaseRetrieve(String beginTime, String endTime,
			String oilField, String basin, String normal, String company,
			int moduleId, int titleOrContent) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		News news = null;
		String sql = null;
		String tableName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		switch (moduleId) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			if (oilField.equals("0") && basin.equals("0") && normal.equals("2")
					&& company.equals("0") && titleOrContent == 2) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where pubTime>=? and pubTime<=?)temp";
				if (beginTime != "" && endTime.equals("")) {
					st = conn.prepareStatement(sql);
					st.setString(1, beginTime);
					st.setString(2, sdf.format(date.getTime()));
				} else if (beginTime.equals("") && endTime != "") {
					st = conn.prepareStatement(sql);
					st.setString(1, "2000-01-01");
					st.setString(2, endTime);
				} else {
					st = conn.prepareStatement(sql);
					st.setString(1, beginTime);
					st.setString(2, endTime);
				}
				//不设置时间
			} else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && company.equals("0") ) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where oil=? or basin=? or siteSource=? or type=?) temp";
				st = conn.prepareStatement(sql);
				st.setString(1, oilField);
				st.setString(2, basin);
				st.setString(3, company);
				st.setInt(4, Integer.valueOf(normal));
				//设置油田和网站
			}else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && !company.equals("0") && oilField.equals("0") ) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where siteSource=?) temp";
				st = conn.prepareStatement(sql);
//				st.setString(1, oilField);
//				st.setString(2, basin);
				st.setString(1, company);
//				st.setInt(4, Integer.valueOf(normal));
				
			}  
			else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && !company.equals("0")&& !oilField.equals("0") ) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where oil=? and siteSource=?) temp";
				st = conn.prepareStatement(sql);
				st.setString(1, oilField);
//				st.setString(2, basin);
				st.setString(2, company);
//				st.setInt(4, Integer.valueOf(normal));
				
			}  
			else if (oilField.equals("0") && basin.equals("0")
					&& normal.equals("2") && company.equals("0")
					&& beginTime.equals("") && endTime.equals("")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where Output=?) temp";
				st = conn.prepareStatement(sql);
				st.setInt(1, titleOrContent);
			} 
//			else if (titleOrContent == 2) {
//				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
//						+ tableName
//						+ " where pubTime>=? and pubTime<=?)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
//				st = conn.prepareStatement(sql);
//				st.setString(1, beginTime);
//				st.setString(2, endTime);
//				st.setString(3, oilField);
//				st.setString(4, basin);
//				st.setString(5, company);
//				st.setInt(6, Integer.valueOf(normal));
//			} 
			else if (oilField.equals("0") && basin.equals("0")
					&& normal.equals("2") && company.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and Output=?)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setInt(3, titleOrContent);
			} else if (beginTime.equals("") && endTime.equals("")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where Output=?)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
				st = conn.prepareStatement(sql);
				st.setInt(1, titleOrContent);
				st.setString(2, oilField);
				st.setString(3, basin);
				st.setString(4, company);
				st.setInt(5, Integer.valueOf(normal));
			}else if (!beginTime.equals("") && !endTime.equals("")&& !company.equals("0")&& oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and siteSource=?)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setString(3, company);
				
			} 
			else if (beginTime.equals("") && !endTime.equals("")&& !company.equals("0")&& oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and siteSource=?)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, "2000-01-01");
				st.setString(2, endTime);
				st.setString(3, company);
				
			} 
			else if (!beginTime.equals("") && !endTime.equals("")&& !company.equals("0")&& !oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where  pubTime>=? and pubTime<=? and siteSource=? and oil=?)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setString(3, company);
				st.setString(4, oilField);
			} 
			else {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and Output=?)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setInt(3, titleOrContent);
				st.setString(4, oilField);
				st.setString(5, basin);
				st.setString(6, company);
				st.setInt(7, Integer.valueOf(normal));
			}
			rs = st.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				news.setRownumber(rs.getInt("rownumber"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				datas.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}
	
	public ArrayList<News> dataBaseRetrieve2(String beginTime, String endTime,
			String oilField, String basin, String normal, String company,
			int moduleId, int titleOrContent) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		News news = null;
		String sql = null;
		String tableName = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		switch (moduleId) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		}
		try {
			conn = JdbcUtils.getConnection();
			// 只设置关键词筛选
			if (oilField.equals("0") && basin.equals("0") && normal.equals("2")
					&& company.equals("0") && beginTime.equals("")
					&& endTime.equals("") && titleOrContent == 2) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where Output=1) temp";
				st = conn.prepareStatement(sql);
			} else if (oilField.equals("0") && basin.equals("0")
					&& normal.equals("2") && company.equals("0")
					&& titleOrContent == 2) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and Output=1)temp";
				if (beginTime != "" && endTime.equals("")) {
					st = conn.prepareStatement(sql);
					st.setString(1, beginTime);
					st.setString(2, sdf.format(date.getTime()));
				} else if (beginTime.equals("") && endTime != "") {
					st = conn.prepareStatement(sql);
					st.setString(1, "2000-01-01");
					st.setString(2, endTime);
				} else {
					st = conn.prepareStatement(sql);
					st.setString(1, beginTime);
					st.setString(2, endTime);
				}
				// 不设置时间
			} else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && company.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where oil=? or basin=? or siteSource=? or type=? and Output=1) temp";
				st = conn.prepareStatement(sql);
				st.setString(1, oilField);
				st.setString(2, basin);
				st.setString(3, company);
				st.setInt(4, Integer.valueOf(normal));
				// 设置油田和网站
			} else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && !company.equals("0")
					&& oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where siteSource=? and Output=1) temp";
				st = conn.prepareStatement(sql);
				// st.setString(1, oilField);
				// st.setString(2, basin);
				st.setString(1, company);
				// st.setInt(4, Integer.valueOf(normal));

			} else if (beginTime.equals("") && endTime.equals("")
					&& titleOrContent == 2 && !company.equals("0")
					&& !oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where oil=? and siteSource=? and Output=1) temp";
				st = conn.prepareStatement(sql);
				st.setString(1, oilField);
				// st.setString(2, basin);
				st.setString(2, company);
				// st.setInt(4, Integer.valueOf(normal));

			} else if (oilField.equals("0") && basin.equals("0")
					&& normal.equals("2") && company.equals("0")
					&& beginTime.equals("") && endTime.equals("")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName + " where Output=1) temp";
				st = conn.prepareStatement(sql);
				// st.setInt(1, titleOrContent);
			}

			// else if (titleOrContent == 2) {
			// sql =
			// "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
			// + tableName
			// +
			// " where pubTime>=? and pubTime<=?)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
			// st = conn.prepareStatement(sql);
			// st.setString(1, beginTime);
			// st.setString(2, endTime);
			// st.setString(3, oilField);
			// st.setString(4, basin);
			// st.setString(5, company);
			// st.setInt(6, Integer.valueOf(normal));
			// }
			else if (oilField.equals("0") && basin.equals("0")
					&& normal.equals("2") && company.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and Output=1)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				// st.setInt(3, titleOrContent);
			} else if (beginTime.equals("") && endTime.equals("")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where Output=1)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
				st = conn.prepareStatement(sql);
				// st.setInt(1, titleOrContent);
				st.setString(1, oilField);
				st.setString(2, basin);
				st.setString(3, company);
				st.setInt(4, Integer.valueOf(normal));
			} else if (!beginTime.equals("") && !endTime.equals("")
					&& !company.equals("0") && oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and siteSource=? and Output=1)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setString(3, company);

			} else if (beginTime.equals("") && !endTime.equals("")
					&& !company.equals("0") && oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and siteSource=? and Output=1)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, "2000-01-01");
				st.setString(2, endTime);
				st.setString(3, company);

			} else if (!beginTime.equals("") && !endTime.equals("")
					&& !company.equals("0") && !oilField.equals("0")) {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where  pubTime>=? and pubTime<=? and siteSource=? and oil=? and Output=1)temp";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				st.setString(3, company);
				st.setString(4, oilField);
			} else {
				sql = "select * from (select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
						+ tableName
						+ " where pubTime>=? and pubTime<=? and Output=1)temp where temp.oil=? or temp.basin=? or temp.siteSource=? or temp.type=?";
				st = conn.prepareStatement(sql);
				st.setString(1, beginTime);
				st.setString(2, endTime);
				// st.setInt(3, titleOrContent);
				st.setString(3, oilField);
				st.setString(4, basin);
				st.setString(5, company);
				st.setInt(6, Integer.valueOf(normal));
			}
			rs = st.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				news.setRownumber(rs.getInt("rownumber"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				datas.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}
	
	public ArrayList<HashMap<String, Object>> getkeywordChart(String keyword,
			String begintime, String endtime) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count,begintime from (select sum(count) as count,begintime from (select count,begintime from hotWord where word=? and DATEDIFF(MONTH,begintime,?)<=0 and DATEDIFF(MONTH,endtime,?)>=0)hu group by begintime)oneword order by begintime ASC ";
		HashMap<String, Object> oneword = null;
		ArrayList<HashMap<String, Object>> onewordList = new ArrayList<HashMap<String, Object>>();
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyword);
			ps.setString(2, begintime);
			ps.setString(3, endtime);
			rs = ps.executeQuery();
			while (rs.next()) {
				oneword = new HashMap<String, Object>();
				oneword.put("count", rs.getString("count"));
				oneword.put("time", rs.getString("begintime"));
				onewordList.add(oneword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return onewordList;
	}

	public void updateModuleInf() throws Exception {
		ArrayList<String> listAllWords = new ArrayList<String>();
		ArrayList<String> listOilWords = new ArrayList<String>();
		ArrayList<String> listCompanyWords = new ArrayList<String>();
		ArrayList<String> listBasinWords = new ArrayList<String>();
		ArrayList<String> listBlackWords = new ArrayList<String>();
		Words words = new Words();
		listAllWords = words.getAllWords();
		listOilWords = words.getWords("Oil");
		listCompanyWords = words.getWords("Company");
		listBasinWords = words.getWords("Basin");
		listBlackWords = words.getWords("Blacklist");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tableName = null;
		try {
			conn = JdbcUtils.getConnection();
			for (int i = 1; i <= 11; i++) {
				switch (i) {
				case 1:
					tableName = "Gzdt";
					break;
				case 2:
					tableName = "Kcgl";
					break;
				case 3:
					tableName = "Zcfg";
					break;
				case 4:
					tableName = "Yzrd";
					break;
				case 5:
					tableName = "Gjhz";
					break;
				case 6:
					tableName = "Kjjz";
					break;
				case 7:
					tableName = "Tpxw";
					break;
				case 8:
					tableName = "Lddt";
					break;
				case 9:
					tableName = "Tjsj";
					break;
				case 10:
					tableName = "Ktkf";
					break;
				case 11:
					tableName = "Yjxx";
					break;
				}
				ps = conn.prepareStatement("select * from " + tableName);
				rs = ps.executeQuery();
				while (rs.next()) {
					int output;
					if (words.Contain(rs.getString("title"), listBlackWords)) {
						ps = conn.prepareStatement("update " + tableName
								+ " set Output=-1 where id=" + rs.getInt("id"));
						ps.executeUpdate();
						output = -1;
					} else if (words.Contain(rs.getString("title"),
							listAllWords)) {
						ps = conn.prepareStatement("update " + tableName
								+ " set Output=1 where id=" + rs.getInt("id"));
						ps.executeUpdate();
						output = 1;
					} else if (words.Contain(rs.getString("content"),
							listAllWords)) {
						ps = conn.prepareStatement("update " + tableName
								+ " set Output=0 where id=" + rs.getInt("id"));
						ps.executeUpdate();
						output = 0;
					} else {
						ps = conn.prepareStatement("update " + tableName
								+ " set Output=-1 where id=" + rs.getInt("id"));
						ps.executeUpdate();
						output = -1;
					}
					System.out.println(output + rs.getString("title"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public void updateblacklist(int blackOrWhite) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String tableName = null;
		int output;
		String sql = null;
		ArrayList<String> listAllWords = new ArrayList<String>();
		ArrayList<String> listOilWords = new ArrayList<String>();
		ArrayList<String> listCompanyWords = new ArrayList<String>();
		ArrayList<String> listBasinWords = new ArrayList<String>();
		ArrayList<String> listBlackWords = new ArrayList<String>();
		Words words = new Words();
		try {
			listAllWords = words.getAllWords();
			listOilWords = words.getWords("Oil");
			listCompanyWords = words.getWords("Company");
			listBasinWords = words.getWords("Basin");
			listBlackWords = words.getWords("Blacklist");
			conn = JdbcUtils.getConnection();
			for (int i = 1; i <= 11; i++) {
				switch (i) {
				case 1:
					tableName = "Gzdt";
					break;
				case 2:
					tableName = "Kcgl";
					break;
				case 3:
					tableName = "Zcfg";
					break;
				case 4:
					tableName = "Yzrd";
					break;
				case 5:
					tableName = "Gjhz";
					break;
				case 6:
					tableName = "Kjjz";
					break;
				case 7:
					tableName = "Tpxw";
					break;
				case 8:
					tableName = "Lddt";
					break;
				case 9:
					tableName = "Tjsj";
					break;
				case 10:
					tableName = "Ktkf";
					break;
				case 11:
					tableName = "Yjxx";
					break;
				}
				sql = "select * from " + tableName + " where Output=?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, blackOrWhite);
				rs = ps.executeQuery();
				while (rs.next()) {
					output = -2;
					if (words.Contain(rs.getString("title"), listBlackWords)) {
						if (rs.getInt("Output") != -1) {
							ps = conn.prepareStatement("update " + tableName
									+ " set Output=-1 where id="
									+ rs.getInt("id"));
							ps.executeUpdate();
							output = -10;
						}
					} else if (words.Contain(rs.getString("title"),
							listAllWords)) {
						if (rs.getInt("Output") != 1) {
							ps = conn.prepareStatement("update " + tableName
									+ " set Output=1 where id="
									+ rs.getInt("id"));
							ps.executeUpdate();
							output = 1;
						}
					} else if (words.Contain(rs.getString("content"),
							listAllWords)) {
						if (rs.getInt("Output") != 0) {
							ps = conn.prepareStatement("update " + tableName
									+ " set Output=0 where id="
									+ rs.getInt("id"));
							ps.executeUpdate();
							output = 0;
						}
					} else {
						if (rs.getInt("Output") != -1) {
							ps = conn.prepareStatement("update " + tableName
									+ " set Output=-1 where id="
									+ rs.getInt("id"));
							ps.executeUpdate();
							output = -9;
						}
					}
					System.out.println(output + rs.getString("title"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
	}

	public ArrayList<News> titleSearch(int moduleId, String title) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<News> datas = new ArrayList<News>();
		News news = null;
		String sql = null;
		String tableName = null;
		switch (moduleId) {
		case 1:
			tableName = "Gzdt";
			break;
		case 2:
			tableName = "Kcgl";
			break;
		case 3:
			tableName = "Zcfg";
			break;
		case 4:
			tableName = "Yzrd";
			break;
		case 5:
			tableName = "Gjhz";
			break;
		case 6:
			tableName = "kjjz";
			break;
		case 7:
			tableName = "Tpxw";
			break;
		case 8:
			tableName = "Lddt";
			break;
		case 9:
			tableName = "Tjsj";
			break;
		case 10:
			tableName = "Ktkf";
			break;
		case 11:
			tableName = "Yjxx";
			break;
		}
		try {
			sql = "select ROW_NUMBER() over(order by totalWeight DESC,pubTime DESC,webWeight DESC) rownumber, * from "
					+ tableName
					+ " where title like '%"
					+ title
					+ "%' and Output!=-1";
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				news = new News();
				news.setId(rs.getInt("id"));
				news.setRownumber(rs.getInt("rownumber"));
				news.setTitle(rs.getString("title"));
				news.setPubTime(rs.getString("pubTime"));
				news.setOriginSource(rs.getString("originSource"));
				news.setSiteSource(rs.getString("siteSource"));
				news.setVisible(rs.getInt("visible"));
				datas.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return datas;
	}

	public ArrayList<Users> accountUsers() {
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sql = null;
		ResultSet rs = null;
		ArrayList<Users> users = new ArrayList<Users>();
		try {
			con = JdbcUtils.getConnection();
			sql = con.createStatement();
			rs = sql.executeQuery("select * from Yhdl");
			for (int i = 0; rs.next(); i++) {
				Users us = new Users();
				us.setUsername(rs.getString("name"));
				us.setPassword(rs.getString("passwords"));
				if (rs.getString("type").equals("1")) {
					us.setType(true);
				} else {
					us.setType(false);
				}
				users.add(us);
			}
			// rs.close();
			// sql.close();
			// con.close();
		} catch (Exception e) {
			System.out.println("accoutAction isError");
		} finally {
			JdbcUtils.releaseConnection(con);
		}
		return users;
	}

	public Users userLogin(String username, String password) {
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		Users us = new Users();
		String sql = "select * from Yhdl";
		try {
			conn = JdbcUtils.getConnection();

			ps = conn.createStatement();
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				if (username.equals(rs.getString("name"))
						&& password.equals(rs.getString("passwords"))) {
					us.setUsername(username);
					us.setPassword(password);
					String type = rs.getString("type");
					if (type.equals("1")) {
						us.setType(true);
					} else {
						us.setType(false);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return us;
	}

	public String addUsers(String username, String password) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "insert into Yhdl values(?,?,?)";
		try {
			conn = JdbcUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, "false");
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return "success";
	}

	public String updateUsers(String username, String password) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "update Yhdl set passwords=? where name=?";
		try {
			conn = JdbcUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, username);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return "success";
	}

	public String deleteUsers(String username) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "delete from Yhdl where name=?";
		try {
			conn = JdbcUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return "success";
	}

	public String update(String username, String password) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "update Yhdl set passwords=? where name=?";
		try {
			conn = JdbcUtils.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, password);
			pst.setString(2, username);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return "success";
	}
	public ArrayList<PhoneUser> getAllUser(PageRoll pageRoll) {
		ArrayList<PhoneUser> phoneUsers = new ArrayList<PhoneUser>();
		String sql1 = "";
		String sql2 = "";
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		sql1 = "select count(userID) from users";
		sql2 = "select temp.username,temp.department,temp.account,temp.job,temp.telephone,temp.userID "
				+ "from (select ROW_NUMBER() over(order by userID) rownumber, * from users)temp "
				+ "where temp.rownumber>=? and temp.rownumber<=? ";
		String sql3="select userID,roleName from userRole,role where userRole.roleID=role.roleID";
		try {

			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
			if (rs.next()) {
				int count = rs.getInt(1);
				pageRoll.setTotalCount(count);
			}
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize());
			ps.setInt(2, pageRoll.getCurrPage() * pageRoll.getPageSize());
			rs = ps.executeQuery();
			while (rs.next()) {
				PhoneUser phoneUser = new PhoneUser();
				phoneUser.setAccount(rs.getString("account"));
				phoneUser.setDepartement(rs.getString("department"));
				phoneUser.setJob(rs.getString("job"));
				phoneUser.setTelephone(rs.getString("telephone"));
				phoneUser.setUserID(rs.getInt("userID"));
				phoneUser.setUsername(rs.getString("userName"));
				phoneUsers.add(phoneUser);
			}
			st=conn.createStatement();
			rs=st.executeQuery(sql3);
			while(rs.next()) {
				for(int i=0;i<phoneUsers.size();i++) {
					if(phoneUsers.get(i).getUserID()==rs.getInt("userID"))
					phoneUsers.get(i).setRole(rs.getString("roleName"));
					
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return phoneUsers;
	}

	public ArrayList<PhoneUser> getDepartmentUser(PageRoll pageRoll,
			String department) {
		ArrayList<PhoneUser> phoneUsers = new ArrayList<PhoneUser>();
		String sql1 = "";
		String sql2 = "";
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		sql1 = "select count(userID) from users";
		sql2 = "select temp.username,temp.department,temp.account,temp.job,temp.telephone,temp.userID "
				+ "from (select ROW_NUMBER() over(order by userID) rownumber, * from users)temp "
				+ "where temp.rownumber>=? and temp.rownumber<=? and temp.department=?";
		String sql3="select userID,roleName from userRole,role where userRole.roleID=role.roleID";
		try {

			conn = JdbcUtils.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
			if (rs.next()) {
				int count = rs.getInt(1);
				pageRoll.setTotalCount(count);
			}
			ps = conn.prepareStatement(sql2);
			ps.setInt(1, (pageRoll.getCurrPage() - 1) * pageRoll.getPageSize());
			ps.setInt(2, pageRoll.getCurrPage() * pageRoll.getPageSize());
			ps.setString(3, department);
			rs = ps.executeQuery();
			while (rs.next()) {
				PhoneUser phoneUser = new PhoneUser();
				phoneUser.setAccount(rs.getString("account"));
				phoneUser.setDepartement(rs.getString("department"));
				phoneUser.setJob(rs.getString("job"));
				phoneUser.setTelephone(rs.getString("telephone"));
				phoneUser.setUserID(rs.getInt("userID"));
				phoneUser.setUsername(rs.getString("userName"));
				phoneUsers.add(phoneUser);
			}
			st=conn.createStatement();
			rs=st.executeQuery(sql3);
			while(rs.next()) {
				for(int i=0;i<phoneUsers.size();i++) {
					if(phoneUsers.get(i).getUserID()==rs.getInt("userID"))
					phoneUsers.get(i).setRole(rs.getString("roleName"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}
		return phoneUsers;
	}

	public void changeRole(int userID, String role, String department) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql1 = "update UserRole set roleID="
				+ "(select roleID from Role where roleName=?) where userID=?";
		String sql2="update users set department=? where userID=?";
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql1);
			if (role != null) {
				ps.setString(1, role);
				ps.setInt(2, userID);
				ps.executeUpdate();
			}
			if (department != null) {
				ps = conn.prepareStatement(sql2);
				ps.setString(1, department);
				ps.setInt(2, userID);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(conn);
		}

	}

	@Override
	public ArrayList<Group> getAllGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNotification(String time, ArrayList<Integer> groups,
			String content, String publisher) {
		// TODO Auto-generated method stub
		
	}
}