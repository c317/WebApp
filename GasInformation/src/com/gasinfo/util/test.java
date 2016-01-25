package com.gasinfo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.gasinfo.wordsanalysis.GetWords;
import com.gasinfo.wordsanalysis.OpenFile;

public class test {

	public static void main(String[] args){
		Newsdao newsdao = DaoFactory.getInstance().getNewsDao();
		ArrayList<Integer> groupsGet = new ArrayList<Integer>();
		groupsGet.add(1);
		groupsGet.add(2);
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		newsdao.setNotification(time, groupsGet, "哈哈哈哈哈哈", "chenfan","人事部","开会");
	}
}
