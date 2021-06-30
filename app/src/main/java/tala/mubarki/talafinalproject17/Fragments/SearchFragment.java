package tala.mubarki.talafinalproject17.Fragments;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.MyUI.HotestShopsActivity;
import tala.mubarki.talafinalproject17.MyUI.ShopDetailsActivity;
import tala.mubarki.talafinalproject17.R;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ImageButton imgbtnHOTEST,imgbtnProfile;
    private TextView tvTitle;
    private EditText etLocation,etAdress;
    private Button btnFind,btnSearch,btnSecond,btnLocation;
    private Spinner spinner_shops;
    private MyShopsAdaptor adaptor;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        btnFind=view.findViewById(R.id.btnFind1);

//        adaptor=new MyShopsAdaptor(getContext(),R.layout.item_shop_view1);

        etAdress=view.findViewById(R.id.EtAdress);
        spinner_shops=view.findViewById(R.id.spinner_shops);
        imgbtnHOTEST=view.findViewById(R.id.imgbtnHotst);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),
                R.array.kind,
                android.R.layout.simple_spinner_item);

        spinner_shops.setAdapter(adapter);
        imgbtnHOTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), HotestShopsActivity.class);
                startActivity(intent);
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String s= ( String ) spinner_shops.getSelectedItem();
                readTasksFromFirebase(s);


            }
        });
        spinner_shops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==7){
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                }
//                if (spinner_shops.getSelectedItem()){
//
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return view;
    }
//?
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedShops=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(),selectedShops,Toast.LENGTH_SHORT).show();
    }
//??
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    private void readTasksFromFirebase(final String stTosearch ) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        reference.child("AllShops").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adaptor.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Shop t = d.getValue(Shop.class);
                    Log.d("MyShops", t.toString());
                    if (stTosearch == null || stTosearch.length() == 0) {
                        {
                            if(stTosearch.indexOf(t.getName())>0)
                            {
                                Intent intent=new Intent(getContext(), ShopDetailsActivity.class);
                                intent.putExtra("Shop", ( Parcelable ) t);
                                getContext().startActivity(intent);
                            }

                        }
                    } else if (t.getName().contains(stTosearch)) {
                        {
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