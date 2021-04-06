package tala.mubarki.talafinalproject17.MyUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import tala.mubarki.talafinalproject17.R;

public class ProfileActivity extends AppCompatActivity {
    private TextView TvProfile1;
    private ScrollView scrView;
    private TableLayout tab;
    private EditText etFirstName,etLastName,etPhone,etEmail2,etPassWord,etPassWordVarify;
    private Button btnSave,btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TvProfile1 = findViewById(R.id.tvProfile1); // title
        etFirstName = findViewById(R.id.etFirstName1);//first name
        etLastName = findViewById(R.id.etLastName1);//family name
        etPhone = findViewById(R.id.etPhone1);//phone number
        etEmail2 = findViewById(R.id.etEmail2);//email address
        etPassWord = findViewById(R.id.etPassWord);//password
        etPassWordVarify = findViewById(R.id.etPassWordVarify);//verifying
        btnSave = findViewById(R.id.btnSave);//save data
        btnReturn = findViewById(R.id.btnReturn);//return to the home screen
        scrView= findViewById(R.id.scrView);
        tab=findViewById(R.id.tab);

    }
    private void validateForm() {
        String passw2 = etPassWordVarify.getText().toString();
        String passw1 = etPassWord.getText().toString();
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail2.getText().toString();

        boolean isOk = true;
        if (fname.length() < 2)
        {
            isOk = false;
            etFirstName.setError("At least to letters");
        }
        if (email.length() < 5 || email.indexOf('@') == 0 || email.indexOf('@') >= email.length() - 2 ||
                email.indexOf('.') == 0 || email.indexOf('.') > email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@'))
        {
            isOk = false;
            etEmail2.setError("Wrong Email Address Please Rewrite");
        }
        if(passw1.equals(passw2)==false)
        {
            isOk=false;
            etPassWordVarify.setError("passwords must be the same!");
        }
        else {
            MyValidations myValidations = new MyValidations();
            if (myValidations.validatepassword(passw1) == false) {
                isOk = false;
                etPassWord.setError("Invalid Password!");
            }
        }
////
        if(isOk)
        {
            //toDo: create account and return to sign in screen/close this screen
            createNewAccount(email,passw1,fname,lname,phone);
        }

    }
/////
   // private void uploadprofile()
    /**
     *
     * @param email
     * @param passw1
     * @param fname
     * @param lname
     * @param phone
     */
    private void createNewAccount(String email, String passw1, String fname, String lname, String phone)
    {//1
        FirebaseAuth auth=FirebaseAuth.getInstance();
        //
        //2
        OnCompleteListener<AuthResult> listener=new OnCompleteListener<AuthResult>() {
            //RESPONS
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ProfileActivity.this,"Successfuly Signing up",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(ProfileActivity.this,"Signing up, Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    etEmail2.setError("Signing up, Failed"+task.getException().getMessage());
                }

            }
        };
        //3
        auth.createUserWithEmailAndPassword(email,passw1).addOnCompleteListener(listener);
    }
}