package com.idega.tracker.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class ReferrerStatisticsHomeImpl extends IDOFactory implements ReferrerStatisticsHome {
	public Class getEntityInterfaceClass() {
		return ReferrerStatistics.class;
	}

	public ReferrerStatistics create() throws CreateException {
		return (ReferrerStatistics) super.createIDO();
	}

	public ReferrerStatistics findByPrimaryKey(Object pk) throws FinderException {
		return (ReferrerStatistics) super.findByPrimaryKeyIDO(pk);
	}
}