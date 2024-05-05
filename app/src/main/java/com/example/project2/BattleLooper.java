package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.BattleScreenBinding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BattleLooper extends AppCompatActivity {

    private static final int DAMAGE_TICK = 2;
    private static final int DAMAGE_INTERVAL = 1000;
    private int damageInterval = DAMAGE_INTERVAL;
    private Handler handler;
    private Mon mon;
    private Opponent opponent;
    private User user;
    private int userId;
    private BattleScreenBinding binding;



    @Override
    protected void onCreate(Bundle savedInstance){
        int num = 0;
        updateSprite(num);
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
            int num = -1;
            @Override
            public void run() {
                int resId = updateSprite(num += 1);
                binding.oppCircle.setImageResource(resId);
               if(opponent.hasMorePokemon()){
                   Pokemon currentMon = opponent.getNextPokemon();
                   if(currentMon.getHp() <= 0){
                       opponent.switchToNextPokemon();
                       updateSprite(num += 1);
                   }
                   updateOpponentPokemonHealth();
                   damageInterval = calculateNewDamageInterval(DAMAGE_INTERVAL, currentMon.getDmg());
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
            damageInterval = calculateNewDamageInterval(damageInterval, DAMAGE_TICK);
            currentMon.setDmg(DAMAGE_TICK);
            updateOpponentPokemonHealth();
        }
    }

    public void onBackPress(){
        startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),user.getId()));
        finish();
    }

    private int calculateNewDamageInterval(int currentInterval, int damageInflicted){
        return currentInterval + damageInflicted;
    }

    static Intent BattleLooperIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, BattleLooper.class);
        intent.putExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, userId);
        return intent;
    }

    public int updateSprite(int num){
        if(num == 0){
            return R.drawable.bulbasaur;
        }else if(num == 1){
            return R.drawable.charmander;
        }else if(num == 2){
            return R.drawable.squirtle;
        }else if(num == 3){
            return R.drawable.chikorita;
        }else if(num == 4){
            return R.drawable.cyndaquil;
        }else if(num == 5){
            return R.drawable.totodile;
        }else if(num == 6){
            return R.drawable.treecko;
        }else if(num == 7){
            return R.drawable.torchic;
        }else if(num == 8){
            return R.drawable.mudkip;
        }else{
            Toast.makeText(this, "Victory", Toast.LENGTH_SHORT).show();
            return R.drawable.cynthia;
        }
    }

}
