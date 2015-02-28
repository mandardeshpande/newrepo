package Misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by mdeshpande on 2/21/15.
 */

public class DateReadWrite
{

    public String readDateFromFile(String fileName,Context context)
    {
        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("File", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("FIle", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public int saveData(String date,String fileName,Context context)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(date);
            outputStreamWriter.close();
            Toast.makeText(context, "Date saved successfully!",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("WROTTE", "File write failed: " + e.toString());
        }

        return 0;
    }
}
