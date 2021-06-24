package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tala.mubarki.talafinalproject17.Fragments.MainTabsShopsActivity;
import tala.mubarki.talafinalproject17.MyUI.ui.main.OwnerShops;
import tala.mubarki.talafinalproject17.MyUtils.MyValidations;
import tala.mubarki.talafinalproject17.R;
//1 xml
public class SignInActivity extends AppCompatActivity {
    //2
    private EditText etEmail,etPassword;
    private Button btnLogin,btnSignup;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //11 check if I signed in before
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)//user signed in before
        {
            checkOwner();
        }

        setContentView(R.layout.activity_sign_in);
        //3
        textView=findViewById(R.id.tvTitle);
        etEmail=findViewById(R.id.ettEmailAddress);
        etPassword=findViewById(R.id.etPass);
        btnLogin=findViewById(R.id.btnSignIn);
        btnSignup = findViewById(R.id.btnSignup);

        //4 listner
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //5
                validateForm();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    //5
    private void validateForm(){
        //1
        String email=etEmail.getText().toString();
        String passw=etPassword.getText().toString();
        //2
        boolean isOk=true;
        //3
        if (email.length()<5 || email.indexOf('@')==0 || email.indexOf('@')>=email.length()-2 ||
                email.indexOf('.') == 0 || email.indexOf('.') > email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@')) {
            isOk = false;
            etEmail.setError("Wrong Email Address Please Rewrite");
        }
        //4
        MyValidations myValidations = new MyValidations();
        if (myValidations.validatepassword(passw) == false) {
            isOk = false;
            etPassword.setError("Invalid Password!");
        }
        //5
        if (isOk){
            logIn(email,passw);
        }
    }
    //6
    private void logIn(String email,String passw)
    {
        //7
        FirebaseAuth auth=FirebaseAuth.getInstance();
        // 8
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //9
                    checkOwner();
                }
                else {
                    //10
                    Toast.makeText(SignInActivity.this,"Faild",Toast.LENGTH_SHORT).show();
                    etEmail.setError(task.getException().getMessage());
                }
            }
        });



    }
    private void checkOwner() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        reference.child("AllOwners").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if(snapshot!= null&& snapshot.hasChildren()) {
                  Intent i = new Intent(SignInActivity.this, OwnerShops.class);
                  startActivity(i);
              }
              else
              {
                  Intent i = new Intent(SignInActivity.this, MainTabsShopsActivity.class);
                  startActivity(i);
              }
        }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

            });

    }
}