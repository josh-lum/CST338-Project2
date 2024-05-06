package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Database.MonDao;
import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.Party;

import android.widget.Button;


public class Generation1 extends AppCompatActivity {
    private static final String GENERATIONS1_USER_ID ="com.example.project2.GENERATIONS1_USER_ID";
    com.example.project2.databinding.Generation1Binding binding ;

    private MonDao monDao;
    private MonRepository repository;
    private int loggedInUserId = -1;


    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logInUser(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_VALUE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int userId = sharedPreferences.getInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, -1);


//        Intent intent = getIntent();
//        int userId = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);
        repository = MonRepository.getRepository(getApplication());
        binding.bulbasaurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("BULBASAUR",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
            }
        });
        binding.charmanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("CHARMANDER",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
            }
        });
        binding.squirtleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
            }
        });

//        Intent intent = getIntent();
//        int userId = intent.getIntExtra(MainActivity.SHARED_PREFERENCE_USERID_VALUE, -1);


        backButton = findViewById(R.id.backButtonGen1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Generation1.this, Generations.class);
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
    static Intent Generation1IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation1.class);
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}