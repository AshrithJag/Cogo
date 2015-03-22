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
import android.widget.Toast;

public class viewcoupon extends Activity {
	
	int count=0;
	int dc=0;
	Context context;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) 
	 {
		
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.coupon_description);
	        context=this;
	        ActionBar actionBar = getActionBar();
	        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6600")));
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        TextView name=(TextView) findViewById(R.id.TextView02);
	        TextView validity=(TextView) findViewById(R.id.TextView03);
	        TextView source=(TextView) findViewById(R.id.TextView01);
	        Button coupon= (Button) findViewById(R.id.button1);
	        Bundle bundle = getIntent().getExtras();
	        name.setText(bundle.getString("Name"));
	        validity.setText("Validity : " + bundle.getString("Validity"));
	        source.setText("Source : " + bundle.getString("Source"));
	        
	        coupon.setText(bundle.getString("Code"));
	        ImageView im = (ImageView) findViewById(R.id.imageView1);
	        im.setImageResource(Integer.parseInt(bundle.getString("Image")));
	        
	        
	        
	        
	        
	        
	        
	        TextView Statement=(TextView) findViewById(R.id.TextView2);
	        ImageButton button = (ImageButton) findViewById(R.id.ImageButton4);
	        button.setOnClickListener(new OnClickListener() {
	        	 
			
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final ImageButton button_like = (ImageButton) findViewById(R.id.ImageButton4);
					if(count==0)
					{
						button_like.setBackgroundResource(R.drawable.orangebutton);
						button_like.setImageResource(R.drawable.ic_action_bad);
						count=1;
					}
					else if(count==1)
					{
						button_like.setBackgroundResource(R.drawable.mybutton);
						button_like.setImageResource(R.drawable.ic_action_good);
						count=0;
					}
				}
				
	 
			});
	        ImageButton button_share = (ImageButton) findViewById(R.id.imageButton1);
	        final Context context = this;
	        button_share.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					AlertDialog.Builder alert = new AlertDialog.Builder(context);
			        alert.setTitle("Share");
			        alert.setMessage("Enter Phone Number");
			        final EditText input = new EditText(context);
			        input.setInputType(InputType.TYPE_CLASS_PHONE);
			        alert.setView(input);
			        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int whichButton) {
			          
			          }
			        });

			        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			          public void onClick(DialogInterface dialog, int whichButton) {
			            // Canceled.
			          }
			        }); 
				        
					alert.show();
					
				}
	        	
	        	
	        });
	        
	        ImageButton down = (ImageButton) findViewById(R.id.imageButton2);
	        
	        down.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					final ImageButton button_like = (ImageButton) findViewById(R.id.imageButton2);
					if(count==0)
					{
						int duration = Toast.LENGTH_SHORT;
						button_like.setBackgroundResource(R.drawable.mybutton);
						Toast toast = Toast.makeText(context, "Saved to Wallet", duration);
						toast.show();
						
						count=1;
					}
					else if(count==1)
					{
						button_like.setBackgroundResource(R.drawable.orangebutton);
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, "Removed from Wallet", duration);
						toast.show();
						count=0;
					}
					
					
					
					
				}
	        	
	        	
	        });
	        
	        
	        
	 }
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu items for use in the action bar
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
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
