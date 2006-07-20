package com.idega.tracker.data;


import java.sql.Timestamp;
import com.idega.data.IDOEntity;

public interface DomainStatistics extends IDOEntity {
	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#getDomainId
	 */
	public int getDomainId();

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#getLocale
	 */
	public int getLocale();

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#getHits
	 */
	public int getHits();

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#getSessions
	 */
	public int getSessions();

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#getDate
	 */
	public Timestamp getDate();

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#setDomainId
	 */
	public void setDomainId(int domainId);

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#setLocale
	 */
	public void setLocale(int locale);

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#setHits
	 */
	public void setHits(int hits);

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#setSessions
	 */
	public void setSessions(int sessionHits);

	/**
	 * @see com.idega.tracker.data.DomainStatisticsBMPBean#setModificationDate
	 */
	public void setModificationDate(Timestamp date);
}