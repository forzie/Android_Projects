package com.example.kaivanshah.testnavdrawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,new HomeFragment()).commit();
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.imageView);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        View hview = navigationView.inflateHeaderView(R.layout.nav_header_main);
        ImageView imageView = (ImageView) hview.findViewById(R.id.imagesession);
        TextView texthead = (TextView) hview.findViewById(R.id.namesession);
        TextView texthead1 = (TextView) hview.findViewById(R.id.emailsession);

        String profile = (String) AppPersistance.start(MainActivity.this).get(AppPersistance.keys.IMAGE_URL);
        Picasso.with(this)
                .load("http://192.168.0.101/testnav/img/"+profile)
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)      // optional
                .resize(400,400)                        // optional
                .into(imageView);

//        imageView.setImageResource(R.drawable.favicon);
        String name = (String) AppPersistance.start(MainActivity.this).get(AppPersistance.keys.USER_NAME);
        texthead.setText(name);
        String email = (String) AppPersistance.start(MainActivity.this).get(AppPersistance.keys.EMAIL);
        texthead1.setText(email);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.changePass) {
            fragment = new ChangePassword();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        if (id == R.id.contactus) {
            fragment = new ContactFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        if (id == R.id.aboutus) {
            fragment = new AboutUsFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        if (id == R.id.action_settings) {
            fragment = new SettingFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        if (id == R.id.logout) {
            final DialogInterface.OnClickListener dialog = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dia, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            AppPersistance.start(getApplicationContext()).remove(AppPersistance.keys.USER_NAME);
                            AppPersistance.start(getApplicationContext()).remove(AppPersistance.keys.EMAIL);
                            Intent login = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are You Sure?").setPositiveButton("Yes",dialog).setNegativeButton("No",dialog).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home) {
            fragment = new HomeFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.category) {
            fragment = new CategoryFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.tips) {
            fragment = new TipsFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.doctor) {
            fragment = new DoctorFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.profile) {
            fragment = new ProfileFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.setting) {
            fragment = new SettingFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        } else if (id == R.id.postQuery) {
            fragment = new PostQueryFragment();
            FragmentManager fm = getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
          else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Silverwing Technologies Pvt Ltd.");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "For More info Visit http://www.silverwingtechnologies.com");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
