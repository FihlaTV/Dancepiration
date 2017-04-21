package com.example.josien.masterthesis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Josien on 21-4-2017.
 */
public class BalletClass extends AppCompatActivity{

    private static final String TAG = BalletClass.class.getName();
    private static final String FILENAME = "b.txt";
    String dancestyle;
    ArrayList<String> responseList = new ArrayList<>();
    String newline = "\r\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ballet_activity);
        parseJSON();
    }
    private void writeToFile(String data) {
        try {
            String separator = System.getProperty("line.separator");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_APPEND));
            outputStreamWriter.append(separator);
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
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                Log.d("JAJAJAJA", "readFromFile() returned: " + ret);
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


        try {
            JSONArray art_objects = json.getJSONArray("record");

            // Loop through the items till it ends
            for (int i = 0; i < art_objects.length(); i++) {
                JSONObject tijd = art_objects.getJSONObject(i);
                String nummer = tijd.getString("Nummer");
                String pasnaam = tijd.getString("Pasnaam");
                dancestyle = tijd.getString("Style");
                String beschrijving = tijd.getString("Beschrijving");
                Log.d("zalhetdan", "parseJSON() returned: " + nummer + " " + pasnaam +  " " +dancestyle + " " + beschrijving);
                if (dancestyle.equals("Ballet")) {
                    writeToFile(beschrijving);

                    responseList.add(beschrijving);
                    String textFromFileString = readFromFile();
                    Log.d("watstaathier", "parseJSON() returned: " + textFromFileString);
                    Log.d("watkomthieruit", "parseJSON() returned: " + responseList);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    Spinner textView = (Spinner)
                            findViewById(R.id.autocomplete);
                    textView.setAdapter(adapter);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    Spinner textView2 = (Spinner)
                            findViewById(R.id.autocomplete2);
                    textView2.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    Spinner textView3 = (Spinner)
                            findViewById(R.id.autocomplete3);
                    textView3.setAdapter(adapter3);

                    ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    Spinner textView4 = (Spinner)
                            findViewById(R.id.autocomplete4);
                    textView4.setAdapter(adapter4);

                    ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    Spinner textView5 = (Spinner)
                            findViewById(R.id.autocomplete5);
                    textView5.setAdapter(adapter5);
                }

            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
    // read json
    // get balletsteps
    // parse it
    // send it to existing steps
}
