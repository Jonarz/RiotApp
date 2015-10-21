package com.lol.jona.loltest.com.lol.jona.loltest.activitys;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lol.jona.loltest.Constants;
import com.lol.jona.loltest.R;
import com.lol.jona.loltest.adapters.DemoCollectionPagerAdapter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jona on 12-10-2015.
 */
public class DetailActivity extends FragmentActivity {

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Bundle bun=getIntent().getExtras();
        String id=bun.getString("id");
        getChamp(id);

    }



    public void getChamp(String id) {
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url= Constants.API_CHAMP+id+Constants.CHAMP_DATA+Constants.API_KEY;
        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject  response) {
                        String nombre=null;
                        try {
                            nombre= response.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mDemoCollectionPagerAdapter =
                                new DemoCollectionPagerAdapter(
                                        getSupportFragmentManager(),response);
                        mViewPager = (ViewPager) findViewById(R.id.pager);
                        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailActivity.this, "Error al descargar La informacion de los campeones", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
