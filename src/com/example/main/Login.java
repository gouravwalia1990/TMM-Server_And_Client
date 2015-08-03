package com.example.main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/////////////////// *** Start  Class *** ///////////////////////////	

public class Login extends Activity {
	
/////////////////// *** Start  Variable Declaration *** ///////////////////////////
	
		Button login ;
		TextView Registration;
		EditText clubid,password;
		ProgressDialog progress;
		HttpPost httppost;
		String response_string; 
		StringBuffer buffer;
		HttpResponse response;
		public String id,pass,id1,pass1,id2,tpass,n,un,name1;
		HttpClient httpclient;
		List<NameValuePair> nameValuePairs;
		public SharedPreferences preferences;
		TextView tv;
		
/////////////////// *** Close  Variable Declaration *** ///////////////////////////	
		

/////////////////// *** Start OnCreateMethod *** ///////////////////////////
		
	@Override
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);
		
/////////////////// Start Initialization Widgets  ///////////////////////////
		
		tv=(TextView)findViewById(R.id.textView4);
		clubid=(EditText)findViewById(R.id.clubId);
		Registration=(TextView)findViewById(R.id.RegistertextView);
		password=(EditText)findViewById(R.id.Password);
		login=(Button)findViewById(R.id.signInButton);
		progress = new ProgressDialog(this);
		preferences = getApplicationContext().getSharedPreferences("7", MODE_PRIVATE);
		
/////////////////// Close Initialization Widgets  ///////////////////////////
		
		Registration.setOnClickListener(new OnClickListener() {
			
		@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),SignUpp.class);
				startActivity(i);
			}
		});
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Map<String, String> networkDetails = getConnectionDetails();
				  if (networkDetails.isEmpty()) {
					  Toast.makeText(Login.this, " Please check Internet connection ", Toast.LENGTH_LONG).show();
				  tv.setText("Please check Internet connection");
				
				  }
				  else{
					  
				  
				if(clubid.getText().toString().trim().length() > 0 && password.getText().toString().trim().length() > 0 )
		        {
					
					 progress.setMessage("Please wait while Login... ");
				     progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				     progress.setIndeterminate(true);
				     progress.show();
					 id=clubid.getText().toString().trim();
				     pass=password.getText().toString().trim();
				     Loginfunction();
		        }
				else
		        {
					
		            Toast.makeText(Login.this,"Please enter Username and Password", Toast.LENGTH_LONG).show();
		        }
				  }
				
				
				
				
			}
			
		});
	
		  }
	
/////////////////// *** Close OnCreateMethod *** ///////////////////////////

/////////////////// *** Start Check Internet Connection  *** ///////////////////////////
	
	private Map<String, String> getConnectionDetails() {
		  Map<String, String> networkDetails = new HashMap<String, String>();
		  try {
		   ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		   NetworkInfo wifiNetwork = connectivityManager
		     .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		   if (wifiNetwork != null && wifiNetwork.isConnected()) {

		    networkDetails.put("Type", wifiNetwork.getTypeName());
		    networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
		    networkDetails.put("State", wifiNetwork.getState().name());
		   }

		   NetworkInfo mobileNetwork = connectivityManager
		     .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		   if (mobileNetwork != null && mobileNetwork.isConnected()) {
		    networkDetails.put("Type", mobileNetwork.getTypeName());
		    networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
		    networkDetails.put("State", mobileNetwork.getState().name());
		    if (mobileNetwork.isRoaming()) {
		     networkDetails.put("Roming", "YES");
		    } else {
		     networkDetails.put("Roming", "NO");
		    }
		   }
		  } catch (Exception e) {
		   networkDetails.put("Status", e.getMessage());
		  }
		  return networkDetails;
		 }

/////////////////// *** Close Check Internet Connection  *** ///////////////////////////
	
	  
/////////////////// *** Start Loginfunction Method *** ///////////////////////////	
	
	private void Loginfunction() 
	{
		// TODO Auto-generated method stub
		new Thread()
		{
			@Override
			public void run()
			{
				 try{            
					
			            httpclient=new DefaultHttpClient();
			            httppost= new HttpPost("http://132connect.com/club/server/login");
			            nameValuePairs = new ArrayList<NameValuePair>(2);
			            nameValuePairs.add(new BasicNameValuePair("club_id",id));  
			            nameValuePairs.add(new BasicNameValuePair("cpwd",pass)); 
			            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			            response=httpclient.execute(httppost);
			            ResponseHandler<String> responseHandler = new BasicResponseHandler();
			            response_string = httpclient.execute(httppost, responseHandler);
			            System.out.println("Response11 : " + response_string);
			            JSONArray array=new JSONArray(response_string);
			            JSONObject DATA=null;
			            Log.e("tp", response_string);
                   for(int i=0;i<array.length();i++)
                    {
                  	   DATA=array.getJSONObject(0);
                  	  id1=DATA.getString("status");
                  	   pass1=DATA.getString("message");
                  	   if(id1.equals("OK")){
                  		   id2 = DATA.getString("id");
                      	   Log.e("uttam", pass1);
                  	   }
                  	  
                   }
                  	 runOnUiThread(new Runnable() 
                  	 {
			                public void run()
			                {
			                	
			                	if(id1.equals("OK"))
			                	{
			            			  progress.cancel();
			            			  preferences = getApplicationContext().getSharedPreferences("7",MODE_PRIVATE);
					           		  SharedPreferences.Editor editor = preferences.edit();
					           		  editor.putString("UNAME",id1);
					           		  editor.putString("PASS",pass);
					           		
					           		editor.putString("N",n);
					           		
					           		  editor.commit();
			                		  Intent next=new Intent(Login.this,Create_Meeting.class);

			                		  finish();
			                		  startActivity(next);
			            				 Toast.makeText(Login.this, pass1, Toast.LENGTH_LONG).show();
					        	}
			                	
			                	 else
			                	 		{
					              		id1.equals("Error");
					                		  progress.cancel();
					                	 Toast.makeText(Login.this, pass1, Toast.LENGTH_LONG).show();
				                	  	} 
			                }
			            });
                    }catch(Exception e){
			            
			            System.out.println("Exception : " + e.getMessage());
			        }
	      	}
		}.start();
 	}
/////////////////// *** Close Loginfunction Method *** ///////////////////////////	
}
/////////////////// *** Close Class *** ///////////////////////////	
