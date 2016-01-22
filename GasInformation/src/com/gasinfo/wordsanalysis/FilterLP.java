package com.gasinfo.wordsanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FilterLP {

	// 过滤非完整意义词
	public static ArrayList<HashMap<String, Object>> filter(
			ArrayList<HashMap<String, Object>> listhashmap) {
		ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<HashMap<String, Object>>();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<HashMap<String, Object>> LhashMaps = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> RhashMaps = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < listhashmap.size(); i++) {
			double ratio = 0.0;
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			String str = listhashmap.get(i).get("Str").toString();
			int count = Integer.parseInt(listhashmap.get(i).get("Count")
					.toString());
			for (int j = 0; j < listhashmap.size(); j++) {
				if (listhashmap.get(j).get("Str").toString().equals(str)) {
					continue;
				}
				if (listhashmap.get(j).get("Str").toString().endsWith(str)) {
					LhashMaps.add(listhashmap.get(j));
					ratio = Double.valueOf(MAX(LhashMaps) / count);
				} else if (listhashmap.get(j).get("Str").toString()
						.startsWith(str)) {
					RhashMaps.add(listhashmap.get(j));
					ratio = Double.valueOf(MAX(RhashMaps) / count);
				}
			}
			if (ratio > 0.9) {
				list.add(str);
			}
			hashMap.put("Str", listhashmap.get(i).get("Str"));
			hashMap.put("Count", count);
			hashMap.put("SE", listhashmap.get(i).get("SE"));
			hashMaps.add(hashMap);
		}
		for (int i = 0; i < hashMaps.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (hashMaps.get(i).get("Str").toString().equals(list.get(j))) {
					hashMaps.remove(i);
					i--;
					break;
				}
			}
		}
		// 去除集合中重复的值
		HashSet<HashMap<String, Object>> hashSet = new HashSet<HashMap<String, Object>>(
				hashMaps);
		hashMaps.clear();
		hashMaps.addAll(hashSet);
		return hashMaps;
	}
	
	//找出前缀或后缀最大的个数
		public  static Integer MAX(ArrayList<HashMap<String, Object>>listHashMaps){
			int max = Integer.parseInt(listHashMaps.get(0).get("Count").toString());
			for (int i = 1; i < listHashMaps.size(); i++) {
				if (Integer.parseInt(listHashMaps.get(i).get("Count").toString())>max) {
					max = Integer.parseInt(listHashMaps.get(i).get("Count").toString());
				}
			}
			return max;
		}
}
