package com.example.androidgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        palyerScore=(TextView)findViewById(R.id.palyerScore);
        computerScore=(TextView)findViewById(R.id.computerScore);

        dice= (ImageView) findViewById(R.id.dice);

        palyer= (ImageView) findViewById(R.id.palyer);
        computer= (ImageView) findViewById(R.id.computer);

        button=(Button) findViewById(R.id.button);

        //다이스 이미지 배열
        images = new int[]{
                R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,
                R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6
        };
        //다이스 초기값
        diceFace=1;

        //말판 번호 배열
        int[] a= new int[33];

    }

    public void roll(View v){
        //랜덤 값 생성
        Random random = new Random();
        diceFace = random.nextInt(6);
        dice.setImageResource(images[diceFace]);

    }
}