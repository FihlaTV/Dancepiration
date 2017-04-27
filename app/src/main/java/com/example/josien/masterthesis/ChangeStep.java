package com.example.josien.masterthesis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Josien on 27-4-2017.
 */
public class ChangeStep extends AppCompatActivity {

    JSONArray art_objects;
    String dancestyle;
    String typepas;
    String beschrijving;
    String dansstijl2;
    ArrayList<String> BalletList = new ArrayList<>();
    ArrayList<String> ModernList = new ArrayList<>();
    ArrayList<String> StreetdanceList = new ArrayList<>();
    ArrayList<String> beginPos = new ArrayList<>();
    Spinner Ballet;
    Spinner Modern;
    Spinner Streetdance;
    Spinner textView;
    Spinner general;
    String[] dansstijlen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changestep);
        this.textView = (Spinner) this.findViewById(R.id.spinnerdansstijl);
        Ballet = (Spinner) this.findViewById(R.id.spinnerdansstappen);
        Modern = (Spinner) this.findViewById(R.id.spinnerdansstappen);
        Streetdance = (Spinner) this.findViewById(R.id.spinnerdansstappen);

        //this.general = (Spinner) this.findViewById(R.id.spinnerdansstappen);
        dansstijlen = new String[]{"Ballet", "Modern", "Streetdance"};
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
                typepas = tijd.getString("TypePas");
                beschrijving = tijd.getString("Beschrijving");

                if (dancestyle.equals("Ballet")) {
                    BalletList.add(beschrijving);
                    Log.d("lijst1", "onCreate() returned: " + BalletList);
                }
                if (dancestyle.equals("Modern")){
                    ModernList.add(beschrijving);
                    Log.d("lijst2", "onCreate() returned: " + ModernList);
                }
                if (dancestyle.equals("Streetdance")){
                    StreetdanceList.add(beschrijving);
                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        final ArrayAdapter<String> ballet =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,BalletList);
        final ArrayAdapter<String> modern =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ModernList);
        final ArrayAdapter<String> streetdance =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,StreetdanceList);

        textView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (item.equals("Ballet")){
                    Ballet.setAdapter(ballet);
                }
                if (item.equals("Modern")){
                    Modern.setAdapter(modern);
                }
                if (item.equals("Streetdance")){
                    Streetdance.setAdapter(streetdance);
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
    public void variaties(View v){
        TextView danspas = (TextView)general.getSelectedView();
    }

    private String getJSONString(Context context) {
        String str = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("2304.json");
            InputStreamReader isr = new InputStreamReader(in);

            char[] inputBuffer = new char[100];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                str += readString;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return str;
}
}