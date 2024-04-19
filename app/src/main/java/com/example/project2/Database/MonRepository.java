package com.example.project2.Database;

import android.app.Application;
import android.util.Log;

import com.example.project2.Database.entities.Mon;
import com.example.project2.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MonRepository {
    private MonDao monDao;
    private ArrayList<Mon> allMon;

    public MonRepository(Application application){
        MonDatabase db = MonDatabase.getDatabase(application);
        this.monDao = db.MonDAO();
        this.allMon = this.monDao.getAllRecords();
    }
    public ArrayList<Mon> getAllMon(){
        Future<ArrayList<Mon>> future = MonDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Mon>>() {
                    @Override
                    public ArrayList<Mon> call() throws Exception {
                        return monDao.getAllRecords();
                    }
                }
        );
        try {
            return future.get();
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all Pokemon in the repository");

        }
        return null;
    };

}
