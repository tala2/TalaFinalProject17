package tala.mubarki.talafinalproject17.MyUtils;

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
import tala.mubarki.talafinalproject17.Fragments.MainShopsActivity;
import tala.mubarki.talafinalproject17.MyUI.SignInActivity;
import tala.mubarki.talafinalproject17.MyUI.ui.main.OwnerShops;
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
        etFirstName = findViewById(R.id.etFirstName);//first name
        etLastName = findViewById(R.id.etLastName);//family name
        etPhone = findViewById(R.id.etPhone);//phone number
        btnSave = findViewById(R.id.btnSaveAdding1);//save data
        btnReturn = findViewById(R.id.btnReturnadd1);//return to Sign Up screen
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
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });
    }

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
            //6 save on fireabase
            //6.1 build your data project
            if(radioCustomer.isChecked())
            {
                Customer customer= new Customer();
                customer.setName(fname);
                customer.setPhone(phone);
                customer.setLastName(lname);
                //6.
                saveCustomer(customer);
            }
            if(radioCustomer.isChecked()){
                Owner owner=new Owner();
                owner.setName(fname);
                owner.setPhone(phone);
                owner.setLastName(lname);
                //6.
                saveOwner(owner);
            }
        }
    }

    private void saveCustomer(Customer customer) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("All Customers").push().getKey();
        //5
        customer.setOwner(uid);
        customer.setKey(key);
        //6. actual storing
        reference.child("All Customers").child(key).setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ProfileActivity.this,"add successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ProfileActivity.this, MainShopsActivity.class);
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
        String key=reference.child("All Owners").push().getKey();
        //5
        owner.setOwner(uid);
        owner.setKey(key);
        //6. actual storing
        reference.child("All Owners").child(key).setValue(owner).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "add successful", Toast.LENGTH_SHORT).show();
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