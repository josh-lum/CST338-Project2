package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.MonDao;
import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.Party;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.Generation2Binding;

public class Generation2 extends AppCompatActivity {

    Button chikoritaButton;
    Button cyndaquilButton;
    Button totodileButton;
    Button backButton;
    Generation2Binding binding;
    private static final String GENERATIONS1_USER_ID ="com.example.project2.GENERATIONS1_USER_ID";

    private MonDao monDao;
    private MonRepository repository;
    private int loggedInUserId = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        logInUser(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_VALUE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int userId = sharedPreferences.getInt(MainActivity.SHARED_PREFERENCE_USERID_KEY, -1);
        editor.apply();


        repository = MonRepository.getRepository(getApplication());
        binding.totodileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("TOTODILE",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });
        binding.cyndquilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("CYNDAQUIL",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });
        binding.chikoritaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mon mon = new Mon("CHIKORITA",0,0, 10);
                int monId = (int)repository.insertMon(mon);
                Party party = new Party(loggedInUserId,monId);
                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),loggedInUserId));
                finish();
            }
        });



        backButton = findViewById(R.id.backButtonGen2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Generation2.this, Generations.class);
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
    static Intent Generation2IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation2.class);
        intent.putExtra(MainActivity.MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}