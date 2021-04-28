package tala.mubarki.talafinalproject17.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tala.mubarki.talafinalproject17.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ShopsFragment extends Fragment {
    private ListView Lstv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        Lstv= view.findViewById(R.id.lstvShops);
       return view;


    }
}