package com.example.auth_app.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.auth_app.PaymentGateway;
import com.example.auth_app.R;
import com.example.auth_app.ui.EVDetails.ProfileFormFragment;


public class Challan extends Fragment {


    public Button back;
    public Button pay1b,pay2b,pay3b;
    public int amount;
    public int a1i,a2i,a3i;
    public static String a1s,a2s,a3s;
    public static TextView a1,a2,a3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.fragment_challan, container, false);
        back=(Button)view.findViewById(R.id.challanback);
        pay1b=(Button)view.findViewById(R.id.pay1);
        pay2b=(Button)view.findViewById(R.id.pay2);
        pay3b=(Button)view.findViewById(R.id.pay3);
        a1=(TextView)view.findViewById(R.id.amount1);
        a2=(TextView)view.findViewById(R.id.amount2);
        a3=(TextView)view.findViewById(R.id.amount3);

        a1.setText("2000");
        a2.setText("500");
        a3.setText("1000");

        pay1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1s = a1.getText().toString();  //textview value to string
                a1i = Integer.parseInt(a1s);    //string to integer
                a1i=a1i*100;                    //multiply by 100
                a1s=String.valueOf(a1i);        // converting the updated value to string.
                Intent intent = new Intent(getActivity(), PaymentGateway.class);
                startActivity(intent);
            }
        });
        pay2b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1s = a2.getText().toString();
                a1i = Integer.parseInt(a1s);    //string to integer
                a1i=a1i*100;                    //multiply by 100
                a1s=String.valueOf(a1i);        // converting the updated value to string.
                Intent intent = new Intent(getActivity(), PaymentGateway.class);
                startActivity(intent);
            }
        });
        pay3b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a1s = a3.getText().toString();
                a1i = Integer.parseInt(a1s);    //string to integer
                a1i=a1i*100;                    //multiply by 100
                a1s=String.valueOf(a1i);        // converting the updated value to string.
                Intent intent = new Intent(getActivity(), PaymentGateway.class);
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new ProfileFragment()).addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }
}