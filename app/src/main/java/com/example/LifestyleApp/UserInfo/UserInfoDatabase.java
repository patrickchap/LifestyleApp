package com.example.LifestyleApp.UserInfo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.LifestyleApp.Tables.UserIDTable;
import com.example.LifestyleApp.Tables.UserInfoTable;
import com.example.LifestyleApp.daos.UserIDDao;

@Database(entities = {UserInfoTable.class, UserIDTable.class}, version = 8, exportSchema = false)

public abstract class UserInfoDatabase extends RoomDatabase {

    private static volatile UserInfoDatabase mInstance;

    public abstract UserInfoDao mUserInfoDao();
    public abstract UserIDDao mUserIDDao();


    public static synchronized UserInfoDatabase getDatabase(final Context context){
        if(mInstance==null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserInfoDatabase.class, "userInfo.db").addCallback(sRoomDatabaseCallback).fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }

    private static Callback sRoomDatabaseCallback = new Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(mInstance).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {
        private final UserInfoDao userInfoDao;
        private final UserIDDao userIDDao;


        PopulateDbAsync(UserInfoDatabase db){

            userInfoDao = db.mUserInfoDao();
            userIDDao = db.mUserIDDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoDao.deleteAll();
            UserInfoTable userInfoTable = new UserInfoTable("dummy_userID");
            userInfoDao.insert(userInfoTable);

            userIDDao.deleteAll();
            UserIDTable userIDTable = new UserIDTable(0,
                    "dummy_userID");
            userIDDao.insert(userIDTable);
            return null;
        }

    }
}