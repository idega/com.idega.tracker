package com.idega.tracker.data;


import javax.ejb.CreateException;
import javax.ejb.FinderException;
import com.idega.data.IDOFactory;

public class DomainStatisticsHomeImpl extends IDOFactory implements DomainStatisticsHome {
	public Class getEntityInterfaceClass() {
		return DomainStatistics.class;
	}

	public DomainStatistics create() throws CreateException {
		return (DomainStatistics) super.createIDO();
	}

	public DomainStatistics findByPrimaryKey(Object pk) throws FinderException {
		return (DomainStatistics) super.findByPrimaryKeyIDO(pk);
	}
}