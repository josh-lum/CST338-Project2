package com.example.project2.Database.entities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project2.Database.Mon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Mon.class}, version = 1, exportSchema = false)
public abstract class MonDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "Mon_Database";
    public static final String MON_TABLE = "monTable";

    private static volatile MonDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MonDatabase getDatabase(final Context context){
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

        }
    }
}
