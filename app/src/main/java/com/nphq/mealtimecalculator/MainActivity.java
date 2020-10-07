package com.nphq.mealtimecalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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

        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new HomeFragment(),"food").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new GalleryFragment(),"sleep").commitNow();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,new SlideshowFragment(),"exercise").commitNow();


        Fragment f1 = getSupportFragmentManager().findFragmentByTag("food");
        Fragment f2 = getSupportFragmentManager().findFragmentByTag("sleep");
        Fragment f3 = getSupportFragmentManager().findFragmentByTag("exercise");


        getSupportFragmentManager().beginTransaction().hide(f2).commitNow();
        getSupportFragmentManager().beginTransaction().hide(f3).commitNow();


        fragment_selected.put("food",true);
        fragment_selected.put("sleep",false);
        fragment_selected.put("exercise",false);






    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void displaySelectedScreen(int id){
        String tag = "";
        String select= "";

        switch (id){
            case R.id.nav_exercise:
                tag = "exercise";
                select = "exercise";
                break;
            case R.id.nav_sleep:
                tag = "sleep";
                select = "sleep";
                break;
            case R.id.nav_food:
                tag = "food";
                select = "food";
                break;

        }
        FragmentManager manager = getSupportFragmentManager();
        if (fragment_selected.get("food").equals(true)){
            fragment_selected.replace("food",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("food")).commitNow();
        }
        else if (fragment_selected.get("sleep").equals(true)){
            fragment_selected.replace("sleep",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("sleep")).commitNow();
        }
        else if (fragment_selected.get("exercise").equals(true)){
            fragment_selected.replace("exercise",false);
            getSupportFragmentManager().beginTransaction().hide(manager.findFragmentByTag("exercise")).commitNow();
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