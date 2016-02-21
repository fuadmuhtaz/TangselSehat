package com.fuadmuhtaz.tangselsehat;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fuadmuhtaz.tangselsehat.databasemanager.DatabaseManager;
import com.fuadmuhtaz.tangselsehat.model.ModelPuskesmas;
import com.fuadmuhtaz.tangselsehat.model.ModelRumahSakit;

import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.modules.MapTileAssetsProvider;
import org.osmdroid.tileprovider.modules.OfflineTileProvider;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.BoundingBoxE6;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.ResourceProxyImpl;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.File;
import java.util.ArrayList;

public class LihatPeta extends AppCompatActivity {

    private DatabaseManager databaseManager;
    private ModelPuskesmas puskesmas;
    private ModelRumahSakit rumahSakit;
    private ResourceProxy resourceProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_peta);

        Bundle extra = getIntent().getExtras();
        String jenis = extra.getString("jenis");

        Double latitude = 0.0;
        Double longitude = 0.0;

        databaseManager = new DatabaseManager(getApplicationContext());
        if(jenis.equals("puskesmas")){
            puskesmas = new ModelPuskesmas();
            puskesmas = databaseManager.getPuskesmas(LihatDaftarPuskesmas.puskesmasDipilih);

            latitude = Double.parseDouble(puskesmas.getLatitude());
            longitude = Double.parseDouble(puskesmas.getLongitude());
        } else {
            rumahSakit = new ModelRumahSakit();
            rumahSakit = databaseManager.getRumahSakit(LihatDaftarRumahSakit.rumahSakitDipilih);

            latitude = Double.parseDouble(rumahSakit.getLatitude());
            longitude = Double.parseDouble(rumahSakit.getLongitude());
        }

        resourceProxy = new ResourceProxyImpl(getApplicationContext());

        MapTileAssetsProvider mapTileAssetsProvider = new MapTileAssetsProvider(new SimpleRegisterReceiver(getApplicationContext()), MainActivity.assetManager);
        mapTileAssetsProvider.setTileSource(TileSourceFactory.MAPNIK);
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setScrollableAreaLimit(new BoundingBoxE6(-6.2008, 106.8376, -6.3774, 106.5755)); //nesw
        map.setUseDataConnection(false);
        map.setMaxZoomLevel(16);
        map.setMinZoomLevel(16);

        ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        if(jenis.equals("puskesmas")){
            items.add(new OverlayItem(puskesmas.getPuskesmas(),"",new GeoPoint(latitude,longitude)));
        } else {
            items.add(new OverlayItem(rumahSakit.getRumahsakit(),"",new GeoPoint(latitude,longitude)));
        }

        ItemizedOverlayWithFocus<OverlayItem> overlay = new ItemizedOverlayWithFocus<OverlayItem>(items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int i, OverlayItem overlayItem) {
                Toast.makeText(getApplicationContext(), overlayItem.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onItemLongPress(int i, OverlayItem overlayItem) {
                return false;
            }
        }, resourceProxy);
        overlay.setFocusItemsOnTap(true);
        map.getOverlays().add(overlay);

        IMapController iMapController = map.getController();
        iMapController.setZoom(16);
        iMapController.setCenter(new GeoPoint(latitude, longitude));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lihat_peta, menu);
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
}
