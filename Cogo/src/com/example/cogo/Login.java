package com.example.cogo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Login extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button bb=(Button) findViewById(R.id.button1);
        final Context context=this;
        final ActionBar actionBar = getActionBar();
        actionBar.hide();
        ProgressBar spinner = (ProgressBar)findViewById(R.id.progressBar1);
        final EditText phone= (EditText)findViewById(R.id.editText1);
		final EditText password= (EditText)findViewById(R.id.editText2);
        File folder = new File(Environment.getExternalStorageDirectory() + "/COGO/Login.txt");
        if (folder.exists()) 
        {
        	phone.setVisibility(View.INVISIBLE);
        	password.setVisibility(View.INVISIBLE);
        	bb.setVisibility(View.INVISIBLE);
        	spinner.setVisibility(View.VISIBLE);
        	
        	//getallcoupons();
        	//new AsyncTaskParseJson().execute();
        	this.finish();
        	Intent newpage = new Intent(context,Viewmain.class);
            startActivity(newpage);
        }
        
        
        bb.setOnClickListener(new OnClickListener() {
            @Override
             public void onClick(View arg0) {
               
               File folder = new File(Environment.getExternalStorageDirectory() + "/COGO");
               if (!folder.exists()) 
               {
                    folder.mkdir();
                    File file = new File(folder,"Login.txt");
                    if(!file.exists())
                    {
                    	try 
                    	{
                    			file.createNewFile();
                    	        FileWriter writer = new FileWriter(file);
                    	        writer.append("PhoneNumber:"+phone.getText()+";"+"Password:"+password.getText()+";");
                    	        writer.flush();
                		        writer.close();
                    			
                    	} 
                    	catch (IOException e) 
                    	{
                    		e.printStackTrace();
                    	}
                    }
                    
               }
               else
               {
            	   File file = new File(folder,"Login.txt");
            	   if(!file.exists())
                   {
                   	try 
                   	{
                   			file.createNewFile();
                   	        FileWriter writer = new FileWriter(file);
                   	        writer.append("PhoneNumber:"+phone.getText()+";"+"Password:"+password.getText()+";");
                   	        writer.flush();
               		        writer.close();
                   			
                   	} 
                   	catch (IOException e) 
                   	{
                   		e.printStackTrace();
                   	}
                   }
            	   
               }
               
               
               Intent newpage = new Intent(context,Viewmain.class);
               startActivity(newpage);
              // Intent newpage = new Intent(context,MainActivity.class);
//               /startActivity(newpage);
               
               
               
               
               
            }
     });
       
        
	}

	private void getallcoupons() {
		
		
		File sdcard = Environment.getExternalStorageDirectory();

		//Get the text file
		File file = new File(sdcard,"/COGO/Login.txt");
		StringBuilder text = new StringBuilder();

		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    line=br.readLine();
		    String num = line.split(":")[1];
		    num=num.split(";")[0];
		    
		   
		}
		catch (IOException e) {
		    
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
		 
        final String TAG = "AsyncTaskParseJson.java";
 
        // set your json string url here
        String yourJsonStringUrl = "http://10.2.44.232/cogo/all_coupon.php?phone=9640352177";
 
        // contacts JSONArray
        JSONArray dataJsonArr = null;
 
        @Override
        protected void onPreExecute() {}
       
        @Override
        protected String doInBackground(String... arg0) {
            JsonParser jParser = new JsonParser();
			JSONArray json;
			try {
				
	            File folder = new File(Environment.getExternalStorageDirectory() + "/COGO");
	            File file = new File(folder,"all_coupons.txt");
	            if(file.exists())
	                 file.delete();
	            file.createNewFile();
	            FileWriter writer = new FileWriter(file);
            	writer.append((jParser.getJSONfromURL(yourJsonStringUrl)));
            	writer.flush();
        		writer.close();
	              
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
 
            return null;
        }
        @Override
        protected void onPostExecute(String strFromDoInBg) {}
 
       
    }
	
	
	
	
	
	
	
	

}
