package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;// ...
// Initialize Firebase Auth
    EditText userId, userPwd;
    Button loginBtn;
    TextView signUp;
    String loginid, loginpwd;
    private static final String TAG = "EmailLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_signin);
        userId = findViewById(R.id.LoginId);
        userPwd = findViewById(R.id.LoginPw);
        loginBtn = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    //@Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.login:
                loginid = userId.getText().toString();
                loginpwd = userPwd.getText().toString();
                startLogin(loginid,loginpwd);
                break;

            case R.id.signup : // 회원가입
                Intent intent = new Intent(this, JoinActivity.class);
                startActivity(intent);
                break;

        }
    }
    private void startLogin(String loginid, String loginpwd) {

        mAuth.signInWithEmailAndPassword(loginid, loginpwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


  /*  public void onClickJoin(View o){
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }*/
  /*  public void onClickLogin(View o){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }*/
}
