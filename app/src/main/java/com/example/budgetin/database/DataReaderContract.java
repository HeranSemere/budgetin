package com.example.budgetin.database;

import android.provider.BaseColumns;

public class DataReaderContract {


    private DataReaderContract() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_FULLNAME = "fullname";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.COLUMN_NAME_FULLNAME + " TEXT," +
                    UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                    UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    UserEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
}




