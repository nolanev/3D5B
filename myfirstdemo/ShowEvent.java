package com.example.ogradyal.myfirstdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;

public class ShowEvent extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        tv = (TextView)findViewById(R.id.list);
        Intent intentShow = getIntent();
        String file = intentShow.getStringExtra(CalendarActivity.EXTRA_EVENT);

        FileInputStream inputStream;
        try{
            inputStream = openFileInput(file);
            int c;
            String temp = "";
            while((c = inputStream.read()) != -1){
                temp =temp + Character.toString((char)c);
            }
            temp = "The Day's Events:\n"+temp+"\n";
            //tv.setText(temp);
            tv.append(temp);
            //Toast.makeText(getBaseContext(),"file read",Toast.LENGTH_SHORT).show();
        }catch(Exception e){

        }
    }
}
