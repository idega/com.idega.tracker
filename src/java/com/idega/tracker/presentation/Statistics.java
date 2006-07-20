/*
 * $Id: Statistics.java,v 1.1 2006/07/20 16:49:32 eiki Exp $
 * Created on Jul 10, 2006
 *
 * Copyright (C) 2006 Idega Software hf. All Rights Reserved.
 *
 * This software is the proprietary information of Idega hf.
 * Use is subject to license terms.
 */
package com.idega.tracker.presentation;

import com.idega.presentation.Block;
import com.idega.presentation.IWContext;
import com.idega.tracker.business.TrackerBusiness;

public class Statistics extends Block {

	public Statistics() {
	}

	public void main(IWContext iwc) throws Exception {
		PageCounter counter = new PageCounter();
		counter.setShowCurrentPageHits(false);
		counter.setShowCurrentPageSessions(false);
		counter.setShowTotalHits(true);
		counter.setShowTotalSessions(true);
		counter.setUpdateStats(false);
		counter.setShowAgents(true);
		counter.setShowReferers(true);
		counter.setShowPageList(true);

		add(counter);
	}

	public String getBundleIdentifier() {
		return TrackerBusiness.IW_BUNDLE_IDENTIFIER;
	}

}
