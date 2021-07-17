package com.zzc.signImg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.zzc.utils.BitmapUtils;
import com.zzc.utils.SignUtils;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    SignaturePad mSignaturePad;
    Button mSaveButton, mClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        setContentView(R.layout.activity_main);

        final SignUtils signUtils = new SignUtils(this);
        mClearButton = findViewById(R.id.clear_button);
        mSaveButton = findViewById(R.id.save_button);
        mSignaturePad = findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                Log.e("ADAC", "测试add");
            }

            @Override
            public void onSigned() {
                mSaveButton.setEnabled(true);
                mClearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                mSaveButton.setEnabled(false);
                mClearButton.setEnabled(false);
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                Bitmap bitmap = BitmapUtils.compressImage(signatureBitmap);

                String imgData = BitmapUtils.bitmapToString(signatureBitmap);

                if (signUtils.addJpgSignatureToGallery(bitmap)) {
                    Toast.makeText(MainActivity.this, "签名保存到图库中", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(MainActivity.this,TestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "无法存储签名", Toast.LENGTH_SHORT).show();
                }
                if (signUtils.addSvgSignatureToGallery(mSignaturePad.getSignatureSvg())) {
                    Toast.makeText(MainActivity.this, "SVG签名保存到库中", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "无法存储SVG签名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void verifyStoragePermissions(Activity activity) {
        //检查我们是否有写权限
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

}