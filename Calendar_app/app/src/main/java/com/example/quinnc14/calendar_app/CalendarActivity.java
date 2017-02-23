    package com.example.quinnc14.calendar_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.CalendarView;
import android.widget.Toast;

public class CalendarActivity extends AppCompatActivity {
    public final static String EXTRA_EVENT = "com.example.myfirstdemo.EVENT";
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView1);
        calendarView.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                date = "" + dayOfMonth +"_" + month; //file name
                String day = "" +dayOfMonth;
                Toast.makeText(getApplicationContext(), day, Toast.LENGTH_SHORT).show();// TODO Auto-generated method stub

                Intent intentShow = new Intent(view.getContext(), ShowEvent.class);
                intentShow.putExtra(EXTRA_EVENT, date);
                startActivity(intentShow);

            }
        });

    }

}