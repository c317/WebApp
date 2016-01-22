package com.archive.crawler.ELFHash;

/*jadclipse*/// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.

import java.io.PrintStream;
import java.text.*;
import java.util.Date;

public class MyFastDateFormat extends DateFormat {

	public MyFastDateFormat(DateFormat df) {
		lastSec = -1L;
		sb = new StringBuffer();
		fp = new FieldPosition(8);
		this.df = df;
	}

	public Date parse(String text, ParsePosition pos) {
		return df.parse(text, pos);
	}

	public StringBuffer format(Date date, StringBuffer toAppendTo,
			FieldPosition fieldPosition) {
		long dt = date.getTime();
		long ds = dt / 1000L;
		if (ds != lastSec) {
			sb.setLength(0);
			df.format(date, sb, fp);
			lastSec = ds;
		} else {
			int ms = (int) (dt % 1000L);
			int pos = fp.getEndIndex();
			int begin = fp.getBeginIndex();
			if (pos > 0) {
				if (pos > begin)
					sb.setCharAt(--pos, Character.forDigit(ms % 10, 10));
				ms /= 10;
				if (pos > begin)
					sb.setCharAt(--pos, Character.forDigit(ms % 10, 10));
				ms /= 10;
				if (pos > begin)
					sb.setCharAt(--pos, Character.forDigit(ms % 10, 10));
			}
		}
		toAppendTo.append(sb.toString());
		return toAppendTo;
	}

	public static void main(String args[]) {
		String format = "yyyy-MM-dd HH:mm:ss.SSS";
		if (args.length > 0)
			format = args[0];
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		MyFastDateFormat fdf = new MyFastDateFormat(sdf);
		Date d = new Date();
		d.setTime(1L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(20L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(500L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(543L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(999L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(1050L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(2543L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(12345L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		d.setTime(12340L);
		System.out.println(fdf.format(d) + "\t" + sdf.format(d));
		int reps = 100000;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			d.setTime(System.currentTimeMillis());
			fdf.format(d);
		}

		long elap = System.currentTimeMillis() - start;
		System.out.println("fast: " + elap + " elapsed");
		System.out.println(fdf.format(d));
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			d.setTime(System.currentTimeMillis());
			sdf.format(d);
		}

		long i = System.currentTimeMillis() - start;
		System.out.println("slow: " + i + " elapsed");
		System.out.println(sdf.format(d));
	}

	DateFormat df;
	long lastSec;
	StringBuffer sb;
	FieldPosition fp;
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\My Eclipse\GasInformation3.0\WebContent\WEB-INF\lib\jasper-runtime.jar
	Total time: 31 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/