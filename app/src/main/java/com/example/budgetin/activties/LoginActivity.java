package com.example.budgetin.activties;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.entity.Account;

import java.util.ArrayList;
import java.util.Locale;


public class LoginActivity extends AppCompatActivity {


    private CheckBox remember;
    private SharedPreferences pref;
    EditText username;
    EditText password;
    TextView regLink;
    Button loginBtn;
    DBHelper dbHelper;
    ArrayList<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        remember = findViewById(R.id.remember);
        regLink = findViewById(R.id.regLink);
        dbHelper = new DBHelper(LoginActivity.this);
        accounts = dbHelper.getAccounts();


        regLink.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(i);
        });

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(v -> {
            if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "Please enter credentials", Toast.LENGTH_LONG).show();
                return;
            }

            for(Account account : accounts){
                if(account.getUsername().equals(username.getText().toString().trim().toLowerCase(Locale.ROOT)) && account.getPassword().equals(password.getText().toString())){

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("NAME", account.getFullname());
                    editor.putString("USERNAME", account.getUsername());
                    editor.putBoolean("AUTHENTICATED", true);
                    editor.apply();
                    Intent i = new Intent(LoginActivity.this, ExpensesActivity.class);
                    startActivity(i);
                    return;
                }
            }

            Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_LONG).show();


        });


        remember.setOnClickListener(
                v -> {
                    editSharedPrefrence("REMEMBER", remember.isChecked());
                }
        );




//        if (pref.getBoolean("REMEMBER", false)) {
//            username.setText(pref.getString("USERNAME", "").toString());
//            password.setText(pref.getString("PASSWORD", "").toString());
//            remember.setChecked(true);
//        }
    }

    void editSharedPrefrence(String key, boolean value){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
}