package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Generation1 extends AppCompatActivity {
    private static final String GENERATIONS1_USER_ID ="com.example.project2.GENERATIONS1_USER_ID";
    com.example.project2.databinding.Generation1Binding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    static Intent Generation1IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation1.class);
        intent.putExtra(GENERATIONS1_USER_ID, userId);
        return intent;
    }
}