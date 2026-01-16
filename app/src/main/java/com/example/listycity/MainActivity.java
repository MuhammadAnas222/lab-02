package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selected_pos = position;
        });
    }

    int selected_pos = -1;

    public void add(View add_button) {
        // Creating an input field for entry
        final android.widget.EditText input = new android.widget.EditText(this);
        input.setHint("Enter city name");

        // Build dialog
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Add City")
                .setView(input)
                .setPositiveButton("CONFIRM", (dialog, which) -> {

                    String cityName = input.getText().toString().trim();

                    if (!cityName.isEmpty()) {
                        dataList.add(cityName);
                        cityAdapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    public void delete(View delete_button) {
        if (selected_pos != -1) {
            dataList.remove(selected_pos);
            selected_pos = -1;
            cityAdapter.notifyDataSetChanged();
        }

    }
}