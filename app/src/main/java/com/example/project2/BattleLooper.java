package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class BattleLooper extends AppCompatActivity {

    private Handler handler;
    private Mon mon;
    private Opponent opponent;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.battle_screen);
        handler = new Handler(Looper.getMainLooper());
        startGameLoop();
    }

    private void startGameLoop(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               if(opponent.hasMorePokemon()){
                   Pokemon currentMon = opponent.getNextPokemon();
                   if(currentMon.getHp() <= 0){
                       opponent.switchToNextPokemon();
                   }
                   updateOpponentPokemonHealth();
                   handler.postDelayed(this,)
               }
            }
        })
    }
}
