package com.example.kangxg.android30;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.InputStream;
import com.example.kangxg.android30.util.*;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener,View.OnClickListener{

    private Animation alphaAnim, translateAnim, scaleAnim, roateAnim;
    private ImageView iv_tween_anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.toSwing);
        btn.setOnClickListener(this);
//        createMjAnimation();
    }
    @Override
    public void  onClick(View v)
    {
        Intent intent = new  Intent();
        intent.setClass(MainActivity.this,SwingAnimActivity.class);
        MainActivity.this.startActivity(intent);
    }
    private void showFrameAnimation() {
        ImageView frameAnim = findViewById(R.id.frameAnim);
        frameAnim.setImageResource(R.drawable.frame_anim);
        AnimationDrawable ad_frame = (AnimationDrawable) frameAnim.getDrawable();
        ad_frame.start();
    }

    private void showGifAnimation() {
        ImageView iv_gif = findViewById(R.id.frameAnim);
        InputStream is = getResources().openRawResource(R.raw.loading);
        GifImage gifImage = new GifImage();
        int code = gifImage.read(is);
        if (code == GifImage.STATUS_OK) {
            GifImage.GifFrame[] frameList = gifImage.getFrames();
            AnimationDrawable ad_gif = new AnimationDrawable();
            for (int i = 0; i < frameList.length; i++) {
                //BitmapDrawable用于把Bitmap格式转换为Drawable格式
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), frameList[i].image);
                ad_gif.addFrame(bitmapDrawable, frameList[i].delay);
            }
            ad_gif.setOneShot(false);
            iv_gif.setImageDrawable(ad_gif);
            ad_gif.start();
        } else if (code == GifImage.STATUS_FORMAT_ERROR) {
            Toast.makeText(this, "该图片不是gif格式", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "gif图片读取失败:" + code, Toast.LENGTH_LONG).show();
        }

    }

    private void showFadeAnimation() {
        Drawable[] drawableArray = {
                getResources().getDrawable(R.drawable.fade_begin),
                getResources().getDrawable(R.drawable.fade_end),
        };
        TransitionDrawable td_fade = new TransitionDrawable(drawableArray);
        ImageView image = findViewById(R.id.frameAnim);
        image.setImageDrawable(td_fade);
        td_fade.startTransition(3300);
    }

    private void createMjAnimation() {
        iv_tween_anim = (ImageView) findViewById(R.id.iv_tween_anim);
        initanim();
        initTweenSpinner();
    }

    private void initanim() {
        //从完全透明变为即将不透明
        alphaAnim = new AlphaAnimation(1.0f, 0.1f);
        alphaAnim.setDuration(3000);
        alphaAnim.setFillAfter(true);

        //向左平移200
        translateAnim = new TranslateAnimation(1.0f, -200f, 1.0f, 1.0f);
        translateAnim.setDuration(3000);
        translateAnim.setFillAfter(true);

        //宽度不变，高度变为原来的二分之一
        scaleAnim = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.5f);
        scaleAnim.setDuration(3000);
        scaleAnim.setFillAfter(true);

        //围绕着圆心顺时针旋转360度
        roateAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        roateAnim.setDuration(3000);
        roateAnim.setFillAfter(true);


    }
    private void initTweenSpinner() {

        ArrayAdapter<String> tweenAdapter = new ArrayAdapter<String>(this,
                R.layout.item_select, tweenArray);
        Spinner sp_tween = (Spinner) findViewById(R.id.sp_tween);
        sp_tween.setPrompt("请选择补间动画类型");
        sp_tween.setAdapter(tweenAdapter);
        sp_tween.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                if (arg2 == 0) {
                    iv_tween_anim.startAnimation(alphaAnim);
                    alphaAnim.setAnimationListener(MainActivity.this);
                } else if (arg2 == 1) {
                    iv_tween_anim.startAnimation(translateAnim);
                    translateAnim.setAnimationListener(MainActivity.this);
                } else if (arg2 == 2) {
                    iv_tween_anim.startAnimation(scaleAnim);
                    scaleAnim.setAnimationListener(MainActivity.this);
                } else if (arg2 == 3) {
                    iv_tween_anim.startAnimation(roateAnim);
                    roateAnim.setAnimationListener(MainActivity.this);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        sp_tween.setSelection(0);
    }
    private String[] tweenArray={"灰度动画", "平移动画", "缩放动画", "旋转动画"};

    @Override
    public void onAnimationStart(Animation animation) {
    }
    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation.equals(alphaAnim)) {
            Animation alphaAnim2 = new AlphaAnimation(0.1f, 1.0f);
            alphaAnim2.setDuration(3000);
            alphaAnim2.setFillAfter(true);
            iv_tween_anim.startAnimation(alphaAnim2);
        } else if (animation.equals(translateAnim)) {
            Animation translateAnim2 = new TranslateAnimation(-200f, 1.0f, 1.0f, 1.0f);
            translateAnim2.setDuration(3000);
            translateAnim2.setFillAfter(true);
            iv_tween_anim.startAnimation(translateAnim2);
        } else if (animation.equals(scaleAnim)) {
            Animation scaleAnim2 = new ScaleAnimation(1.0f, 1.0f, 0.5f, 1.0f);
            scaleAnim2.setDuration(3000);
            scaleAnim2.setFillAfter(true);
            iv_tween_anim.startAnimation(scaleAnim2);
        } else if (animation.equals(roateAnim)) {
            Animation rotateAnim2 = new RotateAnimation(0f, -360f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnim2.setDuration(3000);
            rotateAnim2.setFillAfter(true);
            iv_tween_anim.startAnimation(rotateAnim2);
        }
    }
    @Override
    public void onAnimationRepeat(Animation animation) {
    }

}

