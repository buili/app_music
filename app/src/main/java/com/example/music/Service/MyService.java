package com.example.music.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;
    private String musicUrl;
    private Handler handler = new Handler();
    private Runnable updateTimeTask;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setLooping(true);

        updateTimeTask = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer.isPlaying()) {
                    Intent updateIntent = new Intent("UPDATE_TIME");
                    updateIntent.putExtra("CURRENT_TIME", mediaPlayer.getCurrentPosition());
                    sendBroadcast(updateIntent);
                    handler.postDelayed(this, 1000); // Cập nhật mỗi giây
                }
            }
        };
    }

    private void prepareMediaPlayer() {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.setOnPreparedListener(mp -> {
                mp.start();

                Intent timeIntent = new Intent("UPDATE_TIME");
                timeIntent.putExtra("TOTAL_TIME", mediaPlayer.getDuration());
                sendBroadcast(timeIntent);

                handler.post(updateTimeTask); // Bắt đầu cập nhật thời gian
            });
            mediaPlayer.setOnErrorListener((mp, what, extra) -> true);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra("ACTION")) {
            String action = intent.getStringExtra("ACTION");
            if ("NEW_SONG".equals(action)) {
                musicUrl = intent.getStringExtra("MUSIC_URL");
                prepareMediaPlayer();
            } else if ("PLAY".equals(action)) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    handler.post(updateTimeTask); // Bắt đầu cập nhật thời gian
                }
            } else if ("PAUSE".equals(action)) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    handler.removeCallbacks(updateTimeTask); // Dừng cập nhật thời gian
                }
            } else if ("SEEK_TO".equals(action)) {
                int position = intent.getIntExtra("SEEK_POSITION", 0);
                mediaPlayer.seekTo(position);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateTimeTask);
        super.onDestroy();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


//public class MyService extends Service {
//
//    private MediaPlayer mediaPlayer;
//    private String musicUrl; // Khai báo biến cho URL của bài hát
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setLooping(true);
//    }
//
//    private void prepareMediaPlayer() {
//        try {
//            mediaPlayer.reset(); // Reset trước khi thiết lập dữ liệu mới
//            mediaPlayer.setDataSource(musicUrl);
//            mediaPlayer.setOnPreparedListener(mp -> {
//                mp.start(); // Bắt đầu phát nhạc khi MediaPlayer đã chuẩn bị xong
//            });
//            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
//                // Xử lý lỗi nếu có
//                return true; // Đã xử lý lỗi
//            });
//            mediaPlayer.prepareAsync(); // Chuẩn bị MediaPlayer không đồng bộ
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent != null && intent.hasExtra("MUSIC_URL")) {
//            musicUrl = intent.getStringExtra("MUSIC_URL");
//            if (musicUrl != null && !musicUrl.isEmpty()) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                } else {
//                    prepareMediaPlayer();
//                }
//            } else {
//                showToast("Invalid music URL");
//            }
//        }
//        return START_STICKY; // Đảm bảo dịch vụ không bị hủy khi bị gián đoạn
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mediaPlayer != null) {
//            if (mediaPlayer.isPlaying()) {
//                mediaPlayer.stop();
//            }
//            mediaPlayer.release(); // Giải phóng tài nguyên
//            mediaPlayer = null;
//        }
//        super.onDestroy();
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}
