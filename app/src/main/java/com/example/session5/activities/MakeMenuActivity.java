package com.example.session5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.session5.R;

public class MakeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_menu);
        Button btnList = (Button) findViewById(R.id.btn_list);
        Button btnRecycler = (Button) findViewById(R.id.btn_recycler);

        buttonList(btnList);
        buttonRecycler(btnRecycler);

    }


    public void buttonList(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakeMenuActivity.this, ListView.class);
                startActivity(intent);
            }
        });
    }

    public void buttonRecycler(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MakeMenuActivity.this, PokemonRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}