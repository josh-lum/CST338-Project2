package com.example.project2;

import static com.example.project2.Database.MonRepository.repository;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.BattleScreenBinding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BattleLooper extends AppCompatActivity {

    private Opponent opponent;
    public static final int DAMAGE_TICK = 2;
    private Handler handler;
    private Mon mon;
    //public Opponent opponent;
    private User user;
    private int userId;
    private BattleScreenBinding binding;
    private MediaPlayer mediaPlayer;
    private Button adminButton;
    private MonRepository repository;


    @Override
    protected void onCreate(Bundle savedInstance) {
        repository = MonRepository.getRepository(getApplication());
        opponent = new Opponent();
        int num = 0;
        updateSprite(num);
        super.onCreate(savedInstance);
        binding = com.example.project2.databinding.BattleScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler(Looper.getMainLooper());

        // set up background music during battle
        mediaPlayer = MediaPlayer.create(this, R.raw.floaroma);
        mediaPlayer.setVolume(1.0f, 1.0f);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        int userId = getIntent().getIntExtra(MainActivity.SHARED_PREFERENCE_USERID_KEY, -1);
        adminButton = findViewById(R.id.autowinadmin);
        LiveData<User> userObserver = repository.getUserByUserId(userId);
        userObserver.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User observedUser) {
                if (observedUser != null) {
                    user = observedUser;
                    if (user.isAdmin()) {
                        adminButton.setVisibility(View.VISIBLE);
                    } else {
                        adminButton.setVisibility(View.GONE);
                    }
                }
            }
        });
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.victory_screen);
            }
        });

        // go back to menu screen
          //Button backButton = findViewById(R.id.backButton);
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPress();
            }
        });

        // deal damage button (its invisible)
        Button inflictDamageButton = findViewById(R.id.inflictDamageButton);
        inflictDamageButton.setOnClickListener(new View.OnClickListener() {

            // the button press works not sure if the inflictDamage function works tho
            @Override
            public void onClick(View v) {
                inflictDamage();
            }
        });
        updateSprites(0);
    }

    private void updateOpponentPokemonHealth(){
        opponent.getNextPokemon().getHp();
    }

    void inflictDamage(){
        if(opponent.hasMorePokemon()){
            Pokemon currentMon = opponent.getNextPokemon();
            currentMon.setHp(currentMon.getHp() - DAMAGE_TICK);
            if(currentMon.getHp() <= 0){
                opponent.switchToNextPokemon();
                updateSprites(opponent.getCurrentMonIndex());
            }
            updateOpponentPokemonHealth();
        }
    }

    private void updateSprites(int num) {
        int resId = updateSprite(num);
        // replace with what user has
        binding.userCircle.setImageResource(R.drawable.blastoise);
        binding.oppCircle.setImageResource(resId);
    }

    public void onBackPress(){
        startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),user.getId()));
        finish();
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
