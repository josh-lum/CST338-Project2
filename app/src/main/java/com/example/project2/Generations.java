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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.GenerationPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.SHARED_PREFERENCE_USERID_VALUE, -1);
//        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY,
//                Context.MODE_PRIVATE);
//        String username = sharedPreferences.getString("username", "");
//        int userId = sharedPreferences.getInt("userId", -1);
        binding.generation1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Generation1.Generation1IntentFactory(getApplicationContext(),userId));
            }
        });
        binding.generation2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Generation2.Generation2IntentFactory(getApplicationContext(),userId));
            }
        });
    }
    static Intent GenerationsIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generations.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }

}