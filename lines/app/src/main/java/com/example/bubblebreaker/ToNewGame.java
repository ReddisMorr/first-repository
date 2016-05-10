package com.example.bubblebreaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ToNewGame extends Activity implements OnClickListener {
	Button btn;
	TextView tv;
	BBSettings bbSettings=new BBSettings();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newgame);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.TV);
		bbSettings.setBubble_diameter(40);
		bbSettings.setBubble_color(6);
		bbSettings=(BBSettings)getIntent().getSerializableExtra("settings0");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn:
			     Intent intent = new Intent(getApplicationContext(), com.example.bubblebreaker.BBMain.class);
				 intent.putExtra("settings",bbSettings);
			     startActivity(intent);
			     break;
			case R.id.btn1:
				Intent intent1 = new Intent(getApplicationContext(), com.example.bubblebreaker.BBSetSettings.class);
				startActivity(intent1);
                break;
		}

	}

}
