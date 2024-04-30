package com.example.project2.Database;

import androidx.room.Dao;
import androidx.room.Delete;
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

//    @Delete
//    void delete(Mon mon);

    @Query("SELECT * FROM " + MonDatabase.MON_TABLE)
    List<Mon> getAllRecords();
}
