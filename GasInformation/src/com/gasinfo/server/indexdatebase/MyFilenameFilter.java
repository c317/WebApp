package com.gasinfo.server.indexdatebase;

import java.io.File;
import java.io.FilenameFilter;

public class MyFilenameFilter implements FilenameFilter{

	private String type;  
    public MyFilenameFilter(String type){  
        this.type = type;  
    }  
    public boolean accept(File dir,String name){  
    	return name.contains(type); 
    }  
}  

