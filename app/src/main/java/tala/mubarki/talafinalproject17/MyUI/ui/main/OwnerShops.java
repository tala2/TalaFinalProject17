package tala.mubarki.talafinalproject17.MyUI.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.MyUI.AddShopActivity;
import tala.mubarki.talafinalproject17.R;

public class OwnerShops extends AppCompatActivity {
    private Button btnadd;
    private ListView listView;
    private TextView MySales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_shops);
        listView=findViewById(R.id.listShops);
        MySales=findViewById(R.id.EtMyShops);
        btnadd=findViewById(R.id.btnAddS);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(OwnerShops.this, AddShopActivity.class);
                startActivity(i);
            }
        });
    }
}