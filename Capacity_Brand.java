package com.example.carcloud_group6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Capacity_Brand extends AppCompatActivity {

    private Spinner noPassengerSpin, carBrandSpin, carModelSpin;
    private Button nextBtn;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capacity_brand);

        noPassengerSpin = findViewById(R.id.noPassengerSpin);
        carBrandSpin = findViewById(R.id.carBrandSpin);
        carModelSpin = findViewById(R.id.carModelSpin);
        nextBtn = findViewById(R.id.nextBtn);

        dbHelper = new DBHelper(this);

        // Set number of passengers items
        String[] passengers = {"2", "4", "6", "8", "12"};
        ArrayAdapter<String> passengerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, passengers);
        passengerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noPassengerSpin.setAdapter(passengerAdapter);

        // Set car brands items
        String[] carBrands = {"Ford", "Nissan", "Mitsubishi", "Toyota"};
        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carBrands);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carBrandSpin.setAdapter(brandAdapter);

        // Handle car brand selection
        carBrandSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedBrand = parent.getItemAtPosition(position).toString();
                updateCarModels(selectedBrand);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Handle next button click
        nextBtn.setOnClickListener(v -> {
            String noPassengers = noPassengerSpin.getSelectedItem().toString();
            String carBrand = carBrandSpin.getSelectedItem().toString();
            String carModel = carModelSpin.getSelectedItem().toString();

            boolean isInserted = dbHelper.insertRentalInfo(1, noPassengers, carBrand, carModel); // Assuming user_id is 1 for now

            if (isInserted) {
                Intent intent = new Intent(Capacity_Brand.this, PersonalInfo.class);
                startActivity(intent);
            } else {
                Toast.makeText(Capacity_Brand.this, "Failed to save data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCarModels(String brand) {
        String[] carModels;
        switch (brand) {
            case "Ford":
                carModels = new String[]{"Ford Explorer", "Ford Edge", "Ford Mustang", "Ford F-150 Raptor", "Ford Transit"};
                break;
            case "Mitsubishi":
                carModels = new String[]{"Mitsubishi Outlander", "Mitsubishi Mirage", "Mitsubishi Triton", "Mitsubishi Pajero", "Mitsubishi Delica"};
                break;
            case "Toyota":
                carModels = new String[]{"Toyota Hilux", "Toyota Supra", "Toyota Fortuner", "Toyota Hiace", "Toyota Camry"};
                break;
            case "Nissan":
                carModels = new String[]{"Nissan GT-R", "Nissan Sunny", "Nissan Juke", "Nissan Patrol", "Nissan Urvan"};
                break;
            default:
                carModels = new String[]{"Select a brand first"};
                break;
        }

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carModels);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carModelSpin.setAdapter(modelAdapter);
    }
}
