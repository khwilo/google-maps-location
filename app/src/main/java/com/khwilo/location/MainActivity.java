package com.khwilo.location;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
    GoogleMap map;

    //a specific location to display
    private static final LatLng GOLDEN_GATE_BRIDGE = new LatLng(37.828891,-122.485884);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        if(map == null){
            Toast.makeText(this, "Google Maps is not available", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.menu_sethybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case R.id.menu_showtraffic:
                map.setTrafficEnabled(true);
                break;
            case R.id.menu_zoomin:
                map.animateCamera(CameraUpdateFactory.zoomIn());
                break;
            case R.id.menu_zoomout:
                map.animateCamera(CameraUpdateFactory.zoomOut());
                break;
            case R.id.menu_gotolocation:
                CameraPosition cameraPosition = new CameraPosition.Builder().target(GOLDEN_GATE_BRIDGE)
                        .zoom(20) //set the zoom
                        .bearing(90) //set the orientation of the camera to east
                        .tilt(30) //set the tilt of the camera to 30 degrees
                        .build(); //creates a CameraPosition from the builder
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                break;
            case R.id.menu_addmarker:
                map.addMarker(new MarkerOptions()
                        .position(GOLDEN_GATE_BRIDGE)
                        .title("GOLDEN GATE BRIDGE")
                        .snippet("San Francisco").icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.andr)));
                break;
            case R.id.menu_getcurrentlocation:
                //get your current location and display a blue dot
                map.setMyLocationEnabled(true);
                break;
            case R.id.menu_showcurrentlocation:
                Location myLocation = map.getMyLocation();
                LatLng myLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                CameraPosition myPostion = new CameraPosition.Builder().target(myLatLng).zoom(20).bearing(90).tilt(30).build();
                map.animateCamera(CameraUpdateFactory.newCameraPosition(myPostion));
                break;
        }

        return true;
    }



}
