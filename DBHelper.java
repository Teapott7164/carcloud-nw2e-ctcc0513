package com.example.carcloud_group6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CarCloudRental.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
        db.execSQL("CREATE TABLE rental_info (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, no_passengers TEXT, car_brand TEXT, car_model TEXT)");
        db.execSQL("CREATE TABLE personal_info (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, pickup_date TEXT, return_date TEXT, name TEXT, address TEXT, contact TEXT, drivers_license TEXT)");
        // Add other necessary tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS rental_info");
        db.execSQL("DROP TABLE IF EXISTS personal_info");
        onCreate(db);
    }

    public boolean insertRentalInfo(int userId, String noPassengers, String carBrand, String carModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("no_passengers", noPassengers);
        contentValues.put("car_brand", carBrand);
        contentValues.put("car_model", carModel);

        long result = db.insert("rental_info", null, contentValues);
        return result != -1;
    }

    public boolean insertPersonalInfo(int userId, String pickupDate, String returnDate, String name, String address, String contact, String driversLicense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("pickup_date", pickupDate);
        contentValues.put("return_date", returnDate);
        contentValues.put("name", name);
        contentValues.put("address", address);
        contentValues.put("contact", contact);
        contentValues.put("drivers_license", driversLicense);

        long result = db.insert("personal_info", null, contentValues);
        return result != -1;
    }

    public Cursor getRentalInfo(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM rental_info WHERE user_id = ?", new String[]{String.valueOf(userId)});
    }

    public Cursor getPersonalInfo(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM personal_info WHERE user_id = ?", new String[]{String.valueOf(userId)});
    }
}

