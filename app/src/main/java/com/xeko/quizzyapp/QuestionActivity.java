package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xeko.quizzyapp.models.GlobalState;
import com.xeko.quizzyapp.models.Quiz;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestion, tvCurrentQuestion;
    Button btnNextQuestion;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;

    boolean backPressed = false;
    @Override
    public void onBackPressed() {
        if (backPressed) {
            super.onBackPressed();
            GlobalState.getInstance().reset();
            return;
        }
        this.backPressed = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // get quiz object from intent
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");

        int currentQuestion = GlobalState.getInstance().getCurrentQuestion();
        int totalQuestions = GlobalState.getInstance().getTotalQuestions();

        tvQuestion = findViewById(R.id.tvQuestion);
        tvCurrentQuestion = findViewById(R.id.tvCurrentQuestion);
        btnNextQuestion = findViewById(R.id.btnNextQuestion);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);

        tvQuestion.setText(quiz.getQuestion());
        tvCurrentQuestion.setText("Question: " + (currentQuestion + 1) + "/" + totalQuestions);

        radioButton1.setText(quiz.getOptions().get(0));
        radioButton2.setText(quiz.getOptions().get(1));
        radioButton3.setText(quiz.getOptions().get(2));
        radioButton4.setText(quiz.getOptions().get(3));


        btnNextQuestion.setOnClickListener(v -> {
            if (!radioButton1.isChecked() && !radioButton2.isChecked() && !radioButton3.isChecked() && !radioButton4.isChecked()) {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            String message = "Wrong, Moving to next question";
            if ((radioButton1.isChecked() && quiz.getOptions().get(0).equals(quiz.getCorrect()))
                    || (radioButton2.isChecked() && quiz.getOptions().get(1).equals(quiz.getCorrect()))
                    || (radioButton3.isChecked() && quiz.getOptions().get(2).equals(quiz.getCorrect()))
                    || (radioButton4.isChecked() && quiz.getOptions().get(3).equals(quiz.getCorrect())))
            {
                message = "Correct, Moving to next question";
                GlobalState.getInstance().incrementScore();
            }



            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            GlobalState.getInstance().nextQuestion(v);
            finish();
        });

    }
}