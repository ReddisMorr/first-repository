package com.example.bubblebreaker;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class BBMain extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bb_layout);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mBBView = (BBView) findViewById(R.id.bubblebreaker);
		mBBView.mBBSettings=(BBSettings)getIntent().getSerializableExtra("settings");
		Log.d("TAG", mBBView.mBBSettings.toString() );
		mBBView.initBBView();
		mBBView.setTextView((TextView) findViewById(R.id.text));
		mBBView.setMode(BBConstants.RUNNING);
	}


	private BBView mBBView;
}