package com.codingblocks.googlemaps;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void search(View view){
        List<Address> Addresslist = null;
        EditText etplace=(EditText)findViewById(R.id.etplacename);
        String location=etplace.getText().toString();
        if (location!=null && location!=""){
        Geocoder geocoder=new Geocoder(this);
            try {
             Addresslist= geocoder.getFromLocationName(location,1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address=Addresslist.get(0);
            LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        }}


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng delhi = new LatLng(28.69, 77.14);
        mMap.addMarker(new MarkerOptions().position(delhi).title("Marker in Saddi Dilli"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(delhi, 10));
        mMap.addCircle(new CircleOptions()
                .center(delhi)
                .radius(20)
                .fillColor(Color.argb(50, 50, 50, 150)));

        mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(27, 77))
                .add(new LatLng(28, 78))
                .add(new LatLng(28, 78))
                .add(new LatLng(29, 77))
                .strokeColor(Color.RED)
                .fillColor(Color.argb(90, 150, 50, 50)));
    }
}
