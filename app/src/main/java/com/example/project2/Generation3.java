package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.Party;
import com.example.project2.databinding.Generation3Binding;

public class Generation3 extends AppCompatActivity {

    Generation3Binding binding;
    private MonRepository repository;

    private int loggedInUserId = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logInUser(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_VALUE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int userId = sharedPreferences.getInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, -1);
        editor.apply();


        repository = MonRepository.getRepository(getApplication());
        binding.mudkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("MUDKIP",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });
        binding.treeckoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("TREECKO",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });
        binding.torchicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("TORCHIC",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });




        binding.backButtonGen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Generation3.this, Generations.class);
                startActivity(intent);
            }
        });


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
    static Intent Generation3IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation3.class);
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}