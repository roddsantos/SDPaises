package com.example.paises.Util;

import com.example.paises.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class HttpRetrofit2 {

    private static final String BASE_URL = "https://restcountries.eu/";

    public static CountryInterface getCountryClient(){
        Retrofit rAdapt = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return rAdapt.create(CountryInterface.class);
    }

    public interface CountryInterface{
        @GET("rest/v1/all")
        Call<List<Country>> getCountry();
    }
}
