package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class erasecut extends AppCompatActivity {
    private ImageView imageView ;
    Button save ;
    Bitmap bitmap ;
    String chair ;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erasecut);
        imageView = findViewById(R.id.imageView);
        save =(Button)findViewById(R.id.button11);
        Intent intent = getIntent();
    //    final int receivedImage = intent.getIntExtra("image",0);//hair style
      //  chair =  intent.getStringExtra("hair");
       // intent.putExtra("hairstyle",receivedImage);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Bitmap im =decodeBase64(pref.getString("image", null));
      //  final int receivedImage = intent.getIntExtra("image",0);
        myviewround myCustomView = new myviewround(erasecut.this,im,im);
        ConstraintLayout rootLayout = findViewById(R.id.rootm);
        rootLayout.addView(myCustomView);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),viewcuts.class);
        //        intent.putExtra("hairstyle",receivedImage);
          //      intent.putExtra("hair",chair);
                imageView.setDrawingCacheEnabled(true);
              bitmap = imageView.getDrawingCache();
                SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                spreferencesEditor.putString("newim", encodeTobase64(bitmap));
                startActivity(intent);
            }
        });

    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
}
class myviewround extends View {
    Bitmap destbitmap ;
    Canvas destcanvas = new Canvas();
    Paint dest = new Paint();
    Path destpath = new Path();
    @RequiresApi(api = Build.VERSION_CODES.O)
    public myviewround(Context context , Bitmap b , Bitmap nn) {
        super(context);
     //   final Bitmap p = BitmapFactory.decodeResource(context.getResources(), R.drawable.transp);
        destbitmap = Bitmap.createBitmap(nn.getWidth(),nn.getHeight(),Bitmap.Config.ARGB_8888);
        destcanvas.setBitmap(destbitmap);
        destcanvas.drawBitmap(nn,0,0,null);
        dest.setAlpha(0);
        dest.setAntiAlias(true);
        dest.setStyle(Paint.Style.STROKE);
        dest.setStrokeJoin(Paint.Join.ROUND);
        dest.setStrokeCap(Paint.Cap.ROUND);
        dest.setStrokeWidth(50);
        dest.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }
    @Override
    protected void onDraw(Canvas canvas) {
        destcanvas.drawPath(destpath,dest);
        canvas.drawBitmap(destbitmap,0,0,null);
        super.onDraw(canvas);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() ;
        float y = event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                destpath.moveTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                destpath.lineTo(x, y);
                break;

            default:
                return false;
        }
        invalidate();
        return true;
    }
}


