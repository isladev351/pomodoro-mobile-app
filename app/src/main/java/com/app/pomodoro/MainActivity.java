package com.app.pomodoro;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity {

    Button btnPause;
    Button btnStart;
    TextView textTime;
    TextView textStatus;

    public int status = 0;
    public long ins = 20000;
    public long timeInput = 30000;
    public long startInput = timeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CountDownTime(timeInput);
        Break(status);
       // Sound(status);
    }
    //CountDownTimer
    public void CountDownTime(final long timeInput)   {
        textTime = findViewById(R.id.text_time);
        textStatus = findViewById(R.id.text_status);
        btnStart = findViewById(R.id.btn_start);
        btnPause = findViewById(R.id.btn_pause);

        final CountDownTimer countdowntimer = new CountDownTimer(timeInput, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                        DecimalFormat f = new DecimalFormat("00");
                        //long hour = (millisUntilFinished / 3600000) % 24;
                        long min = (millisUntilFinished / 60000) % 60;
                        long sec = (millisUntilFinished / 1000) % 60;
                        textTime.setText(f.format(min) + ":" + f.format(sec));
            }
            @Override
            public void onFinish() {
                    status += 1;
                    Break(status);
                    btnStart.setEnabled(true);
            }
        };

        //countDownTimer to break time
        if(timeInput == ins )countdowntimer.start();

        //btn start
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = 0;
                textStatus.setText("Berjalan");
                countdowntimer.start();
                btnStart.setEnabled(false);
            }
        });

        //btn pause/berhenti
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countdowntimer.cancel();
                btnStart.setEnabled(true);
            }
        });


    }
    //free
    public void Break(int status){
        if(status == 0 ){
            textTime.setText("Mulai!");
        }else if(status == 1){
            textStatus.setText("Istirahat!");
            CountDownTime(ins);
        }else{
              CountDownTime(startInput);
              textStatus.setText("");
              textTime.setText("Selesai!");
        }
    }

}//END Class
























