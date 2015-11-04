package com.vcps.notify;

import com.parse.ParseUser;
import com.vcps.notify.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {
	
	private static String TAG = SplashActivity.class.getName();
	private static long SLEEP_TIME = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_splash);
		
		final IntentLauncher launcher = new IntentLauncher();
		//Boolean confirmedEULA;
		//final ParseUser currentUser = ParseUser.getCurrentUser();
		//confirmedEULA = currentUser.getBoolean("EULA");
		
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
//		
//		 
//	
//		
//		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//	           public void onClick(DialogInterface dialog, int id) {
//	             //currentUser.put("EULA", true);
//	             
//	      		//launcher.start();
//
//	           }
//	       });
//		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//	           public void onClick(DialogInterface dialog, int id) {
//	               Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
//	               intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
//	               intent.putExtra("EXIT", true);
//	               startActivity(intent);
//	               
//	               if (getIntent().getBooleanExtra("EXIT", false)) {
//	            	   finish();
//	            	}
//	               
//	           }
//	       });
//		AlertDialog dialog = builder.create();
		
//		if (confirmedEULA == true){
//			launcher.start();
//
//			
//		} else {
//			dialog.show();
//			
//			
//		}
		launcher.start();
	}
	
	private class IntentLauncher extends Thread {

		@Override
		public void run() {
			
			
		
				
			
			try {
				Thread.sleep(SLEEP_TIME*1000);
				Log.e(TAG, "Does this open up a new filter?");
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			
			Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
			SplashActivity.this.startActivity(intent);
			SplashActivity.this.finish();
			
		}
		
	}

	
}
