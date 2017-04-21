package com.example.josien.masterthesis;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.Random;

/**
 * Created by Josien on 21-4-2017.
 */
public class BalletClass extends AppCompatActivity{

    private static final String TAG = BalletClass.class.getName();
    private static final String FILENAME = "b.txt";
    String dancestyle;
    ArrayList<String> responseList = new ArrayList<>();
    String bestaandechoreo;
    String nieuwesuggestie;
    Spinner textView;
    Spinner textView2;
    Spinner textView3;
    Spinner textView4;
    Spinner textView5;
    JSONArray art_objects;



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
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
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
            art_objects = json.getJSONArray("record");

            // Loop through the items till it ends
            for (int i = 0; i < art_objects.length(); i++) {
                JSONObject tijd = art_objects.getJSONObject(i);
                String nummer = tijd.getString("Nummer");
                String pasnaam = tijd.getString("Pasnaam");
                dancestyle = tijd.getString("Style");
                String beschrijving = tijd.getString("Beschrijving");
                if (dancestyle.equals("Ballet")) {
                    writeToFile(beschrijving);

                    responseList.add(beschrijving);
                    String textFromFileString = readFromFile();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    textView = (Spinner)
                            findViewById(R.id.autocomplete);
                    textView.setAdapter(adapter);

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    textView2 = (Spinner)
                            findViewById(R.id.autocomplete2);
                    textView2.setAdapter(adapter2);

                    ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    textView3 = (Spinner)
                            findViewById(R.id.autocomplete3);
                    textView3.setAdapter(adapter3);

                    ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    textView4 = (Spinner)
                            findViewById(R.id.autocomplete4);
                    textView4.setAdapter(adapter4);

                    ArrayAdapter<String> adapter5 = new ArrayAdapter<>(this,
                            android.R.layout.simple_dropdown_item_1line, responseList);
                    textView5 = (Spinner)
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

    public void okey (View view) throws JSONException {

        TextView stap1  =  (TextView)textView.getSelectedView();
        TextView stap2  =  (TextView)textView2.getSelectedView();
        TextView stap3  =  (TextView)textView3.getSelectedView();
        TextView stap4  =  (TextView)textView4.getSelectedView();
        TextView stap5  =  (TextView)textView5.getSelectedView();
        String Stap1 = stap1.getText().toString();
        String Stap2 = stap2.getText().toString();
        String Stap3 = stap3.getText().toString();
        String Stap4 = stap4.getText().toString();
        String Stap5 = stap5.getText().toString();

        ArrayList<String> List = new ArrayList<>();
        List.add(Stap1);
        List.add(Stap2);
        List.add(Stap3);
        List.add(Stap4);
        List.add(Stap5);

        bestaandechoreo = Stap1 + "\r\n" + Stap2 + "\r\n" + Stap3 + "\r\n" + Stap4 + "\r\n" + Stap5;

        Random random = new Random();
        String R = List.get(random.nextInt(List.size()));
        Log.d("Paktierandom?", "okey() returned: " + R);
        String pas1 = responseList.get(random.nextInt(responseList.size()));
        Log.d("Randompas?", "okey() returned: " + pas1);
        if (List.contains(pas1)){
            Log.d("zitalindelijst", "okey() returned: " + pas1 + R);
            pas1= responseList.get(random.nextInt(responseList.size()));
        }
        if (R.equals(pas1)){
            Log.d("dezelfdepassen", "okey() returned: " + pas1 + R);
            pas1 = responseList.get(random.nextInt(responseList.size()));
        }

            AlertDialog.Builder builder = new AlertDialog.Builder(BalletClass.this);
            builder
                    .setMessage("Dit is nu je choreografie: \r\n" + bestaandechoreo +
                            "\r\nverander " + R + " naar: " + pas1)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .show();
        }


        //R = R.replace(R, pas1);


    public void algorithm (View view){
        //do something really smart
    }

}
