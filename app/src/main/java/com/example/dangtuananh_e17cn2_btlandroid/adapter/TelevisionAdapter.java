package com.example.dangtuananh_e17cn2_btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.model.Laptop;
import com.example.dangtuananh_e17cn2_btlandroid.model.Television;

import java.util.ArrayList;

public class TelevisionAdapter extends BaseAdapter {
    TextView  tvName, tvManu, tvYear, tvPrice;
    Context context;
    ArrayList<Television> television;
    LayoutInflater inflater;

    public TelevisionAdapter(Context context, ArrayList<Television> television) {
        this.context = context;
        this.television = television;
    }

    @Override
    public int getCount() {
        return television.size();
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
        view = inflater.from(context).inflate(R.layout.television_card,viewGroup,false);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvManu = (TextView) view.findViewById(R.id.tvManu);
        tvYear = (TextView) view.findViewById(R.id.tvYear);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);

        tvName.setText(tvName.getText()+television.get(i).getName());
        tvManu.setText(tvManu.getText()+television.get(i).getManufacturer());
        tvYear.setText(tvYear.getText()+""+ television.get(i).getYear());
        tvPrice.setText(tvPrice.getText()+""+television.get(i).getPrice());

        return view;
    }
}
