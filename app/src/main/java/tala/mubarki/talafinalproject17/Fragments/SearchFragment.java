package tala.mubarki.talafinalproject17.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;

import tala.mubarki.talafinalproject17.MyUI.SignInActivity;
import tala.mubarki.talafinalproject17.R;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private ImageButton imgbtnHOTEST,imgbtnProfile;
    private TextView tvTitle;
    private Button btnFind,btnShop,btnMap,btnMore,btnSearch;
    private Spinner spinner_sales;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search, container, false);
        spinner_sales=view.findViewById(R.id.spinner_sales);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getContext(),
                R.array.shops,
                android.R.layout.simple_spinner_item);

        spinner_sales.setAdapter(adapter);
        spinner_sales.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==4){
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                //    Intent ii=new Intent(SearchFragment.this, SignInActivity.class);
                  //  startActivity(ii);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

      //  view.setAda(adapter);
      //  view.setOnItemSelected(getContext());
        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SearchFragment.this)
                        .navigate(R.id.action_mapsFragment_to_profileFragment);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedShops=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getContext(),selectedShops,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    //imgbtnHOTEST=findViewById(R.id.imgbtnHOTEST);
  //  imgbtnProfile=findViewById(R.id.imgbtnPROFILE);
   // btnFind=findViewById(R.id.btnFind);
    //3


}