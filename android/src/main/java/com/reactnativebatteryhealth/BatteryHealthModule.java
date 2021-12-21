package com.reactnativebatteryhealth;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.*;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import static android.content.Context.BATTERY_SERVICE;

@ReactModule(name = BatteryHealthModule.NAME)
public class BatteryHealthModule extends ReactContextBaseJavaModule {
    public static final String NAME = "BatteryHealth";
    public BatteryHealthModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    @ReactMethod
    public void getHealth(Promise promise) {

      IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
      Intent batteryStatus = getReactApplicationContext().registerReceiver(null, ifilter);

      int deviceHealth = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
      int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
      int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);

      BatteryManager bm = (BatteryManager) getReactApplicationContext().getSystemService(BATTERY_SERVICE);
      int capacity = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

      WritableMap batteryInfo = Arguments.createMap();
      batteryInfo.putString("health", getBatteryHealthStatus(deviceHealth));
      batteryInfo.putInt("capacity", capacity);
      batteryInfo.putInt("voltage", voltage);
      batteryInfo.putInt("cycleCount", 0);
      batteryInfo.putDouble("temperature",  ((float) temperature) / 10);

      promise.resolve(batteryInfo);
    }

    public String getBatteryHealthStatus(Integer status) {
      Map<Integer, String> batteryHealthStatus = new HashMap<>();
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_COLD, "Cold");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_DEAD, "Dead");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_GOOD, "Good");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_OVERHEAT, "OverHeat");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE, "Over voltage");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_UNKNOWN, "Unknown");
      batteryHealthStatus.put(BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE, "Unspecified Failure");
      return  batteryHealthStatus.get(status);
    }
}
