package com.vcps.notify;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.vcps.notify.R;

public class AboutAdapter extends ParseQueryAdapter<ParseObject> {
	public AboutAdapter (Context context) {
		super(context,  new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("About");
				//query.setLimit(1); doesn't work,  just update the row in Parse.com Dashboard
				query.whereEqualTo("deviceType", "android");
				query.orderByDescending("createdAt");
				
				return query;
			}
		});
	}
	
	public View getItemView(ParseObject object, View v, ViewGroup parent) {
		
		if (v == null) {
			v = View.inflate(getContext(), R.layout.listview_about_item, null);
		}
		super.getItemView(object, v, parent);
		
		TextView descriptionTextView = (TextView) v.findViewById(R.id.label_description);
		TextView companyTextView = (TextView) v.findViewById(R.id.label_company);
		TextView versionTextView = (TextView) v.findViewById(R.id.label_version);
		TextView releaseTextView = (TextView) v.findViewById(R.id.label_release);
		TextView updateTextView = (TextView) v.findViewById(R.id.label_update);
		
		descriptionTextView.setText(object.getString("description"));
		companyTextView.setText(object.getString("company"));
		versionTextView.setText(object.getString("version"));
		releaseTextView.setText(object.getString("release"));
		updateTextView.setText(object.getString("update"));
		
		return v;
		
	}

}
