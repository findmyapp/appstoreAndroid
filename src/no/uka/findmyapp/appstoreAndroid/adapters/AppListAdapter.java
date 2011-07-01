package no.uka.findmyapp.appstoreAndroid.adapters;

import java.util.List;

import no.uka.findmyapp.appstoreAndroid.R;
import no.uka.findmyapp.appstoreAndroid.models.App;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


public class AppListAdapter extends ArrayAdapter<App> {
	private int textViewResourceId;

	public AppListAdapter(Context context, int textViewResourceId, List<App> items) {	
		super(context, textViewResourceId, items);
		this.textViewResourceId = textViewResourceId;
	}

	public View getView(int position, View convertView, ViewGroup parent){
		LinearLayout eventView = null;
		App app = getItem(position);

		if(convertView == null){
			eventView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
			vi.inflate(textViewResourceId, eventView, true);
		}
		else
		{
			eventView = (LinearLayout) convertView;
		}

		TextView title = (TextView) eventView.findViewById(R.id.title);
		TextView publisher = (TextView) eventView.findViewById(R.id.publisher);
		TextView body = (TextView) eventView.findViewById(R.id.place);

		title.setText(app.getName());
		publisher.setText(app.getPublisher());
		body.setText(String.valueOf(app.getRating()));

		return eventView;
	}
}
