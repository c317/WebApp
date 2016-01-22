package com.gasinfo.server.heritrix;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.gasinfo.config.*;
import com.gasinfo.model.FileOperate;

public class CopyFile extends FileOperate{
	
	Configuration rc = new Configuration("daoconfig.properties");
	String RootURL_model = rc.getValue("RootURL_model");

	/*strSourceFilepath 例如E:\Workplace\exjobs\ZhongShiYou\1
	 * strTargetFilepath 例如 E:\Workplace\exjobs\ZhongShiYou\2
	 */
	public void copyFileForHeritrix(String strSourceFilepath , String strTargetFilepath) throws IOException{
		(new File(strTargetFilepath)).mkdirs();
		
		File[] file = (new File(strSourceFilepath)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].getName().equalsIgnoreCase("order.xml")|| 
					file[i].getName().equalsIgnoreCase("seeds.txt")) {
				copyFile(file[i],new File(strTargetFilepath + File.separator + file[i].getName()));
			}
		}

		/*如果是新增加的网站爬取任务就不需要修改recover-path的属性，
		 * 如果是现有的任务就需要把recover-path值修改为前一个任务的日志路径
		 */
		//我不要增量更新了，所以隐了
//		if(!strSourceFilepath.equals(RootURL_model)){
//			XmlMod dom4jParser = new XmlMod();
//			dom4jParser.modifyRecoverPath(strTargetFilepath , strSourceFilepath);
//		}
		
	}
	
	public void modifySeedsTXT(String strTargetFilepath , String seed) throws IOException{
		
		InputStream inputstream = new ByteArrayInputStream(seed.getBytes("UTF-8"));
		DataInputStream datainputstream = new DataInputStream(inputstream);
		BufferedInputStream inBuff = new BufferedInputStream(datainputstream);

		
		FileOutputStream output = new FileOutputStream(new File(strTargetFilepath + File.separator + "seeds.txt"));
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

	
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
	
		outBuff.flush();

		inBuff.close();
		outBuff.close();
		output.close();
		datainputstream.close();
		inputstream.close();
	}
	
	
	public  void copyFile(File sourceFile, File targetFile)
			throws IOException {
	
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

	
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

	
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
	
		outBuff.flush();

		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}
}
