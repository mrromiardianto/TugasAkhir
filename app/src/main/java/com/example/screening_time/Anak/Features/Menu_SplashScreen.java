package com.example.screening_time.Anak.Features;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Server.Koneksi_RMQ;
import com.example.screening_time.Anak.Server.MyRmq;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Utils.AdminReceiver;
import com.example.screening_time.Anak.Utils.AlarmReceiver;
import com.example.screening_time.Anak.Utils.AppCheckServices;
import com.example.screening_time.Anak.Utils.AppLockConstants;
import com.example.screening_time.Anak.Utils.UninstallIntentReceiver;
import com.example.screening_time.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_SplashScreen extends AppCompatActivity implements MyController, MyRmq {
    SharedPreferences sharedPreferences;
    Animation uptodown, downtoup,Fadein,FadeOut;
    TelephonyManager telephonyManager;
    @BindView(R.id.SplashScreen)
    LinearLayout Splash_Screen;
    String IMEINumber;
    private static final int REQUEST_CODE = 101;
    Context context;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE= 0;
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    public static int MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS =0;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    private static DevicePolicyManager dpm;
//    private static SharedPrefManager pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int waktu_loading=3000;
        loading=new ProgressDialog(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu__splash_screen);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.to_left);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.to_right);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        FadeOut= AnimationUtils.loadAnimation(this, R.anim.fade_out);
        Splash_Screen.setAnimation(FadeOut);
//        PackageManager p = getPackageManager();
//        ComponentName componentName = new ComponentName(this, Menu_SplashScreen.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
//        p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        ComponentName cn=new ComponentName(this, AdminReceiver.class);
        DevicePolicyManager mgr= (DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);

        if (mgr.isAdminActive(cn)) {
            int msgId;
//            if (mgr.isActivePasswordSufficient()) {
//                msgId=R.string.compliant;
//            } else {
//                msgId=R.string.not_compliant;
//            }

//            Toast.makeText(this, msgId, Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent= new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, cn);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, getString(R.string.device_admin_explanation));
            startActivity(intent);
            finish();
        }


        ActivityCompat.requestPermissions(Menu_SplashScreen.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        checkPermissions();
        notifikasi();
        notifikasi_tutup();
//        startService(new Intent(this, MyService.class));
        startService(new Intent(Menu_SplashScreen.this, AppCheckServices.class));
        startService(new Intent(Menu_SplashScreen.this, UninstallIntentReceiver.class));
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
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    @SuppressLint("MissingPermission")
    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                OverlayPermissionDialogFragment dialogFragment = new OverlayPermissionDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "Izin Penggunaan Aplikasi");
            }else if(!hasUsageStatsPermission()) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                UsageAcessDialogFragment dialogFragment = new UsageAcessDialogFragment();
                ft.add(dialogFragment, null);
                ft.commitAllowingStateLoss();
            } else if (ActivityCompat.checkSelfPermission(Menu_SplashScreen.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Menu_SplashScreen.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
                    telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    IMEINumber = telephonyManager.getDeviceId();
                    SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
                    sharedPrefManager.saveSPString(SharedPrefManager.SP_IMEI,IMEINumber);
                    GetEmai();
//                    return;
            }else{
//               try {
//                   telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                   IMEINumber = telephonyManager.getDeviceId();
                   String android_id = Settings.Secure.getString(Menu_SplashScreen.this.getContentResolver(),
                           Settings.Secure.ANDROID_ID);
//                   Toast.makeText(context, ""+android_id, Toast.LENGTH_SHORT).show();
                   Log.i("id_data",android_id);

//                   IMEINumber="1212";
//                   if ()
//                   if (IMEINumber.equals("")){
//                       Log.i("data","123");
//                       Toast.makeText(context, "Imei Kosong", Toast.LENGTH_SHORT).show();
//                   }//                   Toast.makeText(context, ""+IMEINumber, Toast.LENGTH_SHORT).show();
//                   if (telephonyManager.getDeviceId() != null) {
//                       String deviceId = telephonyManager.getDeviceId();
//                       Toast.makeText(context, ""+deviceId, Toast.LENGTH_SHORT).show();
//                   } else {
//                      String deviceId = Settings.Secure.getString(
//                               context.getContentResolver(),
//                               Settings.Secure.ANDROID_ID);
//                       Toast.makeText(context, ""+deviceId, Toast.LENGTH_SHORT).show();
//                   }

                   SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
                   sharedPrefManager.saveSPString(SharedPrefManager.SP_IMEI,android_id);
                   GetEmai();
//               } catch (Exception e) {
//                   e.printStackTrace();
//               }

            }
        }
    }
    public void GetEmai(){
//        startService(new Intent(Menu_SplashScreen.this, AppCheckServices.class));
//        try {
//            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
//            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 999, alarmIntent, 0);
//            int interval = (86400 * 1000) / 4;
//            if (manager != null) {
//                manager.cancel(pendingIntent);
//            }
//            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
        sharedPrefManager.getSP_IMEI();
        ChexImei(sharedPrefManager.getSP_IMEI());
    }

    private void ChexImei(String Emai) {
//        loading = new ProgressDialog(ge);
        loading.setMessage("Mohon Tunggu.....");
        loading.setCancelable(false);
        loading.show();
        Controller controller =new Controller(Menu_SplashScreen.this);
        controller.ChexImei(Emai);
    }

    @Override
    public void ImeiTerdaftar(String imei){
        loading.dismiss();
        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        final boolean isPasswordSet = sharedPreferences.getBoolean(AppLockConstants.IS_PASSWORD_SET, false);
//        if (isPasswordSet) {
//            Toast.makeText(Menu_SplashScreen.this, ""+isPasswordSet, Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(Menu_SplashScreen.this,Menu_Masuk.class);
            startActivity(intent);
            finish();
//        } else {
//            Intent intent =new Intent(Menu_SplashScreen.this,PasswordSetActivity.class);
//            startActivity(intent);
//            finish();
//        }
    }


    @Override
    public void gagalmasuk(String Message) {
        Toast.makeText(context, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ImeiTidakTerdaftar(){
        loading.dismiss();
        Intent intent =new Intent(Menu_SplashScreen.this,Menu_RegisterPassword.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void berhasilmasuk(String Message) {
        Toast.makeText(context, ""+Message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void TidakAdaKoneksi(String error_message){
        loading.dismiss();
        Toast.makeText(this, ""+error_message, Toast.LENGTH_SHORT).show();

    }
    private boolean hasUsageStatsPermission() {
        AppOpsManager appOps = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            appOps = (AppOpsManager)
                    getSystemService(Context.APP_OPS_SERVICE);
        }
        int mode = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    android.os.Process.myUid(), getPackageName());
            Log.d("package",getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }

    @Override
    public void Berhasil(String message) {
        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void Gagal() {
        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();

    }

    public static class UsageAcessDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.usage_data_access_description)
                    .setTitle("Izin Akses Penggunaan")
                    .setPositiveButton("Izinkan", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                            startActivityForResult(
                                    new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                                    MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
                        }
                    });

            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
    public static class OverlayPermissionDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.ovarlay_permission_description)
                    .setTitle("Izin Penggunaan")
                    .setPositiveButton("Izinkan", new DialogInterface.OnClickListener() {
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Menu_SplashScreen", "cp 1");
        try {
            checkPermissions();
        } catch (Exception e) {
            e.printStackTrace();
        }

        notifikasi();
        notifikasi_tutup();
    }
    public void notifikasi(){
        Koneksi_RMQ rmq=new Koneksi_RMQ(Menu_SplashScreen.this);
        rmq.setupConnectionFactory();
        final Handler incomingMessageHandler = new Handler() {
            @SuppressLint("HandlerLeak")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                Log.d("RMQMessage", message);
                String s = message.toString();
                String[] tokens = s.split("");
                String title="Pemberitahuan";
                try {
                    JSONObject jsonRESULTS = new JSONObject(s);
                    String imei = jsonRESULTS.getString("imei");
                    String nama = jsonRESULTS.getString("nama");
                    String Package = jsonRESULTS.getString("package");
                    String jammulai = jsonRESULTS.getString("jammulai");
                    String jamakhir = jsonRESULTS.getString("jamakhir");
                    String isi="Aplikasi "+nama+" Sekarang Bisa Dibuka dari jam="+jammulai+"-"+jamakhir;
                    String status="buka";
                    showNotification(title, isi);
                    Controller controller =new Controller(Menu_SplashScreen.this);
                    controller.UpdateJadwal(imei,Package,status);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                LoginPassword.setText(message);
//                Toast.makeText(Data_Sampah.this, "ini data dari RMQ"+message, Toast.LENGTH_SHORT).show();
            }
        };

        Thread subscribeThread = new Thread();
        String data="notifikasi";
        rmq.subscribe(incomingMessageHandler,subscribeThread,data,data);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String title, String body) {
        Context context = getApplicationContext();
        Intent intent = getIntent();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        notificationManager.notify(notificationId, mBuilder.build());
    }
    public void notifikasi_tutup(){
        Koneksi_RMQ rmq=new Koneksi_RMQ(Menu_SplashScreen.this);
        rmq.setupConnectionFactory();
        final Handler incomingMessageHandler = new Handler() {
            @SuppressLint("HandlerLeak")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                Log.d("RMQMessage", message);
                String s = message.toString();
                String[] tokens = s.split("");
                String title="Pemberitahuan";
                try {
                    JSONObject jsonRESULTS = new JSONObject(s);
                    String imei = jsonRESULTS.getString("imei");
                    String nama = jsonRESULTS.getString("nama");
                    String Package = jsonRESULTS.getString("package");
                    String jammulai = jsonRESULTS.getString("jammulai");
                    String jamakhir = jsonRESULTS.getString("jamakhir");
                    String isi="Aplikasi "+nama+" Sekarang Tidak bisa Dibuka,Bisa di buka kembali pada jam="+jammulai;
                    String status="tutup";
                    showNotification(title, isi);
                    Controller controller =new Controller(Menu_SplashScreen.this);
                    controller.UpdateJadwal(imei,Package,status);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                LoginPassword.setText(message);
//                Toast.makeText(Data_Sampah.this, "ini data dari RMQ"+message, Toast.LENGTH_SHORT).show();
            }
        };

        Thread subscribeThread = new Thread();
        String data="notifikasi_tutup";
        rmq.subscribe(incomingMessageHandler,subscribeThread,data,data);
    }
    @Override
    public void gagalupdate(String pesan){
    }
    @Override
    public void berhasilupdate(String pesan){
    }

}