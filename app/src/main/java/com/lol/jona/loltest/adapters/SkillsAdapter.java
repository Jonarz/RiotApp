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
public class SkillsAdapter extends ArrayAdapter<JSONObject> {

    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    JSONArray data;
    Context ctx;

    public SkillsAdapter(Context context, JSONArray array) {
        super(context, R.layout.list_row);
        data=array;
        ctx=context;
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // position=position+1;
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.skills_row, parent, false);
        String nombreSkin=null;
        String nombre=null;
        String description=null;
        String nom_url=null;
        JSONObject passive=null;
        JSONObject item=null;
        try {
            item=data.getJSONObject(position);
            passive=item.getJSONObject("image");
            nombre = item.getString("name");
            description =item.getString("sanitizedDescription");
            nom_url=passive.getString("full");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView textNombre=(TextView) view.findViewById(R.id.skillNom);
        TextView textDesc=(TextView) view.findViewById(R.id.skillDesc);
        textNombre.setText(nombre);
        textDesc.setText(description);
        mNetworkImageView = (NetworkImageView) view.findViewById(R.id.skillImg);
        mImageLoader = MySingleton.getInstance(ctx).getImageLoader();
        String url=null;
        if(item.has("key")){
            url= Constants.SKILL_IMG+nom_url;
        }else {
            url = Constants.PASIV_SPELL + nom_url;
            textNombre.setText(nombre + " (pasiva)");
            //view.setBackgroundColor(Color.YELLOW);
        }

        mNetworkImageView.setImageUrl(url, mImageLoader);

        /*TextView textView=(TextView) view.findViewById(R.id.nombreSkin);
        textView.setText(nombreSkin);
        mNetworkImageView = (NetworkImageView) view.findViewById(R.id.networkImageViewSkins);
        mImageLoader = MySingleton.getInstance(ctx).getImageLoader();
        String url= Constants.CHAMP_SPLASH+nombre+"_"+position+".jpg";
        mNetworkImageView.setImageUrl(url, mImageLoader);*/
        return view;
    }
}