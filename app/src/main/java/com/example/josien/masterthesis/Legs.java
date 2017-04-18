package com.example.josien.masterthesis;

/**
 * Created by Josien on 17-4-2017.
 */
public class Legs extends BodyParts {

    public Legs(){
        this.posX = 0;
        this.posY = 0;
        this.bodyID = 1;
    }

    public boolean twoLegsInTheAir(Legs leg1, Legs leg2){
        if(leg1.posY > 0 && leg2.posY > 0 ) {
            return true;
        } else {
            return false;
        }
    }
}
