package com.vcps.notify;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.PushService;
 
public class ParseApplication extends Application {
 
    @Override
    public void onCreate() {
        super.onCreate();
       
        // Tester App
        Parse.initialize(this, "WNKPnvpzxRYMBdJzkF5aPFpiWqBefYRlGSXKv2CI", "gn74Tjoeg13jESfCf6LqKvUCx1bmskq1A8rqwAsQ");
        //ParseObject installation = ParseInstallation.getCurrentInstallation();
        
        
        //BILLINGS BETA PARSE CODE
        //Parse.initialize(this, "kMPUDDkrcGmKF256qJhEf3VMGLmY97SKTm0ps8Ji", "OKxJprdkucIfan7TyoC9jP79gJd6T4U0Svf9sffA");
        
       
      
 
        
        PushService.setDefaultPushCallback(this, DashboardActivity.class);
       
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
 
        
    }
    
    
 
}