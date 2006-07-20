package com.idega.tracker.data;


import javax.ejb.CreateException;
import com.idega.data.IDOHome;
import javax.ejb.FinderException;

public interface UserAgentStatisticsHome extends IDOHome {
	public UserAgentStatistics create() throws CreateException;

	public UserAgentStatistics findByPrimaryKey(Object pk) throws FinderException;
}