package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.databinding.Generation3Binding;

public class Generation3 extends AppCompatActivity {

    Generation3Binding binding;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.SHARED_PREFERENCE_USERID_VALUE, -1);
        binding.treeckoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));

            }
        });
        binding.mudkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));
            }
        });
        binding.torchicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));
            }
        });



        backButton = findViewById(R.id.backButtonGen3);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Generation3.this, Generations.class);
                startActivity(intent);
            }
        });

    }
    static Intent Generation3IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation3.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }

}
