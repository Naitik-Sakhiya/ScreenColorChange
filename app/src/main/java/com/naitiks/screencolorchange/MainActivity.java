package com.naitiks.screencolorchange;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout parentView;
    private Button startBtn, stopBtn;
    private Handler timeHandler;
    private Runnable runnableThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parentView = (LinearLayout) findViewById(R.id.view_parent);
        startBtn = (Button) findViewById(R.id.btn_start);
        stopBtn = (Button) findViewById(R.id.btn_stop);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        timeHandler = new Handler();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start :
                startRandomColors();
                break;
            case R.id.btn_stop:
                stopRandomColors();
                break;
            default: break;
        }
    }

    private int getRandomColor(){
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        return color;
    }
    private void startRandomColors(){
        runnableThread = new Runnable() {
            @Override
            public void run() {
                setColor();
            }
        };
        runnableThread.run();
    }

    private void setColor(){
        parentView.setBackgroundColor(getRandomColor());
        timeHandler.postDelayed(runnableThread, 500);
    }

    private void stopRandomColors(){
        timeHandler.removeCallbacks(runnableThread);
    }

    @Override
    public void onPause(){
        super.onPause();
        stopRandomColors();
    }

}
