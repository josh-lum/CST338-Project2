package com.example.project2.Database;

import androidx.room.Database;

import com.example.project2.Database.entities.Mon;

@Database(entities = {Mon.class}, version = 1, exportSchema = false)
public class MonDatabase {
}
