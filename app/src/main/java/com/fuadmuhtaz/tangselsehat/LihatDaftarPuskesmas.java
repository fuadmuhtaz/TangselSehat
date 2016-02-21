package com.fuadmuhtaz.tangselsehat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class LihatDaftarPuskesmas extends AppCompatActivity {

    public static int puskesmasDipilih = 0;

    private PuskesmasListAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_daftar_puskesmas);

        cAdapter = new PuskesmasListAdapter(this);
        ListView listView = (ListView) findViewById(R.id.daftar_puskesmas);
        listView.setAdapter(cAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView idpsks = (TextView) view.findViewById(R.id.idpsks);
                int id = Integer.valueOf(idpsks.getText().toString());
                puskesmasDipilih = id;
                lihatPuskesmas();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lihat_daftar_puskesmas, menu);
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

    private void lihatPuskesmas(){
        Intent intent = new Intent(this, LihatPuskesmas.class);
        startActivity(intent);
    }
}
