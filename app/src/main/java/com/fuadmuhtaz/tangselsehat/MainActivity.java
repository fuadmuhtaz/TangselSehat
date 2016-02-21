package com.fuadmuhtaz.tangselsehat;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    public static AssetManager assetManager;
    public static Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getAssets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void lihatTentangAplikasi(View view){
        Intent intent = new Intent(this, LihatTentangAplikasi.class);
        startActivity(intent);
    }

    public void lihatDaftarPuskesmas(View view){
        Intent intent = new Intent(this, LihatDaftarPuskesmas.class);
        startActivity(intent);
    }

    public void lihatDaftarRumahSakit(View view){
        Intent intent = new Intent(this, LihatDaftarRumahSakit.class);
        startActivity(intent);
    }
}
