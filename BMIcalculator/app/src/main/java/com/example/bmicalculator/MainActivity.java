package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etHeight;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectComponents();
    }
    private void connectComponents() {
        etWeight = findViewById(R.id.etweightMain);
        etHeight = findViewById(R.id.etheightMain);
        btnCalculate= findViewById( R.id.btnCalculateMain);
    }

    public void calculate(View view) {
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();
        if ( weight.isEmpty() || !TextUtils.isDigitsOnly(weight) || weight.isEmpty() || !TextUtils.isDigitsOnly(weight)) {
            Toast.makeText(this, " Check data you entered!", Toast.LENGTH_SHORT).show();
            return;
        }
        double w, h, bmi;
        w= Double.parseDouble(weight);
        h= Double.parseDouble(height);
        bmi = w/ (h*h);
        Toast.makeText(this,  " Your BMI is : " + bmi, Toast.LENGTH_LONG).show();

    }
}
