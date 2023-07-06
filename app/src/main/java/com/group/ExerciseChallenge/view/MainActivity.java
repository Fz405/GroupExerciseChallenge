package com.group.ExerciseChallenge.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.group.ExerciseChallenge.R;
import com.group.ExerciseChallenge.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= findViewById(R.id.usrname);
        databaseHelper = new DatabaseHelper(this);

        // Accessing protected resources
        // ...

        // Example: Retrieve the currently logged-in user's information

        // Inside onCreate() method of MainActivity
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
          if (username != null) {
            String message = "Welcome, " + username + "!";
            tv.setText(message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
        }



        // Use the retrieved user object as needed
        // ...
    }


}
