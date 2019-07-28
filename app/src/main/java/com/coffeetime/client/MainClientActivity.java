package com.coffeetime.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.coffeetime.LoginActivity;
import com.coffeetime.R;

public class MainClientActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        // kita set default nya Home Fragment
        loadFragment(new HomeFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView navigation = findViewById(R.id.navigation);
        // beri listener pada saat item/menu bottomnavigation terpilih
        navigation.setOnNavigationItemSelectedListener(this);

        //mTextMessage = (TextView) findViewById(R.id.message);
        //client_nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        Bundle data = new Bundle();

        switch (item.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_riwayat:
                fragment = new RiwayatFragment();
                break;
            case R.id.navigation_profil:
                fragment = new ProfilClientFragment();
                data.putString("a","tes");
                fragment.setArguments(data);
                break;
        }   return loadFragment(fragment);
    }

    // method untuk load fragment yang sesuai
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }   return false;
    }

    public void biji(View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void bubuk(View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void kopijadi(View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void terfavorit(View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void terbaru (View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void terlaris (View view) {
        startActivity(new Intent(MainClientActivity.this, ListWarkopActivity.class));
    }

    public void Logout(View view) {
        startActivity(new Intent(MainClientActivity.this, LoginActivity.class));
    }
}
