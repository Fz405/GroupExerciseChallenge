package com.group.ExerciseChallenge.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group.ExerciseChallenge.R;
import com.group.ExerciseChallenge.database.DatabaseHelper;
import com.group.ExerciseChallenge.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button registerButton;
    private DatabaseHelper databaseHelper;
    private EditText ageEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerBtn);
        ageEditTxt = findViewById(R.id.ageEditText);

        databaseHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                int age = Integer.parseInt(ageEditTxt.getText().toString());

                if (!username.isEmpty() && !password.isEmpty()) {
                    User user = new User(username, password, age);
                    long userId = databaseHelper.addUser(user);

                    if (userId != -1) {
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
