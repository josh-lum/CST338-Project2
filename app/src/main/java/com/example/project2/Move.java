package com.example.project2;

public class Move {
    String name;
    Type type;
    int dmg;

    public Move(String name, Type type, int dmg) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
    }

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

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
}

