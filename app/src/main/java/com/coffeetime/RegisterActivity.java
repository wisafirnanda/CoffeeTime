package com.coffeetime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeetime.model.User;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity {

    Button btndaftar;
    EditText namaText, emailText, phoneText, passwordText;
    TextView masuk;

    private ProgressDialog progressDialog;

    Endpoints endpoints;
    User user;

    String nama, email, phone, password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);

        btndaftar = findViewById(R.id.btndaftarwarkop);
        masuk = findViewById(R.id.login);

        namaText = findViewById(R.id.nama);
        emailText = findViewById(R.id.email);
        phoneText = findViewById(R.id.phone);
        passwordText = findViewById(R.id.password);

    }

    public void daftarclient(View view) {
        validasi();
        isidata("1");
    }

    public void daftarwarkop(View view) {
        validasi();
        isidata("0");
    }

    public void login(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    private void isidata(String tipe_user){
        user = new User();
        user.setNama(namaText.getText().toString());
        user.setEmail(emailText.getText().toString());
        user.setNoHp(phoneText.getText().toString());
        user.setPassword(passwordText.getText().toString());
        user.setTipeUser(tipe_user);

        //request connection
        endpoints = Connection.getEndpoints(this);
        endpoints.aadUser(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("sukses")) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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

    public void validasi (){
        nama = namaText.getText().toString();
        email = emailText.getText().toString();
        phone = phoneText.getText().toString();
        password = passwordText.getText().toString();

        if (TextUtils.isEmpty(nama)){
            Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty (email)){
            Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Nomor tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
