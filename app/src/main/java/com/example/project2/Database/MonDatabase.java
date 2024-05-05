package com.example.project2.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.Database.entities.*;

import com.example.project2.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Mon.class, User.class, Party.class}, version = 8, exportSchema = false)
public abstract class MonDatabase extends RoomDatabase {
    public static final String USER_TABLE = "userTable";
    public static final String DATABASE_NAME = "MonDatabase";
    public static final String MON_TABLE = "monTable";
    public static final String PARTY_TABLE = "partyTable";

    private static volatile MonDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MonDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MonDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MonDatabase.class,
                            DATABASE_NAME).fallbackToDestructiveMigration().addCallback(addDefaultValues).build();

                }
            }
        }
        return INSTANCE;
    }


    private final static RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "Database Created!");

            databaseWriteExecutor.execute(()->{
                MonDatabase database = INSTANCE;
                UserDAO dao = INSTANCE.UserDAO();
                dao.deleteAll();
                User admin = new User("admin1","admin1");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser1 = new User("testuser1","testuser1");
                dao.insert(testUser1);
            });
        }
    };

    public abstract MonDao MonDAO();
    public abstract UserDAO UserDAO();
}
