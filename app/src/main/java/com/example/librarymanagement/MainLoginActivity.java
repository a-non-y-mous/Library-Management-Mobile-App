package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.*;

public class MainLoginActivity extends AppCompatActivity {
    Animation top_anim,bottom_anim;
    ImageView image;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_login);

      top_anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bottom_animation);

        image=findViewById(R.id.imageLogo);
        logo=findViewById(R.id.textlib);
        slogan=findViewById(R.id.textlibman);

        image.setAnimation(top_anim);
        logo.setAnimation(bottom_anim);
        slogan.setAnimation(bottom_anim);

        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent intent = new Intent(MainLoginActivity.this, Dashboard.class);
                Pair[] pairs=new Pair[2];
                pairs[0]= new Pair<View,String>(image,"logo_image");
                pairs[1]= new Pair<View,String>(logo,"logo_text");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainLoginActivity.this,pairs);
                    startActivity(new Intent(MainLoginActivity.this,Dashboard.class),options.toBundle());
                    finish();
                }
            }
        }, SPLASH_SCREEN);
    }
}