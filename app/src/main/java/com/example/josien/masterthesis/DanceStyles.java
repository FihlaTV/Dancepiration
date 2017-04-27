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

    public void streetdance(View view){
        Intent streetdance = new Intent(this, StreetdanceClass.class);

        streetdance.putExtra("start_screen", 500);
        startActivity(streetdance);
    }

    public void bodyparts(View view){
        Intent bodypart = new Intent(this, BodyPart.class);

        bodypart.putExtra("start_screen", 500);
        startActivity(bodypart);
    }

    public void variaties(View view){
        Intent variaties = new Intent(this, ChangeStep.class);

        variaties.putExtra("start_screen", 500);
        startActivity(variaties);
    }
}
