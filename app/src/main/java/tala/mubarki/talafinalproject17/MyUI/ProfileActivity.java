package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.Data.Customer;
import tala.mubarki.talafinalproject17.Data.Owner;
import tala.mubarki.talafinalproject17.Fragments.MainTabsShops;
import tala.mubarki.talafinalproject17.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView TvProfile1;
    private ScrollView scrView;
    private TableLayout tab;
    private EditText etFirstName,etLastName,etPhone,etEmail2,etPassWord,etPassWordVarify;
    private Button btnSave,btnReturn;
    private RadioButton radioOwner, radioCustomer;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TvProfile1 = findViewById(R.id.tvAdd); // title
        radioGroup=findViewById(R.id.radio);
        etFirstName = findViewById(R.id.etFirstName);//first name
        etLastName = findViewById(R.id.etLastName);//family name
        etPhone = findViewById(R.id.etPhone);//phone number
        btnSave = findViewById(R.id.btnSaveAdding1);//save data
        scrView= findViewById(R.id.scrView);
        tab=findViewById(R.id.tab);
        radioCustomer=findViewById(R.id.customer);
        radioOwner=findViewById(R.id.owner);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });

    }
    /**
     * checks if the fields are correct
     */
    private void validateForm() {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String phone = etPhone.getText().toString();
        boolean isOk = true;
        if (fname.length() < 2)
        {
            isOk = false;
            etFirstName.setError("At least to letters");
        }
        if(lname.length()==0){
            isOk=false;
            etLastName.setError("Wrong LastName");
        }
        if (isOk){
            //if the radioOwner is choosed
            if(radioCustomer.isChecked())
            { Customer customer= new Customer();
                customer.setName(fname);
                customer.setPhone(phone);
                customer.setLastName(lname);
                customer.setType("customer");
                //6 save on fireabase
                saveCustomer(customer);
            }
            //if the radioOwner is choosed
            if(radioOwner.isChecked()){
                Owner owner=new Owner();
                owner.setType("owner");
                owner.setName(fname);
                owner.setPhone(phone);
                owner.setLastName(lname);
                //6.save on Firebase
                saveOwner(owner);
            }
        }
    }

    /**
     * save to firebase
     * @param customer
     */
    private void saveCustomer(Customer customer) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("AllCustomers").push().getKey();
        //5
        customer.setOwner(uid);
        customer.setKey(key);
        //6. takes reference of AllCustomers then the uid then it request a permisin in the firebase with listner in order to get the response
        reference.child("AllCustomers").child(uid).setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ProfileActivity.this,"add successful",Toast.LENGTH_SHORT).show();
                    //sets/adds the customer then go to MainShop
                    Intent i=new Intent(ProfileActivity.this, MainTabsShops.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(ProfileActivity.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
    private void saveOwner(Owner owner) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("AllOwners").push().getKey();
        //5
        owner.setOwner(uid);
        owner.setKey(key);
        //6. takes reference of AllOwners then the uid then it request a permisin in the firebase with listner in order to get the response
        reference.child("AllOwners").child(uid).setValue(owner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "add successful", Toast.LENGTH_SHORT).show();
                    //sets/adds the owners then go to OwnerShops
                    Intent i=new Intent(ProfileActivity.this, OwnerShops.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(ProfileActivity.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
}