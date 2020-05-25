package hitesh.asimplegame;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class TtsStt extends AppCompatActivity {


    private TextToSpeech tts;              // TTS 변수 선언
    private TextView TTSView;

    Intent intent;
    SpeechRecognizer mRecognizer;
    TextView STTView;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    String STTAns;

    Button restart, mainback4 ;

    private static final String TAG = QuestionActivity.class.getSimpleName();

    private List<Question> questionList;
    private int score = 0;
    private int questionID = 0;
    private Question currentQ;
    private TextView scored;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_stt);


        restart =(Button)findViewById(R.id.restart);
        mainback4 = (Button)findViewById(R.id.mainback4);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Soundclass.play(Soundclass.Click);
                Intent intent = new Intent(getApplicationContext(), TtsStt.class);
                finish();
                startActivity(intent);
            }
        });

        mainback4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Soundclass.play(Soundclass.Click);
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                finish();
                startActivity(intent);
            }
        });


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);

        STTView = (TextView) findViewById(R.id.Stttext);


        Button button = (Button) findViewById(R.id.Sttbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
            }
        });

        Button ttsbtn = (Button) findViewById(R.id.ttsbtn);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.KOREAN);
            }
        });
        ttsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(TTSView.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        QuizDBOpenHelper db = new QuizDBOpenHelper(this,1);  // my question bank class
        questionList = db.getAllQuestions();  // this will fetch all quetonall questions
        currentQ = questionList.get(questionID); // the current question

        TTSView = (TextView) findViewById(R.id.txtQuestion1);
        scored = (TextView) findViewById(R.id.score1);

        setQuestionView();


    }

    public void getAnswer(String AnswerString) {
        if (currentQ.getANSWER().equals(AnswerString)) {

            // if conditions matches increase the int (score) by 1
            // and set the text of the score view
            score++;
            scored.setText("Score : " + score);
            Toast toast = Toast.makeText(getApplicationContext(), "정답입니다", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "틀렸습니다", Toast.LENGTH_SHORT);
            toast.show();
            // if unlucky start activity and finish the game
            Intent intent = new Intent(TtsStt.this, ResultActivity.class);

            // passing the int value
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); // Put your score to your next
            startActivity(intent);
            finish();
        }

        if (questionID < 20) {
            // if questions are not over then do this
            currentQ = questionList.get(questionID);
            setQuestionView();

        } else {
            // if over do this
            Soundclass.play(Soundclass.Wow);
            Intent intent = new Intent(TtsStt.this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putInt("score", score); // Your score
            intent.putExtras(b); //
            // Put your score to your next
            startActivity(intent);
            finish();
        }
    }

    private void setQuestionView() {

        TTSView.setText(currentQ.getQUESTION());

        questionID++;
    }


    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int i) {
        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);
            STTView.setText(rs[0]);
            STTAns = STTView.getText().toString();

            switch(STTAns){
                case "일":
                    STTAns = "1";
                    break;
                case "이":
                    STTAns = "2";
                    break;
                case "삼":
                    STTAns = "3";
                    break;
                case "사":
                    STTAns = "4";
                    break;
                case "오":
                    STTAns = "5";
                    break;
                case "육":
                    STTAns = "6";
                    break;
                case "칠":
                    STTAns = "7";
                    break;
                case "팔":
                    STTAns = "8";
                    break;
                case "구":
                    STTAns = "9";
                    break;
                case "십":
                    STTAns = "10";
                    break;
            }
            getAnswer(STTAns);

        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };

}
