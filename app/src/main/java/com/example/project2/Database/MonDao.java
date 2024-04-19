package com.example.project2.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.Database.entities.Mon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MonDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    void insert(Mon mon);

    @Query("Select * from " + MonDatabase.MON_TABLE)
    ArrayList<Mon> getAllRecords();
}
