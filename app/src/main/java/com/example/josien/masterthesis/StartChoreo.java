package com.example.josien.masterthesis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by Josien on 12-4-2017.
 */
public class StartChoreo extends AppCompatActivity {

    String alles;
    private static final String TAG = StartChoreo.class.getName();
    private static final String FILENAME = "myFile.txt";
    private Spinner startPositie;
    private Spinner dansStijl;
    private Spinner vervolgStap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_choreo);
        this.startPositie = (Spinner) this.findViewById(R.id.startpositie);
        this.dansStijl = (Spinner) this.findViewById(R.id.dansstijl);
        this.vervolgStap = (Spinner) this.findViewById(R.id.vervolgstap);
        parseJSON();
        };

    public void dezetekst(View v) {
        TextView startPos  =  (TextView)startPositie.getSelectedView();
        TextView dansstijl  =  (TextView)dansStijl.getSelectedView();
        TextView vervolgstap  =  (TextView)vervolgStap.getSelectedView();
        String positie = startPos.getText().toString();
        String stijl = dansstijl.getText().toString();
        String stap = vervolgstap.getText().toString();

        alles = positie + " " + stijl + " " + stap;
        Log.d("pleasedoehet", "dezetekst() returned: " + alles);

        writeToFile(alles  + " - ");

        String textFromFileString =  readFromFile();

        if (alles.equals(textFromFileString) )
            Toast.makeText(getApplicationContext(), "both string are equal", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "there is a problem", Toast.LENGTH_SHORT).show();
    }

    private void writeToFile(String data) {
        try {
            Log.d("Iksnaphetnietmeer", "writeToFile() returned: " + alles);
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
            InputStream in = assetManager.open("newjson.json");
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
            JSONObject respObj = json;
            JSONArray art_objects = respObj.getJSONArray("record");

            // Loop through the items till it ends
            for (int i = 0; i < art_objects.length(); i++) {
                JSONObject tijd = art_objects.getJSONObject(i);
                String title = tijd.getString("Nummer");
                String producer = tijd.getString("Lichaamsdeel");
                String longtitle = tijd.getString("Duration"); //duration wordt niet meegepakt omdat de waarde leeg kan zijn (overal dus 0 invullen als het niet van toepassing is)
                String productionplace = tijd.getString("Style");
                Log.d("zalhetdan", "parseJSON() returned: " + title + producer + longtitle + productionplace);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

}

