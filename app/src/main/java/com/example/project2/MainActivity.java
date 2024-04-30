package com.example.project2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project2.Database.MonRepository;
import com.example.project2.Database.entities.User;
import com.example.project2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String MAIN_ACTIVITY_USER_ID ="com.example.project2.MAIN_ACTIVITY_USER_ID";
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

        repository = MonRepository.getRepository(getApplication());
        assert repository !=null;
        repository.invokeDB();
        logInUser();
        invalidateOptionsMenu();
        if(loggedInUserId == -1){
            Intent intent = LoginPage.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }


        /* need to change more in future but:

           getGenerationNumber.setOnClickListener(new View.OnClickListener);


         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.logoutmenu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.LogOutMenuItem);
        item.setVisible(true);
        item.setTitle(user.getUsername());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                showLogoutDialog();
                return false;
            }
        });
        return true;
    }
    private void showLogoutDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertBuilder.create().show();
    }


    private void logout() {
        //finish this
        startActivity(LoginPage.loginIntentFactory(getApplicationContext()));
    }

    private void logInUser(){
        user = new User("username","password");
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, -1);
    }

    static Intent MainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

}