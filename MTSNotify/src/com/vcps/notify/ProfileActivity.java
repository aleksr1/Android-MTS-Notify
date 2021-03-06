package com.vcps.notify;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseUser;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.RefreshCallback;
import com.vcps.notify.R;

public class ProfileActivity extends ActionBarActivity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	

	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			final View rootView = inflater.inflate(R.layout.fragment_profile,
					container, false);
			
			
			
			
			//  ******  OLD METHOD USING LIST VIEW ****
			ArrayList<String> profile = new ArrayList<String>();
			final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(),
	                android.R.layout.simple_list_item_1);
			ListView profileListView = (ListView) rootView.findViewById(R.id.listView_profile);
			profileListView.setAdapter(listAdapter);
			
	                
			//final TextView nameLabel = (TextView) rootView.findViewById(R.id.nameLabel);
			//final TextView emailLabel = (TextView) rootView.findViewById(R.id.userLabel);
			Button editButton  = (Button) rootView.findViewById(R.id.editButton);
			
//			ParseUser user = ParseUser.getCurrentUser();
//			String name = user.getString("name").toString();
//			String email = user.getString("email").toString();
//			nameLabel.setText(name);
//			emailLabel.setText(email);
			
			ParseUser.getCurrentUser().refreshInBackground(new RefreshCallback() {
				public void done(ParseObject objects, ParseException e) {
					if (e == null) {
						
							ParseUser u = (ParseUser)objects;
							String name = u.getString("name").toString();
							String email = u.getString("email").toString();
							
//							nameLabel.setText(name);
//							emailLabel.setText(email);
							listAdapter.add(name);
							listAdapter.add(email);
					
						} else {
							 Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
						}
					}

			});
				
		
			editButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(v.getContext(), EditProfileActivity.class);
					startActivity(intent);
					
				}
			});
			
			
			
			return rootView;
		}
	}
}
 