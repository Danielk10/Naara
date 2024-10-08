package com.naarasalonyspa.naara;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AppCenter.start(getApplication(), "447c7f85-0c9e-4470-a7b3-86c55255b873",
                                  Analytics.class, Crashes.class);
  
        setContentView(R.layout.activity_main);
     
    }
}