package hitesh.asimplegame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InGameActivity extends AppCompatActivity {
    static private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);


        Button easybtn =(Button)findViewById(R.id.easybtn);
        easybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);
                level =1;
                Intent intent = new Intent(InGameActivity.this, QuestionActivity.class);
                //finish();

                Bundle bundle = new Bundle();
                bundle.putInt("level", level);// Your score
                intent.putExtras(bundle); // Put your score to your next
                startActivity(intent);
            }
        });

        Button hardbtn =(Button)findViewById(R.id.hardbtn);
        hardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);
                level =2;
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                //finish();
                Bundle bundle = new Bundle();
                bundle.putInt("level", level);// Your score
                intent.putExtras(bundle); // Put your score to your next
                startActivity(intent);
            }
        });

        Button ttssttbtn =(Button)findViewById(R.id.ttssttbtn);
        ttssttbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Soundclass.play(Soundclass.Click);

                Intent intent = new Intent(getApplicationContext(), TtsStt.class);
                finish();
                startActivity(intent);
            }
        });


        Button mainback5 = (Button)findViewById(R.id.mainback5);
        mainback5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Soundclass.play(Soundclass.Click);
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                finish();
                startActivity(intent);
            }
        });

    }



}
