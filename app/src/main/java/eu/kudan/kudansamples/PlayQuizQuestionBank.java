package eu.kudan.kudansamples;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class PlayQuizQuestionBank {

    //declare list of PlayQuizQuestionWrapper object
    List<PlayQuizQuestionWrapper> list = new ArrayList<>();
    PlayQuizDatabaseHelper db;

    //method returns Number of questions in the list
    public int getLengthofList(){ return list.size();}

    /**
     * Below methods returns questions, choices, answers from list based on the list index
     */

    public String getQuestion(int a){ return  list.get(a).getQuestion();}

    public String getChoice(int index,int num){ return  list.get(index).getChoice(num);}

    public String getCorrectAnswer(int a){ return  list.get(a).getAnswer();}


    //Initialize the database if database is empty
    public void initQuestions(Context context){
        db = new PlayQuizDatabaseHelper(context);
        list = db.selectTableQuiz();

        if(list.isEmpty()){
            db.insertToTableQuiz(new PlayQuizQuestionWrapper("1. Food passes into Small Intestine from",
                    new String[]{"Stomach","Food Pipe","Large Intestine","Mouth"},"Large Intestine"));

            db.insertToTableQuiz(new PlayQuizQuestionWrapper("2. 10 - X = 5. What is the value of X?",
                    new String[]{"2","5","10","3"},"5"));

            db.insertToTableQuiz(new PlayQuizQuestionWrapper("3. Where does the sun rise ? ",
                    new String[]{"East","West","North","South"},"East"));

            list= db.selectTableQuiz();
        }
    }


}
