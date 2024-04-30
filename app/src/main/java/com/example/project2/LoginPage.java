package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.UserDAO;
import com.example.project2.databinding.LoginScreenBinding;

public class LoginPage extends AppCompatActivity {
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
    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginPage.class);

    }
}
