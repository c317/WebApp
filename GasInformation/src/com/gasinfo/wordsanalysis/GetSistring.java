package com.gasinfo.wordsanalysis;

import java.util.ArrayList;
import java.util.HashMap;

public class GetSistring {

	// 得到半无限长字符串
	public  ArrayList<HashMap<String, Object>> getSistring(String content,
			String str) throws Exception {

		ArrayList<String> list = new ArrayList<String>();
		list = GetStrings(str);
		ArrayList<String> Sistringlist = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			// 从前往后截取
			for (int j = list.get(i).toString().length(); j > 0; j--) {
				Sistringlist.add(list.get(i).substring(0, j));
			}
			// 从后往前截取
			for (int j = 1; j < list.get(i).toString().length(); j++) {
				Sistringlist.add(list.get(i).substring(j,
						list.get(i).toString().length()));
			}
		}
		// 删除长度小于3或大于6的半无限长字符串
		for (int i = 0; i < Sistringlist.size(); i++) {
			if (Sistringlist.get(i).toString().length() < 3
					|| Sistringlist.get(i).toString().length() > 6) {
				Sistringlist.remove(i);
				i--;// 每删除一个长度发生变化
			}
		}

		ArrayList<HashMap<String, Object>> listhashmap = new ArrayList<HashMap<String, Object>>();
		// 计算每个半无限长字符串的频数
		for (int k = 0; k < Sistringlist.size(); k++) {
			HashMap<String, Object> hashmap = new HashMap<String, Object>();
			hashmap.put("Str", Sistringlist.get(k));
			int count = OpenFile.strCount(content, Sistringlist.get(k)
					.toString());
			hashmap.put("Count", count);
			listhashmap.add(hashmap);
		}
		return listhashmap;
	}

	// 得到去除标点符号的字符串数组
	public  ArrayList<String> GetStrings(String str) {

		ArrayList<String> list = new ArrayList<String>();
		String[] strings = str.split("\\p{P}");
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].length() > 1) {
				list.add(strings[i]);
			}
		}
		return list;
	}
	
	/**
     * 比较一个字符串是否包含另一个字符串
     * @param str 长的字符串
     * @param searchChars 是否包含的字符串
     * @return
     */
	public  boolean containsAny(String str, String searchChars) { 
		  if(str.length()!=str.replace(searchChars,"").length())  { 
			  return true; 
		  } 
		  	return false;
		 }

	//一个词被包含在另一个词中，则删除这个词
	public  ArrayList<HashMap<String, Object>> delete(ArrayList<HashMap<String, Object>> hashMaps){
		int i=0;//for (i = 0; i < hashMaps.size(); i++)
		while(i<hashMaps.size()) {
			String Str = hashMaps.get(i).get("Str").toString();
			for (int j = 0; j < hashMaps.size(); j++) {
				String Str1 = hashMaps.get(j).get("Str").toString();
				if (Str1.equals(Str)) {
					continue;
				}
				else if (containsAny(Str1, Str)) {
					if(i<0){
						i=0;
						hashMaps.remove(i);
					}else{
						hashMaps.remove(i);
						i--;
					}
					
				}				
			}
			if(i<=-1){
				i=0;
			}else{
				i++;
			}
		}
		return hashMaps;
	}
}
