package com.example.auth_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register_user extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    TextView banner;
    EditText ename,eage,eemail,epassword;
    ProgressBar progressBar;
    Button reg_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.ev_connect2);
        banner.setOnClickListener((View.OnClickListener) this);
        reg_user = (Button) findViewById(R.id.reg_user);
        reg_user.setOnClickListener(this);
        ename = (EditText) findViewById(R.id.reg_name);
        eage = (EditText) findViewById(R.id.reg_age);
        eemail = (EditText) findViewById(R.id.reg_email);
        epassword =(EditText) findViewById(R.id.reg_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_reg);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ev_connect2:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.reg_user:
                registerUser();
        }
    }

    private void registerUser() {
        String email = eemail.getText().toString().trim();
        String name = ename.getText().toString().trim();
        String age = eage.getText().toString().trim();
        String password = epassword.getText().toString().trim();
        if(name.isEmpty()){
            ename.setError("Full name is required");
            ename.requestFocus();
            return;
        }
        if(age.isEmpty()){
            eage.setError("Age is required");
            eage.requestFocus();
            return;
        }
        if(email.isEmpty()){
            eemail.setError("Email is required");
            eemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eemail.setError("Please Provide Valid Email!");
            eemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            epassword.setError("Password is required");
            epassword.requestFocus();
            return;
        }
        if(password.length()<6){
            epassword.setError("Minimum length of password should be 6");
            epassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(name,age,email);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Register_user.this,"User has been registered",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);

                                //redirect to login layout
                                startActivity(new Intent(Register_user.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(Register_user.this,"Failed! Try again",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }else{
                    Toast.makeText(Register_user.this,"Failed! Try again",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}