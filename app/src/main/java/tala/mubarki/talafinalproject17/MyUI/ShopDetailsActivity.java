package tala.mubarki.talafinalproject17.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;

public class ShopDetailsActivity extends AppCompatActivity {
    private TextView textView,TvName,TvDiscount,TvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_shop);
        textView=findViewById(R.id.tvNewShop);
        TvDiscount=findViewById(R.id.tvDiscount1);
        TvCategory=findViewById(R.id.tvCategory);
        TvName=findViewById(R.id.tvNameShop);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        if(intent!=null){
            Shop shop=(Shop) intent.getExtras().get("Shop");
            TvName.setText("Name:"+shop.getName());
            TvCategory.setText("Name:"+shop.getName());

        }
    }
}