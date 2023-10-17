package com.example.login;

import static com.example.login.Utiles.getPreferenceManager;
import static com.example.login.Utiles.isValid;
import static com.example.login.Utiles.saveIntoSp;
import static com.example.login.Utiles.showingToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    TextView Register1,textView;
    EditText fullname,email,pass1,pass2;
    Button registerbtn,loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register1=findViewById(R.id.Register1);
        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        pass1=findViewById(R.id.pass1);
        pass2=findViewById(R.id.pass2);
        registerbtn=findViewById(R.id.registerbtn);
        loginbtn=findViewById(R.id.loginbtn);
        textView=findViewById(R.id.textView);
    }
    protected void onResume(){
        super.onResume();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),LogIN.class));
              }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userFullName=fullname.getText().toString();
                String useremail=email.getText().toString();
                String userpass1=pass1.getText().toString();
                String userpass2=pass2.getText().toString();

                if(isValid(userFullName,useremail,userpass1,userpass2)){
                   int responseCode = Utiles.saveIntoSp(Register.this,userFullName,useremail,userpass1);
                    if(responseCode==100) {

                        fullname.setText("");
                        email.setText("");
                        pass1.setText("");
                        pass2.setText("");
                        startActivity(new Intent(getApplication(), LogIN.class));
                    }else {
                        Toast.makeText(Register.this, "User Already Registered...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplication(), LogIN.class));
                        
                    }
                }else{
                    showingToast(Register.this);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
