package tala.mubarki.talafinalproject17.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ShopsFragment extends Fragment {
    private ListView lstv;
    private MyShopsAdaptor adaptor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        lstv= view.findViewById(R.id.lstvShops);
        lstv.setAdapter(adaptor);
        readTasksFromFirebase(null);
       return view;
    }

    private void readTasksFromFirebase(final String stTosearch) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getUid();
        DatabaseReference reference=database.getReference();
        reference.child("All Shops").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adaptor.clear();
                for (DataSnapshot d : snapshot.getChildren())
                {
                    Shop t=d.getValue(Shop.class);
                    Log.d("My Shops",t.toString());
                    if(stTosearch==null|| stTosearch.length()==0){
                        if(t.isCompleted()==false){
                            adaptor.add(t);
                        }
                    }
                    else
                        if (t.getName().contains(stTosearch))
                        {
                            if(t.isCompleted()==false){
                                adaptor.add(t);
                            }
                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}