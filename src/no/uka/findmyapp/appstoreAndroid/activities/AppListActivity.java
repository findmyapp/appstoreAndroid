package no.uka.findmyapp.appstoreAndroid.activities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;



import no.uka.findmyapp.appstoreAndroid.R;
import no.uka.findmyapp.appstoreAndroid.activities.AppListHelper.ParseException;
import no.uka.findmyapp.appstoreAndroid.activities.AppListHelper.ApiException;
import no.uka.findmyapp.appstoreAndroid.adapters.AppListAdapter;
import no.uka.findmyapp.appstoreAndroid.models.App;
import no.uka.findmyapp.appstoreAndroid.models.AppStoreList;
import no.uka.findmyapp.appstoreAndroid.models.Holder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import 	android.util.Log;

//private ContactListAdapter contactAdapter;
//private List<Contact> contactsArraylist;

public class AppListActivity extends Activity  implements AnimationListener {
	private static final String TAG = "AppListActivity";

	private Handler mHandler = new Handler();

	private JSONObject fbJson;
	AppListAdapter eventAdapter;
	ArrayList<App> eventsArrayList;
	ListView eventListView;
	private View mTitleBar;
	private TextView mTitle;
	private Animation mSlideIn;
	private Animation mSlideOut;
	private ProgressBar mProgress;
	
	AppStoreList appstoreList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);


		// Load animations used to show/hide progress bar
		mSlideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
		mSlideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);

		eventsArrayList = new ArrayList<App>();
		mTitleBar = findViewById(R.id.title_bar);
		mTitle = (TextView) findViewById(R.id.title);
		mProgress = (ProgressBar) findViewById(R.id.progress);
		eventListView = (ListView) findViewById(R.id.eventListView);
		AppListHelper.prepareUserAgent(this);
		
		
		////////
		eventsArrayList.clear();
		//populateListView("");
		new FetchTask().execute();
	} // end onCreate()

	public void onResume() {
		super.onResume();
	}	
	private void populateListView(String callContent) {
		Log.i("populating", "pop");


		/*
		//TODO
		App aBirds = new App();
		aBirds.setAppName("Angry Birds: goes to UKA");
		aBirds.setAndroidMarketURL("www.android.market.com/andgrybirds");
		aBirds.setRating(10);
		eventsArrayList.add(aBirds);

		App cutTheRope = new App();
		cutTheRope.setName("Cut the Rope");
		cutTheRope.setAndroidMarketURL("www.android.market.com/cuttherope");
		cutTheRope.setRating(4);
		eventsArrayList.add(cutTheRope);
		 */



		Gson gson = new Gson();
		appstoreList = new AppStoreList();
		/*
		callContent = "{\"listType\":1,\"requestCount\":10," +
				"\"listCount\":3,\"appList\":[{\"name\":\"MyStar\",\"androidMarketUri\":\"no.jg.jinfo\"}," +
				"{\"name\":\"Battleheart\",\"androidMarketUri\":\"com.KelliNoda.Battleheart\"}," +
				"{\"name\":\"Meteor Blitz\",\"androidMarketUri\":\"com.alleylabs.MeteorBlitz\"}]," +
				"\"platform\":2}";*/

		//callContent = "{\"listType\":1,\"requestCount\":10,\"listCount\":3,\"appList\":[{\"name\":\"MyStar\",\"androidMarketUri\":\"no.jg.jinfo\"},{\"name\":\"Battleheart\",\"androidMarketUri\":\"com.KelliNoda.Battleheart\"},{\"name\":\"Meteor Blitz\",\"androidMarketUri\":\"com.alleylabs.MeteorBlitz\"}],\"platform\":2}";
		
		
		try {
			TypeToken<AppStoreList> typeToken = new TypeToken<AppStoreList>() {};
			//Holder holder = gson.fromJson(callContent, typeToken.getType());
			appstoreList = gson.fromJson(callContent, typeToken.getType());
		} catch (JsonSyntaxException e) {
			Log.i("JSON ERROR","-" + e.getLocalizedMessage());
			Log.i("JSON ERROR","-" + e.getMessage());
			Log.i("JSON ERROR","-" + e.getCause());
		}
		Log.i("JSON String","-" + callContent);
		Log.i("JSON Object","-" + appstoreList.toString());
		Log.i("JSON Object count","-" + appstoreList.getListCount());
		

		eventAdapter = new AppListAdapter(this, R.layout.list_adapter, eventsArrayList);
		eventListView.setAdapter(eventAdapter);
		List<App> appList = appstoreList.getAppList();
		for(App app : appList) {
			//app.setAndroidMarketUri(androidMarketUri)("www.android.market.com/andgrybirds");
			app.setRating(10);
			app.setPublisher("UKA team 1!");
			eventsArrayList.add(app);
		}

		eventListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				//viewContact.setClass(getApplicationContext(), ContactDetailsActivity.class);
				/**
					databaseHandler.open();
					databaseHandler.removePerson(contactArrayList.get(position).getId());
					databaseHandler.close();
					populateList();
				 */
				Intent viewEvent = new Intent();
				viewEvent.setClass(getApplicationContext(), AppDetailsActivity.class);
				viewEvent.putExtra("SelectedEvent", eventsArrayList.get(position));///////
				startActivity(viewEvent); 
			}
		});

	}

	/**
	 * User-agent string to use when making requests. Should be filled using
	 * {@link #prepareUserAgent(Context)} before making any other calls.
	 */
	private static String sUserAgent = null;

	/**
	 * {@link StatusLine} HTTP status code when no server error has occurred.
	 */
	private static final int HTTP_STATUS_OK = 200;


	/**
	 * Shared buffer used by {@link #getUrlContent(String)} when reading results
	 * from an API request.
	 */
	private static byte[] sBuffer = new byte[512];


	protected static synchronized String getUrlContent(String url) throws ApiException {
		if (sUserAgent == null) {
			throw new ApiException("User-Agent string must be prepared");
		}

		// Create client and set our specific user-agent string
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		request.setHeader("User-Agent", sUserAgent);

		try {
			HttpResponse response = client.execute(request);

			// Check if server response is valid
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != HTTP_STATUS_OK) {
				throw new ApiException("Invalid response from server: " +
						status.toString());
			}

			// Pull content stream from response
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();

			ByteArrayOutputStream content = new ByteArrayOutputStream();

			// Read response into a buffered stream
			int readBytes = 0;
			while ((readBytes = inputStream.read(sBuffer)) != -1) {
				content.write(sBuffer, 0, readBytes);
			}

			// Return result from buffered stream
			return new String(content.toByteArray());
		} catch (IOException e) {
			throw new ApiException("Problem communicating with API", e);
		}
	}


	/**
	 * Background task to handle REST calls. This correctly shows and
	 * hides the loading animation from the GUI thread before starting a
	 * background query to the web service. When finished, it transitions
	 * back to the GUI thread where it updates with the newly-found entry.
	 */
	private class FetchTask extends AsyncTask<String, String, String> {
		/**
		 * Before jumping into background thread, start sliding in the
		 * {@link ProgressBar}. We'll only show it once the animation finishes.
		 */
		@Override
		protected void onPreExecute() {
			//mTitleBar.startAnimation(mSlideIn);
		}

		/**
		 * Perform the background query using {@link ExtendedWikiHelper}, which
		 * may return an error message as the result.
		 */
		@Override
		protected String doInBackground(String... args) {
			String callContent = null;

			try {
				callContent = AppListHelper.getPageContent();
			} catch (ParseException e) {
				Log.e(TAG, "Problem making wiktionary request", e);
			} catch (ApiException e) {
				Log.e(TAG, "Problem making wiktionary request", e);
			}

			return callContent;
		}


		/**
		 * Our progress update pushes a title bar update.
		 */
		@Override
		protected void onProgressUpdate(String... args) {
			//String searchWord = args[0];
			//setEntryTitle(searchWord);
		}

		/**
		 * When finished, push the newly-found entry content into our
		 * {@link WebView} and hide the {@link ProgressBar}.
		 */
		@Override
		protected void onPostExecute(String callContent) {
			//mTitleBar.startAnimation(mSlideOut);
			//mProgress.setVisibility(View.INVISIBLE);

			populateListView(callContent);
		}
	}


	@Override
	public void onAnimationEnd(Animation animation) {
		//mProgress.setVisibility(View.VISIBLE);

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}

