package com.example.project2.Database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MonRepository {
    private MonDao monDao;
    private UserDAO userDao;
    private ArrayList<Mon> allMon;
    private static MonRepository repository;

    public MonRepository(Application application){
        MonDatabase db = MonDatabase.getDatabase(application);
        this.userDao = db.UserDAO();
        this.monDao = db.MonDAO();
        this.allMon = (ArrayList<Mon>) this.monDao.getAllRecords();
    }
    public ArrayList<Mon> getAllMon(){
        Future<ArrayList<Mon>> future = MonDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Mon>>() {
                    @Override
                    public ArrayList<Mon> call() throws Exception {
                        return (ArrayList<Mon>) monDao.getAllRecords();
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
    }
    public static MonRepository getRepository(Application application){
        if(repository!=null){
            return repository;
        }
        Future<MonRepository> future = MonDatabase.databaseWriteExecutor.submit(
                new Callable<MonRepository>() {
                    @Override
                    public MonRepository call() throws Exception {
                        return new MonRepository(application);
                    }
                }
        );
        try{
            return future.get();
        } catch(InterruptedException | ExecutionException e){
            Log.d(MainActivity.TAG, "Problem getting user repository");
        }
        return null;
    }
    public void insertMon(Mon mon){
        MonDatabase.databaseWriteExecutor.execute(()->{
            monDao.insert(mon);
                });
    }
    public void invokeDB(){MonDatabase.databaseWriteExecutor.execute(userDao::getAllRecords);}
    public void insertUser(User... user){
        MonDatabase.databaseWriteExecutor.execute(()->{
            userDao.insert(user);
        });
    }


    public LiveData<User> getUserbyUserName(String username) {
        return userDao.getUserByUsername(username);


    }

}
