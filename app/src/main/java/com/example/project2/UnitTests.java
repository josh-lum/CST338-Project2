//package com.example.project2;
//
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import androidx.core.app.RemoteInput;
//import androidx.room.Room;
//
//import com.example.project2.Database.MonDatabase;
//import com.example.project2.Database.MonRepository;
//import com.example.project2.Database.UserDAO;
//
//import org.junit.After;
//import org.junit.Before;
//
//
//public class UnitTests {
//    private UserDAO userDao;
//    private MonDatabase monDatabase;
//    private MonRepository monRepository;
//
//    // Joshua Lum's tests
//
//    @Before
//    private void setUp(){
//        Context context = ApplicationProvider.getApplicationContext();
//        monDatabase = Room.inMemoryDatabaseBuilder(context, MonDatabase.class).build();
//        userDao = monDatabase.UserDAO();
//        monRepository = new MonRepository(userDao);
//    }
//
//
//
//   // private BattleLooper battleLooper;
//
//
//
//
//
//
//
//
//    // Quinn Krommenhoek's tests
//}
