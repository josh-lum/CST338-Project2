package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.LoginScreenBinding;

public class LandingPage extends AppCompatActivity {
    private LoginScreenBinding binding;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle instance){
        super.onCreate(instance);
        binding = LoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.enLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.MainActivityIntentFactory(getApplicationContext(), 0);
                startActivity(intent);
            }
        });


//        MonDatabase db = MonDatabase.getDatabase(this);
//        userDAO = db.UserDAO();
//
//        User user = userDAO.getUserByUsername("username");
//
//        if(user != null){
//            String username = user.getUsername();
//            boolean isAdmin = user.isAdmin();
//        }else{
//            // type something like user doesn't exist
//        }
    }
    static Intent LandingPageIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_VALUE, userId);
        return intent;
    }
}
