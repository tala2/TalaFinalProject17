package tala.mubarki.talafinalproject17.MyUI.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.R;

public class OwnerShops extends AppCompatActivity {
    private ImageButton addSales,mySaleProfile;
    private ListView listView;
    private TextView MySales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_shops);
        addSales=findViewById(R.id.addSales);
        listView=findViewById(R.id.listSales);
        MySales=findViewById(R.id.EtMySales);
        mySaleProfile=findViewById(R.id.imgbtnSprofile);
    }
}