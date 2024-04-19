package com.example.project2.Database.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.Database.Mon;

@Dao
public interface MonDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    void Insert(Mon mon);

    @Query("Select * from " + MonDatabase.monTable)
    List<Mon> getAllRecords();
}
