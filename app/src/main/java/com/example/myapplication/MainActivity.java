package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout layout;

    private TextView tvHunger, tvHealth, tvEnergy, tvHappiness;
    private Button btnFeed, btnPlay, btnRest , button52 , button3;

    private int hunger = 100;
    private int health = 100;
    private int energy = 100;
    private int happiness = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHunger = findViewById(R.id.tv_hunger);
        tvHealth = findViewById(R.id.tv_health);
        tvEnergy = findViewById(R.id.tv_energy);
        tvHappiness = findViewById(R.id.tv_happiness);

        btnFeed = findViewById(R.id.btn_feed);
        btnPlay = findViewById(R.id.btn_play);
        btnRest = findViewById(R.id.btn_rest);
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ауууууу", Toast.LENGTH_SHORT).show();
            }
        });
        Button button3 = findViewById(R.id.button3);

        ObjectAnimator animator = ObjectAnimator.ofFloat(button3, "translationX", 0f, 500f);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();



        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                while (true) {
                    decreaseStats();
                    updateStats();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        };


        asyncTask.execute();

        btnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hunger += 10;
                health += 10;
                if (hunger > 100) {
                    hunger = 100;
                    health = 100;
                }
                updateStats();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                happiness += 10;
                if (happiness > 100) {
                    happiness = 100;
                }
                updateStats();
            }
        });


        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                energy += 10;
                if (energy > 100) {
                    energy = 100;
                }
                updateStats();
            }
        });
        Button button = findViewById(R.id.button52);
        final ConstraintLayout layout = findViewById(R.id.layout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.argb(255, (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
                layout.setBackgroundColor(color);
            }
        });
    }

    private void decreaseStats() {
        hunger -= 1;
        health -= 1;
        energy -= 3;
        happiness -= 2;
        if (hunger <= 0 || health <= 0 || energy <= 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Ваш тамагочи погиб")
                            .setMessage("Тамагочи не выдержало недостатка голода, здоровья или энергии!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            })
                            .show();
                }
            });
        }
    }



    private void updateStats() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvHunger.setText("Голод: " + hunger);
                tvHealth.setText("Здоровье: " + health);
                tvEnergy.setText("Энергия: " + energy);
                tvHappiness.setText("Счастье: " + happiness);
                if (hunger <= 30) {
                    Toast.makeText(MainActivity.this, "Покормите тамагочи!", Toast.LENGTH_SHORT).show();
                }
                if (health <= 30) {
                    Toast.makeText(MainActivity.this, "Лечите тамагочи!", Toast.LENGTH_SHORT).show();
                }
                if (energy <= 30) {
                    Toast.makeText(MainActivity.this, "Пустите тамагочи спать!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}