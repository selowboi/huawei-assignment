package com.example.session5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.session5.R;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        TextView tvListView = findViewById(R.id.tv_list_view);
        Intent intent = getIntent();
        String capitalCity = intent.getStringExtra("CAPITAL_CITY");
        tvListView.setText(capitalCity);
    }
}