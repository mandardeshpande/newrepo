package com.example.mdeshpande.multipleactivities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.content.Context;
import android.widget.EditText;
import android.util.Log;

public class EditFieldActivity extends ActionBarActivity {

    private Button hide;
    private Button goBack;
    private EditText editTextObj;
    private EditText focusText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_field);

        hide = (Button)findViewById(R.id.hide);
        goBack = (Button)findViewById(R.id.back);

        Intent curIntent = getIntent();
        String editTextValue = curIntent.getStringExtra("editText");

        editTextObj = (EditText)findViewById(R.id.textbox1);
        editTextObj.setText(editTextValue);

        focusText = (EditText)findViewById(R.id.textbox3);
        focusText.setFocusable(true);
        focusText.requestFocus();



        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

            }
        });

        goBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_field, menu);
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
}
