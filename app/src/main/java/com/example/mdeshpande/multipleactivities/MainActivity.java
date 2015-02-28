package com.example.mdeshpande.multipleactivities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.AdapterView;

import Misc.DateReadWrite;
import Misc.DessertFragment;


public class MainActivity extends Activity implements DessertFragment.onDessertClickHandler {

    private Spinner spinner;
    private Button selectActivity;
    private EditText editText;
    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
               int itemId = (int)spinner.getSelectedItemId();
                setSelectedActivity(itemId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }


    public void addActivitiesToSpinner()
    {
        spinner = (Spinner) findViewById(R.id.activities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activities_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        setSelectedActivity(spinner.getSelectedItemPosition());

    }

    public void setSelectedActivity(int itemId)
    {

        DateReadWrite drwObj = new DateReadWrite();
        editText = (EditText)findViewById(R.id.editText);
        switch (itemId)
        {
            case 0:     String savedDate = drwObj.readDateFromFile("date.txt", getBaseContext());
                        editText.setText(savedDate);
                        break;

            case 1:     editText.setText("");
                        break;

            case 2:     FragmentManager fm = getFragmentManager();
                         if (fm.findFragmentById(android.R.id.content) == null)
                         {
                             DessertFragment listMain = new DessertFragment();
                             fm.beginTransaction().add(R.id.fragmentDessert, listMain).commit();
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
                     break;

            case 1 : Intent editIntent = new Intent(MainActivity.this, EditFieldActivity.class);
                     editIntent.putExtra("editText",editText.getText().toString());
                     startActivity(editIntent);
                     break;

            case 2 : Intent listIntent = new Intent(MainActivity.this, DessertActivity.class);
                     listIntent.putExtra("position", selectedPosition);
                     startActivity(listIntent);
                     break;
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDessertSelectedHandler(int position)
    {
        DessertFragment dessertFragmentObj = new DessertFragment();
        dessertFragmentObj.setSelectedPosition(position);
        selectedPosition = position;
    }
}
