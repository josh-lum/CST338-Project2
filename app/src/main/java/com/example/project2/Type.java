package com.example.project2;

import java.util.HashMap;
//Not sure if type is an object or not yet, hashmap will contain matchups.
public class Type {
    private final String WATER = "water";
    private final String FIRE = "fire";
    private final String GRASS = "grass";
    private final Double NOTEFFECTIVE = .5;
    private final Double SUPEREFFECTIVE = 2.0;

    private final int MULTIPLIER = 2;

    public Type(String type){

    }
//    public int typeMult(Type type){
//        if()
//    }



    public int getMULTIPLIER() {
        return MULTIPLIER;
    }
}
