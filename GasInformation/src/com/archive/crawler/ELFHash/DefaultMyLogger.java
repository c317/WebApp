package com.archive.crawler.ELFHash;


/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.

import java.io.*;
import javax.servlet.ServletContext;

// Referenced classes of package org.apache.jasper.logging:
//            Logger

public class DefaultMyLogger extends MyLogger {

	public DefaultMyLogger() {
	}

	public DefaultMyLogger(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected void realLog(String message) {
		if (servletContext != null) {
			servletContext.log(message);
			return;
		}
		try {
			defaultSink.write(message);
			defaultSink.write(newline);
			flush();
		} catch (IOException e) {
			bad(e, message, null);
		}
	}

	protected void realLog(String message, Throwable t) {
		if (servletContext != null) {
			servletContext.log(message, t);
			return;
		}
		try {
			defaultSink.write(message);
			defaultSink.write(newline);
			defaultSink.write(throwableToString(t));
			defaultSink.write(newline);
			flush();
		} catch (IOException e) {
			bad(e, message, t);
		}
	}

	private void bad(Throwable t1, String message, Throwable t2) {
		System.err.println("Default sink is unwritable! Reason:");
		if (t1 != null)
			t1.printStackTrace();
		if (message != null)
			System.err.println(message);
		if (t2 != null)
			t2.printStackTrace();
	}

	public void flush() {
		try {
			defaultSink.flush();
		} catch (IOException e) {
		}
	}

	private static char newline[];
	protected ServletContext servletContext;

	static {
		String separator = System.getProperty("line.separator", "\n");
		newline = separator.toCharArray();
	}
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\My Eclipse\GasInformation3.0\WebContent\WEB-INF\lib\jasper-runtime.jar
	Total time: 47 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/