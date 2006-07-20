package com.idega.tracker.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface ReferrerStatisticsHome extends IDOHome {
	public ReferrerStatistics create() throws CreateException;

	public ReferrerStatistics findByPrimaryKey(Object pk) throws FinderException;
}