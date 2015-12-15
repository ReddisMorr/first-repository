package com.example.thi.culculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  public EditText fr_num;
    public EditText fr_den;
    public EditText act;
    public EditText sc_num;
    public EditText sc_den;
    public TextView fin_num;
    public TextView fin_den;
    public TextView tir;
    public Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fr_num=(EditText)findViewById(R.id.fr_num);
        fr_den=(EditText)findViewById(R.id.fr_den);
        sc_num=(EditText)findViewById(R.id.sc_num);
        sc_den=(EditText)findViewById(R.id.sc_den);
        act=(EditText)findViewById(R.id.act);
        tir=(TextView)findViewById(R.id.tir);
        fin_num=(TextView)findViewById(R.id.fin_num);
        fin_den=(TextView)findViewById(R.id.fin_den);
        btn=(Button)findViewById(R.id.btn);
    }
    public void onClick(View v){
        fin_den.setText("");
        tir.setText("");
        fin_num.setText("");
        int frn=0, frd=1, scn=0, scd=1;
        try {
            frn = Integer.parseInt(fr_num.getText().toString());
            frd = Integer.parseInt(fr_den.getText().toString());
            scn = Integer.parseInt(sc_num.getText().toString());
            scd = Integer.parseInt(sc_den.getText().toString());
        }catch (NumberFormatException e) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пожалуйста, введите число, а не прочие символы", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(frd==0||scd==0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Введен неправильный знаменатель", Toast.LENGTH_SHORT);
            toast.show();
        }else {
            Fraction first = new Fraction(frn, frd);
            Fraction second = new Fraction(scn, scd);
            first.add(second);
            int f=first.getNumerator();
            String t=Integer.toString(f);
            fin_num.setText(t);
            if(first.getDenominator()!=1){
                tir.setText("--------");
                f=first.getDenominator();
                t =Integer.toString(f);
                fin_den.setText(t);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Т.к. знаменатель дроби равен 1, дробь равна ее числителю.", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
