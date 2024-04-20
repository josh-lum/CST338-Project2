package com.example.project2.Database;

import android.app.Application;
import android.util.Log;

import com.example.project2.Database.entities.Mon;
import com.example.project2.Database.entities.User;
import com.example.project2.MainActivity;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class UserRepository {
    private UserDAO userDao;
    private ArrayList<User> allUsers;

    public UserRepository(Application application){
        UserDatabase db = UserDatabase.getDatabase(application);
        this.userDao = db.UserDAO();
        this.allUsers = this.userDao.getAllRecords();
    }
    public ArrayList<User> getAllMon(){
        Future<ArrayList<User>> future = UserDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<User>>() {
                    @Override
                    public ArrayList<User> call() throws Exception {
                        return userDao.getAllRecords();
                    }
                }
        );
        try {
            return future.get();
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
            Log.i(MainActivity.TAG, "Problem when getting all Users in the repository");

        }
        return null;
    }
    public void insertMon(User user){
        UserDatabase.databaseWriteExecutor.execute(()->{
            userDao.insert(user);
        });
    }


}
