package com.example.cogo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{
	
	private final Activity context;
	private final String[] web;
	private final int[] im;
	private final String[] statement;
	
	public CustomList(Activity context,String[] web,int[] im,String[] statement) 
	{
			super(context, R.layout.list_row,web);
			this.context = context;
			this.web=web;
			this.im=im;
			this.statement=statement;
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
			LayoutInflater inflater = context.getLayoutInflater();
			View rowView= inflater.inflate(R.layout.list_row, null, true);
			TextView textview1 = (TextView) rowView.findViewById(R.id.TextView1);
			textview1.setText(web[position]);
			Button button = (Button) rowView.findViewById(R.id.button1);
		    button.setTag(position);
		    ImageView icon = (ImageView)rowView.findViewById(R.id.imageView1);  
		    icon.setImageResource(im[position]);
			return rowView;
	}
}
