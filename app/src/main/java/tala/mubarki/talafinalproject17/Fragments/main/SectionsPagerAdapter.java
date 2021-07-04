package tala.mubarki.talafinalproject17.Fragments.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tala.mubarki.talafinalproject17.Fragments.MapsFragment;
import tala.mubarki.talafinalproject17.Fragments.HotestShopsFragment;
import tala.mubarki.talafinalproject17.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private MapsFragment mapsFragment1;
    private HotestShopsFragment hotestShopsFragment;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.shops,R.string.map};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            if(hotestShopsFragment ==null)
            {
                hotestShopsFragment =new HotestShopsFragment();
            }
            return hotestShopsFragment;
        }
        if(position==1){
            if(mapsFragment1==null)
            {
                mapsFragment1=new MapsFragment();
            }
            return mapsFragment1;
//
        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}