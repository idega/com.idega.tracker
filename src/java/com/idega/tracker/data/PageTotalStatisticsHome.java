package com.idega.tracker.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface PageTotalStatisticsHome extends IDOHome {
	public PageTotalStatistics create() throws CreateException;

	public PageTotalStatistics findByPrimaryKey(Object pk) throws FinderException;
}