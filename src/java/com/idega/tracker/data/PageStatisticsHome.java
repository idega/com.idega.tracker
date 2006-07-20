package com.idega.tracker.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface PageStatisticsHome extends IDOHome {
	public PageStatistics create() throws CreateException;

	public PageStatistics findByPrimaryKey(Object pk) throws FinderException;
}