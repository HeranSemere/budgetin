package com.example.budgetin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetin.R;
import com.example.budgetin.activties.AddExpenseActivity;
import com.example.budgetin.activties.LoginActivity;
import com.example.budgetin.activties.ProfileActivity;
import com.example.budgetin.activties.SpecCategoryActivity;
import com.example.budgetin.activties.StatActivity;
import com.example.budgetin.adapter.ExpensesAdapter;
import com.example.budgetin.adapter.RecyclerViewAdapterImageCard;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.entity.ImageRecyclerData;
import com.example.budgetin.entity.Item;

import java.util.ArrayList;

public class ExpensesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> recyclerDataArrayList;

    private long pressedTime;
    SharedPreferences pref;
    DBHelper dbHelper;
    TextView welcome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.expenseDrawerLayout);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        dbHelper = new DBHelper(ExpensesActivity.this);

        recyclerView=findViewById(R.id.expenseRV);
        welcome=findViewById(R.id.welcome);
        recyclerDataArrayList=new ArrayList<>();

        recyclerDataArrayList = dbHelper.getItems(pref.getString("USERNAME", ""));

        welcome.setText("Welcome "+pref.getString("USERNAME", " to BudgetIn"));

        ExpensesAdapter adapter=new ExpensesAdapter(recyclerDataArrayList,this);


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        findViewById(R.id.addExpenseBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ExpensesActivity.this, AddExpenseActivity.class);
                startActivity(i);



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
        Intent i =new Intent( ExpensesActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( ExpensesActivity.this, ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( ExpensesActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( ExpensesActivity.this, StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){
        SharedPreferences pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        Intent i =new Intent( ExpensesActivity.this, LoginActivity.class);
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