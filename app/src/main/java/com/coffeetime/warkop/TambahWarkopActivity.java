package com.coffeetime.warkop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeetime.R;
import com.coffeetime.model.User;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahWarkopActivity extends Activity {
    LayoutInflater inflater;
    EditText nama_warkopText, nama_pemilikText, cp_warkopText, waktu_bukaText;
    TextView alamat_warkopText;
    String stxtaddresplace;
    LatLng latLng ;
    float latitude;
    float longitude;

    Endpoints endpoints;
    Warkop warkop;
    User user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    private Place place_Picker;
    private static int Place_Picker_Request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_warkop);

        nama_warkopText = findViewById(R.id.nama_warkop);
        nama_pemilikText = findViewById(R.id.nama_pemilik);
        cp_warkopText = findViewById(R.id.cp_warkop);
        waktu_bukaText = findViewById(R.id.waktu_buka);
        alamat_warkopText = findViewById(R.id.alamat_warkop);

        sharedPreferences = TambahWarkopActivity.this.getSharedPreferences("coffee", 0);
        editor = sharedPreferences.edit();
        editor.apply();

        Gson gson = new Gson();
        String json = sharedPreferences.getString("user","");

        user = gson.fromJson(json, new TypeToken<User>(){

        }.getType());
    }

    public void tambahWarkop(View view) {

        warkop = new Warkop();
        warkop.setNamaWarkop(nama_warkopText.getText().toString());
        warkop.setNamaPemilik(nama_pemilikText.getText().toString());
        warkop.setCpWarkop(cp_warkopText.getText().toString());
        warkop.setWaktuBuka(waktu_bukaText.getText().toString());
        warkop.setAlamatWarkop(alamat_warkopText.getText().toString());
        warkop.setAlamatWarkopLatitude(String.valueOf(latitude));
        warkop.setAlamatWarkopLongitude(String.valueOf(longitude));

        //ambil data dari tabel user
        warkop.setIdUser(user.getIdUser());

        //request connection
        endpoints = Connection.getEndpoints(this);
        endpoints.aadWarkop(warkop).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("sukses")) {
                        String id_warkop = jsonObject.getString("id");
                        String id_user = user.getIdUser();
                        editor.putString("id_warkop",id_warkop);
                        editor.putString("id_user",id_user);
                        editor.commit();
                        startActivity(new Intent(TambahWarkopActivity.this, MainWarkopActivity.class));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void pickAlamatWarkop(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            startActivityForResult(intentBuilder.build(TambahWarkopActivity.this),5);
        } catch (GooglePlayServicesNotAvailableException e) {
            Toast.makeText(TambahWarkopActivity.this, "Google Play Services is not available.",
                    Toast.LENGTH_LONG)
                    .show();
        } catch (GooglePlayServicesRepairableException e) {
            GooglePlayServicesUtil
                    .getErrorDialog(e.getConnectionStatusCode(), TambahWarkopActivity.this, 0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("masuk onresult", "onresult");
        Log.i("result   ", "request code "+requestCode+","+"result"+resultCode+"data"+data);
        super.onActivityResult(requestCode,resultCode,data);

        // menangkap hasil balikan dari Place Picker, dan menampilkannya pada TextView
        if (requestCode == 5 && resultCode == RESULT_OK) {

            place_Picker = PlacePicker.getPlace(TambahWarkopActivity.this,data);

            stxtaddresplace = String.format("%s", place_Picker.getAddress().toString());
            latLng = place_Picker.getLatLng();
            latitude = (float) latLng.latitude;
            longitude = (float) latLng.longitude;
            alamat_warkopText.setText(""+ stxtaddresplace);

            }
        }
}