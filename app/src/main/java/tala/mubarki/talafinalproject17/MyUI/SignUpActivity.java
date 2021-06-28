package tala.mubarki.talafinalproject17.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class SignUpActivity extends AppCompatActivity {
    //2 find view by id
    private TextView TvSignUp;
    private ScrollView scrView;
    private TableLayout tab;
    private EditText etEmail2,etPassWord, etPassWordVerify;
    private Button btnSave,btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TvSignUp = findViewById(R.id.tvSignUp); // title
        etEmail2 = findViewById(R.id.etEmail2);//email address
        etPassWord = findViewById(R.id.etPassWord);//password
        etPassWordVerify = findViewById(R.id.etPassWordVarify);//verifying
        btnSave = findViewById(R.id.btnSaveAdding);//save data
        scrView= findViewById(R.id.scrView);
        tab=findViewById(R.id.tab);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });
    }
    private void validateForm() {
        String passw2 = etPassWordVerify.getText().toString();
        String passw1 = etPassWord.getText().toString();
        String email = etEmail2.getText().toString();

        boolean isOk = true;
        if (email.length() < 5 || email.indexOf('@') == 0 || email.indexOf('@') >= email.length() - 2 ||
                email.indexOf('.') == 0 || email.indexOf('.') > email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@'))
        {
            isOk = false;
            etEmail2.setError("Wrong Email Address Please Rewrite");
        }
        if(passw1.equals(passw2)==false)
        {
            isOk=false;
            etPassWordVerify.setError("passwords must be the same!");
        }
        else {
      //     MyValidations myValidations = new MyValidations();
//            if (myValidations.validatepassword(passw1) == false) {
//                isOk = false;
//                etPassWord.setError("Invalid Password!");
//            }
        }

        if(isOk)
        {
            //toDo: create account and return to sign in screen/close this screen
            createNewAccount(email,passw1);
        }

    }
    /**
     *  @param email
     * @param passw1
     */
    private void createNewAccount(String email, String passw1)
    {//1
        FirebaseAuth auth=FirebaseAuth.getInstance();
        //
        //2
        OnCompleteListener<AuthResult> listener=new OnCompleteListener<AuthResult>() {
            //RESPONS
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Successfuly Signing up",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(SignUpActivity.this, ProfileActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Signing up, Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    etEmail2.setError("Signing up, Failed"+task.getException().getMessage());
                }

            }
        };
        //3
        auth.createUserWithEmailAndPassword(email,passw1).addOnCompleteListener(listener);
    }
}