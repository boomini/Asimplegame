package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import hitesh.asimplegame.LoginActivity;
import hitesh.asimplegame.QuestionActivity;
import hitesh.asimplegame.R;


public class JoinActivity extends Activity {
    EditText id, pwd, pwConfirm, name, nickname, phone;
    String tid, tpwd, tpwConfirm, tname, tnickname, tphone;
    boolean pwCheck;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        // ...

        mAuth = FirebaseAuth.getInstance();
        id = (EditText) findViewById(R.id.InputId);
        pwd = (EditText) findViewById(R.id.InputPw);
        pwConfirm = (EditText) findViewById(R.id.InputConfirmPw);
        name = (EditText) findViewById(R.id.InputName);
        nickname = (EditText) findViewById(R.id.InputNum);
        phone = (EditText) findViewById(R.id.phone);


        //Button joinBtn = (Button) findViewById(R.id.Joinbtn); // 회원가입

    } //onCreate() 종료

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }


    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Joinbtn: // 회원가입 버튼
                tid = id.getText().toString();
                tpwd = pwd.getText().toString();
                tname = name.getText().toString();
                tnickname = nickname.getText().toString();
                tphone = phone.getText().toString();
                tpwConfirm = pwConfirm.getText().toString();

                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", tpwd);

                //유효성 검사
                if(tid.trim().length() == 0 || tpwd.trim().length() == 0 || tpwConfirm.trim().length() == 0 ||tname.trim().length() == 0 || tphone.trim().length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("minsu", "공백 발생");
                    return;
                }

                else if(!tpwd.equals(tpwConfirm)){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();


                }

                else if(!pwCheck){
                    Toast.makeText(this, "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();

                }

                else if(spaceCheck(tpwd)){
                    Toast.makeText(this, "비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();

                }
                else {

                    mAuth.createUserWithEmailAndPassword(tid, tpwd)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                      //  updateUI(user);
                                         gotoNext();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(JoinActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }

        }


    }



    public boolean spaceCheck(String spaceCheck) // 문자열 안에 스페이스 체크
    {
        for(int i = 0; i < spaceCheck.length(); i++)
        {

            if(spaceCheck.charAt(i) == ' ')
                return true;

        }
        return false;
    }

    private void gotoNext() {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
    }

}