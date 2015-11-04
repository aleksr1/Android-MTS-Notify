package com.vcps.notify;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.RefreshCallback;
import com.parse.SaveCallback;
import com.vcps.notify.R;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class EditProfileActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.edit_profile, menu);
//		return true;
//	}

	//@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		ParseUser user = ParseUser.getCurrentUser();

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_edit_profile,
					container, false);
			
			final EditText nameEditText = (EditText) rootView.findViewById(R.id.nameEditText);
			final EditText userEditText = (EditText) rootView.findViewById(R.id.userEditText);
			Button submitButton = (Button) rootView.findViewById(R.id.button_submit2);
			
			//ParseUser user = ParseUser.getCurrentUser();
			String name = user.getString("name").toString();
			String email = user.getString("email").toString();
			nameEditText.setText(name);
			userEditText.setText(email);
			
//			ParseUser.getCurrentUser().refreshInBackground(new RefreshCallback() {
//				public void done(ParseObject objects, ParseException e) {
//					if (e == null) {
//						
//							ParseUser u = (ParseUser)objects;
//							String name = u.getString("name").toString();
//							String email = u.getString("email").toString();
//							
//							nameEditText.setText(name);
//							userEditText.setText(email);
//							listAdapter.add(name);
//							listAdapter.add(email);
//					
//						} else {
//							 Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
//						}
//					}
//
//			});
			
			submitButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(final View v) {
					String name = nameEditText.getText().toString();
					String email = userEditText.getText().toString().toLowerCase();
					
					
					
					user.put("name", name);
					user.setUsername(email);
					user.setEmail(email);
					user.saveInBackground(new SaveCallback() {
						
						@Override
						public void done(ParseException e) {
							if (e != null) {
								Toast.makeText(getActivity(),
										e.toString(),
										Toast.LENGTH_LONG).show();
								} else {
									Intent intent = new Intent(v.getContext(), ProfileActivity.class);
									startActivity(intent);
									
									
							    }							
						}
					});
					Intent intent = new Intent(v.getContext(), DashboardActivity.class);
					startActivity(intent);
					
				}
			});
			
			return rootView;
		}
	}
}
