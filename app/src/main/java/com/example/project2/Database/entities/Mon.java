package com.example.project2.Database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

import java.util.Objects;

@Entity(tableName = MonDatabase.monTable)
public class Mon {
    @PrimaryKey(autoGenerate = true)
    private Integer idNumber;
    private String name;
    private String type;
    //keep moves for now, if we run out of time delete it
    private String moves;
    private int xp;
    private int level;
    private int damage;
    private int attack;

    public Mon(String name, String type, String moves, int xp, int level, int damage, int attack) {
        this.name = name;
        this.type = type;
        this.moves = moves;
        this.xp = xp;
        this.level = level;
        this.damage = damage;
        this.attack = attack;
    }

    // getters and setters for database

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mon that = (Mon) o;
        return xp == that.xp && damage == that.damage && attack == that.attack && Objects.equals(idNumber, that.idNumber) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(moves, that.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, name, type, moves, xp, damage, attack);
    }
}
