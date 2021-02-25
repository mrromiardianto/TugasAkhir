package com.example.screening_time.Anak.Fragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Utils.AlarmReceiver;
import com.example.screening_time.Anak.Utils.AppCheckServices;
import com.example.screening_time.Anak.Utils.AppLockConstants;
import com.example.screening_time.R;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;
    SharedPreferences sharedPreferences;
    private static final int REQUEST_CODE = 101;
    Context context;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    public static int MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_menu__splash_screen);
        checkPermissions();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
         String IMEINumber = telephonyManager.getDeviceId();
        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_IMEI,IMEINumber);


    }
    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {
                OverlayPermissionDialogFragment dialogFragment = new OverlayPermissionDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "Overlay Permission");

            }else if(!hasUsageStatsPermission()){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    UsageAcessDialogFragment dialogFragment = new UsageAcessDialogFragment();
                    ft.add(dialogFragment, null);
                    ft.commitAllowingStateLoss();
            } else {
                    startService();
                }

            }
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super().
        super.onSaveInstanceState(outState);
    }

    public void startService(){
        /****************************** too much important don't miss it *****************************/
        startService(new Intent(SplashActivity.this, AppCheckServices.class));

        try {
            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 999, alarmIntent, 0);
            int interval = (86400 * 1000) / 4;
            if (manager != null) {
                manager.cancel(pendingIntent);
            }
            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }


        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        final boolean isPasswordSet = sharedPreferences.getBoolean(AppLockConstants.IS_PASSWORD_SET, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isPasswordSet) {
                    Intent i = new Intent(SplashActivity.this, Menu_Utama.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this, PasswordSetActivity.class);
                    startActivity(i);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
        /***************************************************************************************/
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        /** check if received result code
         is equal our requested code for draw permission  */
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("SplashActivity", "cp 1");
        checkPermissions();
//        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
//            // ** if so check once again if we have permission */
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                Log.d("SplashActivity", "cp 2");
//                if (Settings.canDrawOverlays(this)) {
//                    Log.d("SplashActivity", "cp 3");
//                       checkPermissions();
//                }
//            }
//        }else if(requestCode == MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS){
//            if (hasUsageStatsPermission()){
//                Log.d("SplashActivity", "cp 4");
//                checkPermissions();
//            }
//        }
    }

    private boolean hasUsageStatsPermission() {
        AppOpsManager appOps = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            appOps = (AppOpsManager)
                    getSystemService(Context.APP_OPS_SERVICE);
        }
        int mode = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), getPackageName());
//            Log.d("package",getPackageCodePath());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }


    public static class OverlayPermissionDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.ovarlay_permission_description)
                    .setTitle("Overlay Permission")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:" + getActivity().getPackageName()));
                            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                        }
                    });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    public static class UsageAcessDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage(R.string.usage_data_access_description)
                    .setTitle("Usage Access Permission")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            startActivityForResult(
                                    new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
                        }
                    });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}
