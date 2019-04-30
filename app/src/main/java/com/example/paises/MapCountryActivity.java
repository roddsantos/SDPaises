package com.example.paises;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.paises.Model.Country;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapCountryActivity extends FragmentActivity implements OnMapReadyCallback {

    private Country country;
    private List<Country> list = RegionListActivity.listCountries;
    private int getActivity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_country);
        country = (Country) getIntent().getSerializableExtra("country");
        getActivity = (int) getIntent().getSerializableExtra("setActivity");
        if(getActivity == 1) setTitle("Map");
        else setTitle(country.name);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng countryLatlgn;
        CameraPosition  pos;
        if(getActivity == 1){
            for(Country c : list){
                countryLatlgn = new LatLng(Double.parseDouble(c.latlng.get(0)), Double.parseDouble(c.latlng.get(1)));
                googleMap.addMarker(new MarkerOptions().position(countryLatlgn).title(c.name));
                pos = new CameraPosition(countryLatlgn,20,0,0);

            }
        }

        else{
            countryLatlgn = new LatLng(Double.parseDouble(country.latlng.get(0)), Double.parseDouble(country.latlng.get(1)));
            googleMap.addMarker(new MarkerOptions().position(countryLatlgn).title(country.name));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(countryLatlgn, 12.0f));
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
}
