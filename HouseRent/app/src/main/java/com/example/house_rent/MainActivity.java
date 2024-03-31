package com.example.house_rent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final double TAX_RATE = 0.12;
    private static final double INSURANCE_RATE = 0.05;
    private static final double UTILITIES_RATE = 0.08;

    private double calculateTotalRent(double rent) {
        double totalRent = rent;
        totalRent += totalRent * TAX_RATE;
        totalRent += totalRent * INSURANCE_RATE;
        totalRent += totalRent * UTILITIES_RATE;
        return totalRent;
    }

}
