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
public class UserInfoDaoTest {

    private UserInfoDatabase userInfoDatabase;
    private UserInfoDao userInfoDao;
    private  UserIDDao userIDDao;
    Context context;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() throws IOException, JSONException {

        context = ApplicationProvider.getApplicationContext();
        userInfoDatabase = Room.inMemoryDatabaseBuilder(context, UserInfoDatabase.class).allowMainThreadQueries().build();
        userInfoDao = userInfoDatabase.mUserInfoDao();
        userIDDao = userInfoDatabase.mUserIDDao();

    }

    @After
    public void closeDB() {
        userInfoDatabase.clearAllTables();
        userInfoDatabase.close();
        userIDDao.deleteAll();
    }

    @Test
    public void insert() throws InterruptedException {


        String userID = UUID.randomUUID().toString();
        UserInfoTable userInfoTable = new UserInfoTable(userID);

        userInfoDao.insert(userInfoTable);
        List<UserInfoTable> liveData =  LiveDataTestUtil.getOrAwaitValue(userInfoDao.getAll());

        System.out.println(liveData.get(0).getUserID().equals(userID));
        assertEquals(liveData.get(0).getUserID(), userID);


        userInfoTable.setGender("Male");
        userInfoDao.insert(userInfoTable);
        List<UserInfoTable> liveData2 =  LiveDataTestUtil.getOrAwaitValue(userInfoDao.getAll());

    }

//    @Test
//    public  void insertUID() throws InterruptedException{
//        AtomicInteger userIDIndex = new AtomicInteger();
//        String userID = UUID.randomUUID().toString();
//        UserData newUser = new UserData(userID);
//        userIDDao.getAll().observeForever(userIDTables -> {
//            if (userIDTables.size() > 0) {
//                UserIDTable userIDTable = userIDTables.get(0);
//                userIDTable.setIndex(userIDIndex.getAndIncrement());
//                userIDTable.setUserId(userID);
//                new insertAsyncTaskUserID(userIDDao).execute(userIDTable);
//            }
//            else {
//                UserIDTable userIDTable = new UserIDTable(userIDIndex.getAndIncrement(), userID);
//                new insertAsyncTaskUserID(userIDDao).execute(userIDTable);
//            }
//        });
//
//        userIDDao.getAll().observeForever( userIDTables -> {
//            assertEquals(userID, userIDTables.get(0).getUserID());
//        });
//    }


    @Test
    public void deleteAll() {

    }

}
