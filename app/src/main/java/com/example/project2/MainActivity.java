package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.project2.Database.entities.MonDatabase;

public class MainActivity extends AppCompatActivity {

    //commented out but its from GymLog

//    public MonRepository(Application application){
  //      MonDatabase db = MonDatabase.getDatabase(application) {
    //        this.MonDao = MonDatabase.MonDao();
      //      this.alllogs = (ArrayList</*Pokemon I think */>) this.MonDao.getAllRecords();
        //}

   // }

    //also from GymLog

    /*
        public ArrayList<Pokemon> getAllLogs(){
            Future<Arraylist<Pokemon>> future = MonDatabase.databaseWriteExecutor.submit(
                new Callable<ArrayList<Pokemon>>(){
                   @Override
                   public Arraylist<Pokemon> call() throws Exception{
                        return (ArrayList<Pokemon>) MonDao.getAllRecords();
                   }
                });
             try{
                return future.get();
             }catch (InterruptedException | ExecutionException e){
             Log.i(MainActivity.TAG, "Problem whne getting all logs in repo"
             }
             return null;
        }
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* need to change more in future but:

           getGenerationNumber.setOnClickListener(new View.OnClickListener);


         */
    }

}