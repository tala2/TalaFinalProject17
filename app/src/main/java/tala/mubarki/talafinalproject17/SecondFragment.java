package tala.mubarki.talafinalproject17;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import tala.mubarki.talafinalproject17.Fragments.FundamentalActivity;
import tala.mubarki.talafinalproject17.MyUtils.Shops;

public class SecondFragment extends Fragment {
    private ImageButton imgbtnHOTEST,imgbtnProfile;
    private TextView tvTitle;
    private Button btnFind,btnShop,btnMap,btnMore,btnSearch;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
    //imgbtnHOTEST=findViewById(R.id.imgbtnHOTEST);
  //  imgbtnProfile=findViewById(R.id.imgbtnPROFILE);
   // btnFind=findViewById(R.id.btnFind);

    //3
        btnS.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i= new Intent(SecondFragment.this, Shops.class);
        }
    });


}
}