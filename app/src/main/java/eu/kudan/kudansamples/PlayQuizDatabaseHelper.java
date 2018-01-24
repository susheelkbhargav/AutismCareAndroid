package eu.kudan.kudansamples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizDatabaseHelper extends SQLiteOpenHelper {

    //Database name
    private static String DATABASE_QUIZ =  "quiz.db";

    //Current Version
    private static final int DATABASE_VERSION = 1;

    //Database Table Name
    private static String TABLE_QUIZ = "Quiz";

    //Fields in the table
    private static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String CHOICE0 = "choice0";
    private static final String CHOICE1 = "choice1";
    private static final String CHOICE2 = "choice2";
    private static final String CHOICE3 = "choice3";

    //Create a table Query string
    private static final String CREATE_TABLE_QUIZ = "CREATE TABLE " +  TABLE_QUIZ
            + "(" +  KEY_ID +" integer PRIMARY KEY AUTOINCREMENT," +
            QUESTION + " text," + CHOICE0 +" text," + CHOICE1 +" text,"+
            CHOICE2 +" text," + CHOICE3 +" text," + ANSWER +" text);";

    public PlayQuizDatabaseHelper(Context context){
        super(context,DATABASE_QUIZ, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUIZ);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLE_QUIZ);
        onCreate(sqLiteDatabase);
    }

    //Inserting questions and answers to database
    public long insertToTableQuiz(PlayQuizQuestionWrapper playQuizQuestionWrapper){
        SQLiteDatabase db = this.getWritableDatabase();

        //Creating Content values
        ContentValues values = new ContentValues();
        values.put(QUESTION, playQuizQuestionWrapper.getQuestion());
        values.put(CHOICE0, playQuizQuestionWrapper.getChoice(0));
        values.put(CHOICE1, playQuizQuestionWrapper.getChoice(1));
        values.put(CHOICE2, playQuizQuestionWrapper.getChoice(2));
        values.put(CHOICE3, playQuizQuestionWrapper.getChoice(3));
        values.put(ANSWER, playQuizQuestionWrapper.getAnswer());

        //insert row in database table
        long insert = db.insert(TABLE_QUIZ,null,values);
        return insert;
    }


    //To extract data from database and save it in an Arraylist of datatype PlayQuizQuestionWrapper
    public List<PlayQuizQuestionWrapper> selectTableQuiz() {

        List<PlayQuizQuestionWrapper> quizArrayList = new ArrayList<>();
        String selectQuery = "Select * from "+ TABLE_QUIZ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery,null);

        //looping through all records and adding to arraylist
        if(c.moveToFirst()) {
            do {
                PlayQuizQuestionWrapper playQuizQuestionWrapper = new PlayQuizQuestionWrapper();

                String quesText = c.getString(c.getColumnIndex(QUESTION));
                playQuizQuestionWrapper.setQuestion(quesText);

                String choice0Text = c.getString(c.getColumnIndex(CHOICE0));
                playQuizQuestionWrapper.setChoice(0, choice0Text);

                String choice1Text = c.getString(c.getColumnIndex(CHOICE1));
                playQuizQuestionWrapper.setChoice(1, choice1Text);

                String choice2Text = c.getString(c.getColumnIndex(CHOICE2));
                playQuizQuestionWrapper.setChoice(2, choice2Text);

                String choice3Text = c.getString(c.getColumnIndex(CHOICE3));
                playQuizQuestionWrapper.setChoice(3, choice3Text);

                String ansText = c.getString(c.getColumnIndex(ANSWER));
                playQuizQuestionWrapper.setAnswer(ansText);

                quizArrayList.add(playQuizQuestionWrapper);

            } while (c.moveToNext());
        }

        return quizArrayList;
    }

}
