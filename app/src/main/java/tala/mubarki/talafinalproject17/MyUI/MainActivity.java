package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Data.MyShopsAdaptor;
import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;

public class MainActivity extends AppCompatActivity {
    private ImageButton ibtnAdd;
    //2 after building the array adapter
    ListView lstShop;
    //adaptor
    MyShopsAdaptor shopsAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference= database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        if (auth.getCurrentUser()==null)
        {
            Intent i= new Intent(MainActivity.this,SignInActivity.class);
            startActivity(i);
        }
        ibtnAdd=findViewById(R.id.ibtnAdd);
        //listner
        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,AddShopActivity.class);
                startActivity(i);
            }
        });
        //3
        lstShop=findViewById(R.id.lstShop);
        //4
        shopsAdaptor=new MyShopsAdaptor(getBaseContext(),R.layout.item_shop_view1);
        //5. connect listview to the adaptor
        lstShop.setAdapter(shopsAdaptor);
        //7
        downloadFromFireBase();
    }
    //6.
    public void downloadFromFireBase(){
        //where we saved before we need to contact to download
        //1.
        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference= database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4.
        reference.child("All Shops").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                shopsAdaptor.clear();
                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Shop shop=child.getValue(Shop.class);
                    shopsAdaptor.add(shop);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Download Error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}