package com.example.fsignuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectComponents();
    }

    private void connectComponents() {
        etEmail= findViewById(R.id.etEmailMain);
        etPassword= findViewById(R.id.etPasswordMain);
        etConfirmPassword= findViewById(R.id.etConfirmPassword);
    }

    public void signup(View view) {
        String email, password, confirmPassword;
        email= etEmail.getText().toString();
        password= etPassword.getText().toString();
        confirmPassword= etConfirmPassword.getText().toString();
        if (email.trim().isEmpty()|| password.trim().isEmpty()||confirmPassword.trim().isEmpty())
        {
            Toast.makeText(this, "some fields are missing!", Toast.LENGTH_SHORT).show();
            return;

    }
        if (!isEmailValid(email))
        {
            Toast.makeText(this,"Email is incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isPasswordValid(password))
        {
            Toast.makeText(this,"Password is incorrect!",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(confirmPassword))
        {
            Toast.makeText(this,"Password not identical!",Toast.LENGTH_SHORT).show();
            return;
        }


}
    public static boolean isPasswordValid(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
public boolean isEmailValid(String email)
{
    String  expression="^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
    }
}

