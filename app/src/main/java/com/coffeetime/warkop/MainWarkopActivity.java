package com.coffeetime.warkop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.coffeetime.R;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Endpoints;

public class MainWarkopActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    Animation animRotate;
    ImageView imgRotate;

    Warkop warkop;
    Endpoints endpoints;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_warkop);

        imgRotate = findViewById(R.id.imgAnim);

        // kita set default nya Home Fragment
        loadFragment(new MenuFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_warkop);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences("coffee", 0);
        editor = sharedPreferences.edit();
        editor.apply();

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container_warkop, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()){
            case R.id.navigation_menu:
                fragment = new MenuFragment();
                break;
            case R.id.navigation_pesanan:
                fragment = new PesananFragment();
                break;
            case R.id.navigation_profil:
                fragment = new ProfilWarkopFragment();
                break;
        }
        return loadFragment(fragment);
    }

    public void ProfilWarkop(View view) {
        startActivity(new Intent(MainWarkopActivity.this, TambahWarkopActivity.class));
    }

    //    MENU
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu,add items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option_menu, menu);//Menu ResourceFile
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.item1:
//                Toast.makeText(getApplicationContext(), "Item 1 Selected", Toast.LENGTH_LONG).show();

                animRotate = AnimationUtils.loadAnimation(MainWarkopActivity.this,R.anim.rotate);
                imgRotate.setVisibility(View.VISIBLE);
                imgRotate.startAnimation(animRotate);

                int _TIMER = 1000;
                Handler handler = new Handler();

                // Timer
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = getIntent();
                        startActivity(intent);
                    }
                }, _TIMER);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
