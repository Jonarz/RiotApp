package com.lol.jona.loltest.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lol.jona.loltest.Constants;
import com.lol.jona.loltest.MySingleton;
import com.lol.jona.loltest.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jona on 13-10-2015.
 */
public class DetailFragment extends android.support.v4.app.Fragment {
    JSONObject data;
    NetworkImageView mNetworkImageView;
    ImageLoader mImageLoader;
    WebView webView;
    public DetailFragment(JSONObject champion){
    data=champion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(
                R.layout.fragment_otro, container, false);

        webView=(WebView) rootView.findViewById(R.id.webView);
        mNetworkImageView = (NetworkImageView) rootView.findViewById(R.id.backImg);
        mImageLoader = MySingleton.getInstance(getActivity()).getImageLoader();
        getLore();

        return rootView;
    }


   private void getLore(){
       try {
           String nombre=data.getString("name");
            mNetworkImageView.setImageUrl(Constants.LOAD_IMG + nombre + "_0.jpg", mImageLoader);
            webView.loadDataWithBaseURL(null, "<font color='white'><center>"+data.getString("lore")+"</center>", "text/html", "utf-8", null);
            webView.setBackgroundColor(Color.TRANSPARENT);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
