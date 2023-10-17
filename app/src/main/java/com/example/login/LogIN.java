package com.example.login;

import static com.example.login.Utiles.getValueFromAp;
import static com.example.login.Utiles.isValid;

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

import java.util.regex.Pattern;

public class LogIN extends AppCompatActivity {
    EditText editTextTextPersonName;
    EditText editTextTextPassword;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        button=findViewById(R.id.button);

    }
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail=editTextTextPersonName.getText().toString();
                String userpass=editTextTextPassword.getText().toString();
                if(isValid(useremail,userpass)){

                    int cridentialIsValid=Utiles.checkAuthentication(LogIN.this,useremail,userpass);
                    switch (cridentialIsValid){
                        case 100: {
                            Toast.makeText(LogIN.this, "Log IN Succesfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(),NewActivity.class));
                            break;
                        }
                        case 101 : {
                            Toast.makeText(LogIN.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 102:{
                            Toast.makeText(LogIN.this, "You are not registered user. Please go to registration secreen", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplication(),Register.class));
                            break;
                        }
                        case 103 : {
                            Toast.makeText(LogIN.this, "Please try again..", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }else{
                    Toast.makeText(LogIN.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}