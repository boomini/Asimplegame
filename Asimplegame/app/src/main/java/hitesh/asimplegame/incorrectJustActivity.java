package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class incorrectJustActivity extends Activity {

    private Question question;
    private TextView txtQuestion;
    private Button button1, button2, button3, btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect_just);

        Bundle incorrect = getIntent().getExtras();
        question=new Question(incorrect.getString("question"),incorrect.getString("qopta"),incorrect.getString("qoptb"),incorrect.getString("qoptc"),incorrect.getString("answer"));
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        // the textview in which the question will be displayed

        // the three buttons,
        // the idea is to set the text of three buttons with the options from question bank
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        btn_main = (Button) findViewById(R.id.main);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soundclass.play(Soundclass.Click);
                int state;
                state = getAnswer(button1.getText().toString());
                if(state==1){
                    button1.setBackgroundColor(Color.rgb(139,195,74));
                }else{
                    button1.setBackgroundColor(Color.rgb(244,67,54));
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soundclass.play(Soundclass.Click);
                int state;
                state = getAnswer(button2.getText().toString());
                if(state==1){
                    button2.setBackgroundColor(Color.rgb(139,195,74));
                }else{
                    button2.setBackgroundColor(Color.rgb(244,67,54));
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soundclass.play(Soundclass.Click);
                int state;
                state = getAnswer(button3.getText().toString());
                if(state==1){
                    button3.setBackgroundColor(Color.rgb(139,195,74));
                }else{
                    button3.setBackgroundColor(Color.rgb(244,67,54));
                }
            }
        });
        setQuestionView();
    }


    private void setQuestionView() {
        // the method which will put all things together
        txtQuestion.setText(question.getQUESTION());
        button1.setText(question.getOPTA());
        button1.setBackgroundColor(Color.WHITE);
        button2.setText(question.getOPTB());
        button2.setBackgroundColor(Color.WHITE);
        button3.setText(question.getOPTC());
        button3.setBackgroundColor(Color.WHITE);
    }

    public Integer getAnswer(String AnswerString) {
        int state;
        if (question.getANSWER().equals(AnswerString)) {
            state = 1;
        } else {
            // if unlucky start activity and finish the game
            state = 0;
            //finish();        }
        }
        return state;
    }

    public void goToMain(View o){
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}
