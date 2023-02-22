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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.util.logout;

public class StatActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private long pressedTime;


    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);

        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.statDrawerLayout);




        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
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
        Intent i =new Intent( StatActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( StatActivity.this,ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( StatActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( StatActivity.this,StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        Intent i =new Intent( StatActivity.this,LoginActivity.class);
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





