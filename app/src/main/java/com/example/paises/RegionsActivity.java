package com.example.paises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

public class RegionsActivity extends AppCompatActivity {
    private ImageButton btnAfrica;
    private ImageButton btnAsia;
    private ImageButton btnEurope;
    private ImageButton btnCentral;
    private ImageButton btnNorth;
    private ImageButton btnSouth;
    private ImageButton btnOceania;
    public static String regionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regions);
        setTitle("Continentes");

        btnAfrica = findViewById(R.id.btnAfrica);
        btnAfrica.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Africa";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });

        btnAsia = findViewById(R.id.btnAsia);
        btnAsia.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Asia";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
        btnEurope = findViewById(R.id.btnEurope);
        btnEurope.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Europe";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
        btnCentral = findViewById(R.id.btnCentralAmerica);
        btnCentral.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Central America";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
        btnNorth = findViewById(R.id.btnNorthAmerica);
        btnNorth.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Northern America";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
        btnSouth = findViewById(R.id.btnSouthAmerica);
        btnSouth.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "South America";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
        btnOceania = findViewById(R.id.btnOceania);
        btnOceania.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                regionIndex = "Oceania";
                Intent intent = new Intent(RegionsActivity.this, RegionListActivity.class);
                intent.putExtra("regionIndex",regionIndex);
                startActivity(intent);
            }
        });
    }
}
