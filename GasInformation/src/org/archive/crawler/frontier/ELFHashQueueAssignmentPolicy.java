package org.archive.crawler.frontier;

import com.archive.crawler.ELFHash.MyLogger;
//import org.apache.taglibs.standard.lang.jstl.Logger;
import org.archive.crawler.datamodel.CandidateURI;
import org.archive.crawler.framework.CrawlController;

public class ELFHashQueueAssignmentPolicy extends QueueAssignmentPolicy {
	private static final MyLogger mylogger = MyLogger
			.getMyLogger(ELFHashQueueAssignmentPolicy.class.getName());

	public String getClassKey(CrawlController controller,

	CandidateURI cauri) {

		String uri = cauri.getUURI().toString();

		long hash = ELFHash(uri);

		String a = Long.toString(hash % 100);

		return a;

	}

	public long ELFHash(String str) {

		long hash = 0;

		long x = 0;

		for (int i = 0; i < str.length(); i++) {

			hash = (hash << 4) + str.charAt(i);

			if ((x = hash & 0xF0000000L) != 0) {

				hash ^= (x >> 24);

				hash &= ~x;

			}

		}

		return (hash & 0x7FFFFFFF);

	}
}
