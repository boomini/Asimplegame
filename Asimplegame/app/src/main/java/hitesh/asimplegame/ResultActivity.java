package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ResultActivity extends Activity {

	private static final String TAG = "firestore db";

	FirebaseFirestore db = FirebaseFirestore.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);


		TextView textResult = (TextView) findViewById(R.id.textResult);
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
		String uid=user.getEmail();
		textResult.setText("Your score is " + " " + score + ". Thanks for playing my game.");
		setList(score,uid);
	}

	public void playagain(View o) {
		Soundclass.play(Soundclass.Click);
			Intent intent = new Intent(this, InGameActivity.class);
			startActivity(intent);
			finish();
	}

	public void checkincorrect(View o){
		Soundclass.play(Soundclass.Click);

		Bundle incorrect = getIntent().getExtras();
		/*Question question=new Question(incorrect.getString("question"),incorrect.getString("question"),incorrect.getString("question"),incorrect.getString("question"),incorrect.getString("question")))
		incorrect.putString("question",incorrect.getString("question"),);
		incorrect.putString("qopta",incorrect.getString("qopta"),;
		incorrect.putString("qoptb",incorrect.getString("qoptb"),;
		incorrect.putString("qoptc",incorrect.getString("qoptc");
		incorrect.putString("answer",incorrect.getString("answer"),);*/
		Intent intent = new Intent(ResultActivity.this, incorrectJustActivity.class);
		intent.putExtras(incorrect);
		startActivity(intent);
	}

	public void ranking(View o){
		Soundclass.play(Soundclass.Click);
		Intent intent = new Intent(ResultActivity.this, RankActivity.class);
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");
		Bundle c = new Bundle();
		c.putInt("score", score);// Your score
		intent.putExtras(c); // Put your score to your next
		startActivity(intent);
	}

	public void mainback6(View v){
		Soundclass.play(Soundclass.Click);
		Intent intent = new Intent(ResultActivity.this, MainScreenActivity.class);
		startActivity(intent);
	}
	private void setList(int score,String uid){
		//클라우드 파이어스토에 쓰기 하도록 하자.
		// Create a new user with a first and last name
		Map<String, Object> rank = new HashMap<>();
		rank.put("grade", score);
		rank.put("uid",uid);

		// Add a new document with a generated ID
		db.collection("ranking")
				.add(rank)
				.addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
					@Override
					public void onSuccess(DocumentReference documentReference) {
						Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Log.w(TAG, "Error adding document", e);
					}
				});
	}
}