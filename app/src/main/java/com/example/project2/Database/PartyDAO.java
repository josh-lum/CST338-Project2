package com.example.project2.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project2.Database.entities.Party;
import com.example.project2.Database.entities.User;

@Dao
public interface PartyDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Party party);

    @Update
    void update(Party party);

    @Query("SELECT * FROM "+ MonDatabase.PARTY_TABLE+" WHERE userId == :userId")
    Party getPartyByUserId(int userId);
}
