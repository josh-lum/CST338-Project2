package com.example.project2;


import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.app.RemoteInput;
import androidx.room.Room;

import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.MonRepository;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
public class UnitTests {

// Joshua Lum's tests

    @Test
    public void testGetterSetter(){
        User user = new User("f", "a");
        user.setUsername("josh");
        assertEquals("josh", user.getUsername());

    }
    @Test
    public void testEquals(){
        User user1 = new User("josh", "a");
        User user2 = new User("quinn", "a");
        assertNotEquals(user1, user2);
    }

    // Quinn Krommenhoek's tests


}
