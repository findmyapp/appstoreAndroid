package no.uka.findmyapp.appstoreAndroid.models;

import java.io.Serializable;
import java.util.Date;

public class App implements Serializable{
	private int id;
	private Date publishDate;
	private String publisher;
	private String name;
	private int timesDownloaded;
	private int rating;
	private String androidMarketUri;
	private double versionNumber;
	
	@Override
	public String toString() {
		return "App [publishDate=" + publishDate + ", publisher=" + publisher
				+ ", appName=" + name + ", timesDownloaded="
				+ timesDownloaded + ", androidMarketURL=" + androidMarketUri
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTimesDownloaded() {
		return timesDownloaded;
	}
	public void setTimesDownloaded(int timesDownloaded) {
		this.timesDownloaded = timesDownloaded;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public String getAndroidMarketUri() {
		return androidMarketUri;
	}
	public void setAndroidMarketUri(String androidMarketUri) {
		this.androidMarketUri = androidMarketUri;
	}
	public double getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(double versionNumber) {
		this.versionNumber = versionNumber;
	}
	
}
