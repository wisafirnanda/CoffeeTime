package com.coffeetime.warkop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.coffeetime.LoginActivity;
import com.coffeetime.R;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainWarkopActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    Warkop warkop;
    Endpoints endpoints;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_warkop);

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
}
