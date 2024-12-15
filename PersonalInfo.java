package com.example.carcloud_group6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfo extends AppCompatActivity {

    private EditText editTextDate, editTextDate2, editTextText, inputAdd, inputContact, inputDriv;
    private Button confirmBtn;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        editTextDate = findViewById(R.id.editTextDate);
        editTextDate2 = findViewById(R.id.editTextDate2);
        editTextText = findViewById(R.id.editTextText);
        inputAdd = findViewById(R.id.inputAdd);
        inputContact = findViewById(R.id.inputContact);
        inputDriv = findViewById(R.id.inputDriv);
        confirmBtn = findViewById(R.id.confirmBtn);

        dbHelper = new DBHelper(this);

        confirmBtn.setOnClickListener(v -> {
            String pickupDate = editTextDate.getText().toString();
            String returnDate = editTextDate2.getText().toString();
            String name = editTextText.getText().toString();
            String address = inputAdd.getText().toString();
            String contact = inputContact.getText().toString();
            String driversLicense = inputDriv.getText().toString();

            boolean isInserted = dbHelper.insertPersonalInfo(1, pickupDate, returnDate, name, address, contact, driversLicense); // Assuming user_id is 1 for now

            if (isInserted) {
                Intent intent = new Intent(PersonalInfo.this, Receipt.class);
                startActivity(intent);
            } else {
                Toast.makeText(PersonalInfo.this, "Failed to save data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
