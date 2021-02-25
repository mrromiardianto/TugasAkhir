package com.example.screening_time.Anak.Utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class Admin_Receiver extends AdminReceiver {
    @Override
    public CharSequence onDisableRequested(final Context context, Intent intent) {

        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startMain); //switch to the home screen, not totally necessary
//        lockPhone(context, secPassword);
        //Log.i(TAG, "DEVICE ADMINISTRATION DISABLE REQUESTED & LOCKED PHONE");

        return "haha. i locked your phone.";
    }
    public static boolean lockPhone(Context context, String password){
        ComponentName devAdminReceiver = new ComponentName(context, Admin_Receiver.class);
        DevicePolicyManager dpm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        boolean pwChange = dpm.resetPassword(password, 0);
        dpm.lockNow();
        return pwChange;
    }
}
