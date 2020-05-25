package hitesh.asimplegame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    static boolean bgm_on_off = true;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button bgmcheck = (Button) findViewById(R.id.bgmcheck);
        Button mainback = (Button) findViewById(R.id.main_back);

        Soundclass.initSounds(getApplicationContext());

        bgmcheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Soundclass.play(Soundclass.Click);
               // MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click);
                //mediaPlayer.start();

                if (bgm_on_off == true) {
                    Log.d("test", "액티비티-서비스 시작버튼클릭");
                    Intent intent = new Intent(
                            getApplicationContext(),//현재제어권자
                            Serviceclass.class); // 이동할 컴포넌트
                    startService(intent); // 서비스 시작v
                    bgm_on_off = false;
                    Toast on_off = Toast.makeText(getApplication(),"배경음이 켜졌습니다", Toast.LENGTH_SHORT);
                    on_off.show();

                } else if (bgm_on_off == false) {
                    // 서비스 종료하기
                    Log.d("test", "액티비티-서비스 종료버튼클릭");
                    Intent intent = new Intent(getApplicationContext(),//현재제어권자
                            Serviceclass.class); // 이동할 컴포넌트
                    stopService(intent); // 서비스 종료
                    bgm_on_off = true;
                    Toast on_off = Toast.makeText(getApplication(),"배경음이 꺼졌습니다", Toast.LENGTH_SHORT);
                    on_off.show();
                }
            }
        });

        mainback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });


    }
}
