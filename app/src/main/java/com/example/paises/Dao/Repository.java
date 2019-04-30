package com.example.paises.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.paises.Model.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Repository {
    private SQLHelper helper;
    private SQLiteDatabase db;

    public Repository(Context ctx){
        helper = new SQLHelper(ctx);
    }

    public long insert(Country country){
        db = helper.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(SQLHelper.COLUMN_NAME, country.name);
        cv.put(SQLHelper.COLUMN_ALPHA3CODE, country.alpha3Code);
        cv.put(SQLHelper.COLUMN_CALLINGCODES, Country.arrayStringToString(country.callingCodes));
        cv.put(SQLHelper.COLUMN_CAPITAL, country.capital);
        cv.put(SQLHelper.COLUMN_REGION, country.region);
        cv.put(SQLHelper.COLUMN_SUBREGION, country.subregion);
        cv.put(SQLHelper.COLUMN_POPULATION, country.population);
        cv.put(SQLHelper.COLUMN_LATLGN, Country.arrayStringToString(country.latlng));
        cv.put(SQLHelper.COLUMN_AREA, country.area);
        cv.put(SQLHelper.COLUMN_GINI, country.gini);
        cv.put(SQLHelper.COLUMN_DEMONYN, country.demonyn);
        cv.put(SQLHelper.COLUMN_TIMEZONES, Country.arrayStringToString(country.timezones));
        cv.put(SQLHelper.COLUMN_BORDERS, Country.arrayStringToString(country.borders));
        cv.put(SQLHelper.COLUMN_NUMERICCODE, country.numericCode);
        cv.put(SQLHelper.COLUMN_CURRENCIES, Country.arrayStringToString(country.currencies));
        cv.put(SQLHelper.COLUMN_LANGUAGES, Country.arrayStringToString(country.languages));

        long id = db.insert(SQLHelper.TABLE_COUNTRY, null, cv);

        if(id != -1){
            country.id = id;
        }
        db.close();
        return id;
    }

    public void excluirAll(){
        db = helper.getWritableDatabase();
        db.delete(SQLHelper.TABLE_COUNTRY, null, null);
        db.close();
    }

    public List<Country> listCountriesRegion(String reg){
        String sql;

        if(reg.equals("Northern America") || reg.equals("South America") || reg.equals("Cental America"))
        { sql = "SELECT * FROM " + SQLHelper.TABLE_COUNTRY + " WHERE subregion='" + reg + "'"; }
        else sql = "SELECT * FROM " + SQLHelper.TABLE_COUNTRY + " WHERE region='" + reg + "'";
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Country> list = new ArrayList();
        getCountryInfo(cursor, list);
        return list;
    }

    public List<Country> listCountries() {
        String sql = "SELECT * FROM " + SQLHelper.TABLE_COUNTRY;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Country> list = new ArrayList();
        getCountryInfo(cursor, list);
        return list;
    }

    public void getCountryInfo (Cursor cursor, List<Country> list){
        while (cursor.moveToNext()) {
            long id = cursor.getLong(
                    cursor.getColumnIndex(SQLHelper.COLUMN_ID));

            String name = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_NAME));

            String alpha3Code;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_ALPHA3CODE)).isEmpty()) {alpha3Code = "";}
            else alpha3Code = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_ALPHA3CODE));

            String area;
            if(SQLHelper.COLUMN_AREA == null) {area = "0";}
            else area = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_AREA));

            String gini;
            if(SQLHelper.COLUMN_GINI ==  null){gini = "0";}
            else gini = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_GINI));

            ArrayList<String> borders;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_BORDERS)).isEmpty()) {borders = null;}
            else borders = new ArrayList<String>(Arrays.asList(cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_BORDERS)).split(", ")));

            ArrayList<String> callingCodes;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_CALLINGCODES)).isEmpty()) {callingCodes = null;}
            else callingCodes = new ArrayList<String>(Arrays.asList(cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_CALLINGCODES)).split(", ")));

            String capital;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_CAPITAL)).isEmpty()) {capital = "";}
            else capital = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_CAPITAL));

            ArrayList<String> currencies;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_CURRENCIES)).isEmpty()) {currencies = null;}
            else currencies = new ArrayList<String>(Arrays.asList(cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_CURRENCIES)).split(", ")));

            String demonyn;
            if(SQLHelper.COLUMN_DEMONYN == null) {demonyn = "";}
            else demonyn = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_DEMONYN));

            ArrayList<String> languages;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_LANGUAGES)).isEmpty()) {languages = null;}
            else languages = new ArrayList<String>(Arrays.asList(cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_LANGUAGES)).split(", ")));

            ArrayList<String> latlng;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_LATLGN)).equals("")) {latlng = null;}
            else latlng = new ArrayList<String>(Arrays.asList(cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUMN_LATLGN)).split(", ")));

            String numericCode;
            if(SQLHelper.COLUMN_NUMERICCODE == null) {numericCode = "0";}
            else numericCode = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_NUMERICCODE));

            String population;
            if(SQLHelper.COLUMN_POPULATION ==  null) {population = "0";}
            else population = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_POPULATION));

            String region;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_REGION)).isEmpty()) {region = "";}
            else region = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_REGION));

            String subregion;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_SUBREGION)).isEmpty()) {subregion = "";}
            else subregion = cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_SUBREGION));

            ArrayList<String> timezones;
            if(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_TIMEZONES)).isEmpty()) {timezones = null;}
            else timezones = new ArrayList<String>(Arrays.asList(cursor.getString(cursor.getColumnIndex(SQLHelper.COLUMN_TIMEZONES)).split(", ")));

            Country country = new Country(name, alpha3Code, callingCodes, capital, region, subregion,
                    population, latlng, demonyn, area, gini, timezones, borders,
                    numericCode, currencies, languages);
            list.add(country);
        }
        cursor.close();
    }

}

