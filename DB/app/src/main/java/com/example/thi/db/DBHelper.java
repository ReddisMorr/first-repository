package com.example.thi.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by THI on 18.01.2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    private final static String TAG= "<DB>";
    private final static String KEY_ID= "id";
    private final static String KEY_USERS_NAME= "name";
    private final static String KEY_USERS_LOGIN= "login";
    private final static String KEY_USERS_PASSWORD= "password";
    private final static String KEY_USERS_IS_ADMIN= "is_admin";
    private final static String TABLE_USERS= "users";
    private final static String CREATE_TABLE_USERS = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
            TABLE_USERS, KEY_ID, KEY_USERS_NAME, KEY_USERS_LOGIN, KEY_USERS_PASSWORD, KEY_USERS_IS_ADMIN);
    public DBHelper(Context context) {
        super(context, "DBmanager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, CREATE_TABLE_USERS);
db=this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public User createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERS_NAME, user.getName());
        values.put(KEY_USERS_LOGIN, user.getLogin());
        values.put(KEY_USERS_PASSWORD, user.getPassword());
        values.put(KEY_USERS_IS_ADMIN, user.isAdmin());
        values.put(KEY_USERS_NAME, user.getName());
        long id = db.insert(TABLE_USERS, null, values);
        user.setId(id);
        return user;
    }
}
