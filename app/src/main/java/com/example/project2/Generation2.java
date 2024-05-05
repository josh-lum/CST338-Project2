package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.User;
import com.example.project2.databinding.Generation2Binding;

public class Generation2 extends AppCompatActivity {

    Button chikoritaButton;
    Button cyndaquilButton;
    Button totodileButton;
    Generation2Binding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
    static Intent Generation2IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation2.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }
}
