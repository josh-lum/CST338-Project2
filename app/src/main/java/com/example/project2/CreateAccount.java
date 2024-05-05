package com.example.project2;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.MonRepository;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.CreateAccountBinding;

public class CreateAccount extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordeditText;
    private Button createAccountButton;
    private UserDAO userDAO;
    private CreateAccountBinding binding;
    private MonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = com.example.project2.databinding.CreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = MonRepository.getRepository(getApplication());

        userDAO = MonDatabase.getDatabase(this).UserDAO();

//        usernameEditText = findViewById(R.id.create_user);
//        passwordeditText = findViewById(R.id.create_password);
//        createAccountButton = findViewById(R.id.create_account_createAccountScreen);
//        String username = binding.createUser.getText().toString();


//        if(username.isEmpty()){
//            toastMaker("Username may not be blank");
//            return;
//        }

        binding.createAccountScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                createAccount();
//                String username = usernameEditText.getText().toString();
//                String password = passwordeditText.getText().toString();
//
//                // adding in a user
//                User user = new User(username, password);
//                userDAO.insert(user);
//                Toast.makeText(CreateAccount.this, "Account Created!", Toast.LENGTH_SHORT).show();
//
//                // brings you back to login page
//                Intent intent = new Intent(CreateAccount.this, LoginPage.class);
//                startActivity(intent);
//                finish();

            }
        });
    }
    private void createAccount() {
        String username = binding.createUser.getText().toString();
        // Perform input validation
        if(username.isEmpty()){
            toastMaker("Username may not be blank");
            return;
        }

        // Check if the username already exists
        LiveData<User> existingUser = repository.getUserByUserName(username);
        existingUser.observe(this, new Observer<User>() {

            @Override
            public void onChanged(User user) {
                existingUser.removeObserver(this);
                if (user != null) {
                    toastMaker("Username already exists");
                } else {
                    String password = binding.createPassword.getText().toString();
                    if(password.isEmpty()){
                        toastMaker("Empty Password");
                        return;
                    }
                    User newUser = new User(username, password);
                    repository.insertUser(newUser);
                    toastMaker("Account created successfully");
                    SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCE_USERID_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", username);
                    editor.putInt("userId", newUser.getId());
                    editor.apply();
                    startActivity(Generations.GenerationsIntentFactory(getApplicationContext(),newUser.getId()));
                    finish();
                }
            }
        });
    }
//    private void verifyUser(){
//        String username = binding.createUser.getText().toString();
//
//        if(username.isEmpty()){
//            toastMaker("Username may not be blank");
//            return;
//        }
//
//
//
//    }
    static Intent CreateAccountIntentFactory(Context context){
        return new Intent(context, CreateAccount.class);

    }
    private void toastMaker(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
