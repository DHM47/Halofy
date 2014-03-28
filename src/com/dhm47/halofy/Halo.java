package com.dhm47.halofy;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;


public class Halo extends Activity {
	public static final int FLAG_FLOATING_WINDOW = 0x00002000;
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context context =  getBaseContext();
		SharedPreferences setting = getPreferences(0);
		boolean firstrun =setting.getBoolean("firstrun", true);
		if (firstrun){
			
			AlertDialog.Builder firsttime =new AlertDialog.Builder(this, 16973947);
			firsttime.setMessage("Making an app a floating window is only possable at launch. So in order to do that the app has to be REST \n\n *progress may be lost ");
			firsttime.setPositiveButton("Halofy", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					
					SharedPreferences setting = getPreferences(0);
					setting.edit().putBoolean("firstrun", false).commit();
					
					try {
						ActivityManager am = (ActivityManager) Halo.this.getSystemService(ACTIVITY_SERVICE);
						RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(5).get(1);
						String foregroundTaskPackageName = foregroundTaskInfo .topActivity.getPackageName();
						Intent intent =getPackageManager().getLaunchIntentForPackage(foregroundTaskPackageName);
						intent.addFlags(FLAG_FLOATING_WINDOW);
						intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
						getApplication().startActivity(intent);
					} catch (Exception e) {
						Toast.makeText(context, "Halofy : Fialed", Toast.LENGTH_SHORT).show();
					}
					
					finish();
				}
			});
			firsttime.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					finish();
				}
			});
			AlertDialog alert1 = firsttime.create();
			alert1.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alert1.show();
			
		}
		else {
			try {
				ActivityManager am = (ActivityManager) Halo.this.getSystemService(ACTIVITY_SERVICE);
				RunningTaskInfo foregroundTaskInfo = am.getRunningTasks(5).get(1);
				String foregroundTaskPackageName = foregroundTaskInfo .topActivity.getPackageName();
				RunningTaskInfo backgroundTaskInfo = am.getRunningTasks(5).get(2);
				String backgroundTaskPackageName = backgroundTaskInfo .topActivity.getPackageName();
				Intent intent1 =getPackageManager().getLaunchIntentForPackage(backgroundTaskPackageName);
				Intent intent =getPackageManager().getLaunchIntentForPackage(foregroundTaskPackageName);
					intent.addFlags(FLAG_FLOATING_WINDOW);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				getApplication().startActivity(intent1);
				getApplication().startActivity(intent);
			} catch (Exception e) {
				Toast.makeText(this, "Halofy : Fialed", Toast.LENGTH_SHORT).show();
			}
			finish();
		}
		
		
	}
	
}
