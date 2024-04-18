package com.example.project2.Database.entities;

import androidx.room.Dao;
import androidx.room.OnConflictStrategy;

@Dao
public interface MonDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    void
}
