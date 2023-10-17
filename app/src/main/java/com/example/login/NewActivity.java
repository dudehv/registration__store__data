package com.example.login;

import static com.example.login.Utiles.getValueFromAp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    HomePage page=null;
    RecyclerView lv;
    EditText edit_text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        lv=(RecyclerView) findViewById(R.id.lv);
        edit_text=findViewById(R.id.edit_text);

        ArrayList<Users> list= (ArrayList) Utiles.getAllRegisteredUser(this);

        page=new HomePage(this,list);
        lv.setLayoutManager(new LinearLayoutManager(NewActivity.this));
        lv.setAdapter(page);
        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=v.findViewById(R.id.tvdata);
                Toast.makeText(NewActivity.this, ""+tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                page.filterData(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected void onResume() {
        super.onResume();


    }

}