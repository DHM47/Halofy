package com.dhm47.halofy;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class Shortcut extends Activity {
	@Override
	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		
		String[] list={
			"Full Screen",
			"Halo"};
		
		
		AlertDialog.Builder choose =new AlertDialog.Builder(this);
		choose.setTitle("Choose Shourtcut")
		.setOnCancelListener( new DialogInterface.OnCancelListener() {public void onCancel(DialogInterface arg0) {finish();}})
		.setItems(list, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case 0 :{
					Intent shortcutIntent =new Intent()  ;
					shortcutIntent.setComponent(new ComponentName("com.dhm47.halofy","com.dhm47.halofy.FullScreen"));
					// this is a broadcast to send to Android API our target intent
					Intent addIntent = new Intent();
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
					// Place target intent in extras to let broadcast get target intent
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "FullScreen");
					// let broadcast get the name of Shortcut
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON,R.drawable.ic_launcher);
					// Hack to Convert drawable to bitmapdrawable to bitmap & add in extras
					setResult(RESULT_OK, addIntent);
					finish();}
				
				case 1 :{
					Intent shortcutIntent =new Intent()  ;
					shortcutIntent.setComponent(new ComponentName("com.dhm47.halofy","com.dhm47.halofy.Halo"));
					// this is a broadcast to send to Android API our target intent
					Intent addIntent = new Intent();
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
					// Place target intent in extras to let broadcast get target intent
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Halofy");
					// let broadcast get the name of Shortcut
					addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON,R.drawable.ic_launcher);
					// Hack to Convert drawable to bitmapdrawable to bitmap & add in extras
					setResult(RESULT_OK, addIntent);
					finish();}
				
				}
				}
		});
		
		AlertDialog alert1 = choose.create();
		alert1.show();
		
	}
	
	protected void onActivityResult(int args1, int args2, Intent intent) {
		setResult(RESULT_OK, intent);
		finish();
	}
}