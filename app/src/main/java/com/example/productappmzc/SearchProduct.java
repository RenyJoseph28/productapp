package com.example.productappmzc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SearchProduct extends AppCompatActivity {
    EditText ed1,ed2,ed3;
    AppCompatButton b1,b2;
    String pcode,getname,getprice;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        db=new DatabaseHelper(this);
        db.getWritableDatabase();
        ed1=(EditText) findViewById(R.id.pp);
        ed2=(EditText) findViewById(R.id.pn);
        ed3=(EditText) findViewById(R.id.pr);
        b1=(AppCompatButton) findViewById(R.id.btt1);
        b2=(AppCompatButton) findViewById(R.id.btt2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcode=ed1.getText().toString();
                Cursor c=db.Search(pcode);
                if(c.getCount()==0)
                {
                    ed2.setText("");
                    ed3.setText("");
                    Toast.makeText(getApplicationContext(), "invalid product code", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    while (c.moveToNext())
                    {
                        getname=c.getString(2);
                        getprice=c.getString(3);
                    }
                    ed2.setText(getname);
                    ed3.setText(getprice);
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}