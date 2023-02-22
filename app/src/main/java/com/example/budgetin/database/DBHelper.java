package com.example.budgetin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.budgetin.entity.Account;
import com.example.budgetin.entity.Item;
import com.example.budgetin.entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;

    public static final String DATABASE_NAME = "BudgetIn";

    public static final String BUDGETIN_ACCOUNT_TABLE_NAME = "account";
    public static final String BUDGETIN_ACCOUNT_COLUMN_NAME_FULLNAME = "fullname";
    public static final String BUDGETIN_ACCOUNT_COLUMN_NAME_USERNAME = "username";
    public static final String BUDGETIN_ACCOUNT_COLUMN_NAME_EMAIL = "email";
    public static final String BUDGETIN_ACCOUNT_COLUMN_NAME_PASSWORD = "password";

    public static final String BUDGETIN_ITEM_TABLE_NAME = "item";
    public static final String BUDGETIN_ITEM_COLUMN_NAME_ITEM_ID = "item_id";
    public static final String BUDGETIN_ITEM_COLUMN_NAME_ITEM_CATEGORY = "category";
    public static final String BUDGETIN_ITEM_COLUMN_NAME_USERNAME = "username";
    public static final String BUDGETIN_ITEM_COLUMN_NAME_AMOUNT = "amount";
    public static final String BUDGETIN_ITEM_COLUMN_NAME_NOTE = "note";

    public static final String BUDGETIN_USER_TABLE_NAME = "user";
    public static final String BUDGETIN_USER_COLUMN_NAME_USERNAME = "username";
    public static final String BUDGETIN_USER_COLUMN_NAME_USER_DATE = "user_date";
    public static final String BUDGETIN_USER_COLUMN_NAME_TOTAL_INCOME = "total_income";
    public static final String BUDGETIN_USER_COLUMN_NAME_TOTAL_EXPENSE = "total_expense";


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String SQL_CREATE_TABLE =
//                "CREATE TABLE " + DataReaderContract.UserEntry.TABLE_NAME + " (" +
//                        DataReaderContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                        DataReaderContract.UserEntry.COLUMN_NAME_FULLNAME + " TEXT," +
//                        DataReaderContract.UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
//                        DataReaderContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
//                        DataReaderContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

        String SQL_CREATE_TABLE_BUDGETIN_ACCOUNT =
                        "CREATE TABLE "+ BUDGETIN_ACCOUNT_TABLE_NAME +"(" +
                                BUDGETIN_ACCOUNT_COLUMN_NAME_FULLNAME +" TEXT NOT NULL," +
                        BUDGETIN_ACCOUNT_COLUMN_NAME_USERNAME +" TEXT NOT NULL," +
                                BUDGETIN_ACCOUNT_COLUMN_NAME_EMAIL +" TEXT NOT NULL," +
                        BUDGETIN_ACCOUNT_COLUMN_NAME_PASSWORD +" TEXT NOT NULL," +
                        "PRIMARY KEY("+BUDGETIN_ACCOUNT_COLUMN_NAME_USERNAME+")" +
                        ")";

        String SQL_CREATE_TABLE_BUDGETIN_ITEM =
                "CREATE TABLE "+ BUDGETIN_ITEM_TABLE_NAME +"(" +
                        BUDGETIN_ITEM_COLUMN_NAME_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        BUDGETIN_ITEM_COLUMN_NAME_USERNAME + " TEXT NOT NULL, " +
                        BUDGETIN_ITEM_COLUMN_NAME_ITEM_CATEGORY + " TEXT NOT NULL, " +
                        BUDGETIN_ITEM_COLUMN_NAME_AMOUNT + " INT NOT NULL, " +
                        BUDGETIN_ITEM_COLUMN_NAME_NOTE + " TEXT NOT NULL " +
                        ")";

        String SQL_CREATE_TABLE_BUDGETIN_USER =
                "CREATE TABLE "+ BUDGETIN_USER_TABLE_NAME +"(" +
                        BUDGETIN_USER_COLUMN_NAME_USERNAME + " TEXT NOT NULL, " +
                        BUDGETIN_USER_COLUMN_NAME_USER_DATE + " TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, " +
                        BUDGETIN_USER_COLUMN_NAME_TOTAL_INCOME + " REAL NOT NULL, " +
                        BUDGETIN_USER_COLUMN_NAME_TOTAL_EXPENSE + " REAL NOT NULL, " +
                        " PRIMARY KEY("+BUDGETIN_USER_COLUMN_NAME_USERNAME+")" +
                        ")";



        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BUDGETIN_ACCOUNT);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BUDGETIN_ITEM);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BUDGETIN_USER);
    }

    public long saveUser(String fullname, String username, String email, String password, String income) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUDGETIN_ACCOUNT_COLUMN_NAME_FULLNAME, fullname);
        values.put(BUDGETIN_ACCOUNT_COLUMN_NAME_USERNAME, username);
        values.put(BUDGETIN_ACCOUNT_COLUMN_NAME_EMAIL, email);
        values.put(BUDGETIN_ACCOUNT_COLUMN_NAME_PASSWORD, password);

        long response = db.insert(BUDGETIN_ACCOUNT_TABLE_NAME, null, values);

        if(response != -1){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();

            ContentValues userValues = new ContentValues();
            userValues.put(BUDGETIN_USER_COLUMN_NAME_USERNAME, username);
            userValues.put(BUDGETIN_USER_COLUMN_NAME_TOTAL_EXPENSE, 0.0);
            userValues.put(BUDGETIN_USER_COLUMN_NAME_USER_DATE, formatter.format(date));
            userValues.put(BUDGETIN_USER_COLUMN_NAME_TOTAL_INCOME, income);

            response = db.insert(BUDGETIN_USER_TABLE_NAME, null, userValues);

        }
        db.close();

        return response;



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BUDGETIN_ACCOUNT_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BUDGETIN_ITEM_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BUDGETIN_USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Account> getAccounts()
    {

        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username = ?", new String[]{"Heran"});
        Cursor cursorAccount = db.rawQuery("SELECT * FROM " + BUDGETIN_ACCOUNT_TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Account> accountArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorAccount.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                accountArrayList.add(new Account(
                        cursorAccount.getString(0),
                        cursorAccount.getString(1),
                        cursorAccount.getString(2),
                        cursorAccount.getString(3)
                        ));
            } while (cursorAccount.moveToNext());
            // moving our cursor to next.
        }


        cursorAccount.close();

        return accountArrayList;

    }

    public User getUser(String username)
    {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursorUser = db.rawQuery("SELECT * FROM " + BUDGETIN_USER_TABLE_NAME +" WHERE username = ?", new String[]{username}, null);

        User user;

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {

                // on below line we are adding the data from cursor to our array list.
                user = new User(
                        cursorUser.getString(0),
                        cursorUser.getString(1),
                        cursorUser.getFloat(2),
                        cursorUser.getFloat(3)
                );

            // moving our cursor to next.
        }else{
            user = null;
        }


        cursorUser.close();

        return user;

    }

    public Account getAccount(String username)
    {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursorAccount = db.rawQuery("SELECT * FROM " + BUDGETIN_ACCOUNT_TABLE_NAME +" WHERE username = ?", new String[]{username}, null);

        Account account;

        // moving our cursor to first position.
        if (cursorAccount.moveToFirst()) {

            // on below line we are adding the data from cursor to our array list.
            account = new Account(
                    cursorAccount.getString(0),
                    cursorAccount.getString(1),
                    cursorAccount.getString(2),
                    cursorAccount.getString(3)
            );

            // moving our cursor to next.
        }else{
            account = null;
        }


        cursorAccount.close();

        return account;

    }

    public long editProfile(Account account){


        SQLiteDatabase db = this.getReadableDatabase();
        long response;

        ContentValues accountValues = new ContentValues();
        accountValues.put(BUDGETIN_ACCOUNT_COLUMN_NAME_FULLNAME, account.getFullname());
        accountValues.put(BUDGETIN_ACCOUNT_COLUMN_NAME_USERNAME, account.getUsername());
        accountValues.put(BUDGETIN_ACCOUNT_COLUMN_NAME_EMAIL, account.getEmail());
        accountValues.put(BUDGETIN_ACCOUNT_COLUMN_NAME_PASSWORD, account.getPassword());

        response = db.update(BUDGETIN_ACCOUNT_TABLE_NAME, accountValues, " username = ?", new String[]{account.getUsername()});

        db.close();

        return response;
    }

    public long saveItem(String username, String itemCategory, String amount, String note) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUDGETIN_ITEM_COLUMN_NAME_USERNAME, username);
        values.put(BUDGETIN_ITEM_COLUMN_NAME_ITEM_CATEGORY, itemCategory);
        values.put(BUDGETIN_ITEM_COLUMN_NAME_AMOUNT, amount);
        values.put(BUDGETIN_ITEM_COLUMN_NAME_NOTE, note);

        long response = db.insert(BUDGETIN_ITEM_TABLE_NAME, null, values);


        db.close();

        return response;

    }

    public ArrayList<Item> getItems(String username)
    {

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursorItem = db.rawQuery("SELECT * FROM " + BUDGETIN_ITEM_TABLE_NAME +" WHERE username = ?", new String[]{username}, null);


        // on below line we are creating a new array list.
        ArrayList<Item> itemsArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorItem.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                itemsArrayList.add(new Item(
                        cursorItem.getInt(0),
                        cursorItem.getString(1),
                        cursorItem.getString(2),
                        cursorItem.getFloat(3),
                        cursorItem.getString(4)
                ));
            } while (cursorItem.moveToNext());
            // moving our cursor to next.
        }


        cursorItem.close();

        return itemsArrayList;

    }


}
