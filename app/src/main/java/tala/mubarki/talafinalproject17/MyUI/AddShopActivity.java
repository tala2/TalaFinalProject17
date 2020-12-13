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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;
//1 design addshop xml
public class AddShopActivity extends AppCompatActivity {
    //2
    private Button btnSaveShop,btnUpload;
    private EditText etAdress,etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        //3 find view by id
        setContentView(R.layout.activity_add_shop);
        etAdress=findViewById(R.id.etEmailAdress);
        etPhone=findViewById(R.id.etPhone);
        btnUpload=findViewById(R.id.btnUpload);
        //4 listner
        btnSaveShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    //5
    public void validateForm(){
        String adress= etAdress.getText().toString();
        String phone= etPhone.getText().toString();
        boolean isOk=true;
        //note: another validate option for the address it have to chek if the address is located
        if(adress.length()==0){
            isOk= false;
            etAdress.setError("Wrong Address");
        }
        if (isOk){
            //6 save on fireabase
            //6.1 build your data project
            Shop shop= new Shop();
            shop.setAdress(adress);
            shop.setPhone(phone);
            //6.
            saveShop(shop);
        }
    }
    //6.3 request to save mt shop (firebase database)
    private void saveShop (Shop shop){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("All shops").push().getKey();
        //5
        shop.setOwner(uid);
        shop.getKey(key);
        //6. actual storing
        reference.child("AllTasls").child(uid).setValue(shop).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddShopActivity.this,"add successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(AddShopActivity.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });


    }
}