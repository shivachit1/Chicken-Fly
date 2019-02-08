package com.example.ours.activities;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ours.R;
import com.example.ours.dialogfragments.menuFragment;

import java.util.ArrayList;

public class Level1 extends AppCompatActivity {
    private ImageView chicken;
    private ConstraintLayout coin;
    private ConstraintLayout mainlayout;
    private ObjectAnimator objectanimator;
    private int coincounter=0;
    private TextView coincountertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        mainlayout = findViewById(R.id.main);

        final ConstraintLayout leftlayout = findViewById(R.id.leftlayout);
        ConstraintLayout rightlayout = findViewById(R.id.rightlayout);
        chicken = findViewById(R.id.chicken);
        coin = findViewById(R.id.coin);
        coincountertext=findViewById(R.id.coincountertextview);
        coincountertext.setText(String.valueOf(coincounter));
        Button menu = findViewById(R.id.menu);
        ImageView cointitleimage = findViewById(R.id.cointitleimage);


        cointitleimage.setBackgroundResource(R.drawable.coinanimation);
        AnimationDrawable anim = (AnimationDrawable) cointitleimage.getBackground();
        anim.setOneShot(false);
        anim.start();
        buildEnemy();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.addToBackStack("menuDailogfragment");
                DialogFragment fragobj = new menuFragment();
                fragobj.show(ft, "menuDailogfragment");
            }
        });

        chicken.setBackgroundResource(R.drawable.chickenanimation);
        chicken.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable anim = (AnimationDrawable) chicken.getBackground();
                anim.setOneShot(false);
                anim.start();

            }
        });
        final MediaPlayer mp = MediaPlayer.create(Level1.this, R.raw.bubble);
        rightlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defendPlayerWithCoins();
            }
        });

        leftlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        leftlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chicken.setX(event.getX());
                chicken.setY(event.getY());
                return false;
            }
        });

    }
    Handler handler2;
    private Runnable runnable2;
    private void UpdateCollison() {

                    for(ImageView image: enemyCoins){
                       if(image.getX()-chicken.getX()<=1){
                           image.setVisibility(View.GONE);
                           mainlayout.removeView(image);
                           coincounter++;
                           enemyCoins.remove(image);

                           coincountertext.setText(String.valueOf(coincounter));

                       }
                    }
                    for(ImageView image: enemyDragons){
                        if(image.getX()-chicken.getX()<=1){
                            image.setVisibility(View.GONE);
                            mainlayout.removeView(image);
                            enemyDragons.remove(image);

                        }
                    }


    }

    private void defendPlayerWithCoins() {

        final ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackgroundResource(R.drawable.coinanimation);
        imageView.setX(chicken.getX() + 150);
        imageView.setY(chicken.getY() + 50);
        mainlayout.addView(imageView);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.setOneShot(false);
        anim.start();
        objectanimator = ObjectAnimator.ofFloat(imageView, "x", 4000);
        objectanimator.setDuration(2000);
        objectanimator.start();


    }
    Handler handler;
    private Runnable runnable;

    ArrayList<ImageView> enemyCoins =new ArrayList<>();
    private void buildEnemy() {
        coin.setBackgroundResource(R.drawable.monsterchickenanimation);
        coin.post(new Runnable() {
            @Override
            public void run() {

                AnimationDrawable anim = (AnimationDrawable) coin.getBackground();
                anim.setOneShot(false);
                anim.start();
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(coin);
                constraintSet.connect(R.id.coin, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, mainlayout.getMaxWidth() / 2);
                constraintSet.connect(R.id.coin, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, mainlayout.getMaxHeight() / 2);
                constraintSet.applyTo(coin);



                 final int interval = 2000;
                 // 1 Second
                handler = new Handler();
               runnable = new Runnable(){
                    int i=-1;
                    public void run() {
                        i=i*(-1);
                        if(i==-1){
                            objectanimator = ObjectAnimator.ofFloat(coin, "y", 50);
                            objectanimator.setDuration(2000);
                            objectanimator.start();
                            handler.postDelayed(runnable, interval);
                        } else{
                            objectanimator = ObjectAnimator.ofFloat(coin, "y", 600);
                            objectanimator.setDuration(2000);
                            objectanimator.start();
                            handler.postDelayed(runnable, interval);
                        }
                        //emenyAttack();
                        throwSmallDragons();
                    }
                };
                handler.postDelayed(runnable, interval);
            }
        });


    }


    private Handler handler1;
    private Runnable runnable1;
    private void emenyAttack() {

        final ImageView imageView = new ImageView(getApplicationContext());
        imageView.setBackgroundResource(R.drawable.coinanimation);
        imageView.setX(coin.getX() - 150);
        imageView.setY(coin.getY() - 50);
        imageView.setLeft(8);
        imageView.setRight(8);
        imageView.setTop(8);
        imageView.setBottom(8);
        mainlayout.addView(imageView);
        enemyCoins.add(imageView);
        AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.setOneShot(false);
        anim.start();
        objectanimator = ObjectAnimator.ofFloat(imageView, "x", -300);
        objectanimator.setDuration(5000);
        objectanimator.start();

    }
    ArrayList<ImageView> enemyDragons =new ArrayList<>();
    private void throwSmallDragons() {

                final ImageView imageView = new ImageView(getApplicationContext());
                imageView.setBackgroundResource(R.drawable.smallmonsteranimation);
                imageView.setX(coin.getX() - 150);
                imageView.setY(coin.getY() - 50);
                imageView.setLeft(8);
                imageView.setRight(8);
                imageView.setTop(8);
                imageView.setBottom(8);
                mainlayout.addView(imageView,100,100);
                enemyDragons.add(imageView);
                AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
                anim.setOneShot(false);
                anim.start();
                objectanimator = ObjectAnimator.ofFloat(imageView, "x", -300);
                objectanimator.setDuration(10000);
                objectanimator.start();


    }


}
