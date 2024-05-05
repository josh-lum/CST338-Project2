package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.Generation3Binding;

public class Generation3 extends AppCompatActivity {

    Generation3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    static Intent Generation3IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation3.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }
}
