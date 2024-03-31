package com.example.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText aEditText, bEditText, cEditText;
    private TextView resultTextView;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aEditText = findViewById(R.id.aEditText);
        bEditText = findViewById(R.id.bEditText);
        cEditText = findViewById(R.id.cEditText);
        resultTextView = findViewById(R.id.resultTextView);

        Button solveButton = findViewById(R.id.solveButton);
        return solveButton.setOnClickListener(this::onClick
    }

    @SuppressLint("SetTextI18n")
    private void onClick(View v) {
        // Get the values of A, B, and C from the EditText fields
        double A = Double.parseDouble(aEditText.getText().toString());
        double B = Double.parseDouble(bEditText.getText().toString());
        double C = Double.parseDouble(cEditText.getText().toString());

        // Calculate the discriminant (D)
        double D = B * B - 4 * A * C;

        if (D > 0) {
            // Two real and distinct roots
            double root1 = (-B + Math.sqrt(D)) / (2 * A);
            double root2 = (-B - Math.sqrt(D)) / (2 * A);

            resultTextView.setText("Root 1: " + root1 + "\nRoot 2: " + root2);
        } else if (D == 0) {
            // One real root (repeated)
            double root = -B / (2 * A);

            resultTextView.setText("Root: " + root);
        } else {
            // Complex roots
            double realPart = -B / (2 * A);
            double imaginaryPart = Math.sqrt(Math.abs(D)) / (2 * A);

            resultTextView.setText("Root 1: " + realPart + " + " + imaginaryPart + "i" +
                    "\nRoot 2: " + realPart + " - " + imaginaryPart + "i");
        }
    }

    public void Ax(View view) {
    }
}