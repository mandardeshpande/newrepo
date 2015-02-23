package com.example.mdeshpande.multipleactivities;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;

import Misc.DessertListItem;


public class DessertActivity extends ActionBarActivity {

    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);

        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(android.R.id.content) == null) {
            DessertFragment list = new DessertFragment();
            fm.beginTransaction().add(R.id.container,list)
                    .commit();
        }
        /*
        goBack = (Button)findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Log.d("back","Go back in dessert");
            }
        });*/
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Log.d("back","backPressed");
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dessert, menu);
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
