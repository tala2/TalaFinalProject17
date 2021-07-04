package tala.mubarki.talafinalproject17.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.MyUI.SignInActivity;
import tala.mubarki.talafinalproject17.R;

public class HotestShopsFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private TextView tvTitle;
    private Spinner spinner_shops;
    //param for the selecting item in the spinner
    private String category;
    private MyShopsAdaptor adaptor;
    private ListView shops_lst;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        spinner_shops=view.findViewById(R.id.spinner_shops);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),R.array.kind, android.R.layout.simple_spinner_item);
        spinner_shops.setAdapter(adapter);
        adaptor=new MyShopsAdaptor(getContext(),R.layout.item_shop_view1);
        shops_lst = view.findViewById(R.id.list_Hotest);
        shops_lst.setAdapter(adaptor);
        readTasksFromFirebase(null);
        /**
         * if the item is selected
         */
        spinner_shops.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //signs out if the user chooses the last item un the spinner
                if (i==6){
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    Intent intent=new Intent(getContext(), SignInActivity.class);
                    startActivity(intent);
                }
                else {
                    if(i==0 ||i==1||i==2||i==3||i==4||i==5)
                    {
                        category = ( String ) spinner_shops.getSelectedItem();
                        readTasksFromFirebase(null);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        readTasksFromFirebase(null);
        return view;
    }
    /**
     * adds the shop if the discount more than 50%
     */
    private void readTasksFromFirebase(final String stTosearch) {
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
                            //checks if the discount is bigger than 50
                            if(t.getDiscountpercent()>50)
                            {
                                adaptor.add(t);
                            }
                        }
                    }
                    else if (t.getCategory().contains(category)) {
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
    @Override
    public void onResume() {
        super.onResume();
        readTasksFromFirebase("");
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedShops=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(),selectedShops,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}