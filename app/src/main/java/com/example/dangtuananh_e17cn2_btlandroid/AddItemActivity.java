package com.example.dangtuananh_e17cn2_btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dangtuananh_e17cn2_btlandroid.laptop.LaptopActivity;
import com.example.dangtuananh_e17cn2_btlandroid.phone.PhoneActivity;
import com.example.dangtuananh_e17cn2_btlandroid.tv.TelevisionActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AddItemActivity extends AppCompatActivity {
    Button btnLaptop, btnPhone, btnTv, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        btnLaptop = findViewById(R.id.btnAddLaptop);
        btnPhone = findViewById(R.id.btnAddPhone);
        btnExit = findViewById(R.id.btnAddExit);
        btnTv = findViewById(R.id.btnAddTv);

        btnLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(AddItemActivity.this, LaptopActivity.class);
                startActivity(t);
            }
        });
        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(AddItemActivity.this, PhoneActivity.class);
                startActivity(t);
            }
        });
        btnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(AddItemActivity.this, TelevisionActivity.class);
                startActivity(t);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent t = new Intent(AddItemActivity.this,MainActivity.class);
                startActivity(t);
                finish();
            }
        });
    }
}