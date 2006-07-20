package com.idega.tracker.data;


import java.sql.Timestamp;
import com.idega.data.IDOEntity;

public interface PageTotalStatistics extends IDOEntity {
	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getPageId
	 */
	public int getPageId();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getLocale
	 */
	public int getLocale();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getHits
	 */
	public int getHits();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getSessions
	 */
	public int getSessions();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getUserId
	 */
	public int getUserId();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#getDate
	 */
	public Timestamp getDate();

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setPageId
	 */
	public void setPageId(int pageId);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setLocale
	 */
	public void setLocale(int locale);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setHits
	 */
	public void setHits(int hits);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setSessions
	 */
	public void setSessions(int sessionHits);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setModificationDate
	 */
	public void setModificationDate(Timestamp date);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setGenerationTime
	 */
	public void setGenerationTime(int milliseconds);

	/**
	 * @see com.idega.tracker.data.PageTotalStatisticsBMPBean#setUserId
	 */
	public void setUserId(int userId);
}