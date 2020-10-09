package com.nphq.mealtimecalculator;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.nphq.mealtimecalculator.ui.reminder.ReminderFragment;
import com.nphq.mealtimecalculator.ui.gallery.GalleryFragment;
import com.nphq.mealtimecalculator.ui.home.HomeFragment;
import com.nphq.mealtimecalculator.ui.slideshow.SlideshowFragment;


import java.util.HashMap;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    public DrawerLayout drawer;

    public ListView mDrawerList;
    public static HashMap<String, Integer> drawable_chest = new HashMap<String, Integer>();
    public static Hashtable<String, Boolean> fragment_selected = new Hashtable<String, Boolean>();
    public static HashMap<String, Integer> drawable_arena = new HashMap<String, Integer>();

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        //mDrawerList = findViewById(R.id.drawer_listview);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_my_decks, R.id.nav_meta_decks, R.id.nav_player_info)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new HomeFragment(),"home").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new GalleryFragment(),"stats").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new SlideshowFragment(),"contact").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new ReminderFragment(),"reminder").commitNow();


        Fragment f1 = getSupportFragmentManager().findFragmentByTag("home");
        Fragment f2 = getSupportFragmentManager().findFragmentByTag("stats");
        Fragment f3 = getSupportFragmentManager().findFragmentByTag("contact");
        Fragment f4 = getSupportFragmentManager().findFragmentByTag("reminder");


        getSupportFragmentManager().beginTransaction().hide(f2).commitNow();
        getSupportFragmentManager().beginTransaction().hide(f3).commitNow();
        getSupportFragmentManager().beginTransaction().hide(f4).commitNow();


        fragment_selected.put("home",true);
        fragment_selected.put("stats",false);
        fragment_selected.put("contact",false);
        fragment_selected.put("reminder",false);






    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displaySelectedScreen(int id){
        String tag = "";
        String select= "";

        switch (id){
            case R.id.nav_contact:
                tag = "contact";
                select = "contact";
                break;
            case R.id.nav_stats:
                tag = "stats";
                select = "stats";
                break;
            case R.id.nav_home:
                tag = "home";
                select = "home";
                break;
            case R.id.nav_reminder:
                tag = "reminder";
                select = "reminder";
                break;

        }
        FragmentManager manager = getSupportFragmentManager();
        if (fragment_selected.get("home").equals(true)){
            fragment_selected.replace("home",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("home")).commitNow();
        }
        else if (fragment_selected.get("stats").equals(true)){
            fragment_selected.replace("stats",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("stats")).commitNow();
        }
        else if (fragment_selected.get("contact").equals(true)){
            fragment_selected.replace("contact",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("contact")).commitNow();
        }
        else if (fragment_selected.get("reminder").equals(true)){
            fragment_selected.replace("reminder",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("reminder")).commitNow();
        }



        if (!tag.equals("")){
            getSupportFragmentManager().beginTransaction().show(manager.findFragmentByTag(tag)).commitNow();
        }
        fragment_selected.replace(tag,true);

        drawer.closeDrawer(GravityCompat.START);


    }






    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
    public void onBackPressed() {


    }








}