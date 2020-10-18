package com.example.LifestyleApp.UserInfo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {UserInfoTable.class}, version = 1, exportSchema = false)

public abstract class UserInfoDatabase extends RoomDatabase {

    private static volatile UserInfoDatabase mInstance;

    public abstract UserInfoDao mUserInfoDao();

    public static synchronized UserInfoDatabase getDatabase(final Context context){
        if(mInstance==null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserInfoDatabase.class, "userInfo.db").addCallback(sRoomDatabaseCallback).build();
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
        private final UserInfoDao mDao;

        PopulateDbAsync(UserInfoDatabase db){
            mDao = db.mUserInfoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            UserInfoTable userInfoTable = new UserInfoTable("dummy_email",
                    "dummy_json");
            mDao.insert(userInfoTable);
            return null;
        }
    }
}
