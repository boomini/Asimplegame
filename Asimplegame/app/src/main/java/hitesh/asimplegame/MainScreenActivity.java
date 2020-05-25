package hitesh.asimplegame;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreenActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button gamestartbtn = (Button) findViewById(R.id.gamestartbtn);
        Button settingbtn = (Button) findViewById(R.id.settingbtn);
        Button helpbtn = (Button) findViewById(R.id.helpbtn);
        Button ttssttbtn =(Button) findViewById(R.id.ttssttbtn);
        Button rankingbtn=(Button) findViewById(R.id.rankingbtn);
        Button incorrect= (Button) findViewById(R.id.incorrectbtn);


//        Intent intent = new Intent(
//                getApplicationContext(),//현재제어권자
//                Serviceclass.class); // 이동할 컴포넌트
//        startService(intent);

        Soundclass.initSounds(getApplicationContext());


        gamestartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), InGameActivity.class);
                //finish();
                startActivity(intent);
            }
        });

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                finish();
                startActivity(intent);

            }
        });


        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);
                AlertDialog.Builder helpbox = new AlertDialog.Builder(MainScreenActivity.this);
                helpbox.setTitle("How to play this game");
                helpbox.setMessage("Select the correct answer from the view and click on it.");
                helpbox.setNeutralButton("Back", null);
                helpbox.create().show();
            }
        });
        rankingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);
                Intent intent = new Intent(MainScreenActivity.this, RankActivity.class);
                startActivity(intent);

            }
        });

        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);
                Intent intent = new Intent(MainScreenActivity.this, IncorrectActivity.class);
                startActivity(intent);

            }
        });



    }

}
