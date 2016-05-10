package com.example.bubblebreaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;


/**
 * Created by THI on 20.04.2016.
 */
public class BBSetSettings extends Activity implements View.OnClickListener {
   public Button back;
   public BBSettings settings1=new BBSettings();
   public RadioGroup colors;
   public RadioGroup bubbles;
   public CheckBox bombBubble;
//    RadioButton color3=(RadioButton)findViewById(R.id.colors3);
//    RadioButton color4=(RadioButton)findViewById(R.id.colors4);
//    RadioButton color5=(RadioButton)findViewById(R.id.colors5);
//    RadioButton color6=(RadioButton)findViewById(R.id.colors6);
//    RadioButton bubble1=(RadioButton)findViewById(R.id.bubbleFew);
//    RadioButton bubble2=(RadioButton)findViewById(R.id.bubbleMid);
//    RadioButton bubble3=(RadioButton)findViewById(R.id.bubbleMany);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        back=(Button) findViewById(R.id.back);
        colors=(RadioGroup)findViewById(R.id.colors);
        bubbles=(RadioGroup)findViewById(R.id.bubbles);
        bombBubble=(CheckBox)findViewById(R.id.bomb01);
        colors.clearCheck();
        bubbles.clearCheck();
        colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case -1:
                        settings1.setBubble_color(6);
                        break;
                    case R.id.colors3:
                        settings1.setBubble_color(3);
                        break;
                    case R.id.colors4:
                        settings1.setBubble_color(4);
                        break;
                    case R.id.colors5:
                        settings1.setBubble_color(5);
                        break;
                    case R.id.colors6:
                        settings1.setBubble_color(6);
                        break;
                    default:
                        break;
                }
            }
        });
        bubbles.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case -1:
                        settings1.setBubble_diameter(40);
                        break;
                    case R.id.bubbleFew:
                        settings1.setBubble_diameter(120);
                        break;
                    case R.id.bubbleMid:
                        settings1.setBubble_diameter(80);
                        break;
                    case R.id.bubbleMany:
                        settings1.setBubble_diameter(40);
                        break;
                    default:
                        break;
                }
            }
        });
        back.setOnClickListener(this);
        if(bombBubble.isChecked()){
            settings1.setBomb(true);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                Intent intent = new Intent(getApplicationContext(), com.example.bubblebreaker.ToNewGame.class);
                intent.putExtra("settings0",settings1);
                startActivity(intent);
                break;

        }

    }

}
