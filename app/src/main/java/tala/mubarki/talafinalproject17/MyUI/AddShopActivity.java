package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.R;
//1 design addshop xml
public class AddShopActivity extends AppCompatActivity {
    //2
    private Button btnSaveShop;
    private EditText etAdress,etDiscount,etName;
    private TextView tvQuestion;
    private Spinner spinner_categ;
    private RadioButton radioyes, radioNo;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3 find view by id
        setContentView(R.layout.activity_add_shop);
        spinner_categ=findViewById(R.id.spinner_categ);
        radioGroup=findViewById(R.id.radioGroup);
        etName=findViewById(R.id.etShopName);
        radioNo=findViewById(R.id.radioNo);
        radioyes=findViewById(R.id.radioYes);
        tvQuestion=findViewById(R.id.TvQuastion);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.kind, android.R.layout.simple_spinner_item);
        spinner_categ.setAdapter(adapter);
        btnSaveShop=findViewById(R.id.btnSaveShop);
        etDiscount=findViewById(R.id.tvDiscount1);
        etAdress=findViewById(R.id.etAdress2);
        radioyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    etAdress.setVisibility(View.VISIBLE);
                    etDiscount.setVisibility(View.VISIBLE);
                    spinner_categ.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.VISIBLE);
                }
                if(b==false)
                {
                    etAdress.setVisibility(View.VISIBLE);
                    etDiscount.setVisibility(View.GONE);
                    spinner_categ.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.VISIBLE);
                }
            }
        });
        spinner_categ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(1==7){
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    auth.signOut();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //4 listner
        btnSaveShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
    }
    //5
    public void validateForm(){
        String adress= etAdress.getText().toString();
        String discount=etDiscount.getText().toString();
        String name=etName.getText().toString();
        boolean isOk=true;
        //note: another validate option for the address it have to chek if the address is located
        if(adress.length()==0){
            isOk= false;
            etAdress.setError("Wrong Address");
        }
        if(discount.length()==0 || discount.length()>3){
            isOk= false;
            etDiscount.setError("Impossible Discount ");
        }
        if(name.length()==0 || name.length()>3){
            isOk= false;
            etName.setError("Impossible Name ");
        }
        if (isOk){
            //6 save on fireabase
            //6.1 build your data project
            Shop shop= new Shop();
            shop.setName(name);
            shop.setAddress(adress);
            shop.setDiscountString(discount);
            shop.setCategory(( String ) spinner_categ.getSelectedItem());
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
        String key=reference.child("AllShops").push().getKey();
        //5
        shop.setOwner(uid);
        shop.setKey(key);
        //6. actual storing
        reference.child("AllShops").child(key).setValue(shop).addOnCompleteListener(new OnCompleteListener<Void>() {
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