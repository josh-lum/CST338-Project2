package com.example.project2;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    String name;
    private Type type;
    private int hp;
    private int xp;
    private int dmg;
    List<Move> moves;

    public Pokemon(String name, String type, int hp, int xp, int dmg) {
        this.name = name;
        this.type = new Type(type);
        this.hp = hp;
        this.xp = xp;
        this.dmg = dmg;
        moves = new ArrayList<>();
    }

    public Pokemon attack(Pokemon mon){
        int damage = this.getDmg();
        mon.setHp(mon.getHp()-damage);
        if(mon.getHp()<=0){
            return mon = null;
        }
        return mon;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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

