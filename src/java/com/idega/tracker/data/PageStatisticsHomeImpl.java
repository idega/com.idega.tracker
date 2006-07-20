package com.idega.tracker.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class PageStatisticsHomeImpl extends IDOFactory implements PageStatisticsHome {
	public Class getEntityInterfaceClass() {
		return PageStatistics.class;
	}

	public PageStatistics create() throws CreateException {
		return (PageStatistics) super.createIDO();
	}

	public PageStatistics findByPrimaryKey(Object pk) throws FinderException {
		return (PageStatistics) super.findByPrimaryKeyIDO(pk);
	}
}