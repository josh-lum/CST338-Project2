package com.example.project2.Database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project2.Database.MonDatabase;

@Entity(tableName = MonDatabase.PARTY_TABLE)
public class Party {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private int monId;

    public Party(int userId, int monId) {
        this.userId = userId;
        this.monId = monId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMonId() {
        return monId;
    }

    public void setMonId(int monId) {
        this.monId = monId;
    }
}
