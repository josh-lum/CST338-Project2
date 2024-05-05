package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.BattleScreenBinding;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BattleLooper extends AppCompatActivity {

    private static final int DAMAGE_TICK = 2;
    private static final int DAMAGE_INTERVAL = 1000;
    private int damageInterval = DAMAGE_INTERVAL;
    private Handler handler;
    private Mon mon;
    private Opponent opponent;
    private User user;
    private BattleScreenBinding binding;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        opponent = new Opponent();
        binding = com.example.project2.databinding.BattleScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler(Looper.getMainLooper());

        // go back to menu screen
//        Button backButton = findViewById(R.id.backButton);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });

        // deal damage button (its invisible)
//        Button inflictDamageButton = findViewById(R.id.inflictDamageButton);
        binding.inflictDamageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflictDamage();
            }
        });
        startGameLoop();
    }

    private void startGameLoop(){
        handler.postDelayed(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                int resId = R.drawable.charmander;
                binding.oppCircle.setImageResource(resId);
               if(opponent.hasMorePokemon()){
                   Pokemon currentMon = opponent.getNextPokemon();
                   if(currentMon.getHp() <= 0){
                       opponent.switchToNextPokemon();
                   }
                   updateOpponentPokemonHealth();
                   handler.postDelayed(this, damageInterval);
               }else{
                   TextView victoryTextView = findViewById(R.id.victoryScreenTextView);
                   victoryTextView.setText("Congratulations you defeated all the opponents pokemon!");
                   victoryTextView.setVisibility(View.VISIBLE);
               }
            }
        }, damageInterval);
    }
    private void updateOpponentPokemonHealth(){
        opponent.getNextPokemon().getHp();
    }

    private void inflictDamage(){
        if(opponent.hasMorePokemon()){
            Pokemon currentMon = opponent.getNextPokemon();
            currentMon.setDmg(DAMAGE_TICK);
            updateOpponentPokemonHealth();
        }
    }
    public void onBackPress(){

        startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),user.getId()));
        finish();
    }

}
