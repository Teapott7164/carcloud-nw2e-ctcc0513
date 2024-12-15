package com.example.carcloud_group6;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputUn;
    private EditText inputPass;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure to set the correct layout

        inputUn = findViewById(R.id.inputUn);
        inputPass = findViewById(R.id.inputPass);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {
            String email = inputUn.getText().toString();
            String password = inputPass.getText().toString();

            if (isValidEmail(email)) {
                // Proceed to the next activity
                Intent intent = new Intent(MainActivity.this, Capacity_Brand.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && email.endsWith("@gmail.com");
    }
}
