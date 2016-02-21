package com.fuadmuhtaz.tangselsehat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuadmuhtaz.tangselsehat.databasemanager.DatabaseManager;
import com.fuadmuhtaz.tangselsehat.model.ModelPuskesmas;
import com.fuadmuhtaz.tangselsehat.model.ModelRumahSakit;

import java.util.ArrayList;

/**
 * Created by fmuhtaz on 2/6/16.
 */
public class RumahSakitListAdapter extends BaseAdapter {

    DatabaseManager databaseManager;
    ArrayList<ModelRumahSakit> modelRumahSakitList;
    LayoutInflater inflater;
    Context _context;

    public RumahSakitListAdapter(Context context){

        modelRumahSakitList = new ArrayList<ModelRumahSakit>();
        _context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        databaseManager = new DatabaseManager(_context);
        modelRumahSakitList = databaseManager.getDaftarRumahSakit();

    }

    @Override
    public int getCount() {
        return modelRumahSakitList.size();
    }

    @Override
    public Object getItem(int i) {
        return modelRumahSakitList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vHolder;
        if(view == null){
            view = inflater.inflate(R.layout.daftar_rumah_sakit_row, null);
            vHolder = new ViewHolder();

            vHolder.idrs = (TextView) view.findViewById(R.id.idrs);
            vHolder.rumahSakit = (TextView) view.findViewById(R.id.nama_rumah_sakit);
            vHolder.alamat = (TextView) view.findViewById(R.id.alamat_rumah_sakit);
            vHolder.foto = (ImageView) view.findViewById(R.id.foto_rumah_sakit);
            view.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) view.getTag();
        }

        ModelRumahSakit rumahSakitObj = modelRumahSakitList.get(i);

        vHolder.idrs.setText(String.valueOf(rumahSakitObj.getId()));
        vHolder.rumahSakit.setText(rumahSakitObj.getRumahsakit());
        vHolder.alamat.setText(rumahSakitObj.getAlamat());
        vHolder.foto.setImageBitmap(rumahSakitObj.getFoto());

        return view;
    }

    class ViewHolder{
        ImageView foto;
        TextView idrs, rumahSakit, alamat;
    }
}
