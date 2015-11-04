package com.vcps.notify;

import com.parse.ParseObject;
import com.vcps.notify.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class FeedbackActivity extends ActionBarActivity {
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		ParseObject feedback = new ParseObject("Feedback");
		String feedbackMSG;
		String userEmail;
		Boolean receiveContact;
		Boolean alreadyContacted = false;
		Boolean deleted = false;
		
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_feedback,
					container, false);
			
			
			final EditText feedbackMessage = (EditText) rootView.findViewById(R.id.input_feedback);
			final EditText email = (EditText) rootView.findViewById(R.id.input_email);
			final CheckBox contactMe = (CheckBox) rootView.findViewById(R.id.checkbox_email);
			Button submit = (Button) rootView.findViewById(R.id.button_submit);
			
			
			submit.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					feedbackMSG = feedbackMessage.getText().toString();
					if (feedbackMSG.matches("")) {
						Toast.makeText(getActivity(), "Information is missing",
								Toast.LENGTH_LONG).show();
					}

					else {
						if (contactMe.isChecked()) {

							userEmail = email.getText().toString();
							feedback.put("email", userEmail);
							if (userEmail.matches("")) {
								Toast.makeText(getActivity(),
										"Email address is missing",
										Toast.LENGTH_LONG).show();
							} else {
								receiveContact = true;
								userEmail = email.getText().toString();
								feedback.put("message", feedbackMSG);
								feedback.put("wantsContact", receiveContact);
								feedback.put("deleted", deleted);
								feedback.put("contacted", alreadyContacted);
								feedback.saveInBackground();
								Toast.makeText(getActivity(),
										"Thank you for your feedback",
										Toast.LENGTH_LONG).show();
								Intent intent = new Intent(getActivity(),
										DashboardActivity.class);
								startActivity(intent);
							}

						} else {
							receiveContact = false;
							feedback.put("message", feedbackMSG);
							feedback.put("wantsContact", receiveContact);
							feedback.put("deleted", deleted);
							feedback.put("contacted", alreadyContacted);
							
							feedback.saveInBackground();
							Toast.makeText(getActivity(),
									"Thank you for your feedback",
									Toast.LENGTH_LONG).show();
							Intent intent = new Intent(getActivity(),
									DashboardActivity.class);
							startActivity(intent);
						}

					}
				}
				
			});
			
			return rootView;
		}
		
		
	}
}
