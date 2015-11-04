package com.vcps.notify;


import com.parse.ui.ParseLoginDispatchActivity;







public class LoginActivity extends ParseLoginDispatchActivity {
	@Override 
	protected Class<?> getTargetClass() {
		return DashboardActivity.class;
	}
}