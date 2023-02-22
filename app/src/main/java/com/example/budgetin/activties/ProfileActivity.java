package com.example.budgetin.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.entity.Account;
import com.example.budgetin.util.logout;

public class ProfileActivity extends AppCompatActivity {

    private long pressedTime;
    SharedPreferences pref;
    DBHelper dbHelper;
    Account account;

    EditText fullname;
    TextView username;
    EditText email;
    EditText password;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fullname = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        save = findViewById(R.id.editBtn);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        dbHelper = new DBHelper(ProfileActivity.this);
        account = dbHelper.getAccount(pref.getString("USERNAME", ""));

        fullname.setText(account.getFullname());
        username.setText(account.getUsername());
        email.setText(account.getEmail());
        password.setText(account.getPassword());

        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.profileDrawerLayout);

        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        save.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        if(fullname.getText().toString().equals("") || username.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("")){
                            Toast.makeText(ProfileActivity.this, "Empty fields not allowed", Toast.LENGTH_LONG).show();
                        }else{
                            Account a = new Account(fullname.getText().toString(),username.getText().toString(),email.getText().toString(),password.getText().toString());
                            long response = dbHelper.editProfile(a);
                            if(response != -1){
                                Toast.makeText(ProfileActivity.this, "Fields updated", Toast.LENGTH_LONG).show();
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NAME", a.getFullname());
                                editor.apply();
                            }else{
                                Toast.makeText(ProfileActivity.this, "Fields could not be updated", Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finishAffinity();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();

        }
        pressedTime = System.currentTimeMillis();
    }
    public void home(MenuItem item){
        Intent i =new Intent( ProfileActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( ProfileActivity.this,ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( ProfileActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( ProfileActivity.this,StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        Intent i =new Intent( ProfileActivity.this,LoginActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void goDark(MenuItem item){
        if(!item.isChecked()){
            item.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            item.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}





