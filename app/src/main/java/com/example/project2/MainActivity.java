package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.ActivityMainBinding;
import com.example.project2.databinding.BattleScreenBinding;


public class MainActivity extends AppCompatActivity {

    private static final int LOGGED_OUT = -1;
    static final String SHARED_PREFERENCE_USERID_KEY="com.example.project2.SHARED_PREFERENCE_USERID_KEY";
    static final String SHARED_PREFERENCE_USERID_VALUE = "com.example.project2.SHARED_PREFERENCE_USERID_VALUE";
    private ActivityMainBinding binding;
    private static final String MAIN_ACTIVITY_USER_ID ="com.example.project2.MAIN_ACTIVITY_USER_ID";
    private static final String SAVED_INSTANCE_STATE_USERID_KEY ="com.example.project2.SAVED_INSTANCE_STATE_USERID_KEY";
    private static final String SAVED_INSTANCE_STATE_USERID_VALUE ="com.example.project2.SAVED_INSTANCE_STATE_USERID_VALUE";

    public static final String TAG = "DAC_MON";
    private MonRepository repository;
    private int loggedInUserId = -1;
    private User user;



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
//        repository.invokeDB();
        repository = MonRepository.getRepository(getApplication());
//        assert repository !=null;

        logInUser(savedInstanceState);
///        invalidateOptionsMenu();
        if(loggedInUserId == -1){
            Intent intent = LoginPage.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }




        binding.BattleMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BattleLooper.class);
//                intent.putExtra(MAIN_ACTIVITY_USER_ID, user.getId());
                startActivity(intent);
            }
        });



        /* need to change more in future but:

           getGenerationNumber.setOnClickListener(new View.OnClickListener);


         */
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater= getMenuInflater();
//        inflater.inflate(R.menu.logoutmenu, menu);
//        return true;
//    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.LogOutMenuItem);
//        item.setVisible(true);
//        if(user==null) return false;
//        item.setTitle(user.getUsername());
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(@NonNull MenuItem item) {
//                showLogoutDialog();
//                return false;
//            }
//        });
//        return true;
//    }
//    private void showLogoutDialog(){
//        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
//        final AlertDialog alertDialog = alertBuilder.create();
//
//        alertBuilder.setMessage("Logout?");
//
//        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                logout();
//            }
//        });
//        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                alertDialog.dismiss();
//            }
//        });
//        alertBuilder.create().show();
//    }


    private void logout() {
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(SHARED_PREFERENCE_USERID_KEY,LOGGED_OUT);
        startActivity(LoginPage.loginIntentFactory(getApplicationContext()));
        sharedPrefEditor.apply();

//        Intent intent =
    }

    private void logInUser(Bundle savedInstanceState){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFERENCE_USERID_KEY,
                Context.MODE_PRIVATE);
        if(sharedPreferences.contains(SHARED_PREFERENCE_USERID_KEY)){
            loggedInUserId = sharedPreferences.getInt(SHARED_PREFERENCE_USERID_VALUE, LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT & savedInstanceState!=null&&savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)){
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY,LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT) {
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if(loggedInUserId==LOGGED_OUT){
            return;
        }




    }

    static Intent MainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

}