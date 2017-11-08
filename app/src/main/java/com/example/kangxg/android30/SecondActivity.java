package com.example.kangxg.android30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kangxg on 2017/11/7.
 */

public class SecondActivity extends  BaseFragmentActiviy implements View.OnClickListener{
    private Button closeBtn = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_next);
        closeBtn = (Button)findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(this);
        TextView textView = findViewById(R.id.secondview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        int age = bundle.getInt("time");
        textView.setText(name +age+"点");
    }
    @Override
    public void onClick(View v)
    {
        Intent intent = new  Intent();

        Bundle bundle = new Bundle();
        bundle.putString("name","我返回了！");
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK,intent);

        finish();
    }


}
