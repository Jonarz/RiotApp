package com.lol.jona.loltest.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lol.jona.loltest.fragments.DetailFragment;
import com.lol.jona.loltest.fragments.SkillsFragment;
import com.lol.jona.loltest.fragments.SkinsFragment;

import org.json.JSONObject;

/**
 * Created by Jona on 13-10-2015.
 */
public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    JSONObject champ;
    public DemoCollectionPagerAdapter(FragmentManager fm,JSONObject data) {
        super(fm);
        champ=data;
    }

    @Override
    public Fragment getItem(int i) {

       Fragment fragment=null;
        Bundle args = new Bundle();
        switch (i){
            case 2: fragment =new DetailFragment(champ);
                    //args.putString(DemoObjectFragment.ARG_OBJECT,"Lore");
                    break;
            case 0 :fragment =new SkillsFragment(champ);
                   // args.putString(DemoObjectFragment.ARG_OBJECT,"Skills");
                break;
            case 1: fragment =new SkinsFragment(champ);
                    //args.putString(DemoObjectFragment.ARG_OBJECT,"Skins");
                break;
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 2: return "Lore";

            case 0: return "Skills";

            case 1:return "Skins";


        }
        return "error";
}

}
