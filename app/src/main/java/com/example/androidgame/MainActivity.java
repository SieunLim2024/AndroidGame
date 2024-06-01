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
    private ImageView dice;
    private Button button;
    private static int diceFace;
    private int[] images;
    private static int turn;//0은 유저 1은 컴퓨터
    private static int player;//말 위치
    private static int computer;//말 위치
    private static int playerScore;//플레이어 점수
    private static int computerScore;//컴퓨터 점수
    private static boolean playerCnt;//한바퀴 돌고 보너스 받을거 있는지
    private static boolean computerCnt;//한바퀴 돌고 보너스 받을거 있는지
    private TextView palyerScoreV;
    private TextView computerScoreV;
    private ImageView palyerMarker;
    private ImageView computerMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        palyerScoreV=(TextView)findViewById(R.id.palyerScoreV);
        computerScoreV=(TextView)findViewById(R.id.computerScoreV);

        dice= (ImageView) findViewById(R.id.dice);

        palyerMarker= (ImageView) findViewById(R.id.palyerMarker);//유저 말
        computerMarker= (ImageView) findViewById(R.id.computerMarker);//컴퓨터 말

        button=(Button) findViewById(R.id.button); //roll 버튼

        //다이스 이미지 배열
        images = new int[]{
                R.drawable.dice_1,R.drawable.dice_2,R.drawable.dice_3,
                R.drawable.dice_4,R.drawable.dice_5,R.drawable.dice_6
        };
        //다이스 초기값
        diceFace=1;

        //말판 번호 배열
        //int[] a= new int[33];

    }

    public void roll(View v){
        //랜덤 값 생성
        Random random = new Random();
        diceFace = random.nextInt(6);
        dice.setImageResource(images[diceFace]);//주사위 이미지 바꿔줌

        if (turn == 0) {
            player += diceFace;//주사위 굴린 만큼 증가

            if (player >= 33) {//한바퀴 돌았을때
                player -= 33;
                playerCnt=true;//한 바퀴 돌면 보너스 금화 받을거
            }
            move(turn);
            turn=1;
        }else {
            computer += diceFace;//주사위 굴린 만큼 증가

            if (computer >= 33) {//한바퀴 돌았을때
                computer -= 33;
                computerCnt=true;//한 바퀴 돌면 보너스 금화 받을거 => 굳이 변수 만들지 말고 바로 증가시키는 함수 만들어서 불러줘도 좋을듯
            }
            move(turn);
            turn=0;
        }

    }
    public static void move(int turn) {
        if (turn == 0) {
            switch(player) {
                case 5://이벤트가 있는 칸이면 이벤트 보여줌...

                    break;

            }
            //maker(말) 이동해주는 코드... => 이것저것 하려면 매개변수랑 이것저것 수정해야할듯

            if(playerCnt) {//보너스 받을 거 있으면...여기서 말고 그냥 따로 함수 만들어도 좋을듯...


            }
        }else {

        }
    }
}