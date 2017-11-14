package com.example.kangxg.android30;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.InputStream;
import com.example.kangxg.android30.util.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showFadeAnimation();
    }
    private void showFrameAnimation()
    {
        ImageView frameAnim = findViewById(R.id.frameAnim);
        frameAnim.setImageResource(R.drawable.frame_anim);
        AnimationDrawable  ad_frame = (AnimationDrawable)frameAnim.getDrawable();
        ad_frame.start();
    }
    private void showGifAnimation(){
        ImageView iv_gif = findViewById(R.id.frameAnim);
        InputStream is = getResources().openRawResource(R.raw.loading);
        GifImage gifImage = new GifImage();
        int code = gifImage.read(is);
        if (code == GifImage.STATUS_OK) {
            GifImage.GifFrame[] frameList = gifImage.getFrames();
            AnimationDrawable ad_gif = new AnimationDrawable();
            for (int i=0; i<frameList.length; i++) {
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
    private  void  showFadeAnimation()
    {
        Drawable[] drawableArray = {
                getResources().getDrawable(R.drawable.fade_begin),
                getResources().getDrawable(R.drawable.fade_end),
        };
        TransitionDrawable td_fade = new TransitionDrawable(drawableArray);
        ImageView image = findViewById(R.id.frameAnim);
        image.setImageDrawable(td_fade);
        td_fade.startTransition(3300);
    }
}
