package com.example.main;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/////////////////// *** Start  Class *** ///////////////////////////	

public class Loading_Activity  extends Activity {
	
/////////////////// *** Start  Variable Declaration *** ///////////////////////////	
	
			public SharedPreferences preferences;
			private static int SPLASH_TIME_OUT = 3000;
			String name;
			
/////////////////// *** Close  Variable Declaration *** ///////////////////////////	
			
/////////////////// *** Start OnCreateMethod *** ///////////////////////////	
         @Override
         	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.loading);
	        
/////////////////// Start Initialization Widgets  ///////////////////////////
	        
	        preferences = getApplicationContext().getSharedPreferences("7", MODE_PRIVATE);
	        name=(preferences.getString("UNAME", ""));
	        
/////////////////// Close Initialization Widgets  ///////////////////////////
	        
	        new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					if(name.equals("")){
						 Intent i1 = new Intent(getApplicationContext(),Login.class);
			                startActivity(i1);
				              finish();
					}
					else{
						Intent i = new Intent(getApplicationContext(),Login.class);
		                startActivity(i);
			               finish();
					}
						         	
				}
			}, SPLASH_TIME_OUT); 
	       
	
	    }
/////////////////// *** Close OnCreateMethod *** ///////////////////////////
}
/////////////////// *** Close Class *** ///////////////////////////	