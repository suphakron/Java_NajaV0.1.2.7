package com.example.theba.java_naja;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        Button Nextpage = (Button) findViewById(R.id.Next_Button);
        Nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Next = new Intent(Chap1_2Activity.this,Chap1_3Activity.class);
                startActivity(Next);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
