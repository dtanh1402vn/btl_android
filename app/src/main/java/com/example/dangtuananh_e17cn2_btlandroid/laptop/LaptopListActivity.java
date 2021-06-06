package com.example.dangtuananh_e17cn2_btlandroid.laptop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dangtuananh_e17cn2_btlandroid.ItemListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.MainActivity;
import com.example.dangtuananh_e17cn2_btlandroid.phone.PhoneListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.tv.TvListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.adapter.LaptopAdapter;
import com.example.dangtuananh_e17cn2_btlandroid.model.Laptop;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LaptopListActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int count = 0;
    Laptop laptop;
    EditText edSearch;
    Button btnSearch;
    ListView listView;
    ArrayList<Laptop> dataArrayList;
    LaptopAdapter laptopAdapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_list);
        edSearch = findViewById(R.id.edSearch);
        btnSearch = findViewById(R.id.btnSearch);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Laptop");
        listView = (ListView) findViewById(R.id.laptopList);
        dataArrayList = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(LaptopListActivity.this, dataArrayList);
        listView.setAdapter(laptopAdapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edSearch.getText().toString().trim();
                databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ++count;
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            laptop = dataSnapshot1.getValue(Laptop.class);
                            dataArrayList.clear();
                            dataArrayList.add(laptop);
                        }
                        func();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        realtimeUpdate();
    }

    public void realtimeUpdate() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataArrayList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    laptop = dataSnapshot1.getValue(Laptop.class);
                    dataArrayList.add(laptop);
                }
                laptopAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void func() {

        if (count != 0) {
            laptopAdapter = new LaptopAdapter(getApplicationContext(), dataArrayList);
            listView.setAdapter(laptopAdapter);
        } else {
            Toast.makeText(getApplicationContext(), "Không có dữ liệu", Toast.LENGTH_SHORT).show();
            listView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mHome:
                startActivity(new Intent(LaptopListActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mPhone:
                startActivity(new Intent(LaptopListActivity.this, PhoneListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mTv:
                startActivity(new Intent(LaptopListActivity.this, TvListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mExit:
                startActivity(new Intent(LaptopListActivity.this, ItemListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
        return false;
    }
}