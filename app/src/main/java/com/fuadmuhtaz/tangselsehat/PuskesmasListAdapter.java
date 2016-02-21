package com.fuadmuhtaz.tangselsehat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuadmuhtaz.tangselsehat.model.ModelPuskesmas;
import com.fuadmuhtaz.tangselsehat.databasemanager.DatabaseManager;

import java.util.ArrayList;

/**
 * Created by fmuhtaz on 2/6/16.
 */
public class PuskesmasListAdapter extends BaseAdapter {

    DatabaseManager databaseManager;
    ArrayList<ModelPuskesmas> modelPuskesmasList;
    LayoutInflater inflater;
    Context _context;

    public PuskesmasListAdapter(Context context){

        modelPuskesmasList = new ArrayList<ModelPuskesmas>();
        _context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseManager = new DatabaseManager(_context);
        modelPuskesmasList = databaseManager.getDaftarPuskesmas();

    }

    @Override
    public int getCount() {
        return modelPuskesmasList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelPuskesmasList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vHolder;
        if(view == null){
            view = inflater.inflate(R.layout.daftar_puskesmas_row, null);
            vHolder = new ViewHolder();

            vHolder.idpsks = (TextView) view.findViewById(R.id.idpsks);
            vHolder.puskesmas = (TextView) view.findViewById(R.id.nama_puskesmas);
            vHolder.alamat = (TextView) view.findViewById(R.id.alamat_puskesmas);
            vHolder.foto = (ImageView) view.findViewById(R.id.foto_puskesmas);
            view.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) view.getTag();
        }

        ModelPuskesmas puskesmasObj = modelPuskesmasList.get(i);

        vHolder.idpsks.setText(String.valueOf(puskesmasObj.getId()));
        vHolder.puskesmas.setText(puskesmasObj.getPuskesmas());
        vHolder.alamat.setText(puskesmasObj.getAlamat());
        vHolder.foto.setImageBitmap(puskesmasObj.getFoto());

        return view;
    }

    class ViewHolder{
        ImageView foto;
        TextView idpsks, puskesmas, alamat;
    }
}
