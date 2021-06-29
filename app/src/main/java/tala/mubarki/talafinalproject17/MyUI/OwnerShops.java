package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;

public class OwnerShops extends AppCompatActivity {
    private Button btnadd;
    private ListView listShops;
    private TextView MySales;
    private MyShopsAdaptor myShopsAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myShopsAdaptor=new MyShopsAdaptor(getBaseContext(),R.layout.item_shop_view1);

        setContentView(R.layout.activity_owner_shops);
        listShops=findViewById(R.id.listShops);
        MySales=findViewById(R.id.EtMyShops);
        listShops.setAdapter(myShopsAdaptor);
        readTasksFromFirebase(null);
        btnadd=findViewById(R.id.btnAddS);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(OwnerShops.this, AddShopActivity.class);
                startActivity(i);
            }
        });
    }
    private void readTasksFromFirebase(final String stTosearch) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        reference.child("AllShops").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myShopsAdaptor.clear();
                for (DataSnapshot d : snapshot.getChildren()) {
                    Shop t = d.getValue(Shop.class);
                    Log.d("My Shops", t.toString());
                    if (stTosearch == null || stTosearch.length() == 0) {
                        {
                            myShopsAdaptor.add(t);
                        }
                    } else
                        if (t.getName().contains(stTosearch)) {
                        {
                            myShopsAdaptor.add(t);
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