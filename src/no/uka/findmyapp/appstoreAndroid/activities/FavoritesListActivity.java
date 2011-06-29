package no.uka.findmyapp.appstoreAndroid.activities;

import no.uka.findmyapp.appstoreAndroid.R;
import android.app.Activity;
import android.os.Bundle;

public class FavoritesListActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
	}
	public void onResume() {
		super.onResume();
	}	
}
