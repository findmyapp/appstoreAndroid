package no.uka.findmyapp.appstoreAndroid.activities;

import java.util.ArrayList;

import no.uka.findmyapp.appstoreAndroid.R;
import no.uka.findmyapp.appstoreAndroid.models.App;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


//private ContactListAdapter contactAdapter;
//private List<Contact> contactsArraylist;

public class AppListActivity extends Activity {
	AppListAdapter eventAdapter;
	ArrayList<App> eventsArrayList;
	ListView eventListView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);

		eventsArrayList = new ArrayList<App>();
		eventListView = (ListView) findViewById(R.id.eventListView);
		eventAdapter = new AppListAdapter(this, R.layout.list_adapter, eventsArrayList);
		eventListView.setAdapter(eventAdapter);
		////////
		populateListView();
	} // end onCreate()

	public void onResume() {
		super.onResume();
	}	
	private void populateListView() {
		eventsArrayList.clear();

		App aBirds = new App();
		aBirds.setAppName("Angry Birds: goes to UKA");
		aBirds.setAndroidMarketURL("www.android.market.com/andgrybirds");
		aBirds.setRating(10);
		eventsArrayList.add(aBirds);

		App cutTheRope = new App();
		cutTheRope.setAppName("Cut the Rope");
		cutTheRope.setAndroidMarketURL("www.android.market.com/cuttherope");
		cutTheRope.setRating(4);
		eventsArrayList.add(cutTheRope);

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

}

