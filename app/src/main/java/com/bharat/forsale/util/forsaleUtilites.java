package com.bharat.forsale.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

public class forsaleUtilites {

    public static boolean hasPermission(Context context){
        String[] permission = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };
        return (ContextCompat.checkSelfPermission(context,permission[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,permission[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,permission[2]) == PackageManager.PERMISSION_GRANTED );
    }




}
