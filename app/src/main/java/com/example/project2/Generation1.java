package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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


    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.Generation1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        int userId = intent.getIntExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);
        repository = MonRepository.getRepository(getApplication());
        binding.bulbasaurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Mon mon = new Mon("BULBASAUR",0,0, 10);
//                repository.insertMon(mon);
//                Party party = new Party(userId,mon.getIdNumber());
//                repository.insertParty(party);
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));
            }
        });
        binding.charmanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));
            }
        });
        binding.squirtleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),userId));
            }
        });

//        Intent intent = getIntent();
//        int userId = intent.getIntExtra(MainActivity.SHARED_PREFERENCE_USERID_VALUE, -1);

        binding.bulbasaurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        backButton = findViewById(R.id.backButtonGen1);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Generation1.this, Generations.class);
                startActivity(intent);
            }
        });


    }
    static Intent Generation1IntentFactory(Context context, int userId){
        Intent intent = new Intent(context, Generation1.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }
}