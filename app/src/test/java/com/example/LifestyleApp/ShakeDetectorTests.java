package com.example.LifestyleApp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.LifestyleApp.StepCounter.ShakeDetector;
import com.example.LifestyleApp.Tables.UserIDTable;
import com.example.LifestyleApp.Tables.UserInfoTable;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoDao;
import com.example.LifestyleApp.UserInfo.UserInfoDatabase;
import com.example.LifestyleApp.daos.UserIDDao;

import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import androidx.test.core.app.ApplicationProvider;

@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class ShakeDetectorTests {

    @Before
    public void setup() throws IOException, JSONException {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testShake() throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        SensorEventListener mShaker = new ShakeDetector();

        Intent intent=new Intent();
//        mShaker.onStartCommand(intent,-1,-1);

        SensorEvent sensorEvent=getEvent();
        Sensor sensor=getSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEvent.sensor=sensor;
        sensorEvent.values[0]=1.2345f;
        sensorEvent.values[1]=2.45f;
        sensorEvent.values[2]=1.6998f;
        mShaker.onSensorChanged(sensorEvent);

//        Field field=mShaker.getClass().getDeclaredField("lastX");
//        System.out.println(">>>>>>>");
//        System.out.println(field);
//        field.setAccessible(true);
//        float[] result= (float[]) field.get(mShaker);
//        Assert.assertEquals(sensorEvent.values.length,result.length);
//        Assert.assertEquals(sensorEvent.values[0],result[0],0.0f);
//        Assert.assertEquals(sensorEvent.values[1],result[1],0.0f);
//        Assert.assertEquals(sensorEvent.values[2],result[2],0.0f);


    }

    private Sensor getSensor(int type) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<Sensor> constructor = Sensor.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        Sensor sensor= constructor.newInstance(new Object[0]);

        Field field=sensor.getClass().getDeclaredField("mType");
        field.setAccessible(true);
        field.set(sensor,type);
        return sensor;
    }



    private SensorEvent getEvent() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<SensorEvent> constructor = SensorEvent.class.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);
        return constructor.newInstance(new Object[]{3});
    }

}
