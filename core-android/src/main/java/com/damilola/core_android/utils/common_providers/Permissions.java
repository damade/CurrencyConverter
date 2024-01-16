package com.damilola.core_android.utils.common_providers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import logcat.LogPriority.*;
import androidx.core.app.ActivityCompat;



public class Permissions {
    private static final String TAG = Permissions.class.getSimpleName();
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;

    public static final String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };

    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public static boolean checkPermissionsArray(Activity activity, String[] permissions){
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for(int i = 0; i< permissions.length; i++){
            String check = permissions[i];
            if(!checkPermissions(activity , check)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission is it has been verified
     * @param permission
     * @return
     */
    public static boolean checkPermissions(Activity activity, String permission){
        Log.d(TAG,"checkPermissions: checking permission: "+permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(activity , permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        }
        else{
            Log.d(TAG,"checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }

    public static void verifyPermissions(Activity activity, String[] permissions){
        Log.d(TAG,"verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                activity,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

}
