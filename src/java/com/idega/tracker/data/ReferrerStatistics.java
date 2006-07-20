package com.idega.tracker.data;


import java.sql.Timestamp;
import com.idega.data.IDOEntity;

public interface ReferrerStatistics extends IDOEntity {
	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#getReferrerUrl
	 */
	public String getReferrerUrl();

	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#getSessions
	 */
	public int getSessions();

	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#getDate
	 */
	public Timestamp getDate();

	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#setReferrerUrl
	 */
	public void setReferrerUrl(String referrerUrl);

	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#setSessions
	 */
	public void setSessions(int sessionHits);

	/**
	 * @see com.idega.tracker.data.ReferrerStatisticsBMPBean#setModificationDate
	 */
	public void setModificationDate(Timestamp date);
}