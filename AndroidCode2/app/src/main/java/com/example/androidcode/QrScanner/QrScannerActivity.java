package com.example.androidcode.QrScanner;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.androidcode.R;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class QrScannerActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textView;
    BarcodeDetector barcodeDetector;
    AssetManager assetManager;
    boolean qrScanned;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        qrScanned = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        surfaceView = findViewById(R.id.camerapreview);
        textView = findViewById(R.id.textView);

        assetManager = getAssets();
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(1280, 960).build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(QrScannerActivity.this, new String[]{Manifest.permission.CAMERA}, 31 );
                    System.out.println("No permission granted");
                }
                try {
                    cameraSource.start(holder);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

                if(qrCodes.size()!=0&&!qrScanned){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(100);
                            textView.setText(qrCodes.valueAt(0).displayValue);
                            try {
                                Intent intent = new Intent();
                                executeCode(getCode(qrCodes.valueAt(0).displayValue), intent);
                                qrScanned = true;

                            } catch (JSONException e) {
                                e.printStackTrace();

                                setResult(RESULT_CANCELED);
                                System.out.println("Error");
                                qrScanned = true;
                                finish();

                            }



                        }
                    });
                }
            }

            public JSONObject getCode(String key){

                try {
                    InputStream inputStream = assetManager.open("jsonfiles/QrCodes.json");
                    String json = null;

                    int size = inputStream.available();
                    byte[] buffer = new byte[size];
                    inputStream.read(buffer);
                    inputStream.close();

                    json = new String(buffer, "UTF-8");

                    try {
                        JSONObject obj = new JSONObject(json);
                        JSONObject keyObj = obj.getJSONObject(key);
                        Log.d("QRSCANNER:", keyObj.toString());

                        return keyObj;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }



                return new JSONObject();
            }

            public void executeCode(JSONObject object, Intent intent) throws JSONException {
                if(object.getString("type").equals("attraction")){
                    System.out.println("Attraction: " + object.getString("attraction"));
                    intent.putExtra("topic", "testTopic");
                    setResult(RESULT_OK, intent);
                    finish();
                }
                if(object.getString("type").equals("easteregg")){
                    System.out.println("id: " + object.getString("id"));
                    intent.putExtra("topic", "testTopic");
                    setResult(RESULT_OK, intent);
                    finish();
                }


            }
        });

    }
}
