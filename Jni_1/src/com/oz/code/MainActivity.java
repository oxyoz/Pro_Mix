package com.oz.code;

import com.oz.code.nlib.Nutils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		TextView main_txt_info =  (TextView) findViewById(R.id.main_txt_info); 
		
		main_txt_info.setText(""+new Nutils().getString());
		
	}
	
}
