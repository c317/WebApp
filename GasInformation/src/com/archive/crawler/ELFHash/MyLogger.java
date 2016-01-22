package com.archive.crawler.ELFHash;


/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.

import java.io.*;
import java.net.SocketException;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import javax.servlet.ServletException;

import com.archive.crawler.ELFHash.MyFastDateFormat;

import org.xml.sax.SAXException;

// Referenced classes of package org.apache.jasper.logging:
//            DefaultMyLogger, LogAware

public abstract class MyLogger {
	public static class Helper implements MyLogAware {

		public MyLogger getMyLogger() {
			if (proxy != null)
				MyLogger = proxy.getMyLogger();
			return MyLogger;
		}

		public void setMyLogger(MyLogger MyLogger) {
			if (MyLogger != null)
				setLogname(MyLogger.getName());
			this.MyLogger = MyLogger;
		}

		public void setLogname(String logname) {
			MyLogger = null;
			this.logname = logname;
		}

		public void setLogPrefix(String prefix) {
			this.prefix = prefix;
		}

		public void setProxy(Helper helper) {
			proxy = helper;
		}

		public Helper getMyLoggerHelper() {
			return this;
		}

		public void log(String msg) {
			log(msg, null, 3);
		}

		public void log(String msg, Throwable t) {
			log(msg, t, 1);
		}

		public void log(String msg, int level) {
			log(msg, null, level);
		}

		public void log(String msg, Throwable t, int level) {
			if (prefix != null)
				msg = prefix + ": " + msg;
			if (proxy != null)
				MyLogger = proxy.getMyLogger();
			if (MyLogger == null && logname != null)
				MyLogger = MyLogger.getMyLogger(logname);
			MyLogger MyLoggerTemp = MyLogger;
			if (MyLoggerTemp == null)
				MyLoggerTemp = MyLogger.defaultMyLogger;
			MyLoggerTemp.log(msg, t, level);
		}

		private String logname;
		private String prefix;
		private MyLogger MyLogger;
		private Helper proxy;

		protected Helper(String logname) {
			this.logname = logname;
			String cname = getClass().getName();
			prefix = cname.substring(cname.lastIndexOf(".") + 1);
		}

		public Helper(String logname, Object owner) {
			this.logname = logname;
			String cname = owner.getClass().getName();
			prefix = cname.substring(cname.lastIndexOf(".") + 1);
		}

		public Helper(String logname, String prefix) {
			this.logname = logname;
			this.prefix = prefix;
		}

		
		public void setLogger(com.archive.crawler.ELFHash.MyLogger logger) {
			// TODO Auto-generated method stub
			
		}

	
		public Helper getLoggerHelper() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public MyLogger() {
		custom = true;
		sink = defaultSink;
		level = 2;
		timestamp = true;
		timestampRaw = false;
		timestampFormat = "yyyy-MM-dd hh:mm:ss";
		timestampFormatter = new MyFastDateFormat(new SimpleDateFormat(
				timestampFormat));
	}

	public static void setDefaultSink(Writer w) {
		defaultSink = w;
	}

	public static MyLogger getMyLogger(String name) {
		return (MyLogger) MyLoggers.get(name);
	}

	public static MyLogger getDefaultMyLogger() {
		return defaultMyLogger;
	}

	public static Enumeration getMyLoggerNames() {
		return MyLoggers.keys();
	}

	public static void putMyLogger(MyLogger MyLogger) {
		MyLoggers.put(MyLogger.getName(), MyLogger);
	}

	public static void removeMyLogger(MyLogger MyLogger) {
		MyLoggers.remove(MyLogger.getName());
	}

	public static String throwableToString(Throwable t) {
		return throwableToString(t, "Root cause:");
	}

	public static String throwableToString(Throwable t, String rootcause) {
		if (rootcause == null)
			rootcause = "Root Cause:";
		StringWriter sw = new StringWriter();
		PrintWriter w = new PrintWriter(sw);
		printThrowable(w, t, rootcause);
		w.flush();
		return sw.toString();
	}

	private static void printThrowable(PrintWriter w, Throwable t,
			String rootcause) {
		if (t != null) {
			t.printStackTrace(w);
			if (t instanceof ServletException) {
				Throwable cause = ((ServletException) t).getRootCause();
				if (cause != null) {
					w.println(rootcause);
					printThrowable(w, cause, rootcause);
				}
			} else if (t instanceof SQLException) {
				SQLException sql = ((SQLException) t).getNextException();
				if (sql != null) {
					w.println("Next SQL Exception:");
					printThrowable(w, ((Throwable) (sql)), rootcause);
				}
			} else if (t instanceof SAXException) {
				Throwable embedded = ((SAXException) t).getException();
				if (embedded != null) {
					w.println("Embedded SAX Exception:");
					printThrowable(w, embedded, rootcause);
				}
			}
		}
	}

	public static boolean canIgnore(Throwable t) {
		String msg = t.getMessage();
		if (t instanceof InterruptedIOException)
			return true;
		if (t instanceof IOException) {
			if ("Broken pipe".equals(msg))
				return true;
		} else if (t instanceof SocketException)
			return true;
		return false;
	}

	public boolean isOpen() {
		return sink != null;
	}

	public final void log(String message) {
		log(message, 3);
	}

	public final void log(String message, int verbosityLevel) {
		log(message, null, verbosityLevel);
	}

	public final void log(String message, Throwable t) {
		log(message, t, 1);
	}

	public final void log(String message, Throwable t, int verbosityLevel) {
		if (matchVerbosityLevel(verbosityLevel))
			if (t == null)
				realLog(message);
			else
				realLog(message, t);
	}

	public boolean matchVerbosityLevel(int verbosityLevel) {
		return verbosityLevel <= getVerbosityLevel();
	}

	protected abstract void realLog(String s);

	protected abstract void realLog(String s, Throwable throwable);

	public abstract void flush();

	public synchronized void close() {
		sink = null;
		MyLoggers.remove(getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		putMyLogger(this);
	}

	public void setPath(String path) {
		if (File.separatorChar == '/')
			this.path = path.replace('\\', '/');
		else if (File.separatorChar == '\\')
			this.path = path.replace('/', '\\');
	}

	public String getPath() {
		return path;
	}

	public String toString() {
		return "MyLogger(" + getName() + ", " + getPath() + ")";
	}

	public void open() {
		if (path == null)
			return;
		try {
			File file = new File(path);
			if (!file.exists())
				(new File(file.getParent())).mkdirs();
			sink = new FileWriter(path);
		} catch (IOException ex) {
			System.err.print("Unable to open log file: " + path + "! ");
			System.err.println(" Using stderr as the default.");
			sink = defaultSink;
		}
	}

	public void setVerbosityLevel(String level) {
		if ("warning".equalsIgnoreCase(level))
			this.level = 2;
		else if ("fatal".equalsIgnoreCase(level))
			this.level = -2147483648;
		else if ("error".equalsIgnoreCase(level))
			this.level = 1;
		else if ("information".equalsIgnoreCase(level))
			this.level = 3;
		else if ("debug".equalsIgnoreCase(level))
			this.level = 4;
	}

	public void setVerbosityLevel(int level) {
		this.level = level;
	}

	public int getVerbosityLevel() {
		return level;
	}

	public void setTimestamp(String value) {
		if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
			timestamp = true;
		else if ("false".equalsIgnoreCase(value)
				|| "no".equalsIgnoreCase(value))
			timestamp = false;
	}

	public boolean isTimestamp() {
		return timestamp;
	}

	public void setTimestampFormat(String value) {
		if (value.equalsIgnoreCase("msec")) {
			timestampRaw = true;
		} else {
			timestampRaw = false;
			timestampFormat = value;
			timestampFormatter = new MyFastDateFormat(new SimpleDateFormat(
					timestampFormat));
		}
	}

	public String getTimestampFormat() {
		if (timestampRaw)
			return "msec";
		else
			return timestampFormat;
	}

	public void setCustomOutput(String value) {
		if ("true".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value))
			custom = true;
		else if ("false".equalsIgnoreCase(value)
				|| "no".equalsIgnoreCase(value))
			custom = false;
	}

	protected String formatTimestamp(long msec) {
		StringBuffer buf = new StringBuffer();
		formatTimestamp(msec, buf);
		return buf.toString();
	}

	protected void formatTimestamp(long msec, StringBuffer buf) {
		if (!timestamp)
			return;
		if (timestampRaw) {
			buf.append(Long.toString(msec));
			return;
		} else {
			Date d = new Date(msec);
			timestampFormatter.format(d, buf, position);
			return;
		}
	}

	public static final int FATAL = -2147483648;
	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFORMATION = 3;
	public static final int DEBUG = 4;
	protected static Writer defaultSink;
	protected static Hashtable MyLoggers = new Hashtable(5);
	protected static MyLogger defaultMyLogger;
	protected boolean custom;
	protected Writer sink;
	String path;
	protected String name;
	private int level;
	protected boolean timestamp;
	protected boolean timestampRaw;
	protected String timestampFormat;
	protected DateFormat timestampFormatter;
	private static FieldPosition position = new FieldPosition(1);

	static {
		defaultSink = new OutputStreamWriter(System.err);
		defaultMyLogger = new DefaultMyLogger();
		defaultMyLogger.setVerbosityLevel(4);
	}
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\My Eclipse\GasInformation3.0\WebContent\WEB-INF\lib\jasper-runtime.jar
	Total time: 62 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/