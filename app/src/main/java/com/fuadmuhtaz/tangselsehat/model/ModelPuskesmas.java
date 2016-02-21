package com.fuadmuhtaz.tangselsehat.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fuadmuhtaz.tangselsehat.MainActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by fmuhtaz on 2/5/16.
 */
public class ModelPuskesmas {

    private int id;
    private String puskesmas;
    private String alamat;
    private String longitude;
    private String latitude;
    private Bitmap foto;

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        try{
            foto = "images/"+foto;
            InputStream is = MainActivity.assetManager.open(foto);
            this.foto = BitmapFactory.decodeStream(is);
        } catch(IOException e){
            return;
        }
    }

    public String getPuskesmas() {
        return puskesmas;
    }

    public void setPuskesmas(String puskesmas) {
        this.puskesmas = puskesmas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
