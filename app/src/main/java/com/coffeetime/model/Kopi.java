package com.coffeetime.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kopi {

    @SerializedName("id_kopi")
    @Expose
    private String idKopi;
    @SerializedName("nama_kopi")
    @Expose
    private String namaKopi;
    @SerializedName("harga_kopi")
    @Expose
    private String hargaKopi;
    @SerializedName("jenis_kopi")
    @Expose
    private String JenisKopi;
    @SerializedName("id_warkop")
    @Expose
    private String idWarkop;

    public String getIdKopi() {
        return idKopi;
    }

    public void setIdKopi(String idKopi) {
        this.idKopi = idKopi;
    }

    public String getNamaKopi() {
        return namaKopi;
    }

    public void setNamaKopi(String namaKopi) {
        this.namaKopi = namaKopi;
    }

    public String getHargaKopi() {
        return hargaKopi;
    }

    public void setHargaKopi(String hargaKopi) {
        this.hargaKopi = hargaKopi;
    }

    public String getJenisKopi() {
        return JenisKopi;
    }

    public void setJenisKopi(String JenisKopi) {
        this.JenisKopi = JenisKopi;
    }

    public String getIdWarkop() {
        return idWarkop;
    }

    public void setIdWarkop(String idWarkop) {
        this.idWarkop = idWarkop;
    }

}