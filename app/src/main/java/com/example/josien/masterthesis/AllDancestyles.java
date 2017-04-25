package com.example.josien.masterthesis;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import java.util.Random;

/**
 * Created by Josien on 21-4-2017.
 */
public class AllDancestyles extends AppCompatActivity {

    /**
     * Created by Josien on 21-4-2017.
*/

        String dancestyle;
        String typepas;
        String beschrijving;
        String[] combi;
        ArrayList<String> responseList = new ArrayList<>();
        ArrayList<String> beginPos = new ArrayList<>();
        ArrayList<String> Typepas;
        List<String> c;
        List<List<String>> Algo2 = new ArrayList<>();
        String pas1;
        String pas2 = "";
        android.widget.Spinner textView;
        Spinner textView2;
        Spinner textView3;
        Spinner textView4;
        Spinner textView5;
        Spinner Spinner;
        Spinner Spinner2;
        Spinner Spinner3;
        Spinner Spinner4;
        Spinner Spinner5;
        Spinner Spinner6;
        JSONArray art_objects;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.all_styles);
            parseJSON();
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

        public void parseJSON() {
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

                        if (typepas.equals("Startpositie")){
                            beginPos.add(beschrijving);
                        }

                        responseList.add(beschrijving);

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, beginPos);
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

                        ArrayAdapter<String> adapter6 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner = (Spinner)
                                findViewById(R.id.spinner);
                        Spinner.setAdapter(adapter6);

                        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner2 = (Spinner)
                                findViewById(R.id.spinner2);
                        Spinner2.setAdapter(adapter7);

                        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner3 = (Spinner)
                                findViewById(R.id.spinner3);
                        Spinner3.setAdapter(adapter8);

                        ArrayAdapter<String> adapter9 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner4 = (Spinner)
                                findViewById(R.id.spinner4);
                        Spinner4.setAdapter(adapter9);

                        ArrayAdapter<String> adapter10 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner5 = (Spinner)
                                findViewById(R.id.spinner5);
                        Spinner5.setAdapter(adapter10);

                        ArrayAdapter<String> adapter11 = new ArrayAdapter<>(this,
                                android.R.layout.simple_dropdown_item_1line, responseList);
                        Spinner6 = (Spinner)
                                findViewById(R.id.spinner6);
                        Spinner6.setAdapter(adapter10);

                        Typepas = new ArrayList<>();
                        combi = new String[]{typepas + "," + beschrijving};


                        c = Arrays.asList(combi);

                        Algo2.add(c);

                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public void randomizer(View view) throws JSONException {

            TextView stap1 = (TextView) textView.getSelectedView();
            TextView stap2 = (TextView) textView2.getSelectedView();
            TextView stap3 = (TextView) textView3.getSelectedView();
            TextView stap4 = (TextView) textView4.getSelectedView();
            TextView stap5 = (TextView) textView5.getSelectedView();
            TextView stap6 = (TextView) Spinner.getSelectedView();
            TextView stap7 = (TextView) Spinner2.getSelectedView();
            TextView stap8 = (TextView) Spinner3.getSelectedView();
            TextView stap9 = (TextView) Spinner4.getSelectedView();
            TextView stap10 = (TextView) Spinner5.getSelectedView();
            TextView stap11 = (TextView) Spinner6.getSelectedView();
            String Stap1 = stap1.getText().toString();
            String Stap2 = stap2.getText().toString();
            String Stap3 = stap3.getText().toString();
            String Stap4 = stap4.getText().toString();
            String Stap5 = stap5.getText().toString();
            String Stap6 = stap6.getText().toString();
            String Stap7 = stap7.getText().toString();
            String Stap8 = stap8.getText().toString();
            String Stap9 = stap9.getText().toString();
            String Stap10 = stap10.getText().toString();
            String Stap11 = stap11.getText().toString();

            ArrayList<String> List = new ArrayList<>();
            ArrayList<String> ListNew = new ArrayList<>();
            List.add(Stap1);
            List.add(Stap2);
            List.add(Stap3);
            List.add(Stap4);
            List.add(Stap5);
            List.add(Stap6);
            List.add(Stap7);
            List.add(Stap8);
            List.add(Stap9);
            List.add(Stap10);
            List.add(Stap11);

            ListNew.add(Stap1);
            ListNew.add(Stap2);
            ListNew.add(Stap3);
            ListNew.add(Stap4);
            ListNew.add(Stap5);
            ListNew.add(Stap6);
            ListNew.add(Stap7);
            ListNew.add(Stap8);
            ListNew.add(Stap9);
            ListNew.add(Stap10);
            ListNew.add(Stap11);

            Random random = new Random();
            int index = random.nextInt(List.size());
            pas1 = responseList.get(random.nextInt(responseList.size()));
            if (List.contains(pas1)) {
                pas1 = responseList.get(random.nextInt(responseList.size()));
                if (List.contains(pas1)) {
                    pas1 = responseList.get(random.nextInt(responseList.size()));
                    if (List.contains(pas1)) {
                        pas1 = responseList.get(random.nextInt(responseList.size()));
                    }
                }
            }

            ListNew.set(index, pas1);

            AlertDialog.Builder builder = new AlertDialog.Builder(AllDancestyles.this);
            builder
                    .setMessage("Dit is nu je choreografie: \r\n" + List +
                            "\r\nverander je choreografie naar: \r\n" + ListNew)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    })
                    .show();
        }


        public void algorithm(View view) {

            TextView stap1 = (TextView) textView.getSelectedView();
            TextView stap2 = (TextView) textView2.getSelectedView();
            TextView stap3 = (TextView) textView3.getSelectedView();
            TextView stap4 = (TextView) textView4.getSelectedView();
            TextView stap5 = (TextView) textView5.getSelectedView();
            TextView stap6 = (TextView) Spinner.getSelectedView();
            TextView stap7 = (TextView) Spinner2.getSelectedView();
            TextView stap8 = (TextView) Spinner3.getSelectedView();
            TextView stap9 = (TextView) Spinner4.getSelectedView();
            TextView stap10 = (TextView) Spinner5.getSelectedView();
            TextView stap11 = (TextView) Spinner6.getSelectedView();
            String Stap1 = stap1.getText().toString();
            String Stap2 = stap2.getText().toString();
            String Stap3 = stap3.getText().toString();
            String Stap4 = stap4.getText().toString();
            String Stap5 = stap5.getText().toString();
            String Stap6 = stap6.getText().toString();
            String Stap7 = stap7.getText().toString();
            String Stap8 = stap8.getText().toString();
            String Stap9 = stap9.getText().toString();
            String Stap10 = stap10.getText().toString();
            String Stap11 = stap11.getText().toString();

            ArrayList<String> Po = new ArrayList<>();
            ArrayList<String> ListA = new ArrayList<>();
            ArrayList<String> ListNew = new ArrayList<>();
            ListA.add(Stap1);
            ListA.add(Stap2);
            ListA.add(Stap3);
            ListA.add(Stap4);
            ListA.add(Stap5);
            ListA.add(Stap6);
            ListA.add(Stap7);
            ListA.add(Stap8);
            ListA.add(Stap9);
            ListA.add(Stap10);
            ListA.add(Stap11);

            ListNew.add(Stap1);
            ListNew.add(Stap2);
            ListNew.add(Stap3);
            ListNew.add(Stap4);
            ListNew.add(Stap5);
            ListNew.add(Stap6);
            ListNew.add(Stap7);
            ListNew.add(Stap8);
            ListNew.add(Stap9);
            ListNew.add(Stap10);
            ListNew.add(Stap11);

            String returnValue = "";
            String returns;
            Random random = new Random();
            int index = random.nextInt(ListA.size());

            String ratata = ListA.get(index);
            String sja;
            String sjo = "";

            for (List<String> algor : Algo2) {
                if (algor.toString().contains(ratata)) {
                    String r[] = algor.toString().split(",");
                    returns = r[r.length - 1];
                    sja = returns.substring(0, returns.length() - 1);
                    if (sja.equals(ratata)) {
                        sjo = r[0];
                        sjo = sjo.substring(1);
                    }
                }
                if (algor.toString().contains(sjo)) {
                    String re[] = algor.toString().split(",");
                    returns = re[re.length - 1];
                    returns = returns.substring(0, returns.length() - 1);
                    Po.add(returns);
                    pas2 = Po.get(random.nextInt(Po.size()));
                    if (ListA.contains(pas2)) {
                        pas2 = Po.get(random.nextInt(Po.size()));
                        if (ListA.contains(pas2)) {
                            pas2 = Po.get(random.nextInt(Po.size()));
                            if (ListA.contains(pas2)) {
                                pas2 = Po.get(random.nextInt(Po.size()));
                            }
                        }
                    }
                }

                if (algor.toString().contains(pas2)) {
                    String result[] = algor.toString().split(",");
                    returnValue = result[0];
                    returnValue = returnValue.substring(1);
                }
            }
            //output = e.g. General
            if (sjo.equals(returnValue)) {
                ListNew.set(index, pas2);
                final AlertDialog.Builder builder = new AlertDialog.Builder(AllDancestyles.this);
                builder
                        .setMessage("Dit is nu je choreografie: \r\n" + ListA +
                                "\r\nverander je choreografie naar: \r\n" + ListNew)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                dialog.cancel();
                            }
                        })
                        .show();

            }
        }
    }


