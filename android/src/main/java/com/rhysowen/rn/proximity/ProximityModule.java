package com.rhysowen.rn.proximity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;

public class ProximityModule extends ReactContextBaseJavaModule implements SensorEventListener {

    private static final String REACT_CLASS = "ProximityAndroid";
    private static final String ON_SENSOR_CHANGE = "OnSensorChange";
    private static final String IS_NEAR_DEVICE = "IsNearDevice";

    private static final String DISTANCE_KEY = "distance";

    private EventManager mEventManager;
    private SensorManager mSensorManager;
    private Sensor mProximity;

    public ProximityModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mEventManager = new EventManager(reactContext);
        mSensorManager = (SensorManager) reactContext.getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void startListener() {
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @ReactMethod
    public void stopListener() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        WritableMap params = Arguments.createMap();

        double distance = event.values[0];
        double maximumRange = mProximity.getMaximumRange();
        boolean isNearDevice = distance < maximumRange;

        params.putBoolean(IS_NEAR_DEVICE, isNearDevice);
        params.putDouble(DISTANCE_KEY, distance);

        mEventManager.sendEvent(ON_SENSOR_CHANGE, params);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
