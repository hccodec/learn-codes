package com.physswjtu.srtp2019;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.physswjtu.srtp2019.Utils.OtherUtil;
import com.physswjtu.srtp2019.widgets.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.physswjtu.srtp2019.Utils.SoundUtil.NOTIFICATION2;
import static com.physswjtu.srtp2019.Utils.SoundUtil.ding;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.video)
    VideoView videoView;
    @BindView(R.id.play)
    Button play;
    @BindView(R.id.pause)
    Button pause;
    @BindView(R.id.replay)
    Button replay;
    @BindView(R.id.url)
    EditText url2;

    void tell(String msg) {
        Log.d("hbj", msg);
        Toast.makeText(VideoActivity.this, msg, Toast.LENGTH_SHORT).show();
        ding(VideoActivity.this, NOTIFICATION2);
    }

    void initVideoPath() {
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(url));
    }

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OtherUtil.setFullScreen(getWindow());
        OtherUtil.setStatusBar(getWindow());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
        setContentView(R.layout.content_videotest);
        ButterKnife.bind(this);
        url = "https://jdvodrvfb210d.vod.126.net/jdvodrvfb210d/nos/mp4/2018/10/09/1010590533_0cb9e85d4d084a1b9d6a80c38ef6d7ad_sd.mp4?ak=7909bff134372bffca53cdc2c17adc27a4c38c6336120510aea1ae1790819de8056244b5e93f34dd9ba5d8908416dec5bd828697e30f779008e779363084d3693059f726dc7bb86b92adbc3d5b34b132865b8202ddce3c4c76932d345f9312c5ab1751379953e9446561f60f0e4ab9c0";
        initVideoPath();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                tell("准备完毕 可以播放");
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                tell("播放完毕");
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                tell("播放失败");
                return false;
            }
        });

        videoView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if (videoView.isPlaying()) {
                    videoView.pause();
                    videoView.stopPlayback();
                    videoView.setVideoURI(Uri.parse(url));
                    videoView.start();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null)
            videoView.suspend();
    }
}
