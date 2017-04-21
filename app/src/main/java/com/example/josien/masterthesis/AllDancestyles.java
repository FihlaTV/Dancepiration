package com.example.josien.masterthesis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * Created by Josien on 21-4-2017.
 */
public class AllDancestyles extends AppCompatActivity {


    private static final String TAG = AllDancestyles.class.getName();
    private static final String FILENAME = "allstyles.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_styles);
        parseJSON();
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }

    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput(FILENAME);

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
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }

        Log.d("stom", "readFromFile() returned: " + ret);
        return ret;
    }

    private String getJSONString(Context context)
    {
        String str = "";
        try
        {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("bestaandepassen.json");
            InputStreamReader isr = new InputStreamReader(in);
            char [] inputBuffer = new char[100];

            int charRead;
            while((charRead = isr.read(inputBuffer))>0)
            {
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                str += readString;
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return str;
    }

    public void parseJSON()
    {
        JSONObject json = new JSONObject();

        try {
            json = new JSONObject(getJSONString(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("ikbenbenieuwd", "parseJSON() returned: " + json);


        try {
            JSONArray art_objects = json.getJSONArray("record");

            // Loop through the items till it ends
            for (int i = 0; i < art_objects.length(); i++) {
                JSONObject tijd = art_objects.getJSONObject(i);
                String nummer = tijd.getString("Nummer");
                String pasnaam = tijd.getString("Pasnaam");
                String dancestyle = tijd.getString("Style");
                String beschrijving = tijd.getString("Beschrijving");
                Log.d("zalhetdan", "parseJSON() returned: " + nummer + " " + pasnaam +  " " +dancestyle + " " + beschrijving);
            }
            Random random = new Random();
            String pas1 = art_objects.getJSONObject(random.nextInt(art_objects.length())).getString("Beschrijving");
            String pas2 = art_objects.getJSONObject(random.nextInt(art_objects.length())).getString("Beschrijving");
            String pas3 = art_objects.getJSONObject(random.nextInt(art_objects.length())).getString("Beschrijving");
            String pas4 = art_objects.getJSONObject(random.nextInt(art_objects.length())).getString("Beschrijving");
            String pas5 = art_objects.getJSONObject(random.nextInt(art_objects.length())).getString("Beschrijving");

            Log.d("randomizer", "parseJSON() returned: " + pas1 + " " + pas2 + " " + pas3 + " " + pas4 + " " + pas5);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
