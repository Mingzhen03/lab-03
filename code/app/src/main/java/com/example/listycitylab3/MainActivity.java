package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<City> dataList;
    private ArrayAdapter<City> cityAdapter;
    private ListView cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seedData();

        cityList = findViewById(R.id.city_list);
        cityAdapter = new ArrayAdapter<>(
                this,
                R.layout.content,
                R.id.content_view,
                dataList
        );
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City selected = dataList.get(position);
            EditCityFragment.newInstance(selected, position)
                    .show(getSupportFragmentManager(), "EditCityFragment");
        });
    }

    private void seedData() {
        dataList = new ArrayList<>();
        dataList.add(new City("Edmonton", "AB"));
        dataList.add(new City("Calgary", "AB"));
        dataList.add(new City("Vancouver", "BC"));
        dataList.add(new City("Toronto", "ON"));
        dataList.add(new City("Los Angeles", "CA"));
        dataList.add(new City("Denver", "CO"));
        dataList.add(new City("Hamilton", "ON"));
        dataList.add(new City("Banff", "AB"));
    }

    public void applyCityEdit(int position, String newName, String newProv) {
        if (position < 0 || position >= dataList.size()) {
            Toast.makeText(this, "Invalid position", Toast.LENGTH_SHORT).show();
            return;
        }
        City c = dataList.get(position);
        c.setName(newName);
        c.setProvince(newProv);

        cityAdapter.notifyDataSetChanged();
    }
}
