package eu.kudan.kudansamples;

public class PlayQuizQuestionWrapper {
    private String question, answer;
    private String[] choices = new String[4];

    public PlayQuizQuestionWrapper(){}

    public PlayQuizQuestionWrapper(String question, String[] choices, String answer)
    {
        this.question = question;
        this.answer = answer;
        this.choices[0]= choices[0];
        this.choices[1]= choices[1];
        this.choices[2]= choices[2];
        this.choices[3]= choices[3];
    }

    public String getQuestion(){ return question; }
    public void setQuestion(String question){ this.question = question; }


    public String getChoice(int i){ return  choices[i]; }
    public void setChoice(int i, String choices){ this.choices[i]=choices;}

    public String getAnswer(){ return answer; }
    public void setAnswer(String answer) {this.answer = answer;}
}
