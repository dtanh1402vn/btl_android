package com.example.dangtuananh_e17cn2_btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dangtuananh_e17cn2_btlandroid.laptop.LaptopListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.phone.PhoneListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.tv.TvListActivity;

public class ItemListActivity extends AppCompatActivity {
    Button btnListLaptop, btnListPhone, btnListTv, btnListExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        btnListLaptop = findViewById(R.id.btnListLaptop);
        btnListPhone = findViewById(R.id.btnListPhone);
        btnListTv = findViewById(R.id.btnListTv);
        btnListExit = findViewById(R.id.btnListExit);
        btnListLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ItemListActivity.this, LaptopListActivity.class);
                startActivity(t);
            }
        });
        btnListPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ItemListActivity.this, PhoneListActivity.class);
                startActivity(t);
            }
        });
        btnListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ItemListActivity.this, TvListActivity.class);
                startActivity(t);
            }
        });
        btnListExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ItemListActivity.this, MainActivity.class);
                startActivity(t);
            }
        });
    }
}