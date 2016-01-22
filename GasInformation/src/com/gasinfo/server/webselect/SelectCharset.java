package com.gasinfo.server.webselect;

import java.io.File;

public class SelectCharset {
	
	public String GetCharset(File file){
		return new GetHtmlCharset().GetCharset(file);
	}

}
