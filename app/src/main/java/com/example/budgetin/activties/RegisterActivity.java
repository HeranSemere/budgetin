package com.example.budgetin.activties;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetin.R;
import com.example.budgetin.database.DBHelper;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    EditText name, username, password, confirmPassword, email, income;
    RadioButton male, female;
    Button signup;
    TextView regLink;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.conPsd);
        regLink = findViewById(R.id.logLink);
        signup = findViewById(R.id.signup);
        income = findViewById(R.id.income);
        dbHelper = new DBHelper(RegisterActivity.this);


        regLink.setOnClickListener(v -> {
            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(i);
        });


        signup.setOnClickListener(v -> {

            String pass=password.getText().toString();
            String cpass=confirmPassword.getText().toString();

            if (name.getText().toString().equals("")) {
                name.setError(getString(R.string.error_field_required));
                name.requestFocus();
            }

//                else if (email.getText().toString()) {
//                    email.setError(getString(R.string.error_phone_length));
//                    focusView = email;
//                    focusView.requestFocus();
//                }
            else if (email.getText().toString().equals("")) {
                email.setError(getString(R.string.error_field_required));
                email.requestFocus();
            }
            else if (username.getText().toString().equals("")) {
                username.setError(getString(R.string.error_field_required));
                username.requestFocus();
            } else if (password.getText().toString().equals("")) {
                password.setError(getString(R.string.error_field_required));
                password.requestFocus();
            }
            else if (!pass.equals(cpass)) {
                confirmPassword.setError("Password does not match");
                confirmPassword.requestFocus();
            }
            else {

                long response = dbHelper.saveUser(name.getText().toString(), username.getText().toString().trim().toLowerCase(Locale.ROOT), email.getText().toString(), password.getText().toString(), income.getText().toString());
                if(response == -1){
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }


            }
        });

    }
}