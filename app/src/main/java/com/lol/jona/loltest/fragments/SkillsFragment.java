package com.lol.jona.loltest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lol.jona.loltest.Constants;
import com.lol.jona.loltest.MySingleton;
import com.lol.jona.loltest.R;
import com.lol.jona.loltest.adapters.SkillsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jona on 13-10-2015.
 */
public class SkillsFragment extends android.support.v4.app.Fragment {

    JSONObject champ;
    ListView listView;
    NetworkImageView mNetworkImageView;
    ImageLoader mImageLoader;

    public SkillsFragment(JSONObject data){
        champ=data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_skills, container, false);

        listView=(ListView) rootView.findViewById(R.id.listView3);

        mNetworkImageView = (NetworkImageView) rootView.findViewById(R.id.skillsBack);
        mImageLoader = MySingleton.getInstance(getActivity()).getImageLoader();
        getSkills();


        return rootView;
    }



    private void getSkills(){
        JSONArray spells=null;
        JSONObject passive=null;
        String name=null;
        try {
            name=champ.getString("name");
            passive=champ.getJSONObject("passive");
            spells=champ.getJSONArray("spells");
            if(spells.length()<=4)
                spells.put(passive);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mNetworkImageView.setImageUrl(Constants.LOAD_IMG + name + "_0.jpg", mImageLoader);
        SkillsAdapter skillsAdapter=new SkillsAdapter(getActivity().getApplicationContext(),spells);
        listView.setAdapter(skillsAdapter);

    }
}
