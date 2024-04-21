package com.example.project2;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.User;

public class LandingPage extends AppCompatActivity {

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle instance){
        super.onCreate(instance);
        setContentView(R.layout.login_screen);

        MonDatabase db = MonDatabase.getDatabase(this);
        userDAO = db.UserDAO();

        User user = userDAO.getUserByUsername("username");

        if(user != null){
            String username = user.getUsername();
            boolean isAdmin = user.isAdmin();
        }else{
            // type something like user doesn't exist
        }
    }
}
