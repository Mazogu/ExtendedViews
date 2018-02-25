package com.example.micha.customviewsandbarcodes;

import android.Manifest;
import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int MY_CAMERA_REQUEST_CODE = 15;
    private ZXingScannerView view;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setBarScanner();
            setContentView(view);
        }
        else {
            checkCameraPermission();
            if (view != null) {
                Log.d(TAG, "onCreate: " + "it's not being called");
                setContentView(view);
            } else {
                Toast.makeText(this, "Ummmmm no scanner or permissions", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_main);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.setResultHandler(this);
        view.startCamera();
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        MY_CAMERA_REQUEST_CODE);
            }
        } else {
            setBarScanner();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_CAMERA_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setBarScanner();
                } else {
                    //no permission;
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(view != null){
            view.stopCamera();
        }
    }

    private void setBarScanner() {
        view = new ZXingScannerView(this);
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "handleResult: " + result.getBarcodeFormat().toString());
        view.resumeCameraPreview(this);
    }
}
