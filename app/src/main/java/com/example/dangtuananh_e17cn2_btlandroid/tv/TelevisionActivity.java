package com.example.dangtuananh_e17cn2_btlandroid.tv;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dangtuananh_e17cn2_btlandroid.AddItemActivity;
import com.example.dangtuananh_e17cn2_btlandroid.MainActivity;
import com.example.dangtuananh_e17cn2_btlandroid.R;
import com.example.dangtuananh_e17cn2_btlandroid.adapter.TelevisionAdapter;
import com.example.dangtuananh_e17cn2_btlandroid.laptop.LaptopActivity;
import com.example.dangtuananh_e17cn2_btlandroid.model.Television;
import com.example.dangtuananh_e17cn2_btlandroid.phone.PhoneActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TelevisionActivity extends AppCompatActivity {
    LayoutInflater inflater1;
    int count = 0;
    String name, manu;
    int year, price;
    EditText edName, edManu, edYear, edPrice, edSearch;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Television television;
    ListView listView;
    ArrayList<Television> dataArrayList;
    TelevisionAdapter televisionAdapter;
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Television");
        edName = findViewById(R.id.edName);
        edManu = findViewById(R.id.edManu);
        edYear = findViewById(R.id.edYear);
        edPrice = findViewById(R.id.edPrice);
        listView = findViewById(R.id.readlist);
        edSearch = findViewById(R.id.edSearch);
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    name = (edName.getText().toString().trim());
                    if (TextUtils.isEmpty(name)) {
                        Toast.makeText(getApplicationContext(), "Chưa nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
                    } else {
                        manu = (edManu.getText()).toString().trim();
                        year = Integer.parseInt(edYear.getText().toString().trim());
                        price = Integer.parseInt(edPrice.getText().toString().trim());
                        television = new Television(databaseReference.push().getKey(), name, manu, year, price);
                        databaseReference.child(television.getKey()).setValue(television);
                        Toast.makeText(getApplicationContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                        edName.setText("");
                        edManu.setText("");
                        edYear.setText("");
                        edPrice.setText("");
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dataArrayList = new ArrayList<>();
        televisionAdapter = new TelevisionAdapter(TelevisionActivity.this, dataArrayList);
        listView.setAdapter(televisionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                final View v = inflater1.from(getApplicationContext()).inflate(R.layout.television_action, null);
                temp = i;
                final EditText updtName, updtManu, updtYear, updtPrice;
                updtName = (EditText) v.findViewById(R.id.updtName);
                updtManu = (EditText) v.findViewById(R.id.updtManu);
                updtYear = (EditText) v.findViewById(R.id.updtYear);
                updtPrice = (EditText) v.findViewById(R.id.updtPrice);
                final AlertDialog.Builder builder = new AlertDialog.Builder(TelevisionActivity.this).setView(v);
                final AlertDialog alert = builder.create();
                v.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Television tempTelevision = new Television(dataArrayList.get(temp).getKey(), updtName.getText().toString().trim(),
                                updtManu.getText().toString().trim(),
                                Integer.parseInt(updtYear.getText().toString().trim()),
                                Integer.parseInt(updtPrice.getText().toString().trim()));
                        databaseReference.child(dataArrayList.get(temp).getKey()).setValue(tempTelevision);
                        dataArrayList.remove(temp);
                        dataArrayList.add(temp, tempTelevision);
                        televisionAdapter.notifyDataSetChanged();
                    }

                });
                v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (temp == -1) {
                            Toast.makeText(getApplicationContext(), "Không có gì để xóa", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child(dataArrayList.get(temp).getKey()).removeValue();
                            dataArrayList.remove(temp);
                            televisionAdapter.notifyDataSetChanged();
                            alert.cancel();
                            temp = -1;
                        }
                    }
                });

                updtName.setText(dataArrayList.get(temp).getName());
                updtManu.setText(dataArrayList.get(temp).getManufacturer());
                updtYear.setText("" + dataArrayList.get(temp).getYear());
                updtPrice.setText("" + dataArrayList.get(temp).getPrice());
                try {
                    alert.show();
                } catch (Exception e) {
                    Log.d("show", "onItemClick: " + e);
                }
                return;
            }
        });
        findViewById(R.id.btnSearch).setOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(TelevisionActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mLaptop:
                startActivity(new Intent(TelevisionActivity.this, LaptopActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mPhone:
                startActivity(new Intent(TelevisionActivity.this, PhoneActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
            case R.id.mExit:
                startActivity(new Intent(TelevisionActivity.this, AddItemActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
        return false;
    }
}