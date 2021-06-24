package tala.mubarki.talafinalproject17.Fragments.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tala.mubarki.talafinalproject17.Fragments.MapsFragment;
import tala.mubarki.talafinalproject17.Fragments.SearchFragment;
import tala.mubarki.talafinalproject17.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private MapsFragment mapsFragment;
    private SearchFragment searchFragment;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_search,R.string.tab_Map};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
       if(position==0){
           if(searchFragment==null)
           {
              searchFragment=new SearchFragment();
           }
           return searchFragment;
       }
        if(position==1){
            if(mapsFragment==null)
            {
                mapsFragment=new MapsFragment();
            }
            return mapsFragment;
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
        // Show 3 total pages.
        return 2;
    }
}