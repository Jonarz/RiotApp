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
 * Created by Jona on 11-10-2015.
 */
public class ListAdapter extends ArrayAdapter<JSONObject> {

    ImageLoader mImageLoader;
    NetworkImageView mNetworkImageView;
    JSONArray data;
    Context ctx;

    public ListAdapter(Context context, JSONArray array) {
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
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(R.layout.list_row, parent, false);
        mNetworkImageView = (NetworkImageView) view.findViewById(R.id.networkImageView);
        mImageLoader = MySingleton.getInstance(ctx).getImageLoader();


        JSONObject item = null;
        try {
            item = (JSONObject) data.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView txtNombre=(TextView) view.findViewById(R.id.nombreRow);
        TextView txtTitulo=(TextView) view.findViewById(R.id.tituloRow);
        JSONObject image=null;
        String imageName=null;
        String id=null;
        try {
            image =item.getJSONObject("image");
            imageName=image.getString("full");
            txtNombre.setText(item.getString("name"));
            txtTitulo.setText(item.getString("title"));
            id=item.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Constants.CHAMP_SPLASH+txtNombre.getText()+"_0.jpg";
        mNetworkImageView.setImageUrl(url, mImageLoader);
        mNetworkImageView.setTag(id);
        return view;
    }
}
