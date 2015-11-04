package com.vcps.notify;



import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.vcps.notify.R;

public class NotificationAdapter extends ParseQueryAdapter<ParseObject> {
	
	public NotificationAdapter(Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				
				ParseQuery<ParseObject> userQuery = new ParseQuery<ParseObject>("Notifications");
				userQuery.whereEqualTo("user", ParseUser.getCurrentUser());
				
				ParseQuery<ParseObject> channelQuery = new ParseQuery<ParseObject>("Notifications");
				channelQuery.whereContainedIn("channels", ParseInstallation.getCurrentInstallation().getList("channels"));
				
				ParseQuery<ParseObject> installationQuery = new ParseQuery<ParseObject>("Notifications");
				installationQuery.whereEqualTo("installation", ParseInstallation.getCurrentInstallation());
				
				List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
				queries.add(userQuery);
				queries.add(channelQuery);
				queries.add(installationQuery);
				
				ParseQuery<ParseObject> mainQuery = ParseQuery.or(queries);
				mainQuery.orderByDescending("createdAt");
				mainQuery.setLimit(20);
				
				return mainQuery;
				
			}
		});
	}
	
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_notifications_item, null);
		}
		super.getItemView(object, v, parent);
		
		TextView createdAtTextView = (TextView) v.findViewById(R.id.label_createdAt);
		TextView msgTextView = (TextView) v.findViewById(R.id.label_message);
		//Format formatter = new SimpleDateFormat("dd-MM-yyyy hh:mma");
		createdAtTextView.setText(object.getCreatedAt().toString());
		msgTextView.setText(object.getString("message"));
		
		return v;
	}

}
