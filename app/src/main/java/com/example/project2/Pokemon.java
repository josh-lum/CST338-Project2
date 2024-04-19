package com.example.project2;

import android.net.IpSecManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pokemon {
    String name;
    private HashMap<String, Double> mods;
    private String type;
    private int hp;
    private int xp;
    private int level;
    private int maxXp = 10;
    private int dmg;
    private HashMap<String, Integer> moves;
    public final String WATER = "water";
    public final String FIRE = "fire";
    public final String GRASS = "grass";
    private final Double NOTEFFECTIVE = .5;
    private final Double SUPEREFFECTIVE = 2.0;
    private final Double NEUTRAL = 1.0;

    public static void main(String[] args) {
        Pokemon bulbasaur = new Pokemon("bulbasaur","grass",10,2);
        Pokemon charmander = new Pokemon("charmander","fire",10,2);
        bulbasaur.attack(charmander);
        System.out.println("bulbasaur xp = "+bulbasaur.getXp());

    }
    public Pokemon(String name, String type, int hp, int dmg) {
        this.name = name;
        this.hp = hp;

        this.dmg = dmg;
        this.type = type;
        this.level = 1;
        moves = new HashMap<>();
        mods = new HashMap<>();
        if(type.equals(WATER)){
            mods.put(FIRE, SUPEREFFECTIVE);
            mods.put(WATER, NEUTRAL);
            mods.put(GRASS, NOTEFFECTIVE);
        }else if(type.equals(FIRE)){
            mods.put(FIRE, NEUTRAL);
            mods.put(WATER, NOTEFFECTIVE);
            mods.put(GRASS, SUPEREFFECTIVE);
        }else if(type.equals(GRASS)){
            mods.put(FIRE, NOTEFFECTIVE);
            mods.put(WATER, SUPEREFFECTIVE);
            mods.put(GRASS, NEUTRAL);
        }
        moves.put("tackle", 3);

    }



    public Pokemon attack(Pokemon mon){
        int damage = this.getDmg();
        Double mod = mods.get(mon.getType());
        mon.setHp(mon.getHp()-(int)(damage*mod));
        if(mon.getHp()<=0){
            addXp(mon);
            return mon = null;
        }
        return mon;
    }
    private void addXp(Pokemon mon){
        int returnXp = mon.getXp();
        while(returnXp+getXp()>maxXp){
            returnXp-=getXp();
            level++;
            dmg++;
        }
        setXp(returnXp+getXp());
    }
//    Work in progress: going to eventually work as a 4 move set maybe
//    public void addMove(Move move){
//
//        if(moves.contains(move)){
//            System.out.println(getName()+" already knows this move");
//            return;
//        }
//        moves.add(move);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

}

