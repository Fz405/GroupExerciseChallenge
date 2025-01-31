package com.group.ExerciseChallenge.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.group.ExerciseChallenge.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "user_db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD_HASH = "password_hash";
    private static final String COLUMN_AGE = "age";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USERNAME + " TEXT,"
                + COLUMN_PASSWORD_HASH + " TEXT,"
                +COLUMN_AGE + " INT "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD_HASH, user.getPasswordHash());
        values.put(COLUMN_AGE, user.getAge());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_USERNAME + " = ?", new String[]{username}, null, null, null);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int usernameIndex = cursor.getColumnIndex(COLUMN_USERNAME);
            int passwordHashIndex = cursor.getColumnIndex(COLUMN_PASSWORD_HASH);
            int ageIndex = cursor.getColumnIndex(COLUMN_AGE);

            if (idIndex != -1 && usernameIndex != -1 && passwordHashIndex != -1 && ageIndex != -1) {
                int id = cursor.getInt(idIndex);
                String storedUsername = cursor.getString(usernameIndex);
                String storedPasswordHash = cursor.getString(passwordHashIndex);
                int storedAge = cursor.getInt(ageIndex);

                user = new User(storedUsername, "", storedAge);
                user.setId(id);
                user.setPassword(storedPasswordHash);
                user.setAge(storedAge);
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return user;
    }
}
