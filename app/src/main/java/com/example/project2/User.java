package com.example.project2;

import android.media.audiofx.AutomaticGainControl;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "UserBank")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String password;

    public User(String userName, String password) {
        this.username = userName;
        this.password = password;
        Log.d("Account", "Created Account");
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
