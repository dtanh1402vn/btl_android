package com.example.dangtuananh_e17cn2_btlandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnListItem, btnAddNewItem, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddNewItem = findViewById(R.id.btnAddItem);
        btnLogout = findViewById(R.id.btnLogout);
        btnListItem = findViewById(R.id.btnListItem);
        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,AddItemActivity.class);
                startActivity(t);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(t);
            }
        });
        btnListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(MainActivity.this,ItemListActivity.class);
                startActivity(t);
            }
        });
    }
}