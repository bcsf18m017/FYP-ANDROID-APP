package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.fyp.Entities.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo=findViewById(R.id.splash_logo);

        boolean up = true;

        if (!up) {
            up = true;
            logo.startAnimation(animate(up));
        } else {
            up = false;
            logo.startAnimation(animate(up));
        }



        Paper.init(this);

        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {


                if(Paper.book().contains("phone"))
                {
                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
                    Date date1= null;
                    try {
                        date1 = f.parse(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    User u=new User("1","NOMAN TAHIR","H#7,St#15 Ramgarh Main Bazaar Mughalpura Lahore","03024677533","3520126349331",R.drawable.user,130000.0,"123",date1,"NOMI");
                    User.user=u;
                    Intent intent=new Intent(MainActivity.this,MainPage.class);
                    intent.putExtra("Caller","Home");
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }
    private Animation animate(boolean up) {
        Animation anim = AnimationUtils.loadAnimation(this, up ? R.anim.logo_anim : R.anim.logo_anim);
        anim.setInterpolator(new LinearInterpolator()); // for smooth animation
        return anim;
    }
}