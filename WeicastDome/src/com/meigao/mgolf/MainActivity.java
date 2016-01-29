package com.meigao.mgolf;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends Activity
		implements View.OnClickListener, Handler.Callback,PlatformActionListener
{
	
	private static final int MSG_USERID_FOUND = 1;
	private static final int MSG_LOGIN = 2;
	private static final int MSG_AUTH_CANCEL = 3;
	private static final int MSG_AUTH_ERROR= 4;
	private static final int MSG_AUTH_COMPLETE = 5;
	
	String info ;

	private Button		btn_wxlogin;

	private TextView	txt_info;

	private Handler  UIHandler;
	
	@Override
	public boolean handleMessage(Message msg)
	{
		switch(msg.what) {
			case MSG_USERID_FOUND: {
				Toast.makeText(this, R.string.userid_found, Toast.LENGTH_SHORT).show();
				txt_info.setText(R.string.userid_found);
				
			}
			break;
			case MSG_LOGIN: {
				
				String text = getString(R.string.logining, msg.obj);
				Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
				
				txt_info.setText(text);
				
				System.out.println("---------------");
				
//				Builder builder = new Builder(this);
//				builder.setTitle(R.string.if_register_needed);
//				builder.setMessage(R.string.after_auth);
//				builder.setPositiveButton(R.string.ok, null);
//				builder.create().show();
			}
			break;
			case MSG_AUTH_CANCEL: {
				Toast.makeText(this, R.string.auth_cancel, Toast.LENGTH_SHORT).show();
				txt_info.setText(R.string.auth_cancel);
				System.out.println("-------MSG_AUTH_CANCEL--------");
			}
			break;
			case MSG_AUTH_ERROR: {
				Toast.makeText(this, R.string.auth_error, Toast.LENGTH_SHORT).show();
				txt_info.setText(R.string.auth_error);
				
				System.out.println("-------MSG_AUTH_ERROR--------");
			}
			break;
			case MSG_AUTH_COMPLETE: {
				Toast.makeText(this, R.string.auth_complete, Toast.LENGTH_SHORT).show();
				
				txt_info.setText(R.string.auth_complete);
				System.out.println("--------MSG_AUTH_COMPLETE-------");
			}
			break;
		}
		
		txt_info.setText(info);
		
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		ShareSDK.initSDK(this);
		
		setContentView(R.layout.activity_main);

		UIHandler =new Handler(this);
		
		btn_wxlogin = (Button) findViewById(R.id.btn_wxlogin);

		txt_info = (TextView) findViewById(R.id.txt_info);

		btn_wxlogin.setOnClickListener(this);

	}

	@Override
	protected void onDestroy()
	{
		ShareSDK.stopSDK(this);

		super.onDestroy();
	}
	
	@Override
	public void onClick(View v)
	{

		authorize(new Wechat(this));
		
	}

	private void authorize(Platform plat)
	{	
		if(plat.isValid()) {
			
			String userId = plat.getDb().getUserId();
			
			String name = plat.getDb().getUserName();
			
			String gender = plat.getDb().getUserGender();
			
			String icon = plat.getDb().getUserIcon();
			
			info = "name:"+name+"\ngender:"+gender+"\nicon:"+icon;
			
			if (!TextUtils.isEmpty(userId)) {
				UIHandler.sendEmptyMessage(MSG_USERID_FOUND);
				login(plat.getName(), userId, null);
				return;
			}
		}
		plat.setPlatformActionListener(this);
		plat.SSOSetting(true);
		plat.showUser(null);
	}

	
	private void login(String plat, String userId, HashMap<String, Object> userInfo) {
		Message msg = new Message();
		msg.what = MSG_LOGIN;
		msg.obj = plat;
		UIHandler.sendMessage(msg);
	}
	
	
	@Override
	public void onCancel(Platform platform, int action) {
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(MSG_AUTH_CANCEL);
			
		}
	}

	@Override
	public void onComplete(Platform platform, int action,
			HashMap<String, Object> res) {
		
		String name = platform.getDb().getUserName();
		
		String gender = platform.getDb().getUserGender();
		
		String icon = platform.getDb().getUserIcon();
		
		info = "name:"+name+"\ngender:"+gender+"\nicon:"+icon;
		
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(MSG_AUTH_COMPLETE);
			login(platform.getName(), platform.getDb().getUserId(), res);
		}
		System.out.println(res);
		System.out.println("------User Name ---------" + platform.getDb().getUserName());
		System.out.println("------User ID ---------" + platform.getDb().getUserId());
	}

	@Override
	public void onError(Platform platform, int action, Throwable t) {
		if (action == Platform.ACTION_USER_INFOR) {
			UIHandler.sendEmptyMessage(MSG_AUTH_ERROR);
		}
		t.printStackTrace();
	}


}
