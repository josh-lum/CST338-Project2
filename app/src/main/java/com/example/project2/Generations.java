package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Database.entities.User;
import com.example.project2.databinding.GenerationPickerBinding;

public class Generations extends AppCompatActivity {
    private static final String GENERATIONS_USER_ID ="com.example.project2.GENERATIONS_USER_ID";
    GenerationPickerBinding binding;
    User user;
    private int loggedInUserId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.GenerationPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        logInUser(savedInstanceState);
//        loggedInUserId = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);

//        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY, MODE_PRIVATE);

//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY, MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//        String username = sharedPreferences.getString("username", "");
//        int userId = sharedPreferences.getInt("userId", -1);
        binding.generation1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_VALUE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, loggedInUserId);
                editor.apply();
                startActivity(Generation1.Generation1IntentFactory(getApplicationContext(),loggedInUserId));

            }
        });
        binding.generation2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Generation2.Generation2IntentFactory(getApplicationContext(),loggedInUserId));
            }
        });
        binding.generation3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Generation2.Generation2IntentFactory(getApplicationContext(), loggedInUserId));
            }

        });

        binding.generation3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Generation3.Generation3IntentFactory(getApplicationContext(), loggedInUserId));
            }
        });
    }
    static Intent GenerationsIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, Generations.class);
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
    private void logInUser(Bundle savedInstanceState){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY,
                Context.MODE_PRIVATE);
        if(sharedPreferences.contains(MainActivity.SHARED_PREFERENCE_USERID_KEY)){
            loggedInUserId = sharedPreferences.getInt(MainActivity.SHARED_PREFERENCE_USERID_VALUE, -1);
        }
        if(loggedInUserId==-1 & savedInstanceState!=null&&savedInstanceState.containsKey(MainActivity.SAVED_INSTANCE_STATE_USERID_KEY)){
            loggedInUserId = savedInstanceState.getInt(MainActivity.SAVED_INSTANCE_STATE_USERID_KEY,-1);
        }
        if(loggedInUserId==-1) {
            loggedInUserId = getIntent().getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);
        }
        if(loggedInUserId==-1){
            return;
        }




    }

}