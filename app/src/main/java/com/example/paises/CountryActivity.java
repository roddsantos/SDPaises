package com.example.paises;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paises.Model.Country;

public class CountryActivity extends AppCompatActivity {
    private Country country;
    private ImageButton btnLocation;
    private int setActivity = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        country = (Country) getIntent().getSerializableExtra("country");
        setTitle(country.name);

        TextView tCountry = (TextView) findViewById(R.id.textCountry);
        TextView tCapital = (TextView) findViewById(R.id.textCapital2);
        TextView tArea = (TextView) findViewById(R.id.textArea2);
        TextView tPop = (TextView) findViewById(R.id.textPop2);
        TextView tGini = (TextView) findViewById(R.id.textGini2);
        TextView tRegion = (TextView) findViewById(R.id.textRegion2);
        TextView tSubregion = (TextView) findViewById(R.id.textSubreg2);
        TextView tAlpha3Code = (TextView) findViewById(R.id.textAlpha3Codes2);
        TextView tCallCodes = (TextView) findViewById(R.id.textCallCodes2);
        TextView tLanguages = (TextView) findViewById(R.id.textLanguages2);
        TextView tCurrencies = (TextView) findViewById(R.id.textCurrencies2);
        TextView tBorders = (TextView) findViewById(R.id.textBorders2);
        TextView tTimezones = (TextView) findViewById(R.id.textTimezones2);
        TextView tDemonym = (TextView) findViewById(R.id.textDemonyn2);
        TextView tNumCodes = (TextView) findViewById(R.id.textNumericCodes2);
        TextView tLatLgn = (TextView) findViewById(R.id.textLatLgn2);

        tCountry.setText(country.name);
        tCapital.setText(country.capital);
        tArea.setText(country.area);
        tPop.setText(country.population);
        tGini.setText(country.gini);
        tRegion.setText(country.region);
        tSubregion.setText(country.subregion);
        tAlpha3Code.setText(country.alpha3Code);
        tCallCodes.setText(Country.arrayStringToString(country.callingCodes));
        tLanguages.setText(Country.arrayStringToString(country.languages));
        tCurrencies.setText(Country.arrayStringToString(country.currencies));
        tBorders.setText(Country.arrayStringToString(country.borders));
        tTimezones.setText(Country.arrayStringToString(country.timezones));
        tDemonym.setText(country.demonyn);
        tNumCodes.setText(country.numericCode);
        tLatLgn.setText(Country.arrayStringToString(country.latlng));

        btnLocation = findViewById(R.id.btnLocationMap);

        btnLocation.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {

                hasPermission();
                if(isConnected()){
                    Toast.makeText(this,"No connection, please wait...", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(CountryActivity.this, MapCountryActivity.class);
                intent.putExtra("country", country);
                intent.putExtra("setActivity", setActivity);
                startActivity(intent);
            }
        });

    }

    void hasPermission(){
        //Pede permissão de localização
        if (ContextCompat.checkSelfPermission(CountryActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CountryActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(CountryActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
    }

    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }

}
