package com.example.carcloud_group6;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Receipt extends AppCompatActivity {

    private TextView carMod, rentDate, totalPrice, name, address, contactno, drivLicense;
    private DBHelper dbHelper;
    private int userId = 1; // Assuming user_id is 1 for now

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        carMod = findViewById(R.id.carMod);
        rentDate = findViewById(R.id.rentdate);
        totalPrice = findViewById(R.id.totalPrice);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        contactno = findViewById(R.id.contactno);
        drivLicense = findViewById(R.id.drivLicense);

        dbHelper = new DBHelper(this);

        Cursor rentalCursor = dbHelper.getRentalInfo(userId);
        Cursor personalCursor = dbHelper.getPersonalInfo(userId);

        if (rentalCursor.moveToFirst() && personalCursor.moveToFirst()) {
            String selectedCarModel = rentalCursor.getString(rentalCursor.getColumnIndexOrThrow("car_model"));
            String pickupDate = personalCursor.getString(personalCursor.getColumnIndexOrThrow("pickup_date"));
            String returnDate = personalCursor.getString(personalCursor.getColumnIndexOrThrow("return_date"));
            String userName = personalCursor.getString(personalCursor.getColumnIndexOrThrow("name"));
            String userAddress = personalCursor.getString(personalCursor.getColumnIndexOrThrow("address"));
            String userContact = personalCursor.getString(personalCursor.getColumnIndexOrThrow("contact"));
            String userDriversLicense = personalCursor.getString(personalCursor.getColumnIndexOrThrow("drivers_license"));

            carMod.setText(selectedCarModel);
            rentDate.setText(pickupDate + "/" + returnDate);
            name.setText(userName);
            address.setText(userAddress);
            contactno.setText(userContact);
            drivLicense.setText(userDriversLicense);

            int daysRented = calculateDaysRented(pickupDate, returnDate);
            int pricePerDay = getPricePerDay(selectedCarModel);
            int totalCost = pricePerDay * daysRented;
            totalPrice.setText("₱" + totalCost);
        }

        rentalCursor.close();
        personalCursor.close();
    }

    private int calculateDaysRented(String pickupDate, String returnDate) {
        // Calculate the number of days between pickupDate and returnDate
        // Placeholder implementation
        return 5; // For example purposes, change as needed
    }

    private int getPricePerDay(String carModel) {
        switch (carModel) {
            case "Ford Explorer":
                return 4500;
            case "Ford Edge":
                return 4000;
            case "Ford Mustang":
                return 8500;
            case "Ford F-150 Raptor":
                return 7000;
            case "Ford Transit":
                return 5500;
            case "Mitsubishi Outlander":
                return 4500;
            case "Mitsubishi Mirage":
                return 2500;
            case "Mitsubishi Triton":
                return 5000;
            case "Mitsubishi Pajero":
                return 6000;
            case "Mitsubishi Delica":
                return 8500;
            case "Toyota Hilux":
                return 4000;
            case "Toyota Supra":
                return 8500;
            case "Toyota Fortuner":
                return 5000;
            case "Toyota Hiace":
                return 5500;
            case "Toyota Camry":
                return 3750;
            case "Nissan GT-R":
                return 20000;
            case "Nissan Sunny":
                return 2500;
            case "Nissan Juke":
                return 3500;
            case "Nissan Patrol":
                return 8500;
            case "Nissan Urvan":
                return 6000;
            default:
                return 0; // Default price if car model is not found
        }
    }
}
