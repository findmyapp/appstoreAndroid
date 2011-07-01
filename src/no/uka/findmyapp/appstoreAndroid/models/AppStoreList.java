package no.uka.findmyapp.appstoreAndroid.models;

import java.util.List;

public class AppStoreList {
	private int listType;
	private int requestCount;
	private int listCount;
	private List<App> appList;
	private int platform;
	
	public AppStoreList() {
		
	}
	
	public AppStoreList(List<App> appList) {
		this.setAppList(appList);
	}
	
	public int getListType() {
		return listType;
	}
	public void setListType(int listType) {
		this.listType = listType;
	}
	public int getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public List<App> getAppList() {
		return appList;
	}
	public void setAppList(List<App> appList) {
		this.appList = appList;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
}
