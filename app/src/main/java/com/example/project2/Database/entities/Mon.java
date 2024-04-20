package com.example.project2.Database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

import java.util.Objects;

@Entity(tableName = MonDatabase.DATABASE_NAME)
public class Mon {
    @PrimaryKey(autoGenerate = true)
    private Integer idNumber;

    private int userId;
    private String name;
    private String type;
    //keep moves for now, if we run out of time delete it
    private String moves;
    private int xp;
    private int level;
    private int damage;
    private int attack;

    public Mon(int userId, String name, String type, String moves, int xp, int level, int damage, int attack) {
        this.name = name;
        this.type = type;
        this.moves = moves;
        this.xp = xp;
        this.level = level;
        this.damage = damage;
        this.attack = attack;
        this.userId = userId;
    }

    // getters and setters for database

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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
        Mon mon = (Mon) o;
        return userId == mon.userId && xp == mon.xp && level == mon.level && damage == mon.damage && attack == mon.attack && Objects.equals(idNumber, mon.idNumber) && Objects.equals(name, mon.name) && Objects.equals(type, mon.type) && Objects.equals(moves, mon.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, userId, name, type, moves, xp, level, damage, attack);
    }

}
