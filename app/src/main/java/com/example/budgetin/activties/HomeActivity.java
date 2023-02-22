package com.example.budgetin.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.adapter.RecyclerViewAdapterImageCard;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.entity.ImageRecyclerData;
import com.example.budgetin.entity.User;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ImageRecyclerData> recyclerDataArrayList;
    private long pressedTime;
    SharedPreferences pref;
    TextView welcomeHome;
    TextView totalIncome;
    TextView totalExpense;
    DBHelper dbHelper;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeHome = findViewById(R.id.welcome_home);
        totalIncome = findViewById(R.id.incomeTextView);
        totalExpense = findViewById(R.id.expenseTextView);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        dbHelper = new DBHelper(HomeActivity.this);
        user = dbHelper.getUser(pref.getString("USERNAME", ""));

        welcomeHome.setText("Welcome "+pref.getString("NAME", "to BudgetIn"));
        totalIncome.setText(Float.toString(user.getTotal_income()));
        totalExpense.setText(Float.toString(user.getTotal_expense()));



       // Toast.makeText(getBaseContext(), Integer.toString(dbHelper.getItems(pref.getString("USERNAME", "")).size()), Toast.LENGTH_SHORT).show();

        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.homeDrawerLayout);

        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        recyclerView=findViewById(R.id.imageRV);
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));
        recyclerDataArrayList.add(new ImageRecyclerData("Food",R.mipmap.ic_launcher, "10%"));

        RecyclerViewAdapterImageCard adapter=new RecyclerViewAdapterImageCard(recyclerDataArrayList,this);


        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        Intent i =new Intent( HomeActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( HomeActivity.this,ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( HomeActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( HomeActivity.this,StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        Intent i =new Intent( HomeActivity.this,LoginActivity.class);
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