package com.example.globalitsupport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "GITDB";
    static int version = 1;

    String createTableUser = "CREATE TABLE if not exists `user` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`phone`\tINTEGER,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`image`\tBLOB\n" +
            ")";

    String nextTable = "CREATE TABLE if not exists `user` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`phone`\tINTEGER,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`image`\tBLOB\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }

    public void updateUser(String id, ContentValues contentValues) {
        getWritableDatabase().update("user", contentValues, "id=" + id, null);
    }

    public ArrayList<UserInfo> getUserList() {
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        ArrayList<UserInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfo info = new UserInfo();
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.name = cursor.getString(cursor.getColumnIndex("name"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.phone = cursor.getString(cursor.getColumnIndex("phone"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.image = cursor.getBlob(cursor.getColumnIndex("image"));

            list.add(info);
        }
        cursor.close();
        return list;
    }

    public UserInfo getUserInfo(String id) {
        String sql = "Select * from user where id =" + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        UserInfo info = new UserInfo();
        while (cursor.moveToNext()) {
            info.id = cursor.getString(cursor.getColumnIndex("id"));
            info.name = cursor.getString(cursor.getColumnIndex("name"));
            info.address = cursor.getString(cursor.getColumnIndex("address"));
            info.phone = cursor.getString(cursor.getColumnIndex("phone"));
            info.email = cursor.getString(cursor.getColumnIndex("email"));
            info.username = cursor.getString(cursor.getColumnIndex("username"));
            info.password = cursor.getString(cursor.getColumnIndex("password"));
            info.image = cursor.getBlob(cursor.getColumnIndex("image"));

        }
        cursor.close();
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createTableUser);
        db.execSQL(nextTable);
    }
}
