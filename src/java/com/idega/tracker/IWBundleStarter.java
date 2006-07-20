package com.idega.tracker;

import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWBundleStartable;


/**
 * <p>
 * TODO tryggvil Describe Type IWBundleStarter
 * </p>
 *  Last modified: $Date: 2006/07/20 16:49:32 $ by $Author: eiki $
 * 
 * @author <a href="mailto:tryggvil@idega.com">tryggvil</a>
 * @version $Revision: 1.1 $
 */
public class IWBundleStarter implements IWBundleStartable{

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#start(com.idega.idegaweb.IWBundle)
	 */
	public void start(IWBundle starterBundle) {
		TrackerViewManager viewMan = TrackerViewManager.getInstance(starterBundle.getApplication());
		viewMan.getTrackerViewNode();
	}

	/* (non-Javadoc)
	 * @see com.idega.idegaweb.IWBundleStartable#stop(com.idega.idegaweb.IWBundle)
	 */
	public void stop(IWBundle starterBundle) {		
	}
	
}
