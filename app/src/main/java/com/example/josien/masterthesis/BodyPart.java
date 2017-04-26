package com.example.josien.masterthesis;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

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
 * Created by Josien on 26-4-2017.
 */
public class BodyPart extends AppCompatActivity {

    Random random = new Random();
    String return1;
    String return2;
    String return3;
    String return11;
    String return22;
    String return33;
    String return111;
    String return222;
    String return333;
    int firstrandom;
    int secondrandom;
    int thirdrandom;
    List<String> first;
    List<String> second;
    List<String> third;
    String[] xfirst;
    String[] xsecond;
    String[] xthird;
    JSONArray art_objects;
    String lichaamsdeelnummer;
    String lichaamsdeel;
    String beschrijving;
    String type_beweging;
    String[] combi;
    List<String> List1;
    List<List<String>> List2 = new ArrayList<>();
    ArrayList<String> allbodyparts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bodypart);
        parseJSON();
    }

    private String getJSONString(Context context) {
        String str = "";
        try {
            AssetManager assetManager = context.getAssets();
            InputStream in = assetManager.open("bodypart.json");
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
                beschrijving = tijd.getString("Beschrijving");
                lichaamsdeelnummer = tijd.getString("Lichaamsdeelnummer");
                lichaamsdeel = tijd.getString("Lichaamsdeel");
                type_beweging = tijd.getString("Type_beweging");

                allbodyparts.add(beschrijving + " " + lichaamsdeelnummer + " " + type_beweging);
                combi = new String[]{beschrijving + "," + lichaamsdeelnummer + "," + type_beweging};

                List1 = Arrays.asList(combi);

                List2.add(List1);


                Log.d("tag", "parseJSON() returned: " + allbodyparts);
                Log.d("tagtag", "parseJSON() returned: " + List1);
                Log.d("tagtagtag", "parseJSON() returned: " + List2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void body(View view) {

        getRandoms();

        if (return111.equals(return222) || return222.equals(return333) || return111.equals(return333)){
            Log.d("jajaja", "body() returned: " + return111 + return222 + return333);
            getRandoms();
        }

        if (return1.equals("Fluent") || return2.equals("Fluent") || return3.equals("Fluent")){
            if (return1.equals("Fluent")){
                return11 = xfirst[0];
                return11 = return11.substring(1);
            }
            if (return2.equals("Fluent")){
                return22 = xsecond[0];
                return22 = return22.substring(1);
            }
            if (return3.equals("Fluent")){
                return33 = xthird[0];
                return33 = return33.substring(1);
            }
        }

        // if everything is static and not the same bodypart; give description
        if (return1.equals(return2) && (return2.equals(return3))){
            return11 = xfirst[0];
            return11 = return11.substring(1);

            return22 = xsecond[0];
            return22 = return22.substring(1);

            return33 = xthird[0];
            return33 = return33.substring(1);

            Log.d("xxxxx", "body() returned: " + return11+return22+return33);
        }
        Log.d("aaaaa", "body() returned: " + return1 + " "+ return2 + " " + return3);

        Log.d("tagtaggg", "parseJSON() returned: " + first + second+third);
    }

    public void getRandoms(){
        firstrandom = random.nextInt(List2.size());
        secondrandom = random.nextInt(List2.size());
        thirdrandom = random.nextInt(List2.size());
        first = List2.get(firstrandom);
        second = List2.get(secondrandom);
        third = List2.get(thirdrandom);
        xfirst =first.toString().split(",");
        xsecond = second.toString().split(",");
        xthird = third.toString().split(",");


        //checks fluent or static
        return1 = xfirst[xfirst.length - 1];
        return1 = return1.substring(0, return1.length() - 1);

        return2 = xsecond[xsecond.length -1];
        return2 = return2.substring(0, return2.length() -1);

        return3 = xthird[xthird.length-1];
        return3 = return3.substring(0, return3.length() -1);

        //checks for same bodypartnumber
        return111 = xfirst[1];
        return222 = xsecond[1];
        return333 = xthird[1];
    }
}
