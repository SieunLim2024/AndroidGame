package com.example.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView dice; //주사위 이미지
    private static Button roll;//주사위 굴리는 버튼
    private static Button story;//스토리 보는 버튼
    private static int diceFace;//주사위 값
    private int[] images;//주사위 이미지 아이디 배열
    private static int[] map;//말판 별로 말 있는지 여부
    private static int[] mapView;//말판 이미지 뷰 아이디 배열
    private static int[][] mapImage;//말판 이미지 아이디 배열
    private static String[][] event;//이벤트 스크립트와 점수 배열
    private static int turn;//0은 유저 1은 컴퓨터
    private static int playerLocation;//말 칸 위치
    private static int computerLocation;//말 칸 위치
    private static int playerScore;//플레이어 점수
    private static int computerScore;//컴퓨터 점수
    private static boolean flagBonus;//한바퀴 돌고 보너스 받을거 있는지
    private static TextView palyerScoreV;//플레이어 점수 텍스트뷰
    private static TextView computerScoreV;//컴퓨터 점수 텍스트 뷰
    private static TextView end;//게임 종료시 안내하는 텍스트 뷰
    public static final int BONUSCOIN = 5; //한 바퀴 돌면 받는 보너스 코인의 수
    public static final int WINSCORE = 25;//목표 점수

    //    public static final int EVENTNUM=7;//이벤트 종류 수
    public static final int EVENT1 = 4; //이벤트 있는 말판 번호
    public static final int EVENT2 = 7;
    public static final int EVENT3 = 10;
    public static final int EVENT4 = 12;
    public static final int EVENT5 = 15;
    public static final int EVENT6 = 17;
    public static final int EVENT7 = 19;

    public static int eventNum = 0; //이벤트 번호
    private boolean storyFlag;//스토리 봤는지 여부

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        palyerScoreV = (TextView) findViewById(R.id.palyerScoreV);//플레이어 점수 텍스트뷰
        computerScoreV = (TextView) findViewById(R.id.computerScoreV);//컴퓨터 점수 텍스트뷰

        end= (TextView) findViewById(R.id.end);//게임 종료시 안내하는 텍스트 뷰

        dice = (ImageView) findViewById(R.id.dice);//주사위 이미지 뷰

        palyerMarker = (ImageView) findViewById(R.id.palyerMarker);//유저 말
        computerMarker = (ImageView) findViewById(R.id.computerMarker);//컴퓨터 말

        roll = (Button) findViewById(R.id.roll); //roll 버튼
        story = (Button) findViewById(R.id.story); //story 버튼

        //다이스 이미지 배열
        images = new int[]{
                R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3,
                R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6
        };

        //다이스 초기값
        diceFace = 1;

        //이벤트 스크립트와 점수 배열
        event = new String[][]{
                {"아무 일도 일어나지 않았다. 앞으로 나아가자!", "0"},
                {"당신은 금화가 담긴 주머니를 발견했다!", "5"},
                {"당신은 도적을 만나 금화를 뺏겼다!", "-5"},
                {"당신은 병에 걸려 치료비를 지불했다!", "-10"},
                {"당신은 금화가 담긴 상자를 발견했다!", "10"},
                {"당신은 모래 바람을 만나 금화를 잃어버렸다!", "-5"},
                {"당신은 주머니가 찢어져 금화가 사라진 걸 깨닫았다!", "-5"}
        };//대사,보너스 혹은 감점

        map=new int[20];//칸별로 말이 있는지 여부 아무도 없으면 0 유저가 있으면 1 컴퓨터가 있으면 2 둘다 있으면 3

        //imageView의 칸별 아이디 값
        mapView= new int[]{
                R.id.map0,R.id.map1,R.id.map2,R.id.map3,R.id.map4,R.id.map5,R.id.map6,R.id.map7,R.id.map8,
                R.id.map9,R.id.map10,R.id.map11,R.id.map12,R.id.map13,R.id.map14,R.id.map15,R.id.map16,
                R.id.map17,R.id.map18,R.id.map19
        };

        //이미지들의 아이디 값
        mapImage = new int[][]{
                //아무도 해당 칸에 없을때, 플레이어 말이 있을때, 컴퓨터의 말이 있을때, 두 말이 모두 있을때
                {R.drawable.map0_00,R.drawable.map0_10,R.drawable.map0_01,R.drawable.map0_11},
                {R.drawable.map1_00,R.drawable.map1_10,R.drawable.map1_01,R.drawable.map1_11},
                {R.drawable.map2_00,R.drawable.map2_10,R.drawable.map2_01,R.drawable.map2_11},
                {R.drawable.map3_00,R.drawable.map3_10,R.drawable.map3_01,R.drawable.map3_11},
                {R.drawable.map4_00,R.drawable.map4_10,R.drawable.map4_01,R.drawable.map4_11},
                {R.drawable.map5_00,R.drawable.map5_10,R.drawable.map5_01,R.drawable.map5_11},
                {R.drawable.map6_00,R.drawable.map6_10,R.drawable.map6_01,R.drawable.map6_11}
        };
    }

    //roll버튼을 누르면
    public void roll(View v) {
        //랜덤 값 생성
        Random random = new Random();
        diceFace = random.nextInt(6);
        dice.setImageResource(images[diceFace]);//주사위 이미지 바꿔줌



        if (turn == 0) {//유저의 순서면
            storyFlag = false;//스토리 안 본걸로 바꿔놓음
            move(playerLocation,playerScore,diceFace); //말(이미지 뷰) 위치 이동

            turn = 1;//턴 넘겨줌
        } else if (turn == 1 && storyFlag == true) {//컴퓨터 차례이고 스토리를 보고 왔다면
            move(computerLocation,computerScore,diceFace); //말(이미지 뷰) 위치 이동

            turn = 0;//턴 넘겨줌
        } else if (turn == 1 && storyFlag == false) {// 스토리를 보지 않았다면
            Toast.makeText(getApplicationContext(), "먼저 스토리를 봐주세요!", Toast.LENGTH_SHORT).show();
        }


    }

    //말을 옮겨주는 메쇠드
    public void move(int userLocation, int userScore, int diceFace) {
        //기존에 있던 말 제거하기=======================================================================
        ImageView oldMapView = (ImageView) findViewById(mapView[userLocation]);//말 옮기기전 위치의 말판
        if (turn == 0) {
            map[userLocation] -= 1;//유저 턴이면 유저 말 빼줌
        } else {
            map[userLocation] -= 2;
        }

        eventNum =checkEventNum(userLocation);//현 위치의 이벤트 번호 받아오기

        oldMapView.setImageResource(mapImage[eventNum][map[userLocation]]);//해당 말 뺀 이미지로 바꿈

        //다이스 값 적용하기===========================================================================
        userLocation += diceFace;//주사위 굴린 만큼 증가

        if (userLocation >= map.length) {//한바퀴 돌았을때
            userLocation -= map.length;//다시 0부터...
            flagBonus=true;
        }

        //말 새로 놓기================================================================================
        ImageView newMapView = (ImageView) findViewById(mapView[userLocation]);//새로 놓은 곳 말판 뷰

        if (turn == 0) {
            map[userLocation] += 1;//유저 턴이면 해당 위치에 유저 말 있다고 바꿈
        } else {
            map[userLocation] += 2;
        }

        eventNum =checkEventNum(userLocation);//현 위치의 이벤트 번호 받아오기

        newMapView.setImageResource(mapImage[eventNum][map[userLocation]]);//말판에 해당 말 더한 이미지로 바꿈
        //점수 계산===================================================================================
        calScore(userScore, Integer.parseInt(event[eventNum][1]));//점수 계산(이벤트로 얻은 것만)

        if(flagBonus){//한바퀴 돌아서 보너스 받아야한다면
            calScore(userScore, BONUSCOIN); //보너스 금화 받기
            flagBonus=false;
        }

        //스토리 버튼 보이기===========================================================================
        if (turn == 0) {//유저 턴이라면
            story.setVisibility(View.VISIBLE);//안 보이던 story 버튼 보여줌
        }
    }

    //현 위치의 이벤트 번호 리턴하는 메소드
    public static int checkEventNum(int userLocation){
        switch (userLocation) {//말 위치가
            case EVENT1://이벤트가 있는 칸이면...
                return 1;

            case EVENT2://이벤트가 있는 칸이면...
                return 2;

            case EVENT3://이벤트가 있는 칸이면...
                return 3;

            case EVENT4://이벤트가 있는 칸이면...
                return 4;

            case EVENT5://이벤트가 있는 칸이면...
                return 5;

            case EVENT6://이벤트가 있는 칸이면...
                return 6;

            case EVENT7://이벤트가 있는 칸이면...
                return 4;

            default://이벤트 없는 칸이라면
                return 0;
        }
    }

    //점수를 계산하는 메소드
    public static void calScore(int userScore, int score) {
        userScore += score;
        if(userScore<0){
            //점수가 0점 이하이면 0으로
            userScore=0;
        }
        if (turn == 0) {//유저 턴이면
            palyerScoreV.setText(playerScore);
        } else {
            computerScoreV.setText(computerScore);
        }

        if(userScore>WINSCORE){//목표 점수 이상이 된다면
            roll.setVisibility(View.GONE);//굴리기 버튼 안 보이게 만듬
            end.setText(String.format("승자는 %s 입니다!", (turn == 0) ? ("당신") : ("컴퓨터")));
        }
    }

    //스토리 버튼 누르면... 화면 전환하면서 대사 전달
    public void story(View v) {
        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra("script", event[eventNum][0]);//해당하는 이벤트 대사(스크립트) 넣어줌
        startActivity(intent);

        storyFlag = true;//스토리 봤는지 여부
        story.setVisibility(View.GONE);//다시 버튼 안 보이게 만듬
    }

}