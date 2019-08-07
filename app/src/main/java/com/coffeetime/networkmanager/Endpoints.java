package com.coffeetime.networkmanager;

import com.coffeetime.model.Kopi;
import com.coffeetime.model.Pembayaran;
import com.coffeetime.model.Pemesanan;
import com.coffeetime.model.User;
import com.coffeetime.model.Warkop;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Endpoints {

    //user
    @POST("user.php?aksi=register")
    Call<ResponseBody> aadUser(@Body User user);

    @POST("user.php?aksi=login")
    Call<User> login (@Body User user);

    @GET("user.php?aksi=tampiluser")
    Call<User> getUser();

    //warkop
    @POST("warkop.php?aksi=inputwarkop")
    Call<ResponseBody> aadWarkop(@Body Warkop warkop);

    @POST("warkop.php?aksi=tampilwarkop")
    Call<Warkop> getWarkop(@Body Warkop warkop);

    @POST("warkop.php?aksi=tampilwarkopid")
    Call<Warkop> getWarkopId(@Body Warkop warkop);

    @GET("warkop.php?aksi=tampilwarkops")
    Call<List<Warkop>> getWarkops();

    //kopi
    @POST("kopi.php?aksi=inputkopi")
    Call<ResponseBody> aadKopi(@Body Kopi kopi);

    @POST("kopi.php?aksi=tampilkopi")
    Call<List<Kopi>> getKopi(@Body Kopi kopi);

    @GET("kopi.php?aksi=tampilkopis")
    Call<List<Kopi>> getKopis();

    @GET("kopi.php?aksi=tampilkopijadi")
    Call<List<Kopi>> getKopiJadi();

    @GET("kopi.php?aksi=tampilkopibubuk")
    Call<List<Kopi>> getKopiBubuk();

    @GET("kopi.php?aksi=tampilkopibiji")
    Call<List<Kopi>> getKopiBiji();

    //pemesanan
    @POST("pemesanan.php?aksi=inputpemesanan")
    Call<ResponseBody> addPemesanan(@Body Pemesanan pemesanan);

    @GET("pemesanan.php?aksi=tampilpemesanan")
    Call<Pemesanan> getPemesanan();

    //pembayaran
    @POST("pembayaran.php?aksi=inputpembayaran")
    Call<ResponseBody> addPembayaran(@Body Pembayaran pembayaran);

    @GET("pembayaran.php?aksi=tampilpembayaran")
    Call<Pembayaran> getPembayaran();
}
