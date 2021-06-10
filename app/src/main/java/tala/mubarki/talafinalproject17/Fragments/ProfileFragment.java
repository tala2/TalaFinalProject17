package tala.mubarki.talafinalproject17.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tala.mubarki.talafinalproject17.Data.Shop;
import tala.mubarki.talafinalproject17.Data.User;
import tala.mubarki.talafinalproject17.MyUI.AddShopActivity;
import tala.mubarki.talafinalproject17.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView TvProfile1;
    private ScrollView scrView;
    private TableLayout tab;
    private EditText etFirstName,etPhone;
    private Button btnSave,btnReturn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        btnReturn=view.findViewById(R.id.btnReturn);
        btnSave=view.findViewById(R.id.btnSaveAdding1);
        etPhone=view.findViewById(R.id.etPhone);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateForm();
            }
        });
        return view;
    }
    public void validateForm(){
        String lastName= etFirstName.getText().toString();
        String phone= etPhone.getText().toString();
        boolean isOk=true;
        //note: another validate option for the address it have to chek if the address is located
        if(lastName.length()==0){
            isOk= false;
            etFirstName.setError("Wrong Address");
        }
        if (isOk){
            //6 save on fireabase
            //6.1 build your data project
            User user= new User();
            user.setName(lastName);
            user.setPhone(phone);
            //6.
            saveProfile(user);
        }
    }

    private void saveProfile(User user) {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference=database.getReference();
        //3. user id
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4. My object key
        String key=reference.child("All Users").push().getKey();
        //5
          user.setOwner(uid);
        user.setKey(key);
        //6. actual storing
        reference.child("All Users").child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                 Toast.makeText(ProfileFragment.this,"add successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                 Toast.makeText(ProfileFragment.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


}