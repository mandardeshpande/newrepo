package com.example.mdeshpande.multipleactivities;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.Toast;

import Misc.DessertListItem;


public class DessertActivity extends Activity implements DessertFragment.onDessertClickHandler {

    private Button goBack;
    Activity activity;
    int selectedPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert);


        selectedPosition = getIntent().getIntExtra("position", -1);
        Log.d("POSACT",String.valueOf(selectedPosition));


        FragmentManager fm = getFragmentManager();

        if (fm.findFragmentById(android.R.id.content) == null) {
            DessertFragment list = new DessertFragment();
            list.setSelectedPosition(selectedPosition);
            fm.beginTransaction().add(R.id.testContainer,list)
                    .commit();
        }



        goBack = (Button)findViewById(R.id.back);
        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){  finish();}
        });



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


    @Override
    public void onDessertSelectedHandler(int position) {

        DessertFragment desFragment = new DessertFragment();
        desFragment.setSelectedPosition(position);
        selectedPosition = position;
        Log.d("POSDESSERT",String.valueOf(position));

    }
}
