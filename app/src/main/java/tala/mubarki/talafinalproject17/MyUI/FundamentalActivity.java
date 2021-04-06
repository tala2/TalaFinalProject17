package tala.mubarki.talafinalproject17.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.R;
import tala.mubarki.talafinalproject17.Shops;

//1 xml
public class FundamentalActivity extends AppCompatActivity {
    //2
    private ImageButton imgbtnHOTEST,imgbtnProfile;
    private TextView tvTitle;
    private Button btnFind,btnShop,btnMap,btnMore,btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3
        setContentView(R.layout.activity_fundamental);
        imgbtnHOTEST=findViewById(R.id.imgbtnHOTEST);
        imgbtnProfile=findViewById(R.id.imgbtnPROFILE);
        btnFind=findViewById(R.id.btnFind);

        //3
        btnShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i= new Intent(FundamentalActivity.this,Shops.class);
            }
        });
        

    }
}