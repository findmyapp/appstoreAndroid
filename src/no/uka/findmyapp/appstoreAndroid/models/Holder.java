package no.uka.findmyapp.appstoreAndroid.models;

import com.google.gson.annotations.SerializedName;

public class Holder {
	@SerializedName("Foo")
	private AppStoreList appstore;

	public AppStoreList getAppstore() {
		return appstore;
	}

	public void setAppstore(AppStoreList appstore) {
		this.appstore = appstore;
	}
	
}
