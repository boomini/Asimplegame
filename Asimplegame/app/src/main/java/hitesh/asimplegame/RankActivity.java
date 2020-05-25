package hitesh.asimplegame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RankActivity extends Activity {
    private static final String TAG = "db";
    private Activity activity;
    public static Context context;
    private FirebaseAuth mAuth;
    private ArrayList<Rank_item> m_arr;
    private RankAdapter adapter;
    ListView list;
    Button btn;
    private int count=0;

//    FirebaseUser user = mAuth.getCurrentUser();

    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        // Intent i = getIntent();
        //      Bundle c = getIntent().getExtras();
//        int score = c.getInt("score");
        btn = (Button) findViewById(R.id.btn_gomain);
        btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RankActivity.this, MainScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
        // Create a new user with a first and last name

        init();
    }

    public void init(){
        list=(ListView)findViewById(R.id.listView);
        // setList(score);
        getList();
    }
    /*  private void setList(int score){
          //클라우드 파이어스토에 쓰기 하도록 하자.
  // Create a new user with a first and last name
          Map<String, Object> rank = new HashMap<>();
          rank.put("grade", score);

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
      }*/
    private void getList(){
       /* m_arr = new ArrayList<Rank_item>();
        ListView lv = (ListView)findViewById(R.id.listView);
        m_arr.add(new Rank_item(String.valueOf(1),String.valueOf(25)));
        m_arr.add(new Rank_item(String.valueOf(2),String.valueOf(23)));
        m_arr.add(new Rank_item(String.valueOf(3),String.valueOf(22)));

        adapter = new RankAdapter(this, m_arr);
        lv.setAdapter(adapter);
        //lv.setDivider(null); 구분선을 없에고 싶으면 null 값을 set합니다.
        lv.setDividerHeight(5);// 구분선의 굵기를 좀 더 크게 하고싶으면 숫자로 높이 지정가능.*/
        // Add a new document with a generated ID
     /*   db.collection("rank")

                .addSnapshotListener(new EventListener<QuerySnapshot>() {

                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {

                        if (e != null) {
                            Log.w(TAG, "Listen failed.ddddddddddddddddddddddddddddddddd", e);
                            return;
                        }
                        else{
                            Log.d(TAG, "성공");
                        }

                        count = value.size();
                        m_arr = new ArrayList<Rank_item>();
                        m_arr.clear();//일딴 초기화 해줘야 한다. 안 그럼 기존 데이터에 반복해서 뒤에 추가된다.
                        for (QueryDocumentSnapshot doc : value) {
                            if (doc.get("grade") != null) {
                                m_arr.add(new Rank_item(doc.getString("rank"),doc.getString("grade")));
                            }
                            else{
                                Log.d(TAG , "No data");
                            }
                        }
                        //어답터 갱신
                      //  adapter.notifyDataSetChanged();
                    }
                });
                */

        db.collection("ranking")
                .orderBy("grade", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        m_arr = new ArrayList<Rank_item>();
                        ListView lv = (ListView)findViewById(R.id.listView);
                        if (task.isSuccessful()) {
                            int rank=1;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                               /* Bundle b = getIntent().getExtras();
                                String uid = b.getString("uid");*/
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                m_arr.add(new Rank_item(rank++,document.getLong("grade").intValue(),document.getString("uid")));
                                Log.d(TAG,  "rank" + rank);
                                Log.d(TAG,  "grade" + document.getLong("grade").intValue());
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                        adapter = new RankAdapter(RankActivity.this, m_arr);
                        lv.setAdapter(adapter);
                        //lv.setDivider(null); 구분선을 없에고 싶으면 null 값을 set합니다.
                        lv.setDividerHeight(5);// 구분선의 굵기를 좀 더 크게 하고싶으면 숫자로 높이 지정가능.*/
                        // Add a new document with a generated ID
                    }
                });
    }
    public void listUpdate(){
        adapter.notifyDataSetChanged();
    }
}
