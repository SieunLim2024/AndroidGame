package com.example.androidgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StoryActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.storyLayout);


        Intent intent=getIntent();//인텐트를 받아온다.
        String scriptText= intent.getStringExtra("script");//인텐트에서 script 값을 받아온다.

        TextView scriptView=(TextView)findViewById(R.id.scriptView);
        scriptView.setText(scriptText);//받아온 script(대사)를 textview에 넣어준다.


        Button close =(Button)findViewById(R.id.close);
        close.setOnClickListener(new onClickListener(){
            public void onClick(View v){
                finish();
                //버튼이 클릭되면 finish() 메소드 호출하여 현재 액티비티를 종료한다.
            }
        });
    }

}
