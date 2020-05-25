package hitesh.asimplegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDBOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "math";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c
    private static int level=0;

    private SQLiteDatabase database;

    Random rnd = new Random();

    public QuizDBOpenHelper(Context context,int level) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.level=level;
    }
    @Override
    public void onOpen(SQLiteDatabase db){
        onUpgrade(db,DATABASE_VERSION,DATABASE_VERSION+1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion(level);
        //db.close();
    }

    private void addQuestion(int level) {
        /*
        Question q1 = new Question("5+2 = ?", "7", "8", "6", "7");
        addQuestion(q1);
        Question q2 = new Question("2+18 = ?", "18", "19", "20", "20");
        addQuestion(q2);
        Question q3 = new Question("10-3 = ?", "6", "7", "8", "7");
        addQuestion(q3);
        Question q4 = new Question("5+7 = ?", "12", "13", "14", "12");
        addQuestion(q4);
        Question q5 = new Question("3-1 = ?", "1", "3", "2", "2");
        addQuestion(q5);
        Question q6 = new Question("0+1 = ?", "1", "0", "10", "1");
        addQuestion(q6);
        Question q7 = new Question("9-9 = ?", "0", "9", "1", "0");
        addQuestion(q7);
        Question q8 = new Question("3+6 = ?", "8", "7", "9", "9");
        addQuestion(q8);
        Question q9 = new Question("1+5 = ?", "6", "7", "5", "6");
        addQuestion(q9);
        Question q10 = new Question("7-5 = ?", "3", "2", "6", "2");
        addQuestion(q10);
        Question q11 = new Question("7-2 = ?", "7", "6", "5", "5");
        addQuestion(q11);
        Question q12 = new Question("3+5 = ?", "8", "7", "5", "8");
        addQuestion(q12);
        Question q13 = new Question("0+6 = ?", "7", "6", "5", "6");
        addQuestion(q13);
        Question q14 = new Question("12-10 = ?", "1", "2", "3", "2");
        addQuestion(q14);
        Question q15 = new Question("12+2 = ?", "14", "15", "16", "14");
        addQuestion(q15);
        Question q16 = new Question("2-1 = ?", "2", "1", "0", "1");
        addQuestion(q16);
        Question q17 = new Question("6-6 = ?", "6", "12", "0", "0");
        addQuestion(q17);
        Question q18 = new Question("5-1 = ?", "4", "3", "2", "4");
        addQuestion(q18);
        Question q19 = new Question("4+2 = ?", "6", "7", "5", "6");
        addQuestion(q19);
        Question q20 = new Question("5+1 = ?", "6", "7", "5", "6");
        addQuestion(q20);
        Question q21 = new Question("5-4 = ?", "5", "4", "1", "1");
        addQuestion(q21);
        */

        int num1,num2;
        String num1_str,num2_str,quiz;
        String opta, optb, optc;
        /*num1 = rnd.nextInt(100)+1;
        num2 = rnd.nextInt(100)+1;
        num1_str = String.valueOf(num1);
        num2_str = String.valueOf(num2);
        quiz=num1_str + "+" + num2_str + "=" + "?";
        opta = String.valueOf(num1+num2);
        optb = String.valueOf(rnd.nextInt(100)+1);
        optc = String.valueOf(rnd.nextInt(100)+1);*/

        switch (level) {
            case 1:
                for (int i = 0; i < 20; i++) {

                    int opt[] = new int[3];
                    String answer;
                    num1 = rnd.nextInt(100) + 1;
                    num2 = rnd.nextInt(100) + 1;
                    num1_str = String.valueOf(num1);
                    num2_str = String.valueOf(num2);
                    quiz = num1_str + "+" + num2_str + "=" + "?";
                    opt[0] = num1 + num2;
                    opt[1] = rnd.nextInt(100) + 1;
                    opt[2] = rnd.nextInt(100) + 1;
                    answer = String.valueOf(num1 + num2);
                    shuffle(opt);
                    Question q = new Question(quiz, String.valueOf(opt[0]), String.valueOf(opt[1]), String.valueOf(opt[2]), answer);
                    addQuestion(q);
                }
            case 2:
                for (int i = 0; i < 20; i++) {

                    int opt[] = new int[3];
                    String answer;
                    num1 = rnd.nextInt(30) + 10;
                    num2 = rnd.nextInt(30) + 10;
                    num1_str = String.valueOf(num1);
                    num2_str = String.valueOf(num2);
                    quiz = num1_str + "*" + num2_str + "=" + "?";
                    opt[0] = num1 * num2;
                    opt[1] = rnd.nextInt(1000) + 1;
                    opt[2] = rnd.nextInt(1000) + 1;
                    answer = String.valueOf(num1 * num2);
                    shuffle(opt);
                    Question q = new Question(quiz, String.valueOf(opt[0]), String.valueOf(opt[1]), String.valueOf(opt[2]), answer);
                    addQuestion(q);
                }


        }

    }
    public static int[] shuffle(int[] arr){
        for(int x=0;x<arr.length;x++){
            int i = (int)(Math.random()*arr.length);
            int j = (int)(Math.random()*arr.length);

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        return arr;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        database.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
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
}
