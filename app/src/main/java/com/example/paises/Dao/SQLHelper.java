package com.example.paises.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String NAME_BASE = "dbCountry";
    private static final int V_BASE = 1;
    public static final String TABLE_COUNTRY = "country_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ALPHA3CODE = "alpha3Code";
    public static final String COLUMN_CALLINGCODES = "callingCodes";
    public static final String COLUMN_CAPITAL = "capital";
    public static final String COLUMN_REGION = "region";
    public static final String COLUMN_SUBREGION = "subregion";
    public static final String COLUMN_POPULATION = "population";
    public static final String COLUMN_LATLGN = "latlgn";
    public static final String COLUMN_DEMONYN = "demonyn";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_GINI = "gini";
    public static final String COLUMN_TIMEZONES = "timezones";
    public static final String COLUMN_BORDERS = "borders";
    public static final String COLUMN_NUMERICCODE = "numericCode";
    public static final String COLUMN_CURRENCIES = "currencies";
    public static final String COLUMN_LANGUAGES = "languages";

    public SQLHelper(Context context) {
        super(context, NAME_BASE, null, V_BASE);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_COUNTRY + " ( " +
                        COLUMN_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_ALPHA3CODE + " TEXT, " +
                        COLUMN_CALLINGCODES + " TEXT, " +
                        COLUMN_CAPITAL + " TEXT, " +
                        COLUMN_REGION + " TEXT, " +
                        COLUMN_SUBREGION + " TEXT, " +
                        COLUMN_POPULATION + " TEXT, " +
                        COLUMN_LATLGN + " TEXT, " +
                        COLUMN_DEMONYN + " TEXT, " +
                        COLUMN_AREA + " TEXT, " +
                        COLUMN_GINI + " TEXT, " +
                        COLUMN_TIMEZONES + " TEXT, " +
                        COLUMN_BORDERS + " TEXT, " +
                        COLUMN_NUMERICCODE + " TEXT, " +
                        COLUMN_CURRENCIES + " TEXT, " +
                        COLUMN_LANGUAGES + " TEXT)"
        );

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as prÃ³ximas versÃµes
    }

}
