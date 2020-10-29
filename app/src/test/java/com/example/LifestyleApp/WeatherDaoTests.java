package com.example.LifestyleApp;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.LifestyleApp.Tables.UserIDTable;
import com.example.LifestyleApp.Tables.UserInfoTable;
import com.example.LifestyleApp.UserInfo.UserData;
import com.example.LifestyleApp.UserInfo.UserInfoDao;
import com.example.LifestyleApp.UserInfo.UserInfoDatabase;
import com.example.LifestyleApp.Weather.WeatherDao;
import com.example.LifestyleApp.Weather.WeatherRoomDatabase;
import com.example.LifestyleApp.Weather.WeatherTable;
import com.example.LifestyleApp.daos.UserIDDao;

import org.apache.tools.ant.taskdefs.Get;
import org.json.JSONException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
public class WeatherDaoTests {


    private WeatherRoomDatabase mWeatherRoomDatabase;
    private WeatherDao mWeatherDao;
    Context context;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() throws IOException, JSONException {

        context = ApplicationProvider.getApplicationContext();
        mWeatherRoomDatabase = Room.inMemoryDatabaseBuilder(context, WeatherRoomDatabase.class).allowMainThreadQueries().build();
        mWeatherDao = mWeatherRoomDatabase.weatherDao();


    }

    @After
    public void closeDB() {
        mWeatherRoomDatabase.clearAllTables();
        mWeatherRoomDatabase.close();
    }

    @Test
    public void insert() throws InterruptedException {

        WeatherTable weatherTable = new WeatherTable("Salt Lake City", "TestJson");
        mWeatherDao.insert(weatherTable);

        List<WeatherTable> liveData =  LiveDataTestUtil.getOrAwaitValue(mWeatherDao.getAll());

        assertEquals(liveData.get(0).getLocation(), "Salt Lake City");

    }
}
