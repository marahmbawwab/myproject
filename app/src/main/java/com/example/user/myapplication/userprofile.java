package com.example.user.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class userprofile extends AppCompatActivity {
   TextView name ,email ,fulname,phone ,act,logout;
   ImageView picture ;
   String user  ;
   Bitmap bitmap ;
    private File destination = null;
    private String imgPath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        name =(TextView)findViewById(R.id.name);
        email =(TextView)findViewById(R.id.email);
        fulname=(TextView)findViewById(R.id.add);
        phone =(TextView)findViewById(R.id.phone);
        picture =(ImageView)findViewById(R.id.image);
        act=(TextView)findViewById(R.id.hh);
        logout =(TextView)findViewById(R.id.log);
        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        name.setText(spreferences.getString("usern", null));
        String image = spreferences.getString("im", null);
        user=spreferences.getString("usern", null);
        Toast.makeText(getApplicationContext(),image,Toast.LENGTH_LONG);
       /* if(!image.equals("")) {
            Bitmap b = BitmapFactory.decodeFile( Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString()+"/beauty_profile/"+image);
            Bitmap bb=Bitmap.createScaledBitmap(b, 120, 120, false);
            picture.setImageBitmap(bb);
        }*/
        if (image.contains("noimage")){

        }
        else  {
            Bitmap b =BitmapFactory.decodeFile( Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString()+"/beauty_profile/"+image);
            Bitmap bb=Bitmap.createScaledBitmap(b, 120, 120, false);
            picture.setImageBitmap(bb);
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.clear();
                editor.commit();
                Intent nextScreen = new Intent(getApplicationContext(), home.class);
                startActivity(nextScreen);
            }
        });

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), third.class);
                startActivity(nextScreen);
            }
        });
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        try {
          show ("showu",user);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void show (String t ,String name) throws ExecutionException, InterruptedException {
        customerhomebackground bb = new customerhomebackground(this);
        String byval = bb.execute(t,user).get();
        String [] data = byval.split("\n");
        email.setText(data[4]);
        phone.setText(data[3]);
        String full = data[6]+"\t"+data[5] ;
        fulname.setText(full);
    }
    private void selectImage() {
        try {
            PackageManager pm = getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                List<Integer> icons=new ArrayList<Integer>();
                icons.add(R.drawable.camera);
                icons.add(R.drawable.gall);
                icons.add(R.drawable.cancl);
                final List<String> options=new ArrayList<String>();
                options.add("Take Photo");
                options.add("Choose From Gallery");
                options.add("Cancel");
                ListAdapter adapter = new arrayadapter(this, options,icons);
                final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                final AlertDialog OptionDialog = builder.create();
                builder.setTitle("Select Option").setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item ) {
                        if (options.get(item).equals("Take Photo")) {
                            OptionDialog.dismiss();
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, 1);
                        } else if (options.get(item).equals("Choose From Gallery")) {
                            OptionDialog.dismiss();
                            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(pickPhoto, 2);
                        } else if (options.get(item).equals("Cancel")) {
                            OptionDialog.dismiss();
                        }
                    }
                }).show();
            } else {
                Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Camera Permission error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Uri selectedImage = data.getData();
            bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
            Bitmap bt=Bitmap.createScaledBitmap(bitmap, 120, 120, false);
            Log.e("Activity", "Pick from Camera::>>> ");
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            destination = new File(Environment.getExternalStorageDirectory() + "/" +
                    getString(R.string.app_name), "IMG_" + timeStamp + ".jpg");
            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgPath = destination.getAbsolutePath();
            picture.setImageBitmap(bt);
            try {
                SaveImage(bt);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (requestCode == 2) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
                Bitmap bt=Bitmap.createScaledBitmap(bitmap, 120, 120, false);
                Log.e("Activity", "Pick from Gallery::>>> ");
                imgPath = getRealPathFromURI(selectedImage);
                destination = new File(imgPath);
                picture.setImageBitmap(bt);
                SaveImage(bt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void SaveImage(Bitmap finalBitmap) throws ExecutionException, InterruptedException {
        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/beauty_profile");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000 ;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        setimage(fname);
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public void setimage (String image) throws ExecutionException, InterruptedException {
      customerhomebackground mm = new customerhomebackground(this);
        String myval =mm.execute("setim",user,image).get();
        Toast.makeText(this,myval,Toast.LENGTH_SHORT).show();
    }
}
