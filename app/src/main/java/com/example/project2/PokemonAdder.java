package com.example.project2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Database.entities.User;

import java.util.Stack;

public class PokemonAdder extends AppCompatActivity {
    private User user;
    private int hp = 10;
    private int damage = 2;
    private Button bulbasaur;
    private ImageView bulbasaurImageView;
    private Button charmander;
    private ImageView charmanderImageView;
    private Button squirtle;
    private ImageView squirtleImageView;
    private Button chikorita;
    private ImageView chikoritaImageView;
    private Button cyndaquil;
    private ImageView cyndaquilImageView;
    private Button totodile;
    private ImageView totodileImageView;
    private Button treecko;
    private ImageView treeckoImageView;
    private Button torchic;
    private ImageView torchicImageView;
    private Button mudkip;
    private ImageView mudkipImageView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        bulbasaur = findViewById(R.id.bulbasaurButton);
        bulbasaur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
