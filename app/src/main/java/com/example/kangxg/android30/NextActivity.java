package com.example.kangxg.android30;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kangxg on 2017/11/6.
 */

public class NextActivity  extends FragmentActivity implements View.OnClickListener {

   private Button closeBtn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        closeBtn = (Button)findViewById(R.id.tipButton);
        closeBtn.setOnClickListener(this);

    }
    private  void testuri()
    {
        Intent intent = new  Intent();
        intent.setAction(intent.ACTION_CALL);
        Uri uri = Uri.parse("tel:"+"15960238696");
        intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public void onClick(View v)
    {
        Intent intent = new  Intent();
        intent.setClass(this,SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("name","凌晨");
        bundle.putInt("time",24);
        intent.putExtras(bundle);
        startActivityForResult(intent, 1001);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     if (data != null) {
         TextView textView = findViewById(R.id.tiptview);
         Intent intent = getIntent();

        String name = data.getStringExtra("name");

        textView.setText(name);
     }
    }

}

