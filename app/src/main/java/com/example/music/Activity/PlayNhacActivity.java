package com.example.music.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music.Adapter.ViewPagerPlaylistnhac;
import com.example.music.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.music.Fragment.Fragment_dia_nhac;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Retrofit.RetrofitClient;
import com.example.music.Service.MyService;
import com.example.music.Util.ApiMusic;
import com.example.music.Util.Util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

//public class PlayNhacActivity extends AppCompatActivity {
//
//    Toolbar toolbar;
//    TextView txtTimesong, txtTotaltimesong;
//    SeekBar sktime;
//    ImageView imgplay, imgrepeat, imgnext, imgpre, imgrandom;
//    ViewPager viewPager;
//    CompositeDisposable compositeDisposable = new CompositeDisposable();
//    ApiMusic apiMusic;
//
//    public static List<BaiHat> baiHatList;
//    public static ViewPagerPlaylistnhac adapternhac;
//
//    Fragment_dia_nhac fragment_dia_nhac;
//    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;
//
//    MediaPlayer mediaPlayer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_play_nhac);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        initView();
//        GetIntent();
//        actionBar();
//        eventClick();
//    }
//
//    private void eventClick() {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (adapternhac.getItem(1) != null) {
//                    if (baiHatList.size() > 0) {
//                        fragment_dia_nhac.Playnhac(baiHatList.get(0).getHinhBaiHat());
//                        handler.removeCallbacks(this);
//                    } else {
//                        handler.postDelayed(this, 300);
//                    }
//                }
//            }
//        }, 500);
//
//        imgplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                    imgplay.setImageResource(R.drawable.play_70);
//                } else {
//                    mediaPlayer.start();
//                    imgplay.setImageResource(R.drawable.pause_70);
//                }
//            }
//        });
//
//    }
//
//    private void initView() {
//        toolbar = findViewById(R.id.toolbarplaynhac);
//        txtTimesong = findViewById(R.id.textviewtimesong);
//        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
//        sktime = findViewById(R.id.seekbarsong);
//        imgplay = findViewById(R.id.imagebuttonplay);
//        imgrepeat = findViewById(R.id.imagebuttrepeat);
//        imgnext = findViewById(R.id.imagebuttonnext);
//        imgrandom = findViewById(R.id.imagebuttonsuffle);
//        imgpre = findViewById(R.id.imagebuttonpre);
//        viewPager = findViewById(R.id.viewpagerplaynhac);
//        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
//
//        baiHatList = new ArrayList<>();
//
//        fragment_dia_nhac = new Fragment_dia_nhac();
//        fragmentPlayDanhSachCacBaiHat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
//
//        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
//        adapternhac.AddFragment(fragmentPlayDanhSachCacBaiHat);
//        adapternhac.AddFragment(fragment_dia_nhac);
//        viewPager.setAdapter(adapternhac);
//
//        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(1);
//        if (baiHatList.size() > 0) {
//            getSupportActionBar().setTitle(baiHatList.get(0).getTenBaiHat());
//            new PlayMp3().execute(baiHatList.get(0).getLinkBaiHat());
//            imgplay.setImageResource(R.drawable.pause_70);
//        }
//    }
//
//    private void actionBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void GetIntent() {
//        Intent intent = getIntent();
//        baiHatList.clear();
//        if (intent != null) {
//            if (intent.hasExtra("cakhuc")) {
//                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
//                //Toast.makeText(getApplicationContext(), baiHat.getTenBaiHat(), Toast.LENGTH_SHORT).show();
//                baiHatList.add(baiHat);
//
//            }
//
//            if (intent.hasExtra("cacbaihat")) {
//                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
////           for (int i = 0; i < baiHatArrayList.size(); i++){
////               Log.d("PlayList", baiHatArrayList.get(i).getTenBaiHat());
////           }
//                baiHatList = baiHatArrayList;
//            }
//        }
//    }
//
//    class PlayMp3 extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return strings[0];
//        }
//
//        @Override
//        protected void onPostExecute(String baihat) {
//            try {
//                super.onPostExecute(baihat);
//                mediaPlayer = new MediaPlayer();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mediaPlayer.stop();
//                        mediaPlayer.reset();
//                    }
//                });
//
//
//                mediaPlayer.setDataSource(baihat);
//                mediaPlayer.prepare();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            mediaPlayer.start();
//            TimeSong();
//        }
//    }
//
//    private void TimeSong() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
//        sktime.setMax(mediaPlayer.getDuration());
//    }
//
//
//}


//public class PlayNhacActivity extends AppCompatActivity {
//
//    Toolbar toolbar;
//    TextView txtTimesong, txtTotaltimesong;
//    SeekBar sktime;
//    ImageView imgplay, imgrepeat, imgnext, imgpre, imgrandom;
//    ViewPager viewPager;
//    CompositeDisposable compositeDisposable = new CompositeDisposable();
//    ApiMusic apiMusic;
//
//    public static List<BaiHat> baiHatList;
//    public static ViewPagerPlaylistnhac adapternhac;
//
//    Fragment_dia_nhac fragment_dia_nhac;
//    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;
//
//    Boolean flag = true;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_play_nhac);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        initView();
//        GetIntent();
//        actionBar();
//        eventClick();
//    }
//
//    private void eventClick() {
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (adapternhac.getItem(1) != null) {
//                    if (baiHatList.size() > 0) {
//                        fragment_dia_nhac.Playnhac(baiHatList.get(0).getHinhBaiHat());
//                        handler.removeCallbacks(this);
//                    } else {
//                        handler.postDelayed(this, 300);
//                    }
//                }
//            }
//        }, 500);
//
//        imgplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                playnhac(baiHatList.get(0).getLinkBaiHat());
//            }
//        });
//
//    }
//
//    private void initView() {
//        toolbar = findViewById(R.id.toolbarplaynhac);
//        txtTimesong = findViewById(R.id.textviewtimesong);
//        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
//        sktime = findViewById(R.id.seekbarsong);
//        imgplay = findViewById(R.id.imagebuttonplay);
//        imgrepeat = findViewById(R.id.imagebuttrepeat);
//        imgnext = findViewById(R.id.imagebuttonnext);
//        imgrandom = findViewById(R.id.imagebuttonsuffle);
//        imgpre = findViewById(R.id.imagebuttonpre);
//        viewPager = findViewById(R.id.viewpagerplaynhac);
//        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);
//
//        baiHatList = new ArrayList<>();
//
//        fragment_dia_nhac = new Fragment_dia_nhac();
//        fragmentPlayDanhSachCacBaiHat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
//
//        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
//        adapternhac.AddFragment(fragmentPlayDanhSachCacBaiHat);
//        adapternhac.AddFragment(fragment_dia_nhac);
//        viewPager.setAdapter(adapternhac);
//
//        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(1);
//        if (baiHatList.size() > 0) {
//            getSupportActionBar().setTitle(baiHatList.get(0).getTenBaiHat());
//            playnhac(baiHatList.get(0).getLinkBaiHat());
//            imgplay.setImageResource(R.drawable.pause_70);
//        }
//    }
//
//    private void playnhac(String url) {
//        Intent intent = new Intent(getApplicationContext(), MyService.class);
//        intent.putExtra("MUSIC_URL", url);
//        startService(intent);
//        if (flag) {
//            imgplay.setImageResource(R.drawable.pause_70);
//            flag = false;
//        } else {
//            imgplay.setImageResource(R.drawable.play_70);
//            flag = true;
//        }
//    }
//
//    private void actionBar() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void GetIntent() {
//        Intent intent = getIntent();
//        baiHatList.clear();
//        if (intent != null) {
//            if (intent.hasExtra("cakhuc")) {
//                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
//
//                baiHatList.add(baiHat);
//
//            }
//
//            if (intent.hasExtra("cacbaihat")) {
//                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
//
//                baiHatList = baiHatArrayList;
//            }
//        }
//    }
//
//
//
//    private void TimeSong() {
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
//        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
//        sktime.setMax(mediaPlayer.getDuration());
//    }
//
//
//}


public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageView imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPager;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiMusic apiMusic;

    public static List<BaiHat> baiHatList;
    public static ViewPagerPlaylistnhac adapternhac;

    Fragment_dia_nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragmentPlayDanhSachCacBaiHat;

    Boolean flag = true;

    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initView();
        GetIntent();
        actionBar();
        eventClick();
    }

    private BroadcastReceiver timeUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if ("UPDATE_TIME".equals(intent.getAction())) {
                    int currentTime = intent.getIntExtra("CURRENT_TIME", 0);
                    int totalTime = intent.getIntExtra("TOTAL_TIME", 0);

                    // Cập nhật thời gian hiện tại
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss", Locale.getDefault());
                    txtTimesong.setText(simpleDateFormat.format(currentTime));
                    sktime.setProgress(currentTime);

                    // Cập nhật tổng thời gian
                    if (totalTime > 0) {
                        sktime.setMax(totalTime);
                        txtTotaltimesong.setText(simpleDateFormat.format(totalTime));
                    }
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("UPDATE_TIME");
        registerReceiver(timeUpdateReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(timeUpdateReceiver);
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (baiHatList.size() > 0) {
                        fragment_dia_nhac.Playnhac(baiHatList.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playnhac(baiHatList.get(0).getLinkBaiHat());
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.repeat_select_icon);
                        imgrandom.setImageResource(R.drawable.suffle_icon);
                    }
                    imgrepeat.setImageResource(R.drawable.repeat_select_icon);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.repeat_50);
                    repeat = false;
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;

                        imgrandom.setImageResource(R.drawable.shuffle_select_icon);
                        imgrepeat.setImageResource(R.drawable.repeat_50);
                    }
                    imgrandom.setImageResource(R.drawable.shuffle_select_icon);
                    checkrandom = true;
                } else {
                    imgrandom.setImageResource(R.drawable.suffle_icon);
                    checkrandom = false;
                }
            }
        });

        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("ACTION", "SEEK_TO");
                intent.putExtra("SEEK_POSITION", seekBar.getProgress());
                startService(intent);
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatList.size() > 0) {
                    if (position < baiHatList.size()) {
                        position++;
                        if (repeat) {
                            if (position == 0) {
                                position = baiHatList.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        if (position > baiHatList.size() - 1) {
                            position = 0;
                        }

                        Intent intent = new Intent(getApplicationContext(), MyService.class);
                        intent.putExtra("ACTION", "NEW_SONG");
                        intent.putExtra("MUSIC_URL", baiHatList.get(position).getLinkBaiHat());
                        startService(intent);

                        imgplay.setImageResource(R.drawable.pause_70);
                        flag = false;

                        fragment_dia_nhac.Playnhac(baiHatList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatList.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiHatList.size() > 0) {
                    if (position < baiHatList.size()) {
                        position--;
                        if (position < 0) {
                            position = baiHatList.size() - 1;
                        }
                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        Intent intent = new Intent(getApplicationContext(), MyService.class);
                        intent.putExtra("ACTION", "NEW_SONG");
                        intent.putExtra("MUSIC_URL", baiHatList.get(position).getLinkBaiHat());
                        startService(intent);

                        imgplay.setImageResource(R.drawable.pause_70);
                        flag = false;

                        fragment_dia_nhac.Playnhac(baiHatList.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(baiHatList.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);

                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimesong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttonplay);
        imgrepeat = findViewById(R.id.imagebuttrepeat);
        imgnext = findViewById(R.id.imagebuttonnext);
        imgrandom = findViewById(R.id.imagebuttonsuffle);
        imgpre = findViewById(R.id.imagebuttonpre);
        viewPager = findViewById(R.id.viewpagerplaynhac);
        apiMusic = RetrofitClient.getInstance(Util.BASE_URL).create(ApiMusic.class);

        baiHatList = new ArrayList<>();

        fragment_dia_nhac = new Fragment_dia_nhac();
        fragmentPlayDanhSachCacBaiHat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();

        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragmentPlayDanhSachCacBaiHat);
        adapternhac.AddFragment(fragment_dia_nhac);
        viewPager.setAdapter(adapternhac);

        fragment_dia_nhac = (Fragment_dia_nhac) adapternhac.getItem(1);
        if (baiHatList.size() > 0) {
            getSupportActionBar().setTitle(baiHatList.get(0).getTenBaiHat());
            playnhac(baiHatList.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.pause_70);
        }
    }

    private void playnhac(String url) {
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        if (flag) {
            intent.putExtra("ACTION", "PLAY");
            imgplay.setImageResource(R.drawable.pause_70);
        } else {
            intent.putExtra("ACTION", "PAUSE");
            imgplay.setImageResource(R.drawable.play_70);
        }
        startService(intent);
        flag = !flag;
    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MyService.class);
                stopService(intent2);
                baiHatList.clear();
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        baiHatList.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");

                baiHatList.add(baiHat);

            }

            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");

                baiHatList.addAll(baiHatArrayList);

            }
        }
    }

    private  void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 300);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Intent intent2 = new Intent(getApplicationContext(), MyService.class);
        stopService(intent2);
        baiHatList.clear();
    }
}

