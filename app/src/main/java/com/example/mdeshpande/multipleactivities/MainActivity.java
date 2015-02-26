package com.example.mdeshpande.multipleactivities;

import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.content.Intent;
import java.io.*;
import android.util.Log;
import android.widget.AdapterView;



public class MainActivity extends ActionBarActivity implements DessertFragment.onDessertClickHandler{

    private Spinner spinner;
    private Button selectActivity;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addActivitiesToSpinner();

        selectActivity = (Button)findViewById(R.id.select);
        selectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateSelectedActivity();
            }
        });

        spinner =  (Spinner)findViewById(R.id.activities_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               int itemId = (int)spinner.getSelectedItemId();
                setSelectedDate(itemId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public String read(String fileName){

        String savedDate = "";
        try{
            FileInputStream fin = openFileInput(fileName);
            int c;

            while( (c = fin.read()) != -1){
                savedDate = savedDate + Character.toString((char)c);
            }



        }catch(Exception e){

        }

        return savedDate;
    }

    public void addActivitiesToSpinner() {

        spinner = (Spinner) findViewById(R.id.activities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activities_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setSelectedDate(0);

    }

    public void setSelectedDate(int itemId)
    {

        editText = (EditText)findViewById(R.id.editText);
        switch (itemId)
        {
            case 0:
                String savedDate = read("date.txt");
                editText.setText(savedDate);
                break;
            case 1: editText.setText("");
                    break;
            case 2: FragmentManager fm = getFragmentManager();
                    if (fm.findFragmentById(android.R.id.content) == null) {
                        DessertFragment listMain = new DessertFragment();
                        fm.beginTransaction().add(R.id.mainContainer,listMain)
                                .commit();
                    }
        }
    }

    public void activateSelectedActivity()
    {
        spinner = (Spinner)findViewById(R.id.activities_spinner);
        int activityIndex = spinner.getSelectedItemPosition();


        switch (activityIndex)
        {
            case 0 : Intent datePickerintent = new Intent(MainActivity.this, DatepickerActivity.class);
                     MainActivity.this.startActivity(datePickerintent);
                     //setSelectedDate();
                     break;

            case 1 : Intent editIntent = new Intent(MainActivity.this, EditFieldActivity.class);
                     //editText.setText("");
                     editIntent.putExtra("editText",editText.getText().toString());
                     startActivity(editIntent);
                     break;

            case 2 : Intent listIntent = new Intent(MainActivity.this, DessertActivity.class);
                     MainActivity.this.startActivity(listIntent);
                     break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        //Toast.makeText(activity,position+ " Clicked!", Toast.LENGTH_SHORT).show();

        Log.d("ITEM",String.valueOf(position));

        int selectedPosition = position;
        Bundle bundle = new Bundle();
        bundle.putInt("POS",selectedPosition);

        DessertFragment frg = new DessertFragment();
        frg.setArguments(bundle);

        Log.d("SET",String.valueOf(selectedPosition));

    }
}
