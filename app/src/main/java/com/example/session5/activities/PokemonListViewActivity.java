package com.example.session5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.session5.R;
import com.example.session5.models.Pokemon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PokemonListViewActivity extends AppCompatActivity {

    ListView lvPokemon;
    ArrayList<String> pokemons = new ArrayList<>();

    private static String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_list_view_content);
        lvPokemon = (ListView) findViewById(R.id.lv_pokemon);
        getPokemons();
        ArrayAdapter<String> pokemonArrayAdapter = new ArrayAdapter<>(this, R.layout.activity_pokemon_list_view, R.id.tv_title_list_view, pokemons);
        lvPokemon.setAdapter(pokemonArrayAdapter);
    }

    public void getPokemons() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                BASE_URL,
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
                                pokemons.add(name);
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
}