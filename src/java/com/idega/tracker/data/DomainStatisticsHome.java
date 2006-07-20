package com.idega.tracker.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface DomainStatisticsHome extends IDOHome {
	public DomainStatistics create() throws CreateException;

	public DomainStatistics findByPrimaryKey(Object pk) throws FinderException;
}