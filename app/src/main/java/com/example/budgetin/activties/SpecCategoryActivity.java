package com.example.budgetin.activties;

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
import android.widget.Toast;

import com.example.budgetin.ExpensesActivity;
import com.example.budgetin.R;
import com.example.budgetin.adapter.RecyclerViewAdapterImageCard;
import com.example.budgetin.adapter.RecyclerViewAdapterItem;
import com.example.budgetin.database.DBHelper;
import com.example.budgetin.entity.ImageRecyclerData;
import com.example.budgetin.entity.Item;
import com.example.budgetin.util.logout;

import java.util.ArrayList;

public class SpecCategoryActivity extends AppCompatActivity {

    private long pressedTime;
    SharedPreferences pref;
    DBHelper dbHelper;


    private RecyclerView recyclerView;
    private ArrayList<Item>  recyclerDataArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spec_category);

        pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        final DrawerLayout drawerLayout;
        drawerLayout= findViewById(R.id.specDrawerLayout);

        dbHelper = new DBHelper(SpecCategoryActivity.this);

        recyclerView=findViewById(R.id.itemsRV);
        recyclerDataArrayList = new ArrayList<>();

        //recyclerDataArrayList =  dbHelper.getItems(pref.getString("USERNAME", ""));

        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));
        recyclerDataArrayList.add(new Item(1, "fsa", "fe", 1, "dfea"));

        RecyclerViewAdapterItem adapter=new RecyclerViewAdapterItem(recyclerDataArrayList,this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        findViewById(R.id.menuHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("inside menu");
                drawerLayout.openDrawer(GravityCompat.START);
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
        Intent i =new Intent( SpecCategoryActivity.this, ExpensesActivity.class);
        finishAffinity();
        startActivity(i);
    }
    public void profile(MenuItem item){
        Intent i =new Intent( SpecCategoryActivity.this,ProfileActivity.class);

        startActivity(i);
    }
    public void expenses(MenuItem item){
        Intent i =new Intent( SpecCategoryActivity.this, AddExpenseActivity.class);
        startActivity(i);
    }
    public void statistics(MenuItem item){
        Intent i =new Intent( SpecCategoryActivity.this,StatActivity.class);
        startActivity(i);
    }
    public void logOut(MenuItem item){
        SharedPreferences pref = getSharedPreferences("ACCOUNT_INFO", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("AUTHENTICATED", false);
        editor.putBoolean("REMEMBER", false);
        editor.clear();
        editor.apply();

        Intent i =new Intent( SpecCategoryActivity.this,LoginActivity.class);
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





