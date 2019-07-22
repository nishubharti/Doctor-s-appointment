package com.example.nishu.doc;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


import com.example.nishu.doc.beans.Clerk;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab_plus,fab_contact,fab_message;
    Animation fabOpn,fabClose,fabRClockwise,fabRantiClockwise;
    boolean isOpen=false;

    private static final String TAG= "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        fab_plus=(FloatingActionButton)findViewById(R.id.fab);
        fab_contact=(FloatingActionButton)findViewById(R.id.fab_contact);
        fab_message=(FloatingActionButton)findViewById(R.id.fab_message);

        fabOpn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        fabClose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fabRClockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        fabRantiClockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num="8521823285";
                Intent intent=new Intent(getApplicationContext(),Wehelp.class);
                intent.putExtra("contact",num);
                startActivity(intent);
            }
        });

        fab_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String num="8521823285";
                Toast.makeText(getApplicationContext(), ""+num, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Intent.ACTION_CALL);
                Uri u=Uri.parse("tel:"+num);
                intent.setData(u);
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                startActivity(intent);     }
        });



        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen)
                {

                    fab_contact.startAnimation(fabClose);
                    fab_message.startAnimation(fabClose);
                    fab_plus.startAnimation(fabRantiClockwise);
                    fab_contact.setClickable(false);
                    fab_message.setClickable(false);
                    isOpen=false;

                }
                else
                {
                    fab_contact.startAnimation(fabOpn);
                    fab_message.startAnimation(fabOpn);
                    fab_plus.startAnimation(fabRClockwise);
                    fab_contact.setClickable(true);
                    fab_message.setClickable(true);
                    isOpen=true;
                }
            }
        });


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if(id == R.id.official)
        {
            Intent i=new Intent(MainActivity.this,LoginModule.class);
            startActivity(i);

        }
        if (id == R.id.action_settings) {
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);

        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            if(isServicesOK())
            {
                init();
            }

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void init()
    {    Intent i=new Intent(MainActivity.this,Map_Activity.class);
        startActivity(i);

    }

    public boolean isServicesOK()
    {
        Log.d(TAG,"isServicesOK:checking google services version");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available== ConnectionResult.SUCCESS){
            //everything is fine and user can make map reuests
            Log.d(TAG,"isServicesOK: Google Play Services is working");
            return true;

        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available))
        {
            //an arror occured but we can resolve it
            Log.d(TAG,"isServicesOK: an error occured but we can fix it");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"we cant make map requests",Toast.LENGTH_SHORT).show();
        }
        return false;


    }
}
