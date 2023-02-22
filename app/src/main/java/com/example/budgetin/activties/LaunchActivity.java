package com.example.budgetin.activties;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;

public class LaunchActivity extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);

//        getSupportActionBar().hide();

        try {
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
            new Handler().postDelayed(() -> {

                boolean goToHome = pref.getBoolean("AUTHENTICATED", false) && pref.getBoolean("REMEMBER", false);
                Intent i = new Intent(LaunchActivity.this, goToHome ? ExpensesActivity.class : LoginActivity.class);
                startActivity(i);
                finish();
            }, 3500);


        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
