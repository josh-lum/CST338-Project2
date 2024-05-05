package com.example.project2.Database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

import java.util.Objects;

@Entity(tableName = MonDatabase.MON_TABLE)
public class Mon {
    @PrimaryKey(autoGenerate = true)
    private Integer idNumber;


    private String name;


    private int xp;
    private int level;
    private int damage;

    public Mon( String name,  int xp, int level, int damage) {
        this.name = name;
        this.xp = xp;
        this.level = level;
        this.damage = damage;
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


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mon mon = (Mon) o;
        return  xp == mon.xp && level == mon.level && damage == mon.damage && Objects.equals(idNumber, mon.idNumber) && Objects.equals(name, mon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, name, xp, level, damage);
    }
}
