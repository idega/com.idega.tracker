package com.idega.tracker.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class PageTotalStatisticsHomeImpl extends IDOFactory implements PageTotalStatisticsHome {
	public Class getEntityInterfaceClass() {
		return PageTotalStatistics.class;
	}

	public PageTotalStatistics create() throws CreateException {
		return (PageTotalStatistics) super.createIDO();
	}

	public PageTotalStatistics findByPrimaryKey(Object pk) throws FinderException {
		return (PageTotalStatistics) super.findByPrimaryKeyIDO(pk);
	}
}