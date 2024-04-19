package com.example.project2.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project2.Database.entities.MonDatabase;
import com.example.project2.Database.entities.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + MonDatabase.USER_TABLE + " ORDER BY username")
    List<User> getAllUsers()

}
