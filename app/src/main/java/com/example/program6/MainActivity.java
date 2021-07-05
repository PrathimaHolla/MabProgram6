package com.example.program6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Button json,xml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        json=(Button)findViewById(R.id.jsonButton);
        xml=(Button)findViewById(R.id.xmlButton);
        json.setOnClickListener(this);
        xml.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(json)){
            Intent intent=new Intent(this,ViewActivity.class);
            intent.putExtra("mode",1);
            startActivity(intent);
        }
        else{
            Intent intent=new Intent(this,ViewActivity.class);
            intent.putExtra("mode",2);
            startActivity(intent);
        }
    }
}