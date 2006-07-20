package com.idega.tracker.data;


import java.sql.Timestamp;
import com.idega.data.IDOEntity;

public interface PageStatistics extends IDOEntity {
	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getPageId
	 */
	public int getPageId();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getPreviousPageId
	 */
	public int getPreviousPageId();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getLocale
	 */
	public int getLocale();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getHits
	 */
	public int getHits();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getSessions
	 */
	public int getSessions();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getUserId
	 */
	public int getUserId();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#getDate
	 */
	public Timestamp getDate();

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setPageId
	 */
	public void setPageId(int pageId);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setPreviousPageId
	 */
	public void setPreviousPageId(int pageId);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setLocale
	 */
	public void setLocale(int locale);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setHits
	 */
	public void setHits(int hits);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setSessions
	 */
	public void setSessions(int sessionHits);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setModificationDate
	 */
	public void setModificationDate(Timestamp date);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setGenerationTime
	 */
	public void setGenerationTime(int milliseconds);

	/**
	 * @see com.idega.tracker.data.PageStatisticsBMPBean#setUserId
	 */
	public void setUserId(int userId);
}