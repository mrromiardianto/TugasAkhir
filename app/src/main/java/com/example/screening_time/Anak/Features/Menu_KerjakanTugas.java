package com.example.screening_time.Anak.Features;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.screening_time.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import pub.devrel.easypermissions.EasyPermissions;

public class Menu_KerjakanTugas extends AppCompatActivity {
    public static final String KEY_User_Document1 = "doc1";
    ImageView IDProf;
    Button Upload_Btn;
    private String Document_img1="";
    String mediaPath1;
    String[] mediaColumns = {MediaStore.Video.Media._ID};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__kerjakan_tugas);
        IDProf=(ImageView)findViewById(R.id.IdProf);
        Upload_Btn=(Button)findViewById(R.id.UploadBtn);

        IDProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
        String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(Menu_KerjakanTugas.this, galleryPermissions)) {
//            uploadFile();
        } else {
            EasyPermissions.requestPermissions(Menu_KerjakanTugas.this, "Access for storage",
                    101, galleryPermissions);
        }
    }
    private void selectImage() {
        final CharSequence[] options = { "Ambil Dari Kamera", "Pilih Dari Gallery","Batal" };
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu_KerjakanTugas.this);
        builder.setTitle("Tambah Foto");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Ambil Dari Kamera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Pilih Dari Gallery")) {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, 0);
                }
                else if (options[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        try {
//            // When an Image is picked
//            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {
//
//                // Get the Image from data
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                assert cursor != null;
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                mediaPath1 = cursor.getString(columnIndex);
////                Nama_Gambar.setText(mediaPath1);
//                // Set the Image in ImageView for Previewing the Media
//                IDProf.setImageBitmap(BitmapFactory.decodeFile(mediaPath1));
//                cursor.close();
//
//            } // When an Video is picked
//            else if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
//
//                // Get the Video from data
//                Uri selectedVideo = data.getData();
//                String[] filePathColumn = {MediaStore.Video.Media.DATA};
//
//                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
//                assert cursor != null;
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//
//                mediaPath1 = cursor.getString(columnIndex);
////                str2.setText(mediaPath1);
////                // Set the Video Thumb in ImageView Previewing the Media
//                IDProf.setImageBitmap(getThumbnailPathForLocalFile(Menu_KerjakanTugas.this, selectedVideo));
//                cursor.close();
//
//            } else {
//                Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
//        long fileId = getFileId(context, fileUri);
//        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
//                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
//    }
//
//    public long getFileId(Activity context, Uri fileUri) {
//        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
//        if (cursor.moveToFirst()) {
//            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
//            return cursor.getInt(columnIndex);
//        }
//        return 0;
//    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds options to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);
                    IDProf.setImageBitmap(bitmap);
                    String path = Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("", picturePath+"");
                IDProf.setImageBitmap(thumbnail);
            }
        }
    }


}