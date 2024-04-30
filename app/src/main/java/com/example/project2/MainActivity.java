package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project2.Database.MonRepository;
import com.example.project2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String MAIN_ACTIVITY_USER_ID ="com.example.project2.MAIN_ACTIVITY_USER_ID";
    public static final String TAG = "DAC_MON";
    private MonRepository repository;
    int loggedInUserId = -1;


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
        binding = com.example.project2.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = MonRepository.getRepository(getApplication());
        assert repository !=null;
        repository.invokeDB();
        logInUser();
        if(loggedInUserId == -1){
            Intent intent = LoginPage.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }


        /* need to change more in future but:

           getGenerationNumber.setOnClickListener(new View.OnClickListener);


         */
    }
    private void logInUser(){
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }
    static Intent MainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

}