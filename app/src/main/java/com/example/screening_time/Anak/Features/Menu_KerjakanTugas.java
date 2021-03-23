package com.example.screening_time.Anak.Features;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.Anak.Server.ApiConfig;
import com.example.screening_time.Anak.Server.AppConfig;
import com.example.screening_time.Anak.Server.InitRetrofit;
import com.example.screening_time.Anak.Session.SharedPrefManager;
import com.example.screening_time.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu_KerjakanTugas extends AppCompatActivity {
    public static final String KEY_User_Document1 = "doc1";
    ImageView viewImage;
    Button Upload_Btn,Pilih;
    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    Uri image_uri;
    String path;
    final int TAKE_PHOTO_REQ = 100;
    String file_path;
    ProgressDialog Loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__kerjakan_tugas);
        viewImage=(ImageView)findViewById(R.id.IdProf);
        Upload_Btn=(Button)findViewById(R.id.UploadBtn);
        Loading=new ProgressDialog(this);
        sharedPrefManager=new SharedPrefManager(this);
        Upload_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpanGambar();
            }
        });
        Pilih=(Button)findViewById(R.id.pilihimage);
        Pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                    PackageManager.PERMISSION_DENIED){
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    }
                    else {
                        openCamera();
                    }
                }
                else {
                    openCamera();
                }
            }
        });
    }
    private void openCamera() {
        Random rand = new Random();
        double rand_dub1 = rand.nextDouble();
        file_path = Environment.getExternalStorageDirectory()
                + "/"+rand_dub1+".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PHOTO_REQ);
    }

    //handling permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this method is called, when user presses Allow or Deny from Permission Request Popup
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
        case TAKE_PHOTO_REQ: {
            if (resultCode ==RESULT_OK && data != null) {
                Bitmap srcBmp = (Bitmap) data.getExtras().get("data");

                // ... (process image  if necesary)

                viewImage.setImageBitmap(srcBmp);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                srcBmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

                // you can create a new file name "test.jpg" in sdcard folder.
                File f = new File(file_path);
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // write the bytes in file
                FileOutputStream fo = null;
                try {
                    fo = new FileOutputStream(f);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // remember close de FileOutput
                try {
                    fo.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Log.e("take-img", "Image Saved to sd card...");
                // Toast.makeText(getApplicationContext(),
                // "Image Saved to sd card...", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}

    private void SimpanGambar() {
        Loading.setMessage("Loading.......");
        Loading.setCancelable(true);
        Loading.show();
        Log.d("datanya", String.valueOf(image_uri));
        File file = new File(file_path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        ApiConfig getResponse = AppConfig.getRetrofit().create(ApiConfig.class);
        Call<ResponseBody> call = getResponse.uploadFile(fileToUpload, filename);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody=response.body();
                Log.v("Response",responseBody.toString());
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        Log.d("Json", String.valueOf(response.body().toString()));
                        if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            String Nama_Gambar=jsonRESULTS.getString("tmp_name");
                            KirimTugas(Nama_Gambar);
                            Log.d("response api", jsonRESULTS.toString());
                        } else if (jsonRESULTS.getString("success").equals("true")){
                            String pesan_login=jsonRESULTS.getString("message");
                            Log.v("ini",pesan_login);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                try {
                    Log.v("response:","gagal");
//                    InputGagal();
                    Toast.makeText(getApplication(), "Server Tidak Merespon", Toast.LENGTH_SHORT).show();
//          progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void KirimTugas(String file){
        String id_tugas=getIntent().getExtras().getString("ID");
        String imei=getIntent().getExtras().getString("IMEI");
        String nama=getIntent().getExtras().getString("NAMA");
        String filedata=file;
        String pacgkage=sharedPrefManager.getSPNama();
        Call<ResponseBody> call = InitRetrofit.getInstance().getApi().KirimTugas(id_tugas,imei,filedata,pacgkage,nama);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("success").equals("true")){
                            Log.d("response api", jsonRESULTS.toString());
                            String message=jsonRESULTS.getString("message");
                            Toast.makeText(Menu_KerjakanTugas.this, message, Toast.LENGTH_SHORT).show();
                            Loading.dismiss();
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            startActivity(intent);
                            finish();
                        } else if (jsonRESULTS.getString("success").equals("false")) {
                            try {
                                Loading.dismiss();
                                Log.d("response api", jsonRESULTS.toString());
                                String message=jsonRESULTS.getString("message");
                                Toast.makeText(Menu_KerjakanTugas.this, message, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        Loading.dismiss();
                        String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                        Toast.makeText(Menu_KerjakanTugas.this, error_message, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("debug", "onFailure: ERROR > " + t.toString());
                try {
                    Loading.dismiss();
                    String error_message ="Tidak Ada Koneksi Internet/Masalah Server";
                    Toast.makeText(Menu_KerjakanTugas.this, error_message, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}