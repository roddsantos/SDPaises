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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paises.Dao.Repository;
import com.example.paises.Model.Country;
import com.example.paises.Util.Adapter;
import com.example.paises.Util.HttpRetrofit2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private Adapter adpt;
    public static List<Country> listCountries;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;
    private String reg;
    private Button btnLRegionMap;
    private int setActivity = 1;
    //private int reg =  (int) getIntent().getSerializableExtra("regionIndex");

    Repository db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_list);

        db = new Repository(getBaseContext());
        reg =  (String) getIntent().getSerializableExtra("regionIndex");
        setTitle(reg);

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swiperefresh.setColorScheme(R.color.colorPrimary,R.color.colorAccent);
        swiperefresh.setOnRefreshListener(this);

        btnLRegionMap = (Button) findViewById(R.id.btnRegionMap);
        listView = (ListView) findViewById(R.id.listView);
        listCountries = new ArrayList<Country>();
        adpt = new Adapter(this, listCountries);

        getDataRetrofit2();

        listView.setAdapter(adpt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RegionListActivity.this, CountryActivity.class);
                intent.putExtra("country",listCountries.get(position));
                startActivity(intent);
            }
        });

        btnLRegionMap.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasPermission();

                Intent intent = new Intent(RegionListActivity.this, MapCountryActivity.class);
                intent.putExtra("setActivity",setActivity);
                startActivity(intent);
            }
        });

    }

    private void getDataRetrofit2(){
        swiperefresh.setRefreshing(true);

        if (isConnected()){
            HttpRetrofit2.getCountryClient().getCountry().enqueue(new Callback<List<Country>>() {
                public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                    if (response.isSuccessful()) {
                        List<Country> bodyCountry = response.body();
                        listCountries.clear();

                        db.excluirAll();
                        for (Country country : bodyCountry) {
                            if(country.region.equals(reg) || country.subregion.equals(reg)){
                                listCountries.add(country);
                            }
                            db.insert(country);
                        }
                        adpt.notifyDataSetChanged();
                    } else {
                        System.out.println(response.errorBody());
                    }
                    swiperefresh.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<List<Country>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else{
            swiperefresh.setRefreshing(false);
            Toast.makeText(this,"No connection, please wait...", Toast.LENGTH_SHORT).show();
            getDataSqlite();
        }

    }
    private void getDataSqlite() {
        listCountries.clear();
        listCountries.addAll(db.listCountriesRegion(reg));
        adpt.notifyDataSetChanged();
    }

    public Boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( cm != null ) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }

    void hasPermission(){
        //Pede permissão de localização
        if (ContextCompat.checkSelfPermission(RegionListActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegionListActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(RegionListActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
    }

    public void onRefresh() {
        getDataRetrofit2();
    }
}
