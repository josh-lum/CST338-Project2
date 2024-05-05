package com.example.project2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.MonRepository;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.LoginScreenBinding;

public class LoginPage extends AppCompatActivity {
    private LoginScreenBinding binding;
    private UserDAO userDAO;
    private MonRepository repository;
//    private LiveData<User> userObserver;

//    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle instance){
        super.onCreate(instance);
        binding = LoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MonDatabase db = MonDatabase.getDatabase(this);
        userDAO = db.UserDAO();
        repository = MonRepository.getRepository(getApplication());


        // for going to create account
//        createAccountButton = findViewById(R.id.create_account);
        binding.createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreateAccount.CreateAccountIntentFactory(getApplicationContext()));

            }
        });

        binding.enLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });


//        MonDatabase db = MonDatabase.getDatabase(this);
//        userDAO = db.UserDAO();
//
//        User user = userDAO.getUserByUsername("username");
//
//        if(user != null){
//            String username = user.getUsername();
//            boolean isAdmin = user.isAdmin();
//        }else{
//            // type something like user doesn't exist
//        }
    }
    private void verifyUser(){
        String username = binding.enUser.getText().toString();


        if(username.isEmpty()){
            toastMaker("Username may not be blank");
            return;
        }
        LiveData<User> userObserver = repository.getUserByUserName(username);
        userObserver.observe(this, user -> {
                if(user !=null){
                    String password = binding.enPass.getText().toString();
                    if(password.equals(user.getPassword())){
                        startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(),user.getId()));
                    }else{
                        toastMaker("Invalid Password");
                        binding.enPass.setSelection(0);
                    }
                }else{
                    toastMaker(username+" is not a valid username");
                    binding.enUser.setSelection(0);

                }
            });


    }

    private void toastMaker(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginPage.class);

    }
}
