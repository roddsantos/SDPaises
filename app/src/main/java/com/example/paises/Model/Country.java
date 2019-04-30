package com.example.paises.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Country implements Serializable{
    public long id;

    @SerializedName("name")
    public String name;

    //@SerializedName("topLevelDomain")
    //public String topLevelDomain;

    //@SerializedName("alpha2Code")
    //public String alpha2Code;

    @SerializedName("alpha3Code")
    public String alpha3Code;

    @SerializedName("callingCodes")
    public ArrayList<String> callingCodes;

    @SerializedName("capital")
    public String capital;

    //@SerializedName("altSpellings")
    //public ArrayList<String> altSpellings;

    @SerializedName("region")
    public String region;

    @SerializedName("subregion")
    public String subregion;

    @SerializedName("population")
    public String population;

    @SerializedName("latlng")
    public ArrayList<String> latlng;

    @SerializedName("demonyn")
    public String demonyn;

    @SerializedName("area")
    public String area;

    @SerializedName("gini")
    public String gini;

    @SerializedName("timezones")
    public ArrayList<String> timezones;

    @SerializedName("borders")
    public ArrayList<String> borders;

    //@SerializedName("nativeName")
    //public String nativeName;

    @SerializedName("numericCode")
    public String numericCode;

    @SerializedName("currencies")
    public ArrayList<String> currencies;

    @SerializedName("languages")
    public ArrayList<String> languages;

    //@SerializedName("translations")
    //public ArrayList<String> translations;

    public Country(String name, String alpha3Code, ArrayList<String> callingCodes, String capital, String region, String subregion,
                   String population, ArrayList<String> latlgn, String demonyn, String area, String gini, ArrayList<String> timezones, ArrayList<String> borders,
                   String numericCode, ArrayList<String> currencies, ArrayList<String> languages){


        this.name = name;

        if(alpha3Code == "") { this.alpha3Code = ""; }
        else this.alpha3Code = alpha3Code;

        this.callingCodes = callingCodes;

        if(capital == "") { this.capital = ""; }
        else this.capital = capital;

        if(region == "") { this.region = ""; }
        else this.region = region;

        if(subregion == "") { this.subregion = ""; }
        else this.subregion = subregion;

        if(population == null) { this.population = "0"; }
        else this.population = population;

        if(latlgn == null) {this.latlng = null;}
        else this.latlng = latlgn;

        if(demonyn == "") { this.demonyn = ""; }
        else this.demonyn = demonyn;

        if(area == null) { this.area = "0"; }
        this.area = area;

        if(gini == null) { this.gini = "0"; }
        else this.gini = gini;

        if(timezones == null) { this.timezones = null; }
        this.timezones = timezones;

        if(borders == null) { this.borders = null; }
        else this.borders = borders;

        if(numericCode == null) { this.numericCode = "0"; }
        this.numericCode = numericCode;

        if(currencies == null) { this.currencies = null; }
        else this.currencies = currencies;

        if(languages  == null) { this.languages = null; }
        else this.languages = languages;
    }

    public String getName(){
        return name;
    }

    public String getAlpha3Code(){ return alpha3Code; }

    public ArrayList<String> getCallingCodes(){ return callingCodes; }

    public String getCapital(){ return capital; }

    public String getRegion(){  return region; }

    public String getSubregion(){ return subregion; }

    public String getArea() { return area; }

    public String getGini() { return gini; }

    public String getPopulation() { return population; }

    public ArrayList<String> getLatlng() { return latlng; }

    public ArrayList<String> getBorders() { return borders; }

    public String getDemonyn() { return demonyn; }

    public ArrayList<String> getTimezones() { return timezones; }

    public ArrayList<String> getLanguages() { return languages; }

    public ArrayList<String> getCurrencies() { return currencies; }

    public String getNumericCode() { return numericCode; }

    public static String arrayStringToString(ArrayList<String> a){
        String list = "";
        Iterator<String> i = a.iterator();
        while(i.hasNext()){
            list += i.next();
            if(i.hasNext()) list += ", ";
        }
        return list;
    }
}
