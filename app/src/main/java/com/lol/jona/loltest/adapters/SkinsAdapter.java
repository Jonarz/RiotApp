package com.lol.jona.loltest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lol.jona.loltest.Constants;
import com.lol.jona.loltest.MySingleton;
import com.lol.jona.loltest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jona on 13-10-2015.
 */
public class SkinsAdapter extends ArrayAdapter<JSONObject> {

    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    JSONArray data;
    Context ctx;
    String nombre;

    public SkinsAdapter(Context context, JSONArray array,String name) {
        super(context, R.layout.list_row);
        data=array;
        ctx=context;
        nombre=name;
    }

    @Override
    public int getCount() {
        return data.length()-1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        position=position+1;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.skins_row, parent, false);
        String nombreSkin=null;
        try {
            JSONObject item=data.getJSONObject(position);
            nombreSkin=item.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView textView=(TextView) view.findViewById(R.id.nombreSkin);
        textView.setText(nombreSkin);
        mNetworkImageView = (NetworkImageView) view.findViewById(R.id.networkImageViewSkins);
        mImageLoader = MySingleton.getInstance(ctx).getImageLoader();
        String url= Constants.CHAMP_SPLASH+nombre+"_"+position+".jpg";
        mNetworkImageView.setImageUrl(url, mImageLoader);
        return view;
    }
}
