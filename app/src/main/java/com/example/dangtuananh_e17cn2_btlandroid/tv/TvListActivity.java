package com.example.dangtuananh_e17cn2_btlandroid.tv;

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
import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.adapter.TelevisionAdapter;
import com.example.dangtuananh_e17cn2_btlandroid.laptop.LaptopListActivity;
import com.example.dangtuananh_e17cn2_btlandroid.model.Television;
import com.example.dangtuananh_e17cn2_btlandroid.phone.PhoneListActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TvListActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<Television> dataArrayList;
    TelevisionAdapter televisionAdapter;
    Television television;
    String name;
    int count = 0;
    EditText edSearch;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_list);
        btnSearch = findViewById(R.id.btnSearch);
        edSearch = findViewById(R.id.edSearch);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Television");
        listView = (ListView) findViewById(R.id.tvList);
        dataArrayList = new ArrayList<>();
        televisionAdapter = new TelevisionAdapter(TvListActivity.this, dataArrayList);
        listView.setAdapter(televisionAdapter);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edSearch.getText().toString().trim();
                databaseReference.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ++count;
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            television = dataSnapshot1.getValue(Television.class);
                            dataArrayList.clear();
                            dataArrayList.add(television);
                            Log.d("log", "onDataChange: " + dataSnapshot1.child("name").getValue());

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
                    television = dataSnapshot1.getValue(Television.class);
                    dataArrayList.add(television);
                }
                televisionAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void func() {

        if (count != 0) {
            televisionAdapter = new TelevisionAdapter(getApplicationContext(), dataArrayList);
            listView.setAdapter(televisionAdapter);
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
                startActivity(new Intent(TvListActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mLaptop:
                startActivity(new Intent(TvListActivity.this, LaptopListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mPhone:
                startActivity(new Intent(TvListActivity.this, PhoneListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mExit:
                startActivity(new Intent(TvListActivity.this, ItemListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
        return false;
    }
}