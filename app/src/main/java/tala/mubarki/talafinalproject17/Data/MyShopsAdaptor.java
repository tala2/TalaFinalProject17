package tala.mubarki.talafinalproject17.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.R;

public class MyShopsAdaptor extends ArrayAdapter<Shop> {
    /**
     * constructor
     * @param context  the activity of (app) that this adapter belong to
     * @param resource XML  design of the item
     */
    //fix error
    public MyShopsAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
        /**
         * bulding single item view
         * @param position index item in list view
         * @param convertView item view
         * @param parent listview
         * @return
         */
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //3.1
        View v = LayoutInflater.from(getContext()).inflate(R.layout.item_shop_view1, parent, false);
        //3.2 find view by ID
        //3.3 get the soutable shop object
        final Shop shop=getItem(position);
        ImageButton btnDelete = v.findViewById(R.id.imgBtnDelete);
        TextView tvName = v.findViewById(R.id.itmTvname);
        //sale
        TextView tvDiscount = v.findViewById(R.id.itmtVDiscount);
        // ImageButton btnLocation=v.findViewById(R.id.);
        //3.4 connect the dot to the view (view the data using item views)
        tvName.setText(shop.getName());
        tvDiscount.setText(shop.getDiscountString());
        //3.5 events
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delProduct(shop);
            }
        });
        tvName.setText(shop.getName());
        tvDiscount.setText(shop.getDiscountpercent()+"");
        return v;
    }
    private void delProduct(Shop myShop){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
       // reference.child("All shops").child(uid).child(myShop.getKey()).removeValue().addOnCompleteListener(new )
        reference.child("AllShops").child(uid).child(myShop.getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()) {
                   Toast.makeText(getContext(),"Delete Successful",Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(getContext(),"Delete Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                   task.getException().printStackTrace();
               }
            }
        });
    }
}
