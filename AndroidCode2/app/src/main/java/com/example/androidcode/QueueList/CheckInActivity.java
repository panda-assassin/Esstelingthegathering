package com.example.androidcode.QueueList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.androidcode.Achievement.Achievement;
import com.example.androidcode.Achievement.AchievementActivity;
import com.example.androidcode.BlankActivity;
import com.example.androidcode.QrScanner.QrScannerActivity;
import com.example.androidcode.R;
import com.example.androidcode.StartUp.HomeScreenActivity;
import com.example.androidcode.mqtt.MQTTConfig;
import com.example.androidcode.mqtt.PahoMqttClient;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.androidcode.Achievement.AchievementActivity;

public class CheckInActivity extends AppCompatActivity {

    private static final String TAG = "CheckInActivity";
    private static final int REQUEST_CODE = 0;

    //mqtt attrributes
    private MqttAndroidClient client;
    private PahoMqttClient pahoMqttClient;

    private String MQTT_TOPIC = "B6-project-led-data"; // stadard topic

    private MyBroadcastReceiver myBroadCastReceiver;


    private RecyclerView recyclerView;
    private QueueAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static final String BROADCAST_ACTION = "com.example.mqttpayloadavailabe";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        pahoMqttClient = new PahoMqttClient();
        MQTTConfig mqttc = new MQTTConfig();
        client = pahoMqttClient.getMqttClient(
                getApplicationContext(),
                mqttc.MQTT_BROKER_URL(),
                mqttc.CLIENT_ID());
        myBroadCastReceiver = new MyBroadcastReceiver();
        //When checking in

        recyclerView = findViewById(R.id.queue_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QueueAdapter();
        recyclerView.setAdapter(adapter);


        ImageButton back = findViewById(R.id.backbttn);
        ImageButton option = findViewById(R.id.optionsbttn);
        Button scan = findViewById(R.id.qrCodeActivityBttn);

        //checks if there is a new topic to subscribe too
        checkTopicChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckInActivity.this, HomeScreenActivity.class));
            }
        });

        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckInActivity.this, BlankActivity.class));
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(CheckInActivity.this, QrScannerActivity.class),REQUEST_CODE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultcode, Intent data){
        if(requestCode == REQUEST_CODE){
            if(resultcode == RESULT_OK){
                Log.d("CHECKINACTIVITY", data.getStringExtra("topic"));


                String[] dataArray = data.getStringArrayExtra("achievementstags");
                for (String s : dataArray) {

                }
                //
                //Data can be requested here with data.get[type]Extra([variable name])
                //

            }
        }
    }

    private void subscribe(){
        try {
            pahoMqttClient.subscribe(client, MQTT_TOPIC, 0);
        } catch (MqttException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void unSubscribe(){
        try {
            pahoMqttClient.unSubscribe(client, MQTT_TOPIC);
        } catch (MqttException | NullPointerException e) {
            e.printStackTrace();
        }

    }

    public void checkTopicChanged(){
        if (client == null){
            return;
        }
        Log.d(TAG, "checkTopicChanged: "  + client.toString());
        String topic = "" + getIntent().getStringExtra("topic");
        if (topic.isEmpty() && topic.equals(this.MQTT_TOPIC)){
            return;
        }
        this.MQTT_TOPIC = topic;



        Log.d(TAG, "checkTopicChanged: " + "hello");
        unSubscribe();
        subscribe();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        private final String TAG = "MyBroadcastReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            try
            {
                String payload = intent.getStringExtra("payload");
                Log.i(TAG,  payload);


                try {
                    JSONObject jsonObject = new JSONObject(payload);
                    int data = jsonObject.getJSONObject("data").getInt("data");

                    //TODO data need to be used to both gain exp and an achievement if it is highest then a certain number
                    //TODO number ranges from 0 - 4095 so around 3500/3800 get the achievement?



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * has to be overridden to unregister  broadcast  receiver
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // make sure to unregister your receiver after finishing of this activity
        unregisterReceiver(myBroadCastReceiver);
    }

}

