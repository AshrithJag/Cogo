package com.example.cogo;

import java.io.File;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.os.Environment;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Viewmain extends Activity {
	
	int count=0;
	Context context;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainpage);
	        context=this;
	        ActionBar actionBar = getActionBar();
	        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
	        ImageButton shop = (ImageButton) findViewById(R.id.imageButton1);
	        ImageButton hotel = (ImageButton) findViewById(R.id.ImageButton4);
	        ImageButton lesiure = (ImageButton) findViewById(R.id.ImageButton2);
	        ImageButton Events = (ImageButton) findViewById(R.id.ImageButton3);
	        final Context context=this;
	        shop.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent newpage = new Intent(context,MainActivity.class);
		            startActivity(newpage);
					
				}
	        
	        });
	        hotel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent newpage = new Intent(context,MainActivity.class);
		            startActivity(newpage);
					
				}
	        
	        });
	        lesiure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent newpage = new Intent(context,MainActivity.class);
		            startActivity(newpage);
					
				}
	        
	        });
	        Events.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent newpage = new Intent(context,MainActivity.class);
		            startActivity(newpage);
					
				}
	        
	        });
	        
	        
	 }
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu items for use in the action bar
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.semimain, menu);
	        return super.onCreateOptionsMenu(menu);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	    	 int id = item.getItemId();
	         if(id==R.id.action_Wallet)
	         {
	         	 Intent newpage = new Intent(context,Mywallet.class);
	              startActivity(newpage);
	         }
	         
	         if(id==R.id.action_notification)
	         {
	         	 Intent newpage = new Intent(context,Notification.class);
	              startActivity(newpage);
	         }
	         if(id==R.id.action_GPS)
	         {
	         	 Intent newpage = new Intent(context,Gps.class);
	              startActivity(newpage);
	         }
	         if(id==R.id.action_Log)
	         {
	         	File folder = new File(Environment.getExternalStorageDirectory() + "/COGO");
	         	File file = new File(folder,"Login.txt");
	         	file.delete();
	         	this.finish();
	         	Intent newpage = new Intent(context,Login.class);
	             startActivity(newpage);
	         }
	        return super.onOptionsItemSelected(item);
	    }


}
