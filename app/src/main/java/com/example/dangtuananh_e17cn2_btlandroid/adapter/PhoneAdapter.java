package com.example.dangtuananh_e17cn2_btlandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.model.Phone;

import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {
    TextView  tvName, tvManu, tvYear, tvPrice;
    Context context;
    ArrayList<Phone> phone;
    LayoutInflater inflater;

    public PhoneAdapter(Context context, ArrayList<Phone> phone) {
        this.context = context;
        this.phone = phone;
    }

    @Override
    public int getCount() {
        return phone.size();
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
        view = inflater.from(context).inflate(R.layout.phone_card,viewGroup,false);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvManu = (TextView) view.findViewById(R.id.tvManu);
        tvYear = (TextView) view.findViewById(R.id.tvYear);
        tvPrice = (TextView) view.findViewById(R.id.tvPrice);

        tvName.setText(tvName.getText()+phone.get(i).getName());
        tvManu.setText(tvManu.getText()+phone.get(i).getManufacturer());
        tvYear.setText(tvYear.getText()+""+ phone.get(i).getYear());
        tvPrice.setText(tvPrice.getText()+""+phone.get(i).getPrice());

        return view;
    }
}
