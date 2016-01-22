package com.gasinfo.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class Configuration {
	private Properties propertie;
	private FileInputStream inputFile;
	private FileOutputStream outputFile;
	private String fileName;
	/**
	 * 初始化Configuration类
	 */
	public Configuration() {
		propertie = new Properties();
	}

	/**
	 * 初始化Configuration类
	 * 
	 * @param fileName
	 *            要读取的配置文件的名称
	 */
	public Configuration(String fileName) {
		this.fileName = fileName;
		
		propertie = new Properties();
		try {
			inputFile = new FileInputStream(this.getClass().getClassLoader()
					.getResource(fileName).getPath());
			propertie.load(inputFile);
			inputFile.close();
		} catch (FileNotFoundException ex) {
			System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("装载文件--->失败!");
			ex.printStackTrace();
		}
	}// end ReadConfigInfo(...)

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param key
	 *            取得其值的键
	 * @return key的值
	 */
	public String getValue(String key) {
		if (propertie.containsKey(key)) {
			String value = propertie.getProperty(key);// 得到某一属性的值
			return value;
		} else
			return "";
	}// end getValue(...)

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param fileName
	 *            properties文件的路径+文件名
	 * @param key
	 *            取得其值的键
	 * @return key的值
	 */
	public String getValue(String fileName, String key) {
		try {
			String value = "";
			inputFile = new FileInputStream(fileName);
			propertie.load(inputFile);
			inputFile.close();
			if (propertie.containsKey(key)) {
				value = propertie.getProperty(key);
				return value;
			} else
				return value;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}// end getValue(...)

	/**
	 * 清除properties文件中所有的key和其值
	 */
	public void clear() {
		propertie.clear();
	}// end clear();

	/**
	 * 改变或添加一个key的值，当key存在于properties文件中时该key的值被value所代替， 当key不存在时，该key的值是value
	 * 
	 * @param key
	 *            要存入的键
	 * @param value
	 *            要存入的值
	 */
	public void setValue(String key, String value) {
		
        Properties prop = new Properties();// 属性集合对象  
        FileInputStream fis;  
        try {  
            fis =  new FileInputStream(this.getClass().getClassLoader()
					.getResource(fileName).getPath());// 属性文件输入流  
            prop.load(fis);// 将属性文件流装载到Properties对象中  
            fis.close();// 关闭流  
            // 修改sitename的属性值  
            prop.setProperty(key, value);  
            // 文件输出流  
            FileOutputStream fos = new FileOutputStream(this.getClass().getClassLoader()
					.getResource(fileName).getPath()); 
            
            FileOutputStream fos2 = new FileOutputStream(prop.getProperty("RootURL_src") + "\\" + fileName); 
            
       
            // 将Properties集合保存到流中  
            prop.store(fos, "Updata Time");  
            prop.store(fos2, "Updata Time");  
            fos.close();// 关闭流  
            fos2.close();// 关闭流  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } 
        }  
		
		
		
		
	// end setValue(...)

	/**
	 * 将更改后的文件数据存入指定的文件中，该文件可以事先不存在。
	 * 
	 * @param fileName
	 *            文件路径+文件名称
	 * @param description
	 *            对该文件的描述
	 */
	public void saveFile(String fileName, String description) {
		try {
			outputFile = new FileOutputStream(fileName);
			propertie.store(outputFile, description);
			outputFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}// end saveFile(...)

	
	
	
	
	public static void main(String[] args) throws IOException {
		Configuration rc = new Configuration("daoconfig.properties");
		rc.setValue("Time_Heritrix","600");
	
		Configuration rc2 = new Configuration("daoconfig.properties");
			System.out.println(rc2.getValue("Time_Heritrix"));
		
	}
}
