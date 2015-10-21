package com.lol.jona.loltest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lol.jona.loltest.R;
import com.lol.jona.loltest.adapters.SkinsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jona on 13-10-2015.
 */
public class SkinsFragment extends android.support.v4.app.Fragment{

    JSONObject champ;
    ListView listView;
    public SkinsFragment(JSONObject data){
    champ=data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_skins, container, false);
        listView=(ListView) rootView.findViewById(R.id.listView2);
        getSkins();
        return rootView;
    }

    private void getSkins(){
        JSONArray array=null;
        JSONObject data=null;
        String nombre=null;
        try {
            nombre=champ.getString("name");
            array=champ.getJSONArray("skins");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SkinsAdapter skinsAdapter=new SkinsAdapter(getActivity().getApplicationContext(),array,nombre);
        listView.setAdapter(skinsAdapter);

    }
}
