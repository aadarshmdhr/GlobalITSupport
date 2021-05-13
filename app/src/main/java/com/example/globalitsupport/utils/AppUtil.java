package com.example.globalitsupport.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AppUtil {

    public static Bitmap getBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}
