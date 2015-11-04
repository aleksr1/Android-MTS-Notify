//MTS NOTIFY Authored By:  Justin Owens 12-31-14goog

package com.vcps.notify;

import java.util.Arrays;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.RefreshCallback;
import com.vcps.notify.R;


public class DashboardActivity extends ActionBarActivity implements
		ActionBar.TabListener {
	
	
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		
		//User relation with Installation
		ParseUser user = ParseUser.getCurrentUser();
		ParseObject installation = ParseInstallation.getCurrentInstallation();
		ParseRelation<ParseObject> relation = user.getRelation("installation");
		relation.add(installation);
		
	
		
		user.saveInBackground();
		
		//Create user Pointer<_User>
		installation.put("user", user);
		installation.saveInBackground();
		//user.saveInBackground();
		ParseAnalytics.trackAppOpened(getIntent());
		
		
		
		
		if (ParseInstallation.getCurrentInstallation().getList("channels") == null) {
			
			Log.w("ParseInstall",  "channels == null");
			
			String news = "news";
			installation.addAllUnique("channels", Arrays.asList(news));
			installation.saveInBackground();
			} 		
			
			ParseInstallation.getCurrentInstallation().refreshInBackground(new RefreshCallback(){

				@Override
				public void done(ParseObject parseObject, ParseException e) {
//					 List<String> channels = ParseInstallation.getCurrentInstallation().getList("channels");
//		                for (int i = 0; i < channels.size(); i++) {
//		                    Log.w("FIRST INSTALL", channels.get(i));
					
				}
				//}
			});
			
			
		
		
		// Set up the action bar.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		//getActionBar().setDisplayShowTitleEnabled(false);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		
		
		}
		
	}
	
	

	 protected void onResume() {
		 super.onResume();
		ParseInstallation.getCurrentInstallation().refreshInBackground(new RefreshCallback(){

			@Override
			public void done(ParseObject parseObject, ParseException e) {
				 /*List<String> channels = ParseInstallation.getCurrentInstallation().getList("channels");
	                for (int i = 0; i < channels.size(); i++) {
	                    Log.w("onResume", channels.get(i));*/
				
			}
			//}
		});
		
		
		
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_about) {
			Intent intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			return true;
		} 
		
		if (id == R.id.action_profile) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
			return true;
		}
		
		if (id == R.id.action_feedback) {
			Intent intent = new Intent(this, FeedbackActivity.class);
			startActivity(intent);
			return true;
		}
		if (id == R.id.action_notification_history) {
			Intent intent = new Intent(this, NotificationHistoryActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			switch(i){
			case 0:
				return new NotificationFragment();
			/*case 1:
				return new NewsFragment();*/
			/*case 2:
				return new CalendarFragment();*/
			}
			return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 1;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			/*case 2:
				return getString(R.string.title_section3).toUpperCase(l);*/
			}
			return null;
		}
	}
	
	public static class NotificationFragment extends Fragment {
		private SwipeRefreshLayout swipeLayoutNotifications;
	    private ListView notiListView;
	    
	    
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
			final  NotificationAdapter notiAdapter = new NotificationAdapter(getActivity());
			swipeLayoutNotifications = (SwipeRefreshLayout) rootView.findViewById(R.id.notificationSwipeRefresh);
			notiListView = (ListView) rootView.findViewById(R.id.listView_notifications);
			notiAdapter.setPaginationEnabled(false);
			notiListView.setAdapter(notiAdapter);
			swipeLayoutNotifications.setColorSchemeColors(
	                R.color.swipe_color_1, R.color.swipe_color_2,
	                R.color.swipe_color_3, R.color.swipe_color_4);	
			swipeLayoutNotifications.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
				@Override
				public void onRefresh() {
					ParseInstallation.getCurrentInstallation().refreshInBackground(new RefreshCallback(){

						@Override
						public void done(ParseObject parseObject, ParseException e) {
							 /*List<String> channels = ParseInstallation.getCurrentInstallation().getList("channels");
				                for (int i = 0; i < channels.size(); i++) {
				                    Log.w("onResume", channels.get(i));*/
							
						}
						//}
					});
					
					notiListView.setAdapter(notiAdapter);
					if (notiListView != null){
						swipeLayoutNotifications.setRefreshing(false);
						}
					}
				});
			
			
			
			notiListView.setOnScrollListener(new AbsListView.OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					if(firstVisibleItem == 0)
						swipeLayoutNotifications.setEnabled(true);
					else
						swipeLayoutNotifications.setEnabled(false);
				}
			});
			return rootView;
			}
		
		}
	
	public static class NewsFragment extends Fragment {
		private SwipeRefreshLayout swipeLayoutNews;
	    private ListView newsListView;
	    
	    
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_news, container, false);
			final NewsAdapter newsAdapter = new NewsAdapter(getActivity());
			newsListView = (ListView) rootView.findViewById(R.id.listView_news);			
			newsListView.setAdapter(newsAdapter);
			swipeLayoutNews = (SwipeRefreshLayout) rootView.findViewById(R.id.newsSwipeRefresh);
			swipeLayoutNews.setColorSchemeColors(
	                R.color.swipe_color_1, R.color.swipe_color_2,
	                R.color.swipe_color_3, R.color.swipe_color_4);
			swipeLayoutNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
				@Override
				public void onRefresh() {
					
				
					newsListView.setAdapter(newsAdapter);
					if (newsListView != null){
						swipeLayoutNews.setRefreshing(false);
						}
					}
				});
			//Fixes the sensitivity of swipe to refresh
			
			newsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
				
				@Override
				public void onScrollStateChanged(AbsListView view, int scrollState) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					if(firstVisibleItem == 0)	
						swipeLayoutNews.setEnabled(true);
					else
						swipeLayoutNews.setEnabled(false);
					
				}
			});
			return rootView;
			}
		
		}
	
	public static class CalendarFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
			return rootView;
		}
		
	}
}
