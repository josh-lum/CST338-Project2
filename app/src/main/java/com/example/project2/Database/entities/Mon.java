package com.example.project2.Database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

import java.util.Objects;

@Entity(tableName = MonDatabase.MON_TABLE)
public class Mon {
    @PrimaryKey(autoGenerate = true)
    private Integer idNumber;

    private int userId;
    private String name;
    private int sprite;

    private int xp;
    private int level;
    private int damage;

    public Mon(int userId, String name,  int xp, int level, int damage, int sprite) {
        this.name = name;
        this.sprite= sprite;
        this.xp = xp;
        this.level = level;
        this.damage = damage;
        this.userId = userId;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSprite(int sprite) {
        this.sprite = sprite;
    }

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
        return userId == mon.userId && sprite == mon.sprite && xp == mon.xp && level == mon.level && damage == mon.damage && Objects.equals(idNumber, mon.idNumber) && Objects.equals(name, mon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, userId, name, sprite, xp, level, damage);
    }
}
