package com.gasinfo.server.heritrix;

import com.gasinfo.config.Configuration;

import java.io.File;

public class AddNumber {
	int No ;
	Configuration rc = new Configuration("daoconfig.properties");
	String RootURL_exSelectedWeb = rc.getValue("RootURL_exSelectedWeb");
	String RootURL = RootURL_exSelectedWeb;//"E:\\My Eclipse\\exSelectedWeb";
	public void addNumber(int No){
		Gjhz_addnumber(No);
		Gzdt_addnumber(No);
		Kcgl_addnumber(No);
		Kjjz_addnumber(No);
		Ktkf_addnumber(No);
		Lddt_addnumber(No);
		Tjsj_addnumber(No);
		Yjxx_addnumber(No);
		Yzrd_addnumber(No);
		Tpxw_addnumber(No);
		Zcfg_addnumber(No);
	}
	
	private void Gjhz_addnumber(int No){
		String classes = "\\Gjhz";
		String[] path = new String[4];
		path[0] = "\\ShiYouShangBao";
		path[1] = "\\ZhongHaiYou";
		path[2] = "\\ZhongShiHua";
		path[3] = "\\ZhongShiYou";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Gzdt_addnumber(int No){
		String classes = "\\Gzdt";
		String[] path = new String[2];
		path[0] = "\\ZhongShiHua";
		path[1] = "\\ZhongShiYou";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}

	private void Kcgl_addnumber(int No){
		String classes = "\\Kcgl";
		String[] path = new String[2];
		path[0] = "\\FeiChangGuiYouQi";
		path[1] = "\\GuoTuZiYuan";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Kjjz_addnumber(int No){
		String classes = "\\Kjjz";
		String[] path = new String[2];
		path[0] = "\\ZhongHaiYou";
		path[1] = "\\ZhongShiYou";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Ktkf_addnumber(int No){
		String classes = "\\Ktkf";
		String[] path = new String[5];
		path[0] = "\\FeiChangGuiYouQi";
		path[1] = "\\TaLiMuYouTian";
		path[2] = "\\ZhongHaiYou";
		path[3] = "\\ZhongShiHua";
		path[4] = "\\ZhongShiYou";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Lddt_addnumber(int No){
		String classes = "\\Lddt";
		String[] path = new String[3];
		path[0] = "\\ZhongHaiYou";
		path[1] = "\\ZhongShiHua";
		path[2] = "\\ZhongShiYou";
		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Tjsj_addnumber(int No){
		String classes = "\\Tjsj";
		String[] path = new String[1];
		path[0] = "\\TongJiJu";

		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Tpxw_addnumber(int No){
		String classes = "\\Tpxw";
		String[] path = new String[1];
		path[0] = "\\ZhongShiYou";

		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Yjxx_addnumber(int No){
		String classes = "\\Yjxx";
		String[] path = new String[1];
		path[0] = "\\ShiYouJingJi";

		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Yzrd_addnumber(int No){
		String classes = "\\Yzrd";
		String[] path = new String[2];
		path[0] = "\\GuoTuZiYuan";
		path[1] = "\\ZhongShiHua";

		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	private void Zcfg_addnumber(int No){
		String classes = "\\Zcfg";
		String[] path = new String[7];
		path[0] = "\\CaiZhengBu";
		path[1] = "\\FaGaiWei";
		path[2] = "\\GuoTuZiYuan";
		path[3] = "\\HaiGuan";
		path[4] = "\\HuanBaoBu";
		path[5] = "\\ShangWuBu";
		path[6] = "\\ZhengFu";

		for(int i = 0 ; i < path.length ; i++){
			String filepath = RootURL + classes + path[i] + "\\" + No;
			File file = new File(filepath);
			if (!file.exists()){
				file.mkdirs();
			}
		}
	}
	
}
