package com.example.auth_app.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.auth_app.R;
import com.example.auth_app.ui.EVDetails.ProfileFormFragment;


public class Challan extends Fragment {


    public Button back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_challan, container, false);
        back=(Button)view.findViewById(R.id.challanback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new ProfileFragment()).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        return view;
    }
}