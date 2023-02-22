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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.util.logout;

public class AddExpenseActivity extends AppCompatActivity {

    private long pressedTime;
    SharedPreferences pref;
    private RadioGroup radioGroup;
    String radioValue = null;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.homeDrawerLayout);

        radioGroup = findViewById(R.id.radioGroup);
        dbHelper = new DBHelper(AddExpenseActivity.this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // on below line we are getting radio button from our group.
                RadioButton radioButton = findViewById(checkedId);

                radioValue = radioButton.getText().toString();
            }
        });

        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radioValue == null){
                    Toast.makeText(getBaseContext(), "Select category", Toast.LENGTH_SHORT).show();
                }else{
                    EditText expense = findViewById(R.id.expenseInput);
                    EditText note = findViewById(R.id.noteExpense);

                    long response = dbHelper.saveItem(pref.getString("USERNAME", ""),radioValue,expense.getText().toString(), note.getText().toString());
                    if(response!=-1){
                        Intent i =new Intent( AddExpenseActivity.this, ExpensesActivity.class);
                        startActivity(i);
                        Toast.makeText(getBaseContext(), "Expense added", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getBaseContext(), "Expense could not be added", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
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
        Intent i =new Intent( AddExpenseActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( AddExpenseActivity.this,ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( AddExpenseActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( AddExpenseActivity.this,StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){


        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        logout.logout();
        Intent i =new Intent( AddExpenseActivity.this,LoginActivity.class);
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





