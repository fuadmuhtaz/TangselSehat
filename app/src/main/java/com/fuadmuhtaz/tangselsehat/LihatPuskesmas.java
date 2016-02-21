package com.fuadmuhtaz.tangselsehat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fuadmuhtaz.tangselsehat.databasemanager.DatabaseManager;
import com.fuadmuhtaz.tangselsehat.model.ModelPuskesmas;

import java.util.ArrayList;

public class LihatPuskesmas extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ModelPuskesmas puskesmas;
    private ArrayList listFasilitas;
    private LayoutInflater inflater;
    private View vHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_puskesmas);

        puskesmas = new ModelPuskesmas();
        databaseManager = new DatabaseManager(getApplicationContext());
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vHolder = inflater.inflate(R.layout.daftar_fasilitas_row, null);

        puskesmas = databaseManager.getPuskesmas(LihatDaftarPuskesmas.puskesmasDipilih);
        listFasilitas = databaseManager.getFasilitas("puskesmas", LihatDaftarPuskesmas.puskesmasDipilih);

        ImageView foto = (ImageView) findViewById(R.id.foto_puskesmas);
        TextView namaPuskesmas = (TextView) findViewById(R.id.nama_puskesmas);
        TextView alamat = (TextView) findViewById(R.id.alamat_puskesmas);
        LinearLayout fasilitas = (LinearLayout) findViewById(R.id.fasilitas_puskesmas);

        foto.setImageBitmap(puskesmas.getFoto());
        namaPuskesmas.setText("Nama : " + puskesmas.getPuskesmas());
        alamat.setText("Alamat : " + puskesmas.getAlamat());

        TextView[] nama_fasilitas = new TextView[listFasilitas.size()];

        for(int i=0; i<listFasilitas.size(); i++){
            nama_fasilitas[i] = new TextView(this);
            nama_fasilitas[i].setText(i+1+". "+listFasilitas.get(i).toString());
            fasilitas.addView(nama_fasilitas[i]);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lihat_puskesmas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void lihatPeta(View view){
        if(puskesmas.getLatitude()!=null){
            Intent intent = new Intent(this, LihatPeta.class);
            intent.putExtra("jenis","puskesmas");
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Peta puskesmas belum tersedia", Toast.LENGTH_SHORT).show();
        }

    }
}
