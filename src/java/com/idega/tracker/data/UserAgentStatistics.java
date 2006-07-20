package com.idega.tracker.data;


import java.sql.Timestamp;
import com.idega.data.IDOEntity;

public interface UserAgentStatistics extends IDOEntity {
	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#getUserAgent
	 */
	public String getUserAgent();

	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#getSessions
	 */
	public int getSessions();

	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#getDate
	 */
	public Timestamp getDate();

	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#setUserAgent
	 */
	public void setUserAgent(String userAgentString);

	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#setSessions
	 */
	public void setSessions(int sessionHits);

	/**
	 * @see com.idega.tracker.data.UserAgentStatisticsBMPBean#setModificationDate
	 */
	public void setModificationDate(Timestamp date);
}