package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.ActivityMainBinding;
import com.example.project2.databinding.BattleScreenBinding;


public class MainActivity extends AppCompatActivity {

    private static final int LOGGED_OUT = -1;
    static final String SHARED_PREFERENCE_USERID_KEY="com.example.project2.SHARED_PREFERENCE_USERID_KEY";
    static final String SHARED_PREFERENCE_USERID_VALUE = "com.example.project2.SHARED_PREFERENCE_USERID_VALUE";
    private ActivityMainBinding binding;
    static final String MAIN_ACTIVITY_USER_ID ="com.example.project2.MAIN_ACTIVITY_USER_ID";
    private static final String SAVED_INSTANCE_STATE_USERID_KEY ="com.example.project2.SAVED_INSTANCE_STATE_USERID_KEY";
    private static final String SAVED_INSTANCE_STATE_USERID_VALUE ="com.example.project2.SAVED_INSTANCE_STATE_USERID_VALUE";

    public static final String TAG = "DAC_MON";
    private MonRepository repository;
    private int loggedInUserId = -1;

    private LiveData<User> user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonRepository.getRepository(getApplication());


        logInUser(savedInstanceState);

        if(loggedInUserId == -1){
            Intent intent = LoginPage.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }else {
            user = repository.getUserByUserId(loggedInUserId);
            user.observe(this, newUser -> {

            });
        }
        user = repository.getUserByUserId(loggedInUserId);


        binding.LogOutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        binding.BattleMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BattleLooper.BattleLooperIntentFactory(getApplicationContext(), loggedInUserId));
            }
        });

    }

    void logout() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        startActivity(LoginPage.loginIntentFactory(getApplicationContext()));
        sharedPrefEditor.apply();
    }

    private void logInUser(Bundle savedInstanceState){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,
                Context.MODE_PRIVATE);
        if(sharedPreferences.contains(SHARED_PREFERENCE_USERID_KEY)){
            loggedInUserId = sharedPreferences.getInt(SHARED_PREFERENCE_USERID_VALUE, LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT & savedInstanceState!=null&&savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)){
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY,LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT){
            return;
        }

    }

    static Intent MainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

}