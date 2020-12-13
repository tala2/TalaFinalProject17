package tala.mubarki.talafinalproject17.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.R;

public class HomeScreenActivity extends AppCompatActivity {
    private TextView tvTitle;
    private Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        btnSignIn=findViewById(R.id.btnSignIn1);
        btnSignUp=findViewById(R.id.btnSignUp);
        //listner
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(HomeScreenActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(HomeScreenActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });
    }
}