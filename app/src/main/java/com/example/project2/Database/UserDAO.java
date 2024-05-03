package com.example.project2.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.Database.entities.User;


import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

//    @Query("SELECT * FROM "+MonDatabase.USER_TABLE +" WHERE username = :username")
//    List<User> getUsername(String username);
    @Query("SELECT * FROM " + MonDatabase.USER_TABLE)
    LiveData<List<User>> getAllRecords();

    @Query("SELECT * FROM "+ MonDatabase.USER_TABLE+" WHERE id == :id")
    LiveData<User> getUserByUserId(int id);


    @Query("DELETE from " + MonDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * FROM "+MonDatabase.USER_TABLE +" WHERE username == :username")
    LiveData<User> getUserByUsername(String username);
}
