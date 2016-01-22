package com.gasinfo.wordsanalysis;

import java.util.ArrayList;
import java.util.HashMap;

public class CaculateSE {
	// 得到每个词的SE值
	public static void caculateSE(String content,
			ArrayList<HashMap<String, Object>> listhashmap) throws Exception {
		double LP_Count, LLP_Count, RLP_Count;
		double SE;
		for (int i = 0; i < listhashmap.size(); i++) {
			String LP = listhashmap.get(i).get("Str").toString();
			LP_Count = OpenFile.strCount(content, LP.toString());
			LLP_Count = OpenFile.strCount(content,
					LP.substring(0, LP.length() - 1));
			RLP_Count = OpenFile.strCount(content, LP.substring(1));
			if (LLP_Count + RLP_Count - LP_Count == 0) {
				SE = 0;
			} else {
				SE = LP_Count / (LLP_Count + RLP_Count - LP_Count);
			}
			listhashmap.get(i).put("SE", SE);
		}
	}

	// 按SE值从小到大排序
	public static ArrayList<HashMap<String, Object>> Paixu(
			ArrayList<HashMap<String, Object>> listhashmap) {

		String str;
		int count;
		double SE;

		for (int i = 0; i < listhashmap.size(); i++) {
			for (int j = i + 1; j < listhashmap.size(); j++) {
				if (Double.valueOf(listhashmap.get(i).get("SE").toString()) < Double
						.valueOf(listhashmap.get(j).get("SE").toString())) {
					str = (String) listhashmap.get(i).get("Str");
					count = Integer.parseInt(listhashmap.get(i).get("Count")
							.toString());
					SE = Double
							.valueOf(listhashmap.get(i).get("SE").toString());

					listhashmap.get(i)
							.put("Str", listhashmap.get(j).get("Str"));
					listhashmap.get(i).put("String",
							listhashmap.get(j).get("String"));
					listhashmap.get(i).put("Count",
							listhashmap.get(j).get("Count"));
					listhashmap.get(i).put("SE", listhashmap.get(j).get("SE"));

					listhashmap.get(j).put("Str", str);
					listhashmap.get(j).put("Count", count);
					listhashmap.get(j).put("SE", SE);
				}
			}
		}
		return listhashmap;
	}
}
