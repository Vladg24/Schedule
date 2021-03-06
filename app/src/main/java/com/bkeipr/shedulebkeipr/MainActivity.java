package com.bkeipr.shedulebkeipr;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bkeipr.shedulebkeipr.Fragment.AboutAddFragment;
import com.bkeipr.shedulebkeipr.Fragment.RingFragment;
import com.bkeipr.shedulebkeipr.Fragment.ScheduleChangeFragment;
import com.bkeipr.shedulebkeipr.Fragment.TabFragment;
import com.bkeipr.shedulebkeipr.Fragment.TeachersFragment;
import com.bkeipr.shedulebkeipr.Schedule_change.DownloadTask;
import com.bkeipr.shedulebkeipr.Schedule_change.Utils;
import com.bkeipr.shedulebkeipr.m_JSON.JSONDownloader;
import com.bkeipr.shedulebkeipr.m_JSON.ParserRing;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.activity_main;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private static TabLayout tabLayout;
    EditText text;
    String group ;

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this , MainActivity.class );
                finish();
                startActivity(i);
            }
        });

        showInputDialog();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new DownloadTask(MainActivity.this, Utils.downloadDocUrl);
    }

    public void initTabs(final String group)
    {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.shitstuff) ;

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment(group)).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.shedule_ring) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new RingFragment()).commit();
                }

                if (menuItem.getItemId() == R.id.shehdule) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView,new TabFragment(group)).commit();
                }

                if (menuItem.getItemId() == R.id.teachers) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new TeachersFragment(group)).commit();
                }

                if (menuItem.getItemId() == R.id.shedule_change) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new ScheduleChangeFragment(group)).commit();
                }
                if (menuItem.getItemId() == R.id.aboutAdd) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView,new AboutAddFragment()).commit();
                }
                return false;
            }

        });

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    public void showInputDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);

        text = (EditText) promptView.findViewById(R.id.edittext);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        checkGroup(text.getText().toString().toUpperCase());


                    }
                })
                .setNegativeButton(null,null);
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    public void checkGroup(String name_g) {
        String url = "http://schedulebkeipr.esy.es/Groups.php";

        for (int i = 0; i < 36; i++) {
             new JSONDownloader(MainActivity.this, url, name_g).execute();
                initTabs(name_g);
                return;
            }
        }

    public void faildGroup()
    {
            Toast.makeText(getApplicationContext(), "Не вірно вказана група", Toast.LENGTH_SHORT).show();
            showInputDialog();
    }
}