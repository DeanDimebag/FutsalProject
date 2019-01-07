package com.example.dipesh.mysqltest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class Menu_test extends AppCompatActivity
{

    public static FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private HomeFragment homeFragment;
    private BookingFragment bookFragment;
    private AccountFragment accountFragment;
    private AboutFragment aboutFragment;
    private Context cont;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,new HomeFragment());
        fragmentTransaction.commit();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.main_nav);
        frameLayout = (FrameLayout) findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        bookFragment = new BookingFragment();
        accountFragment = new AccountFragment();
        aboutFragment = new AboutFragment();

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId() )
                {

                    case R.id.nav_home:
                        bottomNavigationView.setItemBackgroundResource(R.color.design_default_color_primary);
                        setFragment(homeFragment);
                        return true;


                    case R.id.nav_notif:
                        bottomNavigationView.setItemBackgroundResource(R.color.design_default_color_primary);
                        setFragment(bookFragment);
                        return true;



                    case R.id.nav_account:
                        bottomNavigationView.setItemBackgroundResource(R.color.design_default_color_primary);
                        setFragment(accountFragment);
                        return true;

                    case R.id.nav_aboutus:
                        bottomNavigationView.setItemBackgroundResource(R.color.design_default_color_primary);
                        setFragment(aboutFragment);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }

}
