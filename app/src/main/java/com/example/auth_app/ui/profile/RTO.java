package com.example.auth_app.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.auth_app.R;
import com.example.auth_app.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;


public class RTO extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    FirebaseFirestore firestore;
    private String userID;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView reg_no,owner_name,model,mfg_dt,reg_upto;

    public static RTO newInstance(String param1, String param2) {
        RTO fragment = new RTO();
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

        View v= inflater.inflate(R.layout.rto, container, false);
        reg_no=(TextView)v.findViewById(R.id.reg_no);
        reg_upto=(TextView)v.findViewById(R.id.reg_upto);
        owner_name=(TextView)v.findViewById(R.id.owner_name);
        model=(TextView)v.findViewById(R.id.model_name);
        mfg_dt=(TextView)v.findViewById(R.id.mfg_date);



        firebaseAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot mainSnapshot) {
                ProfileData userProfile = mainSnapshot.getValue(ProfileData.class);
               reg_no.setText(userProfile.u_RegistrationNo);
               reg_upto.setText(userProfile.RegtUpto);
               owner_name.setText(userProfile.u_OwnerName);
               model.setText(userProfile.Model);
               mfg_dt.setText(userProfile.MfgDate);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return v;
    }
}