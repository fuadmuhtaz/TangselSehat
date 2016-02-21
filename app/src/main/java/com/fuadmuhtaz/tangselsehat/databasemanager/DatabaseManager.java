package com.fuadmuhtaz.tangselsehat.databasemanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fuadmuhtaz.tangselsehat.model.ModelPuskesmas;
import com.fuadmuhtaz.tangselsehat.model.ModelRumahSakit;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by fmuhtaz on 2/6/16.
 */
public class DatabaseManager{

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "tangselsehat.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public DatabaseManager(Context context){
        this.context = context;

        CustomSQLiteAssetHelper helper = new CustomSQLiteAssetHelper(context);
        this.db = helper.getReadableDatabase();
    }

    private class CustomSQLiteAssetHelper extends SQLiteAssetHelper{

        public CustomSQLiteAssetHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
    }

    public ArrayList<ModelPuskesmas> getDaftarPuskesmas(){

        ArrayList<ModelPuskesmas> arrayList = new ArrayList<ModelPuskesmas>();
        Cursor cursor;
        ModelPuskesmas puskesmas;
        try{

            String queryStatement = "select idpsks, foto, puskesmas, alamat from tblpuskesmas order by puskesmas";

            cursor = db.rawQuery(queryStatement, null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do{
                    puskesmas = new ModelPuskesmas();
                    puskesmas.setId(cursor.getInt(0));
                    puskesmas.setFoto(cursor.getString(1));
                    puskesmas.setPuskesmas(cursor.getString(2));
                    puskesmas.setAlamat(cursor.getString(3));
                    arrayList.add(puskesmas);
                } while (cursor.moveToNext());
            }
        } finally {

        }

        return arrayList;
    }

    public ModelPuskesmas getPuskesmas(int id){

        ModelPuskesmas puskesmas = new ModelPuskesmas();
        Cursor cursor;
        try{

            String queryStatement = "select * from tblpuskesmas where idpsks="+id;
            cursor = db.rawQuery(queryStatement, null);
            if(cursor.moveToNext()){
                puskesmas.setId(cursor.getInt(0));
                puskesmas.setPuskesmas(cursor.getString(1));
                puskesmas.setAlamat(cursor.getString(2));
                puskesmas.setLatitude(cursor.getString(3));
                puskesmas.setLongitude(cursor.getString(4));
                puskesmas.setFoto(cursor.getString(5));
            }
        } finally {

        }

        return puskesmas;
    }

    public ArrayList<ModelRumahSakit> getDaftarRumahSakit(){

        ArrayList<ModelRumahSakit> arrayList = new ArrayList<ModelRumahSakit>();
        Cursor cursor;
        ModelRumahSakit rumahSakit;
        try{

            String queryStatement = "select idrs, foto, rumahsakit, alamat from tblrumahsakit";

            cursor = db.rawQuery(queryStatement, null);
            cursor.moveToFirst();
            if(!cursor.isAfterLast()){
                do{
                    rumahSakit = new ModelRumahSakit();
                    rumahSakit.setId(cursor.getInt(0));
                    rumahSakit.setFoto(cursor.getString(1));
                    rumahSakit.setRumahsakit(cursor.getString(2));
                    rumahSakit.setAlamat(cursor.getString(3));
                    arrayList.add(rumahSakit);
                } while (cursor.moveToNext());
            }
        } finally {

        }

        return arrayList;
    }

    public ModelRumahSakit getRumahSakit(int id){

        ModelRumahSakit rumahSakit = new ModelRumahSakit();
        Cursor cursor;
        try{

            String queryStatement = "select * from tblrumahsakit where idrs="+id;
            cursor = db.rawQuery(queryStatement, null);
            if(cursor.moveToNext()){
                rumahSakit.setId(cursor.getInt(0));
                rumahSakit.setRumahsakit(cursor.getString(1));
                rumahSakit.setAlamat(cursor.getString(2));
                rumahSakit.setLatitude(cursor.getString(3));
                rumahSakit.setLongitude(cursor.getString(4));
                rumahSakit.setFoto(cursor.getString(5));
            }
        } finally {

        }

        return rumahSakit;
    }

    public ArrayList getFasilitas(String type, int id){

        ArrayList fasilitas = new ArrayList();
        Cursor cursor;

        String queryStatement;
        if(type.equals("puskesmas")){
            queryStatement = "select fasilitas from tblpelayananpsks where id="+id;
        } else{
            queryStatement = "select fasilitas from tblpelayananrs where id="+id;
        }

        try{
            cursor = db.rawQuery(queryStatement, null);
            while(cursor.moveToNext()){
                fasilitas.add(cursor.getString(0));
            }
        } finally {

        }

        return fasilitas;
    }
}
