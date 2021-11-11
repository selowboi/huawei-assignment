package com.example.session5.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.session5.R;
import com.example.session5.adapter.PokemonAdapter;
import com.example.session5.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonRecyclerViewActivity extends AppCompatActivity implements PokemonAdapter.OnItemClickListener{

    ArrayList<Pokemon> pokemons = new ArrayList<>();
    RecyclerView rvCity;

    String url = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_recycler_view);
        rvCity = (RecyclerView) findViewById(R.id.rv_pokemon);
        getPokemons();;
        initAdapter();
    }

    public void getPokemons() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject jsonObject = results.getJSONObject(i);
                                String name = jsonObject.getString("name");
                                String url = jsonObject.getString("url");
                                pokemons.add(new Pokemon(name, url));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


    private void initAdapter() {
        PokemonAdapter cityAdapter = new PokemonAdapter(pokemons);
        rvCity.setAdapter(cityAdapter);
        rvCity.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(PokemonRecyclerViewActivity.this, ListView.class);
        Pokemon pokemon = pokemons.get(position);
        intent.putExtra("URL", pokemon.getUrl());
        startActivity(intent);
    }
}