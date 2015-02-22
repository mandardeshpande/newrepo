package com.example.mdeshpande.multipleactivities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import  android.widget.Button;
import android.widget.DatePicker;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import java.io.*;
import android.widget.Toast;


public class DatepickerActivity extends ActionBarActivity {

    private Button select_date;
    final Context context = this;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);

        String latestDate = read();

        if(latestDate != "")
        {
            int month = Integer.parseInt(latestDate.split("-")[0].substring(1))-1;
            int day = Integer.parseInt(latestDate.split("-")[1]);
            int year = Integer.parseInt(latestDate.split("-")[2]);

            datePicker = (DatePicker) findViewById(R.id.datePicker);
            datePicker.init(year,month,day,null);

        }
        select_date = (Button)findViewById(R.id.set_date);
        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Are you sure you want to save?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click Ok to save!")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                datePicker = (DatePicker) findViewById(R.id.datePicker);

                                int day = datePicker.getDayOfMonth();
                                int month = datePicker.getMonth() + 1;
                                int year = datePicker.getYear();
                                String formattedMonth = (month <= 9) ? "0" + month : String.valueOf(month);
                                String formattedDate = formattedMonth + "-" + day + "-" + year;
                                Log.d("Date", formattedDate);



                                saveData(formattedDate);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    public String read(){
        String savedDate = "";
        try{
            FileInputStream fin = openFileInput("date.txt");
            int c;

            while( (c = fin.read()) != -1){
                savedDate = savedDate + Character.toString((char)c);
            }

        }catch(Exception e){

        }

        return savedDate;
    }

    public void saveData(String date)
    {
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput("date.txt", Context.MODE_PRIVATE);
                outputStream.write(date.getBytes());
                Log.d("FILE", date);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //display file saved message
            Toast.makeText(getBaseContext(), "Date saved successfully!",
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datepicker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
