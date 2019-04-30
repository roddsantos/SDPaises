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


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private Adapter adpt;
    private List<Country> listCountries;
    private ListView listView;
    private SwipeRefreshLayout swiperefresh;
    Repository db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Repository(getBaseContext());

        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swiperefresh.setColorScheme(R.color.colorPrimary,R.color.colorAccent);
        swiperefresh.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.listView);

        listCountries = new ArrayList<Country>();

        adpt = new Adapter(this, listCountries);

        getDataRetrofit2();

        listView.setAdapter(adpt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //hasPermission();

                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                intent.putExtra("country",listCountries.get(position));
                startActivity(intent);
            }
        });
    }


    /**
     * Extrai dados da API, caso o app esteja conectado
     * com a internet; caso contrario, extrai dados do
     * banco de dados local
     */
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
                            listCountries.add(country);
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
        listCountries.addAll(db.listCountries());
        adpt.notifyDataSetChanged();
    }

    /**
     *  Trata permissões de localização
     */
    void hasPermission(){
        //Pede permissão de localização
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
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

    @Override
    public void onRefresh() {
        getDataRetrofit2();
    }



}
