package com.coffeetime;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.coffeetime.client.MainClientActivity;
import com.coffeetime.model.User;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;
import com.coffeetime.warkop.MainWarkopActivity;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    Button btnlogin;
    EditText emailText, passwordText;

    Endpoints endpoints;
    User user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnlogin);

        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("coffee", 0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void login(View view) {

        user = new User();
        user.setEmail(emailText.getText().toString());
        user.setPassword(passwordText.getText().toString());

        endpoints = Connection.getEndpoints(this);
        endpoints.login(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                int tipeUser = Integer.parseInt(user.getTipeUser());
                Log.i ("WEW",user.getIdWarkop());
                Gson gson = new Gson();

                String json = gson.toJson(user);
                editor.putString("user",json);

                switch (tipeUser) {
                    case 0:
                        editor.putInt("login", 0);

                        Intent intent = new Intent(LoginActivity.this, MainWarkopActivity.class);
                        intent.putExtra("user",user);
                        startActivity(intent);
                        break;

                    case 1:
                        editor.putInt("login", 1);

                        Intent intent2 = new Intent(LoginActivity.this, MainClientActivity.class);
                        intent2.putExtra("user",user);
                        startActivity(intent2);
                        break;
                }
                editor.commit();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        //startActivity(new Intent(LoginActivity.this, MainClientActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
