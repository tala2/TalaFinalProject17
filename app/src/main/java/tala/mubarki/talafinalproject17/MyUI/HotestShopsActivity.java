package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.Fragments.HotestShopsFragment;
import tala.mubarki.talafinalproject17.R;

public class HotestShopsActivity extends AppCompatActivity {
    private ListView shopsHotes_lst;
    private Button btn;
    private MyShopsAdaptor adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotest_shops);
        btn=findViewById(R.id.btnRe);
        shopsHotes_lst=findViewById(R.id.list_Hotest);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HotestShopsActivity.this, HotestShopsFragment.class);
                startActivity(intent);
            }
        });
        adaptor=new MyShopsAdaptor(this,R.layout.item_shop_view1);
        readTaskFromFireBase(null);
    }

    /**
     * adds the shop if the discount more than 50%
     */
    private void readTaskFromFireBase(final String stTosearch) {
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

                                adaptor.add(t);

                        }
                    }
                    else if (t.getName().contains(stTosearch)) {
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