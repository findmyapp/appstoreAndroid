package no.uka.findmyapp.appstoreAndroid.activities;


import no.uka.findmyapp.appstoreAndroid.R;
import no.uka.findmyapp.appstoreAndroid.models.App;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppDetailsActivity extends Activity {

	private App selectedApp;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		TextView app = (TextView) findViewById(R.id.app);
		app.setText("Kaizers");
		
		

		
		Bundle extras = getIntent().getExtras();
		selectedApp = new App();
		
		if (extras != null) {
			selectedApp = (App) extras.getSerializable("SelectedEvent");
			app.setText(selectedApp.getName());
			
			Button button = (Button) findViewById(R.id.market_button);
			
			button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( "market://" + selectedApp.getAndroidMarketUri() ) );
	            	Log.v("LINK", Uri.parse( "market://" + selectedApp.getAndroidMarketUri() ).toString());
	            	startActivity(browse);
	            }
	        });
			Log.v("AA", "INSIDE");
			
			/**Intent editContact = new Intent();
			editContact.putExtra("SelectedContact", selectedContact);
			
			TextView tv_firstname = (TextView) findViewById(R.id.lbl_name);
			TextView tv_phone = (TextView)findViewById(R.id.lbl_phone);
			TextView tv_mail = (TextView)findViewById(R.id.lbl_email);
			
			tv_firstname.setText(selectedContact.getFirstname() + " " + selectedContact.getLastname());
			tv_phone.setText("Phone: " + selectedContact.getPhone());
			tv_mail.setText("Mail: " + selectedContact.getEmail());
			*/
		}
		
	}
}