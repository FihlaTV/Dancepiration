package com.example.josien.masterthesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Josien on 21-4-2017.
 */
public class DanceStyles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dansstijl_activity);
    }

    public void ballet(View view){
        Intent balletClass = new Intent(this, BalletClass.class);

        balletClass.putExtra("start_screen", 500);
        startActivity(balletClass);

    }

    public void alledansstijlen(View view){
        Intent allDancestyles = new Intent(this, AllDancestyles.class);

        allDancestyles.putExtra("start_screen", 500);
        startActivity(allDancestyles);
    }

    public void modern(View view){
        Intent modern = new Intent(this, ModernClass.class);

        modern.putExtra("start_screen", 500);
        startActivity(modern);
    }
}
