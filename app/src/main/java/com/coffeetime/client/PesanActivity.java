package com.coffeetime.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeetime.R;
import com.coffeetime.adapter.PesananClientAdapter;
import com.coffeetime.model.PesananClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class PesanActivity extends Activity {

    private RecyclerView recyclerView;
    private PesananClientAdapter adapter;
    private ArrayList<PesananClient> pesananClientArrayList;

    private TextView alamat_antarText;
    String stxtaddresplace;

    // konstanta untuk mendeteksi hasil balikan dari place picker
    private int Place_Picker_Request = 1;
    private Place place_Picker;
    private static int MY_PERMISSION_FINE_LOCATION = 1;
    LatLng latLng ;
    float latitude;
    float longitude;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        recyclerView = findViewById(R.id.recyclerview);

        addData();

        adapter = new PesananClientAdapter(pesananClientArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PesanActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        alamat_antarText = findViewById(R.id.alamat_antar);

    }

    void addData(){
        pesananClientArrayList = new ArrayList<>();
        pesananClientArrayList.add(new PesananClient("Wisa", "Sanger", "Kopi jadi", "x 2", "10.000"));
        pesananClientArrayList.add(new PesananClient("Nanda", "Arabica Gayo", "Bubuk", "x 1", "30.000"));
        pesananClientArrayList.add(new PesananClient("Wisa", "Sanger", "Kopi jadi", "x 2", "10.000"));
        pesananClientArrayList.add(new PesananClient("Nanda", "Arabica Gayo", "Bubuk", "x 1", "30.000"));

    }

    public void pickAlamatAntar(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            startActivityForResult(intentBuilder.build(PesanActivity.this), 5);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(PesanActivity.this, "Google Play Services is not available.",
                    Toast.LENGTH_LONG)
                    .show();
        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil
                    .getErrorDialog(e.getConnectionStatusCode(), PesanActivity.this, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("masuk onresult", "onresult");
        Log.i("result   ", "request code "+requestCode+","+"result"+resultCode+"data"+data);
        super.onActivityResult(requestCode,resultCode,data);

        // menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == 5 && resultCode == RESULT_OK) {

            place_Picker = PlacePicker.getPlace(PesanActivity.this,data);

            stxtaddresplace = String.format("%s", place_Picker.getAddress().toString());
            latLng = place_Picker.getLatLng();
            latitude = (float) latLng.latitude;
            longitude = (float) latLng.longitude;
            alamat_antarText.setText(""+ stxtaddresplace);
        }
    }
}
