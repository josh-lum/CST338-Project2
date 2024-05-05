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
import android.widget.ImageView;
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
    private BattleScreenBinding binding;
    private ImageView bulbasaurImageView;
    private ImageView charmanderImageView;
    private ImageView squirtleImageView;
    private ImageView chikoritaImageView;
    private ImageView cyndaquilImageView;
    private ImageView totodileImageView;
    private ImageView treeckoImageView;
    private ImageView torchicImageView;
    private ImageView mudkipImageView;
    private int localAccumulator = 0;


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        opponent = new Opponent();
        binding = com.example.project2.databinding.BattleScreenBinding.inflate(getLayoutInflater());
        handler = new Handler(Looper.getMainLooper());
        setContentView(binding.getRoot());

        // sprite loading
        bulbasaurImageView = findViewById(R.id.bulbasaurImageView);
        charmanderImageView = findViewById(R.id.charmanderImageView);
        squirtleImageView = findViewById(R.id.squirtleImageView);
        chikoritaImageView = findViewById(R.id.chikoritaImageView);
        cyndaquilImageView = findViewById(R.id.cyndaquilImageView);
        totodileImageView = findViewById(R.id.totodileImageView);
        treeckoImageView = findViewById(R.id.treeckoImageView);
        torchicImageView = findViewById(R.id.torchicImageView);
        mudkipImageView = findViewById(R.id.mudkipImageView);

        // go back to login screen
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });

        // deal damage button (its invisible)
        Button inflictDamageButton = findViewById(R.id.inflictDamageButton);
        inflictDamageButton.setOnClickListener(new View.OnClickListener() {
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
                int start = 0;
                int resId = updateSprite(start);
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

    }

    private void inflictDamage(){
        if(opponent.hasMorePokemon()){
            Pokemon currentMon = opponent.getNextPokemon();
            currentMon.setDmg(DAMAGE_TICK);
            updateOpponentPokemonHealth();
            Toast.makeText(this, "did damage!", Toast.LENGTH_SHORT).show();
        }else{

        }
    }
    public void onBackPress(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public int updateSprite(int number){

        if(number == 0){
            localAccumulator += 1;
            return R.drawable.bulbasaur;
        }else if(number == 1){
            localAccumulator += 1;
            return R.drawable.charmander;
        }else if(number == 2){
            localAccumulator += 1;
            return R.drawable.squirtle;
        }else if(number == 3){
            localAccumulator += 1;
            return R.drawable.chikorita;
        }else if(number == 4){
            localAccumulator += 1;
            return R.drawable.cyndaquil;
        }
        else if(number == 5){
            localAccumulator += 1;
            return R.drawable.totodile;
        }else if(number == 6){
            localAccumulator += 1;
            return R.drawable.treecko;
        }else if(number == 7){
            localAccumulator += 1;
            return R.drawable.torchic;
        }else if(number == 8){
            localAccumulator += 1;
            return R.drawable.mudkip;
        }else{
            return R.drawable.leaguechamp;
        }
    }

}
