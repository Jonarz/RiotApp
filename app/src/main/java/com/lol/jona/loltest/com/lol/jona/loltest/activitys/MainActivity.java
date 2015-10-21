package com.lol.jona.loltest.com.lol.jona.loltest.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lol.jona.loltest.Constants;
import com.lol.jona.loltest.R;
import com.lol.jona.loltest.adapters.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    ListView list;
    /*TextView mTextView;
    TextView mTextView2;
*/
    //String imgUrl=Constants.IMG_URL;
    //String url =Constants.API_URL2;
    NetworkImageView img;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        list =(ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(this);
        // Request a string response
        getFreeToPlayChamps();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    public void getFreeToPlayChamps() {
        // Request a string response from the provided URL.
        JsonObjectRequest  stringRequest = new JsonObjectRequest(Request.Method.GET, Constants.API_ALL_CHAMPS,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject  response) {
                        JSONArray champions=null;
                        JSONObject champion=null;
                        try {
                            champion = response.getJSONObject("data");
                            champions= champion.toJSONArray(champion.names());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ListAdapter adapter=new ListAdapter(MainActivity.this,champions);
                        list.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Error al descargar La informacion de los campeones",Toast.LENGTH_LONG).show();
            }
        });
        //Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        img= (NetworkImageView) view.findViewById(R.id.networkImageView);
        Intent intent=new Intent(this, DetailActivity.class);
        intent.putExtra("id", img.getTag().toString());
        startActivity(intent);
    }
}
