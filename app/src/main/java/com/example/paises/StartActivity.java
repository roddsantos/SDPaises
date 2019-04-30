package com.example.paises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;

public class StartActivity extends AppCompatActivity {

    private ImageButton btnWorld;
    private ImageButton btnRegions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnWorld = findViewById(R.id.btnListCountries);

        btnWorld.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegions = findViewById(R.id.btnListRegions);

        btnRegions.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(StartActivity.this, RegionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
