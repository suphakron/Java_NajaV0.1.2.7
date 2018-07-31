package com.example.theba.java_naja;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Chap1_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap1_2);

        TextView CopBODY = (TextView) findViewById(R.id.Body1);
        CopBODY.setTextIsSelectable(true);

        TextView CopBODY2 = (TextView) findViewById(R.id.Body2);
        CopBODY2.setTextIsSelectable(true);

        TextView CopHead = (TextView) findViewById(R.id.Header);
        CopHead.setTextIsSelectable(true);

        getSupportActionBar().setTitle("บทที่ 1 ภาษาคอมพิวเตอร์และการพัฒนาโปรแกรม");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

    }
}
