package com.example.project2.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

@Entity(tableName = MonDatabase.PARTY_TABLE)
public class Party {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private Integer userId;
    private Integer monId;

    public Party(Integer userId, Integer monId) {
        this.userId = userId;
        this.monId = monId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMonId() {
        return monId;
    }

    public void setMonId(Integer monId) {
        this.monId = monId;
    }
}
