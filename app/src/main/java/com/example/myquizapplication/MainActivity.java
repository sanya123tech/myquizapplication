package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView TotalQuestionTextView;
    TextView QuestionTextView;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;

    int Score=0;
    int totalQuestion=QuestionAnswer.question.length;
    int currentQuestionIndex=0;
    String selectedAnswer="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TotalQuestionTextView=findViewById(R.id.total_questions);
        QuestionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.AnsA);
        ansB=findViewById(R.id.AnsB);
        ansC=findViewById(R.id.AnsC);
        ansD=findViewById(R.id.AnsD);
        submitBtn=findViewById(R.id.Submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        TotalQuestionTextView.setText("Total question"+totalQuestion);

        loadNewQuestion();

    }



    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton=(Button) view;
        if(clickedButton.getId()==R.id.Submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                Score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();



        }
        else {
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }
    void loadNewQuestion(){

        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
QuestionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }
    void  finishQuiz(){
        String passStatus="";
        if(Score>totalQuestion*0.60){
            passStatus="Passed";
        }else{
            passStatus="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is"+Score+"out of"+totalQuestion)
                .setPositiveButton("Restart",((dialogInterface, i) ->restartQuiz() ))
                .setCancelable(false)
                .show();

    }
    void restartQuiz(){
        Score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }

}