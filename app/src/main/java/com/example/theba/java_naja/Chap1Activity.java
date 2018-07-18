package com.example.theba.java_naja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Chap1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap1);



        Button button_chap1 = (Button) findViewById(R.id.img_Button1);

        button_chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Chapter1 = new Intent(Chap1Activity.this,Chap1_1Activity.class);
                startActivity(Chapter1);
            }
        });

    }

}
