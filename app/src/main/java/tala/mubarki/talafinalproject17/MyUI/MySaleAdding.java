package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.Data.MySale;
import tala.mubarki.talafinalproject17.R;

public class MySaleAdding extends AppCompatActivity {
    private Button btnSaveSale,btnReturnSale;
    private EditText etDiscount,etType,etCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sale_adding);
        //3 find view by id
        setContentView(R.layout.activity_add_shop);
        //etCoupon=findViewById(R.id.etcoupon);
      //  etDiscount=findViewById(R.id.etAddDiscount);
        etType=findViewById(R.id.etAddingType);
        //4 listner
        btnSaveSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
    }
    //5
    public void validateForm(){
        String type= etType.getText().toString();
        String discount= etDiscount.getText().toString();
        String coupon=etCoupon.getText().toString();
        boolean isOk=true;
        //note: another validate option for the address it have to chek if the address is located
        if(type.length()==0){
            isOk= false;
            etType.setError("Wrong Address");
        }
        if (isOk){
            //6 save on fireabase
            //6.1 build your data project
            MySale sale= new MySale();
            sale.setType(type);
            sale.setDiscountString(discount);
            //6.
            saveSale(sale);
        }
    }
    //6.3 request to save mt shop (firebase database)
    private void saveSale (MySale sale){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("All shops").push().getKey();
        //5
        sale.getKey(key);
        //6. actual storing
        reference.child("AllTasls").child(uid).setValue(sale).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MySaleAdding.this,"add successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(MySaleAdding.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });


    }
}
