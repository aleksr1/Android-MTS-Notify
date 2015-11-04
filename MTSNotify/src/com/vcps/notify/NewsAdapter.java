package com.vcps.notify;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.vcps.notify.R;

public class NewsAdapter extends ParseQueryAdapter<ParseObject> {
	
	static List<String> installation = ParseInstallation.getCurrentInstallation().getList("channels");
	
	public NewsAdapter (Context context) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseObject>() {

			@Override
			public ParseQuery<ParseObject> create() {
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("News");
				query.whereContainedIn("channels", ParseInstallation.getCurrentInstallation().getList("channels"));
				query.orderByDescending("createdAt");
				return query;
			}
		});
		
		if (ParseInstallation.getCurrentInstallation().getList("channels") == null) {
			
		}
		
		for (int i = 0; i < installation.size(); i++) {
            Log.w("news thingy", installation.get(i));
		}
	}
	public View getItemView (ParseObject object, View v, ViewGroup parent) {
		
		if (v == null ) {
			v = View.inflate(getContext(), R.layout.listview_news_item, null);	
		}
		super.getItemView(object, v, parent);
		
		
		
		TextView createdAtTextView = (TextView) v.findViewById(R.id.label_createdAt);
		TextView msgTextView = (TextView) v.findViewById(R.id.label_message);
		TextView subjectTextView = (TextView) v.findViewById(R.id.label_subject);
		
		createdAtTextView.setText(object.getCreatedAt().toString());
		subjectTextView.setText(object.getString("subject"));
		msgTextView.setText(object.getString("message"));
		
		return v;
	}
}