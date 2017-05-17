package com.example.josien.masterthesis;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
import java.util.List;

/**
 * Created by Josien on 27-4-2017.
 */
public class ChangeStep extends AppCompatActivity {

    JSONArray art_objects;
    String dancestyle;
    String typepas;
    String beschrijving;
    String dansstijl2;
    String returns;
    ArrayList<String> BalletList = new ArrayList<>();
    ArrayList<String> ModernList = new ArrayList<>();
    ArrayList<String> StreetdanceList = new ArrayList<>();
    ArrayList<String> beginPos = new ArrayList<>();
    Spinner Ballet;
    Spinner Modern;
    Spinner Streetdance;
    Spinner textView;
    Spinner general;
    String dansstijl;
    String[] dansstijlen;
    String sjo="";
    List<String> c;
    List<String> c2;
    List<String> c3;
    List<List<String>> Balletl = new ArrayList<>();
    List<List<String>> Modernl = new ArrayList<>();
    List<List<String>> Streetdancel = new ArrayList<>();
    String[] combi;
    String[] combi2;
    String[] combi3;
    ArrayList<String> Po = new ArrayList<>();
    String ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changestep);
        this.textView = (Spinner) this.findViewById(R.id.spinnerdansstijl);
        Ballet = (Spinner) this.findViewById(R.id.spinnerdansstappen);
        Modern = (Spinner) this.findViewById(R.id.spinnerdansstappen);
        Streetdance = (Spinner) this.findViewById(R.id.spinnerdansstappen);

        general = (Spinner) this.findViewById(R.id.spinnerdansstappen);
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
                dancestyle = tijd.getString("Style");
                typepas = tijd.getString("TypePas");
                beschrijving = tijd.getString("Beschrijving");

                if (dancestyle.equals("Ballet")) {
                    BalletList.add(beschrijving);
                    Log.d("lijst1", "onCreate() returned: " + BalletList);
                    combi = new String[]{typepas + "," + beschrijving};
                    c = Arrays.asList(combi);

                    Balletl.add(c);
                }
                if (dancestyle.equals("Modern")) {
                    ModernList.add(beschrijving);
                    Log.d("lijst2", "onCreate() returned: " + ModernList);
                    combi2 = new String[]{typepas + "," + beschrijving};
                    c2 = Arrays.asList(combi2);

                    Modernl.add(c2);
                }
                if (dancestyle.equals("Streetdance")) {
                    StreetdanceList.add(beschrijving);
                    combi3 = new String[]{typepas + "," + beschrijving};
                    c3 = Arrays.asList(combi3);

                    Streetdancel.add(c3);
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
    public void variaties(View v) throws InterruptedException {
        TextView danspas = (TextView) general.getSelectedView();
        TextView dansstyle = (TextView) textView.getSelectedView();
        dansstijl = dansstyle.getText().toString();
        ok = danspas.getText().toString();
        Log.d("okok", "variaties() returned: " + ok);
        Log.d("okokok", "variaties() returned: " + dansstijl);


        String sja;


        Po.clear();
        Log.d("geeen", "variaties() returned: " + Po);

        if (dansstijl.equals("Ballet")) {
            for (List<String> algor : Balletl) {
                if (algor.toString().contains(ok)) {
                    String r[] = algor.toString().split(",");
                    returns = r[r.length - 1];
                    sja = returns.substring(0, returns.length() - 1);
                    if (sja.equals(ok)) {
                        Log.d("sjaaa", "variaties() returned: " + sja);
                        sjo = r[0];
                        sjo = sjo.substring(1);
                        Log.d("sjooo", "variaties() returned: " + sjo);
                    }
                }
            }
        }
        if (dansstijl.equals("Modern")) {
            for (List<String> algor : Modernl) {
                if (algor.toString().contains(ok)) {
                    String r[] = algor.toString().split(",");
                    returns = r[r.length - 1];
                    sja = returns.substring(0, returns.length() - 1);
                    if (sja.equals(ok)) {
                        Log.d("sjaaa", "variaties() returned: " + sja);
                        sjo = r[0];
                        sjo = sjo.substring(1);
                        Log.d("sjooo", "variaties() returned: " + sjo);
                    }
                }
            }
        }
        if (dansstijl.equals("Streetdance")) {
            for (List<String> algor : Streetdancel) {
                if (algor.toString().contains(ok)) {
                    String r[] = algor.toString().split(",");
                    returns = r[r.length - 1];
                    sja = returns.substring(0, returns.length() - 1);
                    if (sja.equals(ok)) {
                        Log.d("sjaaa", "variaties() returned: " + sja);
                        sjo = r[0];
                        sjo = sjo.substring(1);
                        Log.d("sjooo", "variaties() returned: " + sjo);
                    }
                }
            }
        }
        if (sjo.equals("Jump") || sjo.equals("General") || sjo.equals("Turn") || sjo.equals("Startpositie")
                || sjo.equals("Battement")) {
            parttwo();
        }
    }
    public void parttwo(){
        if (dansstijl.equals("Ballet")) {
            for (List<String> algor : Balletl) {
                if (algor.toString().contains(sjo)) {
                    Log.d("tesnel", "variaties() returned: " + sjo);
                    String re[] = algor.toString().split(",");
                    returns = re[re.length - 1];
                    returns = returns.substring(0, returns.length() - 1);
                    Log.d("hoedan", "variaties() returned: " + returns);
                    Po.add(returns);
                    Log.d("geenidee", "variaties() returned: " + Po);
                }
            }
        }
        if (dansstijl.equals("Modern")) {
            for (List<String> algor : Modernl) {
                if (algor.toString().contains(sjo)) {
                    Log.d("tesnel", "variaties() returned: " + sjo);
                    String re[] = algor.toString().split(",");
                    returns = re[re.length - 1];
                    returns = returns.substring(0, returns.length() - 1);
                    Log.d("hoedan", "variaties() returned: " + returns);
                    Po.add(returns);
                    Log.d("geenidee", "variaties() returned: " + Po);
                }
            }
        }
        if (dansstijl.equals("Streetdance")) {
            for (List<String> algor : Streetdancel) {
                if (algor.toString().contains(sjo)) {
                    Log.d("tesnel", "variaties() returned: " + sjo);
                    String re[] = algor.toString().split(",");
                    returns = re[re.length - 1];
                    returns = returns.substring(0, returns.length() - 1);
                    Log.d("hoedan", "variaties() returned: " + returns);
                    Po.add(returns);
                    Log.d("geenidee", "variaties() returned: " + Po);
                }
            }
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(ChangeStep.this, R.style.AlertDialogCustom);
        //final AlertDialog.Builder builder = new AlertDialog.Builder(BalletClass.this);
        builder
                .setTitle("VARIATIES")
                .setMessage(Html.fromHtml("<h3>"+"Dit zijn variaties op "+ ok +"</h3>" + ": " + "\r\n" + "<br>" + "<h1>"+Po+"</h1>"))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })
                .show();
/*
            final AlertDialog.Builder builder = new AlertDialog.Builder(ChangeStep.this);
            builder
                    .setMessage("Dit zijn variaties op " + ok + ": " + Po)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    })
                    .show();
                    */
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