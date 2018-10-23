package com.example.theba.java_naja;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AchievementActivity extends AppCompatActivity {

    private TextView button_chap1, button_chap2, button_chap3, button_chap4,
                     button_chap5, button_chap6, button_chap7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);


        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#30000000")));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        getSupportActionBar().setTitle("ความสำเร็จ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_chap1 = (TextView) findViewById(R.id.but_chap1);
        button_chap2 = (TextView) findViewById(R.id.but_chap2);
        button_chap3 = (TextView) findViewById(R.id.but_chap3);
        button_chap4 = (TextView) findViewById(R.id.but_chap4);
        button_chap5 = (TextView) findViewById(R.id.but_chap5);
        button_chap6 = (TextView) findViewById(R.id.but_chap6);
        button_chap7 = (TextView) findViewById(R.id.but_chap7);

        button_chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AchievementActivity.this,Achieve_Chap1Activity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

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
