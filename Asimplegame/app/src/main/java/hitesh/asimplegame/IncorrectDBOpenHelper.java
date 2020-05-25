package hitesh.asimplegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class IncorrectDBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "incorrect";
    //tasks table name
    private static final String TABLE_INCORRECT = "incorrect";
    //tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "opta"; //option a
    private static final String KEY_OPTB = "optb"; //option b
    private static final String KEY_OPTC = "optc"; //option c

    private SQLiteDatabase database;

    public IncorrectDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_INCORRECT + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        Log.i("db생성" , "성공적ㄲㄲㄲㄲㄲㄲㄲㄲㄲㄲㄲㅇ란어리머이ㅓ미아러미;넝리ㅓㅁㄴ;이럼ㄴㅇㄹ");
        //db.close();
    }

    public void addQuestion(Question q){
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_QUES, q.getQUESTION());
            values.put(KEY_ANSWER, q.getANSWER());
            values.put(KEY_OPTA, q.getOPTA());
            values.put(KEY_OPTB, q.getOPTB());
            values.put(KEY_OPTC, q.getOPTC());
            System.out.println("question" + q.getQUESTION() + "answer" + q.getANSWER() + "opta" + q.getOPTA() + "optb" + q.getOPTB() + "optc" + q.getOPTC());
            database = this.getWritableDatabase();
            database.insert(TABLE_INCORRECT, null, values);
        } catch (Exception e) {
            System.out.println("오류");
            e.printStackTrace();
        }
    }

    public void addQuestion(String question, String optA, String optB, String optC, String answer){
        Question q = new Question(question, optA, optB, optC,answer);
        addQuestion(q);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_INCORRECT;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_INCORRECT);
        // Create tables again
        onCreate(database);
    }
}
