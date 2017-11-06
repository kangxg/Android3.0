package com.example.kangxg.android30;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kangxg on 2017/11/6.
 */

public class NextActivity  extends AppCompatActivity implements View.OnClickListener {

   private Button closeBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        closeBtn = (Button)findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(this);
        TextView textView = findViewById(R.id.textview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("time");
        textView.setText(name +age);
    }
    @Override
    public void onClick(View v)
    {

    }
}

