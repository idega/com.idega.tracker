package com.idega.tracker.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class UserAgentStatisticsHomeImpl extends IDOFactory implements UserAgentStatisticsHome {
	public Class getEntityInterfaceClass() {
		return UserAgentStatistics.class;
	}

	public UserAgentStatistics create() throws CreateException {
		return (UserAgentStatistics) super.createIDO();
	}

	public UserAgentStatistics findByPrimaryKey(Object pk) throws FinderException {
		return (UserAgentStatistics) super.findByPrimaryKeyIDO(pk);
	}
}