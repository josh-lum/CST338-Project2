package com.example.project2.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MonDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    long insert(Mon mon);

//    @Delete
//    void delete(Mon mon);
    @Query("SELECT * FROM "+ MonDatabase.MON_TABLE+" WHERE idNumber == :id")
    Mon getMonByMonId(int id);

    @Query("SELECT * FROM " + MonDatabase.MON_TABLE)
    List<Mon> getAllRecords();
}
