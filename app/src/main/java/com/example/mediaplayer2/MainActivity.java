package com.example.mediaplayer2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button button;
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //타이틀바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // VideoView : 동영상을 재생하는 뷰
        vv = (VideoView) findViewById(R.id.videoView1);

        // MediaController : 특정 View 위에서 작동하는 미디어 컨트롤러 객체
        MediaController mc = new MediaController(this);
        mc.setAnchorView(vv);       //미디어 컨트롤러에 비디오뷰 지정
        vv.setMediaController(mc);  // Video View 에 사용할 컨트롤러 지정

        button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //갤러리로 Intent한다
                Intent intent = new Intent(Intent.ACTION_PICK);
                //갤러리 내의 비디오 형식의 파일들만 가져온다
                intent.setType("video/*");
                //값을 가지고 onActivityResult로 이동
                startActivityForResult(intent, 1);
            }
        });

    } // end of onCreate


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    //동영상 재생
                    vv.setVideoURI(data.getData());
                    vv.start();
                }
        }
    }
} // end of class
