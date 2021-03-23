package com.example.screening_time.Anak.Utils;

import android.app.ActivityManager;
import android.app.Dialog;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.screening_time.Anak.Features.Menu_PopUp;
import com.example.screening_time.Anak.Server.Controller.Controller;
import com.example.screening_time.Anak.Server.Controller.MyController;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.Anak.Session.SharedPreference;
import com.example.screening_time.R;
import com.takwolf.android.lock9.Lock9View;

import java.util.List;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;



public class AppCheckServices extends Service implements MyController {
    public static final String TAG = "AppCheckServices";
    private Context context = null;
    private Timer timer;
    ImageView imageView;
    private WindowManager windowManager;
    private Dialog dialog;
    public static String currentApp = "";
    public static String previousApp = "";
    SharedPreference sharedPreference;
    List<String> pakageName;
    String PackageMName;
    Controller controller;
    SharedPrefManager sharedPrefManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sharedPreference = new SharedPreference();
        if (sharedPreference != null) {
            pakageName = sharedPreference.getLocked(context);
        }
        controller = new Controller(this);
        sharedPrefManager = new SharedPrefManager(context);
//        timer = new Timer("AppCheckServices");
//        timer.schedule(updateTask, 1000L, 1000L);
//        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        imageView = new ImageView(this);
//        imageView.setVisibility(View.GONE);
        int LAYOUT_FLAG;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE){
                LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
      }else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE;
        }
////        try {
//            final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.WRAP_CONTENT,
//                    WindowManager.LayoutParams.TYPE_PHONE,
//                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                    LAYOUT_FLAG,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                    PixelFormat.TRANSLUCENT);
//            params.gravity = Gravity.TOP | Gravity.CENTER;
//            params.x = ((getApplicationContext().getResources().getDisplayMetrics().widthPixels) / 2);
//            params.y = ((getApplicationContext().getResources().getDisplayMetrics().heightPixels) / 2);
//            windowManager.addView(imageView, params);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            timer = new Timer("AppCheckServices");
            timer.schedule(updateTask, 500L, 500L);
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            imageView = new ImageView(this);
            imageView.setVisibility(View.GONE);
            final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    LAYOUT_FLAG,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.TOP | Gravity.CENTER;
            params.x = ((getApplicationContext().getResources().getDisplayMetrics().widthPixels) / 2);
            params.y = ((getApplicationContext().getResources().getDisplayMetrics().heightPixels) / 2);
            windowManager.addView(imageView, params);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private TimerTask updateTask = new TimerTask() {
        @Override
        public void run() {
            if (sharedPreference != null) {
                pakageName = sharedPreference.getLocked(context);
            }
            if (isConcernedAppIsInForeground()) {
                Log.d("isConcernedAppIsInFrgnd", "true");
                Log.v("data","Terkunci");
//                Toast.makeText(getApplication(), "Ngapain loh buka buka", Toast.LENGTH_SHORT).show();
                if (imageView != null) {
                    imageView.post(new Runnable() {
                        public void run() {
                            if (!currentApp.matches(previousApp)) {
//                                mpackageName = runningTask.get(runningTask.lastKey()).getPackageName();
//                                Toast.makeText(context, "My App"+pakageName, Toast.LENGTH_SHORT).show();
                                previousApp = currentApp;
                                final PackageManager pm = getApplicationContext().getPackageManager();
                                ApplicationInfo ai;
                                try {
                                    ai = pm.getApplicationInfo( previousApp, 0);
                                } catch (final PackageManager.NameNotFoundException e) {
                                    ai = null;
                                }
                                final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                                String Imei=sharedPrefManager.getSP_IMEI();
                                controller.SimpanUsage(Imei,previousApp,applicationName);
                                Intent intent =new Intent(context, Menu_PopUp.class);
                                intent.putExtra("PACKAGE", previousApp);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);

//                                startActivity();
                                Toast.makeText(context, "HAYO MAU NGAPAIN?", Toast.LENGTH_SHORT).show();
                                Intent i =new Intent(context, Menu_PopUp.class);
                                i.putExtra("PACKAGE", previousApp);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);

//                                previousApp = currentApp;
//                                showUnlockDialog();

//                                Toast.makeText(context, ""+previousApp, Toast.LENGTH_SHORT).show();
                            }else {
                                Log.d("AppCheckSErvice", "currentApp matches previous App");
                                Intent intent =new Intent(context, Menu_PopUp.class);
                                intent.putExtra("PACKAGE", previousApp);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
//                                previousApp=currentApp;
////                Toast.makeText(context, ""+previousApp, Toast.LENGTH_SHORT).show();
//                                String Mpakage=previousApp;
////                    String nama=context.getPackageManager().getActivityInfo(Mpakage);
//                                Log.d(TAG,"isEmpty No : "+previousApp);
//                                final PackageManager pm = getApplicationContext().getPackageManager();
//                                ApplicationInfo ai;
//                                try {
//                                    ai = pm.getApplicationInfo( previousApp, 0);
////                        Toast.makeText(context, ""+ai, Toast.LENGTH_SHORT).show();
//                                } catch (final PackageManager.NameNotFoundException e) {
//                                    ai = null;
//                                }
//                                final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
//                                String Imei=sharedPrefManager.getSP_IMEI();
//                                controller.SimpanUsage(Imei,previousApp,applicationName);
                            }

                        }
                    });
                }
            } else {
                Log.d("isConcernedAppIsInFrgnd", "false");
//                previousApp=currentApp;
////                Toast.makeText(context, ""+previousApp, Toast.LENGTH_SHORT).show();
//                String Mpakage=previousApp;
////                    String nama=context.getPackageManager().getActivityInfo(Mpakage);
//                Log.d(TAG,"isEmpty No : "+previousApp);
//                final PackageManager pm = getApplicationContext().getPackageManager();
//                ApplicationInfo ai;
//                try {
//                    ai = pm.getApplicationInfo( previousApp, 0);
////                        Toast.makeText(context, ""+ai, Toast.LENGTH_SHORT).show();
//                } catch (final PackageManager.NameNotFoundException e) {
//                    ai = null;
//                }
//                final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
//                String Imei=sharedPrefManager.getSP_IMEI();
//                controller.SimpanUsage(Imei,previousApp,applicationName);
                if (imageView != null) {
                    imageView.post(new Runnable() {
                        public void run() {
                            hideUnlockDialog();
                        }
                    });
                }
            }
        }
    };

    void showUnlockDialog() {
//        Toast.makeText(context, "MyApp", Toast.LENGTH_SHORT).show();
        showDialog();
    }
    void hideUnlockDialog() {
        previousApp = "";
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void showDialog() {
        if (context == null)
            context = getApplicationContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptsView = layoutInflater.inflate(R.layout.popup_unlock, null, false);
        Lock9View lock9View = (Lock9View) promptsView.findViewById(R.id.lock_9_view);
        Button forgetPassword = (Button) promptsView.findViewById(R.id.forgetPassword);
        lock9View.setCallBack(new Lock9View.CallBack() {
            @Override
            public void onFinish(String password) {
                if (password.matches(sharedPreference.getPassword(context))) {
                    dialog.dismiss();

                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Pattern Try Again", Toast.LENGTH_SHORT).show();

                }
            }
        });

//        forgetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(AppCheckServices.this, PasswordRecoveryActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//                dialog.dismiss();
//            }
//        });

        dialog = new Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.setContentView(promptsView);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == KeyEvent.ACTION_UP) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                }
                return true;
            }
        });

        dialog.show();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {

        }
        return START_STICKY;
    }

    public boolean isConcernedAppIsInForeground() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> task = manager.getRunningTasks(5);
        if (Build.VERSION.SDK_INT <= 20) {
            if (task.size() > 0) {
                ComponentName componentInfo = task.get(0).topActivity;
                for (int i = 0; pakageName != null && i < pakageName.size(); i++) {
                    if (componentInfo.getPackageName().equals(pakageName.get(i))) {
                        currentApp = pakageName.get(i);
//                        PackageMName=
                        return true;
                    }
                }
            }
        } else {
            String mpackageName = manager.getRunningAppProcesses().get(0).processName;
            UsageStatsManager usage = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            List<UsageStats> stats = usage.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, 0, time);
            if (stats != null) {
                SortedMap<Long, UsageStats> runningTask = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    runningTask.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (runningTask.isEmpty()) {
                    Log.d(TAG,"isEmpty Yes");
                    mpackageName = "";

                    final PackageManager pm = getApplicationContext().getPackageManager();
                    ApplicationInfo ai;
                    try {
                        ai = pm.getApplicationInfo( previousApp, 0);
                    } catch (final PackageManager.NameNotFoundException e) {
                        ai = null;
                    }
                    final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                    String Imei=sharedPrefManager.getSP_IMEI();
                    controller.SimpanUsage(Imei,mpackageName,applicationName);
                }else {
                    mpackageName = runningTask.get(runningTask.lastKey()).getPackageName();
                    String Mpakage=mpackageName;
////                    String nama=context.getPackageManager().getActivityInfo(Mpakage);
                    Log.d(TAG,"isEmpty No : "+mpackageName);
                    final PackageManager pm = getApplicationContext().getPackageManager();
                    ApplicationInfo ai;
                    try {
                        ai = pm.getApplicationInfo( mpackageName, 0);
//                        Toast.makeText(context, ""+ai, Toast.LENGTH_SHORT).show();
                    } catch (final PackageManager.NameNotFoundException e) {
                        ai = null;
                    }
                    final String applicationName = (String) (ai != null ? pm.getApplicationLabel(ai) : "(unknown)");
                    String Imei=sharedPrefManager.getSP_IMEI();
                    controller.SimpanUsage(Imei,mpackageName,applicationName);
                }
            }



            for (int i = 0; pakageName != null && i < pakageName.size(); i++) {
                Log.d("AppCheckService", "pakageName Size" + pakageName.size());
                if (mpackageName.equals(pakageName.get(i))) {
                    currentApp = pakageName.get(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
        if (imageView != null) {
            windowManager.removeView(imageView);
        }
        /**** added to fix the bug of view not attached to window manager ****/
        try {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ImeiTerdaftar(String imei) {

    }

    @Override
    public void gagalmasuk(String Message) {

    }

    @Override
    public void ImeiTidakTerdaftar() {

    }

    @Override
    public void berhasilmasuk(String Message) {

    }

    @Override
    public void TidakAdaKoneksi(String error_message) {

    }

    @Override
    public void gagalupdate(String pesan) {

    }

    @Override
    public void berhasilupdate(String pesan) {

    }
}
