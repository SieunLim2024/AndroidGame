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
    private int[][] location;
    private String[] event;
    private static int turn;//0은 유저 1은 컴퓨터
    private static int playerLocation;//말 칸 위치
    private static int computerLocation;//말 칸 위치
    private static int playerScore;//플레이어 점수
    private static int computerScore;//컴퓨터 점수
    private static boolean playerBonus;//한바퀴 돌고 보너스 받을거 있는지
    private static boolean computerBonus;//한바퀴 돌고 보너스 받을거 있는지
    private TextView palyerScoreV;
    private TextView computerScoreV;
    private ImageView palyerMarker;
    private ImageView computerMarker;
    public static final int BONUSCOIN=5; //한 바퀴 돌면 받는 보너스 코인의 수\
    public static final int EVENTNUM=7;//이벤트 종류 수
    public static final int EVENT1=5; //이벤트 있는 칸 번호
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

//        event=new String[EVENTNUM];
//        event= new String[]{
//                "당신은 금화가 담긴 주머니를 발견했다! \n ",
//                "",
//                "",
//                "",
//                "",
//                "",
//                ""
//
//        };//대사,(표정)
        //칸 별 좌표 값 =>이거 대신 말이 있는 이미지 없는 이미지로 할까 고민
        location= new int[20][2];

    }

    public void roll(View v){
        //랜덤 값 생성
        Random random = new Random();
        diceFace = random.nextInt(6);
        dice.setImageResource(images[diceFace]);//주사위 이미지 바꿔줌

        if (turn == 0) {//유저의 순서면
            playerLocation += diceFace;//주사위 굴린 만큼 증가

            if (playerLocation >= 33) {//한바퀴 돌았을때
                playerLocation -= 33;
                bonus(playerScore); //한 바퀴 돌았으니 보너스 금화 받기
            }
            move(playerLocation);
            turn=1;
        }else {
            computerLocation += diceFace;//주사위 굴린 만큼 증가

            if (computerLocation >= 33) {//한바퀴 돌았을때
                computerLocation -= 33;
                bonus(computerScore); //한 바퀴 돌았으니 보너스 금화 받기
            }
            move(computerLocation); //말(이미지 뷰) 위치 이동


            turn=0;
        }


    }
    public static void move(int userLocation) {
        if (turn == 0) {
            //maker(말) 이동해주는....


            switch(userLocation) {//유저 말 위치가
                case EVENT1://이벤트가 있는 칸이면 이벤트 보여줌...
                    event(EVENT1);//이벤트 발생
                    break;
            }


        }else {

        }
    }
    public static void bonus(int userScore){
        userScore+=BONUSCOIN;
    }

    public static void event(int EVENTNUM){

    }
}