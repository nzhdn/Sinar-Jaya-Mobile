package com.nzh.n4.sinarjaya.model;

import com.google.gson.annotations.SerializedName;

public class Agenbus {
    @SerializedName("id_agen")
    private String idAgenbus;

    @SerializedName("nama_agen")
    private String namaAgenbus;

    @SerializedName("gambar_agen")
    private String gambarAgenbus;

    @SerializedName("deskripsi_agen")
    private String deskripsiAgenbus;

    @SerializedName("alamat_agen")
    private String alamatAgenbus;

    @SerializedName("latitude_agen")
    private String latitudeAgenbus;

    @SerializedName("longitude_agen")
    private String longitudeAgenbus;

    public String getIdAgenbus() {
        return idAgenbus;
    }
    public void setIdAgenbus(String idAgenbus){
        this.idAgenbus = idAgenbus;
    }


    public String getNamaAgenbus() {
        return namaAgenbus;
    }
    public void setNamaAgenbus(String namaAgenbus){
        this.namaAgenbus = namaAgenbus;
    }


    public String getGambarAgenbus() {
        return gambarAgenbus;
    }
    public void setGambarAgenbus(String gambarAgenbus){
        this.gambarAgenbus = gambarAgenbus;
    }


    public String getDeskripsiAgenbus() {
        return deskripsiAgenbus;
    }
    public void setAlamatAgenbus(String alamatAgenbus) {
        this.alamatAgenbus = alamatAgenbus;
    }



    public String getAlamatAgenbus() {
        return alamatAgenbus;
    }
    public void setDeskripsiAgenbus(String deskripsiAgenbus) {
        this.deskripsiAgenbus = deskripsiAgenbus;
    }



    public String getLatitudeAgenbus() {
        return latitudeAgenbus;
    }
    public void setLatitudeAgenbus(String latitudeAgenbus) {
        this.latitudeAgenbus = latitudeAgenbus;
    }



    public String getLongitudeAgenbus() {
        return longitudeAgenbus;
    }
    public void setLongitudeAgenbus(String longitudeAgenbus) {
        this.longitudeAgenbus = longitudeAgenbus;
    }
}
