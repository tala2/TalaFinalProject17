package tala.mubarki.talafinalproject17.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import tala.mubarki.talafinalproject17.R;
//1 xml
public class FundamentalActivity extends AppCompatActivity {
    //2
    private ImageButton imgbtnHOTEST,imgbtnProfile;
    private TextView tvTitle;
    private Button btnFind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3
        setContentView(R.layout.activity_fundamental);
        imgbtnHOTEST=findViewById(R.id.imgbtnHOTEST);
        imgbtnProfile=findViewById(R.id.imgbtnPROFILE);

        btnFind=findViewById(R.id.btnFind);

    }
}