package Misc;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;

/**
 * Created by mdeshpande on 2/21/15.
 */

public class AppMisc {

    public String readDateFromFile(String fileName)
    {
        String savedDate = "";
        try
        {
            FileInputStream fin = new FileInputStream(fileName);

            int fileSize;

            while( (fileSize = fin.read()) != -1)
            {
                savedDate = savedDate + Character.toString((char)fileSize);
            }
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return savedDate;
    }

    public int saveData(String date,String fileName)
    {
        FileOutputStream outputStream;
        File f = new File(fileName);
        try
        {
            if(!f.exists())
            {
                f.createNewFile();
            }
            outputStream = new FileOutputStream(fileName);
            outputStream.write(date.getBytes());
            Log.d("FILE", date);
            outputStream.close();

            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }
}
