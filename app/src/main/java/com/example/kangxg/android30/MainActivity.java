package com.example.kangxg.android30;

import android.app.Fragment;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.app.Activity;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Button btn =  findViewById(R.id.nextButton);
         btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
       Intent intent = new  Intent();
       intent.setClass(MainActivity.this,NextActivity.class);

        //startActivityForResult(intent, 0);
       MainActivity.this.startActivity(intent);

    }

}
