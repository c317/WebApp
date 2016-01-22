///* FrontierScheduler
// * 
// * $Id: FrontierScheduler.java 4671 2006-09-26 23:47:15Z paul_jack $
// *
// * Created on June 6, 2005
// * 
// * Copyright (C) 2005 Internet Archive.
// *
// * This file is part of the Heritrix web crawler (crawler.archive.org).
// *
// * Heritrix is free software; you can redistribute it and/or modify
// * it under the terms of the GNU Lesser Public License as published by
// * the Free Software Foundation; either version 2.1 of the License, or
// * any later version.
// *
// * Heritrix is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Lesser Public License for more details.
// *
// * You should have received a copy of the GNU Lesser Public License
// * along with Heritrix; if not, write to the Free Software
// * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
// *
// */
//package org.archive.crawler.postprocessor;
//
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import org.archive.crawler.datamodel.CandidateURI;
//import org.archive.crawler.datamodel.CrawlURI;
//import org.archive.crawler.datamodel.FetchStatusCodes;
//import org.archive.crawler.framework.Processor;
//
///**
// * 'Schedule' with the Frontier CandidateURIs being carried by the passed
// * CrawlURI.
// * Adds either prerequisites or whatever is in CrawlURI outlinks to the
// * Frontier.  Run a Scoper ahead of this processor so only links that
// * are in-scope get scheduled.
// * @author stack
// */
//public class FrontierScheduler extends Processor
//implements FetchStatusCodes {
//
//    private static final long serialVersionUID = -5178775477602250542L;
//
//    private static Logger LOGGER =
//        Logger.getLogger(FrontierScheduler.class.getName());
//    
//    /**
//     * @param name Name of this filter.
//     */
//    public FrontierScheduler(String name) {
//        super(name, "FrontierScheduler. 'Schedule' with the Frontier " +
//            "any CandidateURIs carried by the passed CrawlURI. " +
//            "Run a Scoper before this " +
//            "processor so links that are not in-scope get bumped from the " +
//            "list of links (And so those in scope get promoted from Link " +
//            "to CandidateURI).");
//    }
//
//    protected void innerProcess(final CrawlURI curi) {
//        if (LOGGER.isLoggable(Level.FINEST)) {
//            LOGGER.finest(getName() + " processing " + curi);
//        }
//        
//        // Handle any prerequisites when S_DEFERRED for prereqs
//        if (curi.hasPrerequisiteUri() && curi.getFetchStatus() == S_DEFERRED) {
//            handlePrerequisites(curi);
//            return;
//        }
//
//        synchronized(this) {
//            for (CandidateURI cauri: curi.getOutCandidates()) {
//                schedule(cauri);
//            }
//        }
//    }
//
//    protected void handlePrerequisites(CrawlURI curi) {
//        schedule((CandidateURI)curi.getPrerequisiteUri());
//    }
//
//    /**
//     * Schedule the given {@link CandidateURI CandidateURI} with the Frontier.
//     * @param caUri The CandidateURI to be scheduled.
//     */
//    protected void schedule(CandidateURI caUri) {
//    	String url = caUri.toString();
//    	System.out.println("------" + url);
//    	if(url.endsWith(".jpeg")
//    			||url.endsWith(".jpg")
//    			||url.endsWith(".JPEG")
//    			||url.endsWith(".JPG")
//    			||url.endsWith(".JSP")
//    			||url.endsWith(".jsp")
//    			||url.endsWith(".ppt")
//    			||url.endsWith(".pptx")
//    			||url.endsWith(".gif")
//    			||url.endsWith(".css")
//    			||url.endsWith(".doc")
//    			||url.endsWith(".docx")
//    			||url.endsWith(".zip")
//    			||url.endsWith(".png")
//    			||url.endsWith(".js")
//    			||url.endsWith(".swf")  
//    			||url.endsWith(".xml")
//    			||url.endsWith(".xlsx")
//    			||url.endsWith(".pdf")
//    			||url.endsWith(".xls")
//    			||url.endsWith(".rar")
//    			||url.endsWith(".exe")
//    			||url.endsWith(".txt")
//    			||url.endsWith(".mp4")
//    			||url.endsWith(".wmv")
//    			||url.endsWith(".mp3")
//    			||url.endsWith("1")
//    			||url.endsWith("2")
//    			||url.endsWith("3")
//    			||url.endsWith("4")
//    			||url.endsWith("5")
//    			||url.endsWith("6")
//    			||url.endsWith("7")
//    			||url.endsWith("8")
//    			||url.endsWith("9")
//    			||url.endsWith("0")
//    	
//    			){
//    		return;
//    	}
//        getController().getFrontier().schedule(caUri);
//    }
//}
/* FrontierScheduler
 * 
 * $Id: FrontierScheduler.java 4671 2006-09-26 23:47:15Z paul_jack $
 *
 * Created on June 6, 2005
 * 
 * Copyright (C) 2005 Internet Archive.
 *
 * This file is part of the Heritrix web crawler (crawler.archive.org).
 *
 * Heritrix is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * any later version.
 *
 * Heritrix is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU Lesser Public License
 * along with Heritrix; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package org.archive.crawler.postprocessor;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.archive.crawler.datamodel.CandidateURI;
import org.archive.crawler.datamodel.CrawlURI;
import org.archive.crawler.datamodel.FetchStatusCodes;
import org.archive.crawler.framework.Processor;

/**
 * 'Schedule' with the Frontier CandidateURIs being carried by the passed
 * CrawlURI. Adds either prerequisites or whatever is in CrawlURI outlinks to
 * the Frontier. Run a Scoper ahead of this processor so only links that are
 * in-scope get scheduled.
 * 
 * @author stack
 */
public class FrontierScheduler extends Processor implements FetchStatusCodes {

	private static final long serialVersionUID = -5178775477602250542L;

	// 杨鼎20150717
	private static final int YEAR = Calendar.getInstance().get(Calendar.YEAR);
	private static final String[] arrYEAR = new String[] {
			String.valueOf(YEAR - 1), String.valueOf(YEAR - 2),
			String.valueOf(YEAR - 3), String.valueOf(YEAR - 4),
			String.valueOf(YEAR - 5), String.valueOf(YEAR - 6),
			String.valueOf(YEAR - 7), String.valueOf(YEAR - 8),
			String.valueOf(YEAR - 9), String.valueOf(YEAR - 10) };
	private static final int MONTH = Calendar.getInstance().get(Calendar.MONTH);
	private static Logger LOGGER = Logger.getLogger(FrontierScheduler.class
			.getName());

	/**
	 * @param name
	 *            Name of this filter.
	 */
	public FrontierScheduler(String name) {
		super(
				name,
				"FrontierScheduler. 'Schedule' with the Frontier "
						+ "any CandidateURIs carried by the passed CrawlURI. "
						+ "Run a Scoper before this "
						+ "processor so links that are not in-scope get bumped from the "
						+ "list of links (And so those in scope get promoted from Link "
						+ "to CandidateURI).");
	}

	protected void innerProcess(final CrawlURI curi) {
		if (LOGGER.isLoggable(Level.FINEST)) {
			LOGGER.finest(getName() + " processing " + curi);
		}

		// Handle any prerequisites when S_DEFERRED for prereqs
		if (curi.hasPrerequisiteUri() && curi.getFetchStatus() == S_DEFERRED) {
			handlePrerequisites(curi);
			return;
		}

		synchronized (this) {
			for (CandidateURI cauri : curi.getOutCandidates()) {
				schedule(cauri);
			}
		}
	}

	protected void handlePrerequisites(CrawlURI curi) {
		schedule((CandidateURI) curi.getPrerequisiteUri());
	}

	/**
	 * Schedule the given {@link CandidateURI CandidateURI} with the Frontier.
	 * 
	 * @param caUri
	 *            The CandidateURI to be scheduled.
	 */
	protected void schedule(CandidateURI caUri) {
		String url = caUri.toString();
		System.out.println("------" + url);

		if ((url.contains(arrYEAR[0])) || url.contains(arrYEAR[1])
				|| url.contains(arrYEAR[2]) || url.contains(arrYEAR[3])
				|| url.contains(arrYEAR[4]) || url.contains(arrYEAR[5])
				|| url.contains(arrYEAR[6]) || url.contains(arrYEAR[7])
				|| url.contains(arrYEAR[8]) || url.contains(arrYEAR[9])
				|| url.endsWith(".jpeg") || url.endsWith(".jpg")
				|| url.endsWith(".JPEG") || url.endsWith(".JPG")
				|| url.endsWith(".JSP") || url.endsWith(".jsp")
				|| url.endsWith(".ppt") || url.endsWith(".pptx")
				|| url.endsWith(".gif") || url.endsWith(".css")
				|| url.endsWith(".doc") || url.endsWith(".docx")
				|| url.endsWith(".zip") || url.endsWith(".png")
				|| url.endsWith(".js") || url.endsWith(".swf")
				|| url.endsWith(".xml") || url.endsWith(".xlsx")
				|| url.endsWith(".pdf") || url.endsWith(".xls")
				|| url.endsWith(".rar") || url.endsWith(".exe")
				|| url.endsWith(".txt") || url.endsWith(".mp4")
				|| url.endsWith(".wmv") || url.endsWith(".mp3")
				|| url.endsWith(".flv")|| url.endsWith(".mpg")
				|| url.endsWith("1") || url.endsWith("2") || url.endsWith("3")
				|| url.endsWith("4") || url.endsWith("5") || url.endsWith("6")
				|| url.endsWith("7") || url.endsWith("8") || url.endsWith("9")
				|| url.endsWith("0")) {
			return;
		}
		getController().getFrontier().schedule(caUri);
	}

//	private String[] getCurrentTwoMonth(int MONTH) {
////    	String[] nCrawlMonth=null;
////    	if(MONTH>2){
////    		for(int i=1; && i<=MONTH-2 ;i++) {
////    			nCrawlMonth[i]=String.valueOf(i);
////    		}
////    	}
////    	else if(MONTH==1) {
////    		
////    	}
////		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
////		if(MONTH==1) {
////			String[]NotCrawlMonth={"2","3","4","5","6","7","8","9","10","11","12"}; 
////			} 
////		else if(MONTH==2) {
////				String[]NotCrawlMonth={"1","3","4","5","6","7","8","9","10","11","12"};
////			}
////		else if(MONTH>2) {
////			for(int i=0;i<) {}
////		}
//    }
}

