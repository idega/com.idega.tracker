package com.idega.tracker.business;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.transaction.TransactionManager;

import com.idega.core.builder.business.BuilderService;
import com.idega.core.builder.business.BuilderServiceFactory;
import com.idega.core.builder.data.ICDomain;
import com.idega.core.localisation.business.ICLocaleBusiness;
import com.idega.data.IDOLegacyEntity;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.idegaweb.IWApplicationContext;
import com.idega.idegaweb.IWCacheManager;
import com.idega.presentation.IWContext;
import com.idega.tracker.data.DomainStatistics;
import com.idega.tracker.data.DomainStatisticsHome;
import com.idega.tracker.data.PageStatistics;
import com.idega.tracker.data.PageStatisticsHome;
import com.idega.tracker.data.PageTotalStatistics;
import com.idega.tracker.data.PageTotalStatisticsHome;
import com.idega.tracker.data.ReferrerStatistics;
import com.idega.tracker.data.ReferrerStatisticsHome;
import com.idega.tracker.data.UserAgentStatistics;
import com.idega.tracker.data.UserAgentStatisticsHome;
import com.idega.transaction.IdegaTransactionManager;
import com.idega.util.IWTimestamp;

//synchronized
//unsynchronized
//unsynchronized

/**
 * Title: is.idega.idegaweb.tracker.business.TrackerBusiness Description: The
 * main business object for the idegaWeb Tracker statistics application
 * Copyright: Copyright (c) 2002 Company: Idega software
 * 
 * @author Eirikur S. Hrafnsson
 * @version 1.0
 */

public class TrackerBusiness {

	public static final String IW_BUNDLE_IDENTIFIER = "com.idega.tracker";

	private static IWCacheManager cm;

	private static ICDomain domainEntity;

	private static String TR_USER_AGENT_KEY = "tr.ua";

	private static String TR_PAGE_CACHE_KEY = "tr.pg";

	private static String TR_PAGE_TOTAL_CACHE_KEY = "tr.pgt";

	private static String TR_REFERRER_CACHE_KEY = "tr.re";

	private static String TR_DOMAIN_CACHE_KEY = "tr.do";

	private static Map pages;

	private static Map referers;

	private static Map agents;

	private static Map pageSessions;

	private static Map pageHits;

	private static Map sessions;

	private static int totalHits = 0;

	private static int totalSessions = 0;

	private static String domainName;

	private static boolean notWritingToDB = true;

	public TrackerBusiness() {
	}

	private static void init(IWContext iwc) {
		try {
			if (cm == null)
				cm = IWCacheManager.getInstance(iwc.getIWMainApplication());
			if (domainEntity == null)
				domainEntity = getBuilderService(iwc).getCurrentDomain();
			if (pages == null) {
				pages = new HashMap();
			}
			if (agents == null) {
				agents = new Hashtable();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	protected static BuilderService getBuilderService(IWApplicationContext iwac)
			throws RemoteException {
		return BuilderServiceFactory.getBuilderService(iwac);
	}

	private static void incrementPageHits(String pageId) {
		if (pageHits == null) {
			pageHits = new Hashtable();
		}
		Integer hits = (Integer) pageHits.get(pageId);
		pageHits.put(pageId, getIncrementedInteger(hits));
	}

	private static void incrementPageSessions(String pageId) {
		if (pageSessions == null) {
			pageSessions = new Hashtable();
		}
		Integer hits = (Integer) pageSessions.get(pageId);
		pageSessions.put(pageId, getIncrementedInteger(hits));
	}

	private static Integer getIncrementedInteger(Integer i) {
		if (i == null)
			i = new Integer(1);
		else {
			i = new Integer(i.intValue() + 1);
		}
		return i;
	}

	public static void runThroughTheStatsMachine(IWContext iwc) throws IDOLookupException, CreateException {
		if (notWritingToDB) {
			init(iwc);
			handlePageStats(iwc);
			handleSessionsRelated(iwc);
		}
	}

	public static void handlePageStats(IWContext iwc)
			throws IDOLookupException, CreateException {
		int pageId = getCurrentPageId(iwc);
		String sPageId = String.valueOf(pageId);
		String sessionId = iwc.getSession().getId();

		if (pageId != -1) {
			PageStatistics page;

			page = ((PageStatisticsHome) IDOLookup
					.getHome(PageStatistics.class)).create();

			page.setPageId(pageId);
			page.setPreviousPageId(pageId);
			/** @todo this shit here* */
			page.setLocale(iwc.getCurrentLocaleId());
			page.setUserId(iwc.getUserId());
			page.setModificationDate(IWTimestamp.getTimestampRightNow());
			page.setGenerationTime(200);
			/** @todo this shit here* */

			ArrayList pageLog = (ArrayList) pages.get(sessionId);
			if (pageLog == null) {
				pageLog = new ArrayList();
				// session stuff
				totalSessions++;
				incrementPageSessions(sPageId);
				/** @todo only counts once!* */
			}

			pageLog.add(page);
			pages.put(sessionId, pageLog);
			// hit stuff
			totalHits++;
			incrementPageHits(sPageId);
		}

	}

	public static void handleReferrerStats(IWContext iwc)
			throws IDOLookupException, CreateException {
		String referer = iwc.getReferer();
		if (domainName == null)
			domainName = iwc.getServerName();

		if (referers == null) {
			referers = new Hashtable();
		}

		if ((referer != null) && (referer.indexOf(domainName) == -1)) {
			ReferrerStatistics stats = (ReferrerStatistics) referers
					.get(referer);
			if (stats == null) {
				stats = ((ReferrerStatisticsHome) IDOLookup
						.getHome(ReferrerStatistics.class)).create();
				stats.setReferrerUrl(referer);
				stats.setSessions(1);
				stats.setModificationDate(IWTimestamp.getTimestampRightNow());
				referers.put(stats.getReferrerUrl(), stats);
			} else {
				stats.setSessions(stats.getSessions() + 1);
			}
		}
	}

	public static ArrayList getRefererArrayList() {
		ArrayList list = new ArrayList();

		if (referers != null) {
			Iterator iter = referers.keySet().iterator();
			while (iter.hasNext()) {
				ReferrerStatistics item = (ReferrerStatistics) referers
						.get((String) iter.next());
				list.add(item);
			}
		}

		return list;
	}

	public static ArrayList getPageHitsArrayList() {
		ArrayList list = new ArrayList();
		try {
			if (pageHits != null) {
				PageStatisticsHome rhome = (PageStatisticsHome) IDOLookup
						.getHome(PageStatistics.class);
				Iterator iter = pageHits.keySet().iterator();
				String key;
				while (iter.hasNext()) {
					key = (String) iter.next();
					PageStatistics page = rhome.create();
					page.setPageId(Integer.parseInt(key));
					page.setHits(((Integer) pageHits.get(key)).intValue());
					list.add(page);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return list;
	}

	public static ArrayList getPageHitsArrayListSortedByHits() {
		PageComparator comparer = new PageComparator(
				PageComparator.ORDER_BY_HITS);
		return comparer.sortedArrayList(getPageHitsArrayList());
	}

	public static ArrayList getPageHitsArrayListReverseSortedByHits() {
		PageComparator comparer = new PageComparator(
				PageComparator.REVERSE_ORDER_BY_HITS);
		return comparer.sortedArrayList(getPageHitsArrayList());
	}

	public static ArrayList getPageHitsArrayListSortedByURL() {
		PageComparator comparer = new PageComparator(
				PageComparator.ORDER_BY_PAGE_NAME);
		return comparer.sortedArrayList(getPageHitsArrayList());
	}

	public static ArrayList getRefererArrayListSortedBySessions() {
		RefererComparator comparer = new RefererComparator(
				RefererComparator.ORDER_BY_SESSIONS);
		return comparer.sortedArrayList(getRefererArrayList());
	}

	public static ArrayList getRefererArrayListReverseSortedBySessions() {
		RefererComparator comparer = new RefererComparator(
				RefererComparator.REVERSE_ORDER_BY_SESSIONS);
		return comparer.sortedArrayList(getRefererArrayList());
	}

	public static ArrayList getRefererArrayListSortedByURL() {
		RefererComparator comparer = new RefererComparator(
				RefererComparator.ORDER_BY_URL);
		return comparer.sortedArrayList(getRefererArrayList());
	}

	public static ArrayList getAgentArrayList() {
		ArrayList list = new ArrayList();

		if (agents != null) {
			Iterator iter = agents.keySet().iterator();
			while (iter.hasNext()) {
				UserAgentStatistics item = (UserAgentStatistics) agents
						.get((String) iter.next());
				list.add(item);
			}
		}
		return list;
	}

	public static ArrayList getAgentArrayListSortedBySessions() {
		AgentComparator comparer = new AgentComparator(
				AgentComparator.ORDER_BY_SESSIONS);
		return comparer.sortedArrayList(getAgentArrayList());
	}

	public static ArrayList getAgentArrayListReverseSortedBySessions() {
		AgentComparator comparer = new AgentComparator(
				AgentComparator.REVERSE_ORDER_BY_SESSIONS);
		return comparer.sortedArrayList(getAgentArrayList());
	}

	public static ArrayList getAgentArrayListSortedByAgent() {
		AgentComparator comparer = new AgentComparator(
				AgentComparator.ORDER_BY_AGENT);
		return comparer.sortedArrayList(getAgentArrayList());
	}

	public static void handleUserAgentStats(IWContext iwc)
			throws IDOLookupException, CreateException {
		String userAgent = iwc.getUserAgent();
		if (agents == null) {
			agents = new Hashtable();
		}

		if (userAgent != null) {
			UserAgentStatistics stats = (UserAgentStatistics) agents
					.get(userAgent);
			if (stats == null) {
				stats = ((UserAgentStatisticsHome) com.idega.data.IDOLookup
						.getHome(UserAgentStatistics.class)).create();
				stats.setUserAgent(userAgent);
				stats.setSessions(1);
				stats.setModificationDate(IWTimestamp.getTimestampRightNow());
				agents.put(stats.getUserAgent(), stats);
			} else {
				stats.setSessions(stats.getSessions() + 1);
			}
		}
	}

	public static int getCurrentPageId(IWContext iwc) {
		int returner = -1;
		try {
			BuilderService service = getBuilderService(iwc.getApplicationContext());
			returner = service.getCurrentPageId(iwc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return returner;
	}

	public static int getCurrentPageHits(IWContext iwc) {
		if (pageHits == null) {
			pageHits = new Hashtable();
		}

		Integer hits = (Integer) pageHits.get(String.valueOf(getCurrentPageId(iwc)));
		if (hits == null)
			return 0;
		else
			return hits.intValue();
	}

	public static int getCurrentPageSessions(IWContext iwc) {
		if (pageSessions == null) {
			pageSessions = new Hashtable();
		}

		Integer sessions = (Integer) pageSessions.get(String
				.valueOf(getCurrentPageId(iwc)));
		if (sessions == null)
			return 0;
		else
			return sessions.intValue();
	}

	private static void handleSessionsRelated(IWContext iwc)
			throws IDOLookupException, CreateException {
		if (sessions == null) {
			sessions = new Hashtable();
		}
		String sId = iwc.getSessionId();
		if (sessions.get(sId) == null) {
			handleReferrerStats(iwc);
			handleUserAgentStats(iwc);
		}
		sessions.put(sId, sId);
	}

	public static int getTotalHits() {
		return totalHits;
	}

	public static int getTotalSessions() {
		return totalSessions;
	}

	public static Map getReferers() {
		return referers;
	}

	public static Map getUserAgents() {
		return agents;
	}

	// ********** save to database functions **************//

	public static void saveStatsToDB() {
		notWritingToDB = false;// stop updating for a while
		/*
		 * saveMapToDB(5); saveMapToDB(referers); saveMapToDB(agents);
		 * 
		 * pageSessions; pageHits;
		 * 
		 * 
		 * domain; totalHits; totalSessions;
		 * 
		 * 
		 */

		notWritingToDB = true;// start updating again
	}

	private static void saveMapToDB(Map stats) {
		if (stats != null) {

			TransactionManager t = IdegaTransactionManager.getInstance();
			try {
				t.begin();
				Iterator iter = stats.keySet().iterator();
				while (iter.hasNext()) {
					IDOLegacyEntity item = (IDOLegacyEntity) iter.next();
					item.insert();
				}
				t.commit();
			} catch (Exception e) {
				e.printStackTrace(System.err);

				try {
					t.rollback();
				} catch (Exception e1) {
					e1.printStackTrace(System.err);
				}

			}
		}

	}

	private static void calculateAndInsertTotalPageAndDomainStats(IWContext iwc)
			throws IDOLookupException, CreateException {
		HashMap totalPage = new HashMap();
		HashMap totalDomain = new HashMap();

		Vector locales = (Vector) iwc.getIWMainApplication()
				.getAvailableLocales();
		Iterator iter = locales.iterator();

		while (iter.hasNext()) {
			Locale item = (Locale) iter.next();
			int localeId = ICLocaleBusiness.getLocaleId(item);
			PageTotalStatistics stats = ((PageTotalStatisticsHome) com.idega.data.IDOLookup
					.getHome(PageTotalStatistics.class)).create();// virkar
			// ekki
			// svona
			DomainStatistics stats2 = ((DomainStatisticsHome) com.idega.data.IDOLookup
					.getHomeLegacy(DomainStatistics.class)).create();

			totalPage.put(new Integer(localeId), stats);
			totalDomain.put(new Integer(localeId), stats2);
		}

	}

}
