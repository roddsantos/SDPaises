package com.example.paises.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paises.Model.Country;
import com.example.paises.R;

import java.util.List;

public class Adapter extends ArrayAdapter<Country> {
    public Adapter(Context context, List<Country> objects){
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Country country = getItem(position);
        if (convertView  == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_country, parent, false);
        }

        TextView tName = (TextView) convertView.findViewById(R.id.name);
        TextView tRegion = (TextView) convertView.findViewById(R.id.region);
        TextView tAlpha3Code = (TextView) convertView.findViewById(R.id.alpha3code);
        TextView tCapital = (TextView) convertView.findViewById(R.id.capital);

        tName.setText(country.name);
        tRegion.setText(country.region);
        tAlpha3Code.setText(country.alpha3Code);
        tCapital.setText(country.capital);

        return convertView;
    }
}
