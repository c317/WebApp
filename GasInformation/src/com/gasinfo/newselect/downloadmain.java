package com.gasinfo.newselect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class downloadmain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
      new downloadmain().downloadmain();
	}

	public void downloadmain(){
		 try {   
			 StringBuffer sb;
			URL url=new URL("http://www.oilchina.com/cnodc/syxx/cnodc_xl.jsp?bsm=05681CC51.0002BBCF.0854&db=cnodcsykj");//取得资源对象
			URLConnection uc=url.openConnection();//生成连接对象
			uc.setDoOutput(true);
			uc.connect(); //发出连接
			String temp;
			sb = new StringBuffer();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
			url.openStream(),"gb2312"));
			while ((temp = in.readLine()) != null) {
			sb.append("\n");
			sb.append(temp);
			}
			in.close();
			System.out.println(sb);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	
	}


}
