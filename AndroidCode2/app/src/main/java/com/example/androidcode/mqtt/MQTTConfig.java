package com.example.androidcode.mqtt;

import java.util.UUID;

public class MQTTConfig {


    /**
     * URL for the MQTT broker
     */
    private String MQTT_BROKER_URL = "tcp://51.254.217.43:1883";



    /**
     * every client needs a different ID
     * the ID is generated in the constructor
     * the id is generated to be a random id
     * with a set pre-fix
     */
    private String CLIENT_ID;

    public MQTTConfig() {
        CLIENT_ID = "ESSTELING_B6_" + UUID.randomUUID().toString();
    }

    public String MQTT_BROKER_URL() {
        return MQTT_BROKER_URL;
    }

    public String CLIENT_ID() {
        return CLIENT_ID;
    }

}
