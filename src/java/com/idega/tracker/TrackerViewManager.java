package com.idega.tracker;

import java.util.ArrayList;
import java.util.Collection;

import com.idega.core.view.DefaultViewNode;
import com.idega.core.view.KeyboardShortcut;
import com.idega.core.view.ViewManager;
import com.idega.core.view.ViewNode;
import com.idega.idegaweb.IWBundle;
import com.idega.idegaweb.IWMainApplication;
import com.idega.tracker.presentation.Statistics;
import com.idega.workspace.view.WorkspaceApplicationNode;
import com.idega.workspace.view.WorkspaceClassViewNode;


/**
 * A view manager for the Tracker application for viewing statistics and managing website load and caching.
 *  Last modified: $Date: 2006/07/20 16:49:32 $ by $Author: eiki $
 * 
 * @author <a href="mailto:eiki@idega.com">Eirikur S. Hrafnsson</a>
 * @version $Revision: 1.1 $
 */
public class TrackerViewManager {

	public static final String TRACKER_BUNDLE_IDENTIFIER = "com.idega.tracker";
	public static final String ROLE_KEY_TRACKER = "tracker-admin";
	private ViewNode trackerNode;
	private IWMainApplication iwma;
	
	/**
	 * @param iwma
	 * @return
	 */
	public static TrackerViewManager getInstance(IWMainApplication iwma) {
		String TRACKER_VIEW_MANAGER_NAME = "trackerviewmanager";
		TrackerViewManager instance = (TrackerViewManager) iwma.getAttribute(TRACKER_VIEW_MANAGER_NAME);
		if(instance==null){
			instance = new TrackerViewManager();
			instance.iwma=iwma;
			iwma.setAttribute(TRACKER_VIEW_MANAGER_NAME,instance);
		}
		return instance;
	}
	
	public ViewManager getViewManager(){
		return ViewManager.getInstance(this.iwma);
	}
	
	
	public ViewNode getTrackerViewNode(){
		IWBundle iwb = this.iwma.getBundle(TRACKER_BUNDLE_IDENTIFIER);
		if(this.trackerNode==null){
			this.trackerNode = initalizeTrackerNodes(iwb);
		}
		return this.trackerNode;
	}

/**
 * Registers the Tracker "MENU". 
 * @param iwb
 * @return
 */
	protected ViewNode initalizeTrackerNodes(IWBundle iwb) {
		ViewManager viewManager = ViewManager.getInstance(this.iwma);
		ViewNode workspace = viewManager.getWorkspaceRoot();
		
		Collection roles = new ArrayList();
		roles.add(TrackerViewManager.ROLE_KEY_TRACKER);
		
		DefaultViewNode trackerNode = new WorkspaceApplicationNode("tracker",workspace,roles);
		trackerNode.setKeyboardShortcut(new KeyboardShortcut("6"));
		
		WorkspaceClassViewNode statsNode = new WorkspaceClassViewNode("statistics",trackerNode);
		statsNode.setName("Statistics");
		statsNode.setComponentClass(Statistics.class);
		statsNode.setMaximizeBlockVertically(true);
		
		return trackerNode;
	}
	
}
