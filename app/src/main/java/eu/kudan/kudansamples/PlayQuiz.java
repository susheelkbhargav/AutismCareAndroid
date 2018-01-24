package eu.kudan.kudansamples;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayQuiz extends AppCompatActivity {

    private PlayQuizQuestionBank playQuizQuestionBank = new PlayQuizQuestionBank();

    private TextView textViewQuestion,textViewScore;
    private Button buttonChoice0, buttonChoice1, buttonChoice2, buttonChoice3;

    private String mAnswer;
    private int mQuestionumber=0, mScore=0;

    public static final String MyPREFERENCES = "MyPlayQuizPrefs" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        sharedpreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        textViewQuestion = (TextView)findViewById(R.id.question);
        textViewScore = (TextView)findViewById(R.id.score);

        buttonChoice0 = (Button)findViewById(R.id.ansButton0);
        buttonChoice1 = (Button)findViewById(R.id.ansButton1);
        buttonChoice2 = (Button)findViewById(R.id.ansButton2);
        buttonChoice3 = (Button)findViewById(R.id.ansButton3);

        playQuizQuestionBank.initQuestions(getApplicationContext());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mQuestionumber = sharedpreferences.getInt("Question Number",0);
        //System.out.println(mQuestionumber);
        updateQuestion();
        updateScore(mScore);
    }

    public void updateQuestion(){
        if(mQuestionumber<playQuizQuestionBank.getLengthofList()){
            textViewQuestion.setText(playQuizQuestionBank.getQuestion(mQuestionumber));
            buttonChoice0.setText(playQuizQuestionBank.getChoice(mQuestionumber,0));
            buttonChoice1.setText(playQuizQuestionBank.getChoice(mQuestionumber,1));
            buttonChoice2.setText(playQuizQuestionBank.getChoice(mQuestionumber,2));
            buttonChoice3.setText(playQuizQuestionBank.getChoice(mQuestionumber,3));
            mAnswer = playQuizQuestionBank.getCorrectAnswer(mQuestionumber);
            mQuestionumber++;
        }
            //if Question number is greater than the total questions, repeat the questions from first
        else{
            mQuestionumber=0;
            updateQuestion();
        }
    }

    public void updateScore(int points){
        textViewScore.setText("Quiz Score: "+ mScore);
    }

    public void onClick(View view){
        //all logic for all answer in one method
        Button answer = (Button) view;
        if(answer.getText().equals(mAnswer)){
            mScore = mScore+1;
            Toast.makeText(this,"Correct Answer!",Toast.LENGTH_SHORT).show();
            updateScore(mScore);

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("Question Number",mQuestionumber);
            editor.commit();

            Intent playBalloonIntent = new Intent(this,PlayMainActivity.class);
            playBalloonIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(playBalloonIntent);
        }

        else
            Toast.makeText(this,"Wrong Answer. Try Again!",Toast.LENGTH_SHORT).show();
    }
}
