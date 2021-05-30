package co.in.nixlab.attendance_manager.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.models.User;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "attendance_manager";
    private static final String TABLE_USERS = "users";

    private static final String KEY_UID = "uid";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UNAME = "uname";
    private static final String KEY_PASS = "pass";
    private static final String KEY_USER_TYPE = "user_type";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_UID +
                " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT," +
                KEY_UNAME + " TEXT," + KEY_PASS + " TEXT," + KEY_USER_TYPE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_EMAIL, user.get_email());
        values.put(KEY_UNAME, user.get_uname());
        values.put(KEY_PASS, user.get_pass());
        values.put(KEY_USER_TYPE, user.getUser_type());

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User loginUser(String uname, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[]
                {KEY_UID, KEY_NAME, KEY_EMAIL, KEY_UNAME, KEY_PASS, KEY_USER_TYPE},
                KEY_UNAME + "=?",
                new String[] {uname}, null, null, null);
        if(cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            User currentUser  = new User(cursor.getString(4));
            if(pass.equals(currentUser.get_pass()))
                return currentUser;
        }
        assert cursor != null;
        cursor.close();
        db.close();
        return null;
    }

    public User getUser(String uid) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user;
        Cursor cursor;

        cursor = db.query(TABLE_USERS, new String[]
                        {KEY_UID, KEY_NAME, KEY_EMAIL, KEY_UNAME, KEY_PASS, KEY_USER_TYPE},
                KEY_UID + "=?",
                new String[]{uid}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;

        user = new User(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));

        cursor.close();
        db.close();
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;

        String SELECT_USERS = "SELECT * FROM " + TABLE_USERS;

        cursor = db.rawQuery(SELECT_USERS, null);
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.set_uid(cursor.getString(0));
                user.set_name(cursor.getString(1));
                user.set_email(cursor.getString(2));
                user.set_uname(cursor.getString(3));
                user.set_pass(cursor.getString(4));
                user.setUser_type(cursor.getString(5));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_EMAIL, user.get_email());
        values.put(KEY_UNAME, user.get_uname());
        values.put(KEY_PASS, user.get_pass());
        values.put(KEY_USER_TYPE, user.getUser_type());

        return db.update(TABLE_USERS, values, KEY_UID + " = ?",
                new String[]{user.get_uid()});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USERS, KEY_UID + " = ?",
                new String[]{user.get_uid()});
        db.close();
    }

    public int getUsersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;

        String GET_USERS_COUNT = "SELECT * FROM " + TABLE_USERS;
        cursor = db.rawQuery(GET_USERS_COUNT, null);
        cursor.close();
        db.close();
        return cursor.getCount();
    }
}
