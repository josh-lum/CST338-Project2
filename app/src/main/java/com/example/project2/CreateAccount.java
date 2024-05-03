package com.example.project2;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project2.Database.MonDatabase;
import com.example.project2.Database.UserDAO;
import com.example.project2.Database.entities.User;

public class CreateAccount extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordeditText;
    private Button createAccountButton;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        userDAO = MonDatabase.getDatabase(this).UserDAO();

        usernameEditText = findViewById(R.id.create_user);
        passwordeditText = findViewById(R.id.create_password);
        createAccountButton = findViewById(R.id.create_account_createAccountScreen);


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String username = usernameEditText.getText().toString();
                String password = passwordeditText.getText().toString();

                User user = new User(username, password);
                userDAO.insert(user);
                Toast.makeText(CreateAccount.this, "Account Created!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
