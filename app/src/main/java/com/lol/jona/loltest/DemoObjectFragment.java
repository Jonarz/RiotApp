package com.lol.jona.loltest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jona on 13-10-2015.
 */
public class DemoObjectFragment extends android.support.v4.app.Fragment {
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        Bundle args = getArguments();
        View rootView = inflater.inflate(
                R.layout.fragment_skills, container, false);

        /*((TextView) rootView.findViewById(R.id.text1)).setText(
                Integer.toString(args.getInt(ARG_OBJECT)));*/
        return rootView;
    }
}
