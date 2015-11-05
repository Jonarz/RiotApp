package com.lol.jona.loltest.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class SkillsFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {

    JSONObject champ;
    ListView listView;
    NetworkImageView mNetworkImageView;
    ImageLoader mImageLoader;
    String cod;

    public SkillsFragment(JSONObject data){
        champ=data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_skills, container, false);

        listView=(ListView) rootView.findViewById(R.id.listView3);
        listView.setOnItemClickListener(this);

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
            cod=champ.getString("id");
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        position=position+2;
        if (position==6)
            position=1;

        String url="http://cdn.leagueoflegends.com/champion-abilities/videos/mp4/";
        String video="";
        if( cod.length()==2 ){
            video = url + "00" + cod + "_0" + position + ".mp4";}
        else{
            video = url + "0" + cod + "_0" + position + ".mp4";}
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video));
        intent.setDataAndType(Uri.parse(video), "video/mp4");
        startActivity(intent);
    }
}
