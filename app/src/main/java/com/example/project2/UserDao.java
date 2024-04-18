package com.example.project2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;




public interface UserDao {

    @Insert
    void addUser(User user);

    @Query("SELECT COUNT(*) FROM users")
    int count();

    @Query("select * from users")
    List<User> getAll();

    @Query("select * from users where id = :id")
    User findThroughId(int id);

    @Update
    void update(User user);
}
