package com.gasinfo.model;

public class FileOperate {
	public String strSourceFilepath ;
	public String strTargetFilepath;
	
	public String getStrSourceFilepath() {
		return strSourceFilepath;
	}

	public void setStrSourceFilepath(String strSourceFilepath) {
		this.strSourceFilepath = strSourceFilepath;
	}



	public String getStrTargetFilepath() {
		return strTargetFilepath;
	}

	public void setStrTargetFilepath(String strTargetFilepath) {
		this.strTargetFilepath = strTargetFilepath;
	}

	public void CopyFile(String strSourceFilepath , String strTargetFilepath){}
	
	
}
