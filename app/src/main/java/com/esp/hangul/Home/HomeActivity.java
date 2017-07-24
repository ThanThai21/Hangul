package com.esp.hangul.Home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esp.hangul.Database.Database;
import com.esp.hangul.Database.DatabaseManager;
import com.esp.hangul.Login.LoginActivity;
import com.esp.hangul.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int RC_LOGIN = 1;
    Toolbar toolbar;
    ImageButton lookupButton;
    SearchView lookupView;

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    BottomPagerAdapter pagerAdapter;
    MenuItem prevMenuItem;

    NavigationView navigationView;
    View headerNavigation;
    ImageView avatarView;
    TextView nameView;

    FirebaseAuth mAuth;
    FirebaseUser user;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivityForResult(intent, RC_LOGIN);
        }

        initDB();

        initToolbar();

        initNavigationView();

        initBottomNavigationView();

        HomeActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                user = mAuth.getCurrentUser();
                updateNavigationView();
            }
        });
    }

    private void initDB() {
        DatabaseManager.database = new Database(this);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Hangul");
        lookupView = (SearchView) toolbar.findViewById(R.id.toolbar_lookup_searchview);
        lookupButton = (ImageButton) toolbar.findViewById(R.id.toolbar_lookup_button);
        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
                lookupView.setVisibility(View.VISIBLE);
                lookupButton.setVisibility(View.GONE);
                lookupView.setIconified(false);
                lookupView.setFocusable(true);
                lookupView.requestFocus();
                lookupView.requestFocusFromTouch();
            }
        });
        ImageView searchClose = (ImageView) lookupView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage);
                lookupView.setVisibility(View.GONE);
                lookupView.clearFocus();
                lookupButton.setVisibility(View.VISIBLE);
            }
        });
        setSupportActionBar(toolbar);
    }

    private void initNavigationView() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerNavigation = navigationView.getHeaderView(0);
        avatarView = (ImageView) headerNavigation.findViewById(R.id.nav_avatar);
        nameView = (TextView) headerNavigation.findViewById(R.id.nav_username);
        if (user != null) {
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(avatarView);
            nameView.setText(user.getDisplayName());
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(true);
        } else {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);
        }
    }



    public void initBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        pagerAdapter = new BottomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_nav_search:
                        viewPager.setCurrentItem(0);
                        currentPage = 0;
                        lookupView.setVisibility(View.VISIBLE);
                        lookupButton.setVisibility(View.GONE);
                        lookupView.setIconified(false);
                        lookupView.setFocusable(true);
                        lookupView.requestFocus();
                        lookupView.requestFocusFromTouch();
                        break;
                    case R.id.bottom_nav_study:
                        viewPager.setCurrentItem(1);
                        currentPage = 1;
                        lookupView.setVisibility(View.GONE);
                        lookupView.clearFocus();
                        lookupButton.setVisibility(View.VISIBLE);
                        break;
                    case R.id.bottom_nav_quiz:
                        viewPager.setCurrentItem(2);
                        currentPage = 2;
                        lookupView.setVisibility(View.GONE);
                        lookupView.clearFocus();
                        lookupButton.setVisibility(View.VISIBLE);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_login) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivityForResult(intent, RC_LOGIN);
        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);
            avatarView.setImageResource(R.mipmap.ic_launcher_round);
            nameView.setText("Bạn chưa đăng nhập");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    private void updateNavigationView() {
        if (user != null) {
            Glide.with(this)
                    .load(user.getPhotoUrl())
                    .into(avatarView);
            nameView.setText(user.getDisplayName());
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(false);
            menu.findItem(R.id.nav_logout).setVisible(true);
        } else {
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_login).setVisible(true);
            menu.findItem(R.id.nav_logout).setVisible(false);
            avatarView.setImageResource(R.mipmap.ic_launcher_round);
            nameView.setText("Bạn chưa đăng nhập");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_LOGIN) {
            user = mAuth.getCurrentUser();
            updateNavigationView();
        }
    }
}

