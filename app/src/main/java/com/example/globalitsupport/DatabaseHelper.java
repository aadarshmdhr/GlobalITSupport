package com.example.globalitsupport;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.globalitsupport.fragments.HomeFragment;
import com.example.globalitsupport.models.Laptop;
import com.example.globalitsupport.models.UserInfo;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "GITDB";
    static int version = 1;

    Context context;

    SharedPreferences sharedPreferences;

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

    String createTableLaptop = "CREATE TABLE if not exists `laptopdetail` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`name`\tTEXT,\n" +
            "\t`model`\tTEXT,\n" +
            "\t`ram`\tTEXT,\n" +
            "\t`harddrive`\tTEXT,\n" +
            "\t`os`\tTEXT,\n" +
            "\t`gpuprocessor`\tTEXT,\n" +
            "\t`detail`\tTEXT,\n" +
            "\t`image`\tBLOB,\n" +
            "\t`price`\tINTEGER\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        this.context =context;
        sharedPreferences = context.getSharedPreferences("Userinfo", 0);
        getWritableDatabase().execSQL(createTableUser);
        getWritableDatabase().execSQL(createTableLaptop);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }

    public void insertLaptop(ContentValues contentValues) {
        getWritableDatabase().insert("laptopdetail", "", contentValues);
    }

    public void updateUser(String id, ContentValues contentValues) {
        getWritableDatabase().update("user", contentValues, "id=" + id, null);
    }

    public void updateLaptop(String id, ContentValues contentValues) {
        getWritableDatabase().update("laptopdetail", contentValues, "id=" + id, null);
    }

    public boolean isLoginSuccessful(String username, String password) {
        String sql = "Select count(*) from user where username='" + username + "' and password='" + password + "'";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while(cursor.moveToNext()){
            int userId = cursor.getInt(cursor.getColumnIndex("id"));
            sharedPreferences.edit().putInt("userId", userId).apply();
        }
        cursor.close();
        SQLiteStatement stm = getReadableDatabase().compileStatement(sql);
        long l = stm.simpleQueryForLong();
        return l == 1;
    }

    public void deleteUser(String id) {
        getWritableDatabase().delete("user", "id=" + id, null);
    }

    public void deleteLaptop(String id) {
        getWritableDatabase().delete("laptopdetail", "id", null);
    }

    public ArrayList<UserInfo> getUserList() {
        String sql = "Select * from user";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        ArrayList<UserInfo> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfo info = new UserInfo();
            info.id = cursor.getInt(cursor.getColumnIndex("id"));
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
            info.id = cursor.getInt(cursor.getColumnIndex("id"));
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

    public ArrayList<Laptop> getAllLaptop() {
        String sql = "Select * from laptopdetail";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        ArrayList<Laptop> laptops = new ArrayList<>();
        while (cursor.moveToNext()) {
            Laptop laptop = new Laptop();
            laptop.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
            laptop.setId(cursor.getInt(cursor.getColumnIndex("id")));
            laptop.setName(cursor.getString(cursor.getColumnIndex("name")));
            laptop.setModelNo(cursor.getString(cursor.getColumnIndex("modelno")));
            laptop.setRam(cursor.getString(cursor.getColumnIndex("ram")));
            laptop.setHardDrive(cursor.getString(cursor.getColumnIndex("harddrive")));
            laptop.setOs(cursor.getString(cursor.getColumnIndex("os")));
            laptop.setGpuProcessor(cursor.getString(cursor.getColumnIndex("gpuprocessor")));
            laptop.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            laptop.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            laptop.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            laptops.add(laptop);
        }
        cursor.close();
        return laptops;
    }

    public Laptop getLaptop(String id) {
        String sql = "Select * from laptopdetail where id =" + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Laptop laptop = new Laptop();
        while (cursor.moveToNext()) {
            laptop.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
            laptop.setId(cursor.getInt(cursor.getColumnIndex("id")));
            laptop.setName(cursor.getString(cursor.getColumnIndex("name")));
            laptop.setModelNo(cursor.getString(cursor.getColumnIndex("modelno")));
            laptop.setRam(cursor.getString(cursor.getColumnIndex("ram")));
            laptop.setHardDrive(cursor.getString(cursor.getColumnIndex("harddrive")));
            laptop.setOs(cursor.getString(cursor.getColumnIndex("os")));
            laptop.setGpuProcessor(cursor.getString(cursor.getColumnIndex("gpuprocessor")));
            laptop.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            laptop.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            laptop.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
        }
        cursor.close();
        return laptop;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(createTableUser);
        db.execSQL(createTableLaptop);
    }
}
