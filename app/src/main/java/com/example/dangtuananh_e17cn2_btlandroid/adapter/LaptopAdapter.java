package com.example.dangtuananh_e17cn2_btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.model.Laptop;

import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    TextView  tvName, tvManu, tvYear, tvPrice;
    Context context;
    Fragment fragment;
    ArrayList<Laptop> laptop;
    LayoutInflater inflater;

    public LaptopAdapter(Context context, ArrayList<Laptop> laptop) {
        this.context = context;
        this.laptop = laptop;
    }
    public LaptopAdapter(Fragment fragment,ArrayList<Laptop> laptop ) {
        this.fragment = fragment;
        this.laptop = laptop;
    }

    @Override
    public int getCount() {
        return laptop.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.from(context).inflate(R.layout.laptop_card,viewGroup,false);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvManu = (TextView) view.findViewById(R.id.tvManu);
        tvYear = (TextView) view.findViewById(R.id.tvYear);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);

        tvName.setText(tvName.getText()+laptop.get(i).getName());
        tvManu.setText(tvManu.getText()+laptop.get(i).getManufacturer());
        tvYear.setText(tvYear.getText()+""+ laptop.get(i).getYear());
        tvPrice.setText(tvPrice.getText()+""+laptop.get(i).getPrice());

        return view;
    }
}
