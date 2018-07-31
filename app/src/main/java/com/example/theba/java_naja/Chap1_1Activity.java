package com.example.theba.java_naja;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Chap1_1Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageview;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap1_1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView CopBODY = (TextView) findViewById(R.id.Body1);
        CopBODY.setTextIsSelectable(true);

        TextView CopBODY2 = (TextView) findViewById(R.id.Body2);
        CopBODY2.setTextIsSelectable(true);

        TextView CopHead = (TextView) findViewById(R.id.Header);
        CopHead.setTextIsSelectable(true);

        ImageView SavePIC1 = (ImageView) findViewById(R.id.Pic1);
        SavePIC1.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }
            @Override
            public void onDoubleClick(View v) {

                ActivityCompat.requestPermissions(Chap1_1Activity.this ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.p1_1);

                File path = Environment.getExternalStorageDirectory();

                File dir = new File(path+"/JAVA NaJa/saved/");
                dir.mkdirs();

                File file = new File(dir,"รูปที่ 1.1 ขั้นตอนการแปลงภาษาแอสเซมบลีเป็นภาษาเครื่อง.jpg");

                OutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }   catch (java.io.IOException e) {
                    e.printStackTrace();
                }
//                        Drawable myDrawable = getResources().getDrawable(R.drawable.picchapter1_1);
//
//                        bitmap = ((BitmapDrawablele)myDrawable).getBitmap();
//
//                        ImagePath = MediaStore.Images.Media.insertImage(
//                                getContentResolver(),
//                                bitmap,
//                                "demo_image",
//                                "demo_image"
//                        );
//
//                        URI = Uri.parse(ImagePath);
//
                       Toast.makeText(Chap1_1Activity.this, "Image Saved Successfully \n\n" + dir, Toast.LENGTH_LONG).show();
            }
        });

        ImageView SavePIC2 = (ImageView) findViewById(R.id.Pic2);
        SavePIC2.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onSingleClick(View v) {

            }

            @Override
            public void onDoubleClick(View v) {

                ActivityCompat.requestPermissions(Chap1_1Activity.this ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

                Bitmap image= BitmapFactory.decodeResource(getResources(),R.drawable.p1_2);

                File path = Environment.getExternalStorageDirectory();

                File dir = new File(path+"/JAVA NaJa/saved/");
                dir.mkdirs();

                File file = new File(dir,"รูปที่ 1.2 ขั้นตอนการแปลโปรแกรม.jpg");

                OutputStream out = null;

                try {
                    out = new FileOutputStream(file);
                    image.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }   catch (java.io.IOException e) {
                    e.printStackTrace();
                }
//                        Drawable myDrawable = getResources().getDrawable(R.drawable.picchapter1_1);
//
//                        bitmap = ((BitmapDrawablele)myDrawable).getBitmap();
//
//                        ImagePath = MediaStore.Images.Media.insertImage(
//                                getContentResolver(),
//                                image,
//                                "demo_image",
//                                "demo_image"
//                        );
//
//                        URI = Uri.parse(ImagePath);

                Toast.makeText(Chap1_1Activity.this, "Image Saved Successfully \n\n" + dir, Toast.LENGTH_LONG).show();
            }
        });
        }
    public abstract class DoubleClickListener implements View.OnClickListener {

        private static final long DOUBLE_CLICK_TIME_DELTA = 300;//milliseconds

        long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA){
                onDoubleClick(v);
            } else {
                onSingleClick(v);
            }
            lastClickTime = clickTime;
        }

        public abstract void onSingleClick(View v);
        public abstract void onDoubleClick(View v);
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
        if (id == R.id.action_settings) {
            return true;
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
