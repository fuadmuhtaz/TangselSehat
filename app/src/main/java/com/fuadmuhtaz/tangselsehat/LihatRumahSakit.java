package com.fuadmuhtaz.tangselsehat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.fuadmuhtaz.tangselsehat.model.ModelRumahSakit;

import java.util.ArrayList;

public class LihatRumahSakit extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ModelRumahSakit rumahSakit;
    private ArrayList listFasilitas;
    private LayoutInflater inflater;
    private View vHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_rumah_sakit);

        rumahSakit = new ModelRumahSakit();
        databaseManager = new DatabaseManager(getApplicationContext());
        inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vHolder = inflater.inflate(R.layout.daftar_fasilitas_row, null);

        rumahSakit = databaseManager.getRumahSakit(LihatDaftarRumahSakit.rumahSakitDipilih);
        listFasilitas = databaseManager.getFasilitas("rumahsakit", LihatDaftarRumahSakit.rumahSakitDipilih);

        ImageView foto = (ImageView) findViewById(R.id.foto_rumah_sakit);
        TextView namaPuskesmas = (TextView) findViewById(R.id.nama_rumah_sakit);
        TextView alamat = (TextView) findViewById(R.id.alamat_rumah_sakit);
        LinearLayout fasilitas = (LinearLayout) findViewById(R.id.fasilitas_rumah_sakit);

        foto.setImageBitmap(rumahSakit.getFoto());
        namaPuskesmas.setText("Nama : " + rumahSakit.getRumahsakit());
        alamat.setText("Alamat : " + rumahSakit.getAlamat());

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
        getMenuInflater().inflate(R.menu.menu_lihat_rumah_sakit, menu);
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
        if(rumahSakit.getLatitude()!=null){
            Intent intent = new Intent(this, LihatPeta.class);
            intent.putExtra("jenis","rumahsakit");
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Peta rumah sakit belum tersedia", Toast.LENGTH_SHORT).show();
        }

    }
}
