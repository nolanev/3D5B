package com.example.ogradyal.myfirstdemo;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;
import android.content.Context;

import java.io.FileOutputStream;




public class MainActivity extends AppCompatActivity {
    private PendingIntent pendingIntent;
    public final static String EXTRA_MESSAGE = "com.example.Myfirstdemo.MESSAGE";
    TimePicker timePicker;
    DatePicker datePicker;
    DatePicker.OnDateChangedListener  onDateChangedListener;
    String time;
    String date;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        int i = preferences.getInt("numberoflaunches", 1);

        if (i < 2){
            alarmMethod();
            i++;
            editor.putInt("numberoflaunches", i);
            editor.commit();
        }


        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        //datePicker.init(17,01,17, new OnDateChangedListener());
        //datePicker.init( dategetYear(), monthOfYear, dayOfMonth, onDateChangedListener);

    }



    public void enterMessage(View view){
        date = "" + datePicker.getDayOfMonth() + "_" + datePicker.getMonth();
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayReminder.class);
        EditText editText = (EditText)findViewById(R.id.enterReminder);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void enterEvent(View view){
        date = "" + datePicker.getDayOfMonth() + "_" + datePicker.getMonth();



        //time = "" + timePicker.getHour() + ":" + timePicker.getMinute();
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
        EditText editText = (EditText) findViewById(R.id.enterReminder);
        String message = editText.getText().toString();
        message = message+ "\n";
        tv = (TextView)findViewById(R.id.list);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(date, Context.MODE_APPEND);
            outputStream.write(message.getBytes());
            outputStream.close();
            //Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void alarmMethod(){
        Intent alarmintent = new Intent(this, NotifyService.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(this, 0, alarmintent, 0);


        //pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,alarmintent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 29);
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        //calendar.set(Calendar.AM_PM, Calendar.AM);
        //calendar.add(Calendar.DAY_OF_MONTH, 1);
        //calendar.set(2017,5,1,19,55,00);
        long when = calendar.getTimeInMillis();         // notification time

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
       // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
        Toast.makeText(MainActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();

    }

    public void selectCal(View view){
        Intent intentCal = new Intent(this, CalendarActivity.class);
        startActivity(intentCal);
    }



}
