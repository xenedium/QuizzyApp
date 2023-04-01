package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xeko.quizzyapp.models.Quiz;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestion, tvCurrentQuestion;
    Button btnNextQuestion;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        // get quiz object from intent
        Quiz quiz = (Quiz) getIntent().getSerializableExtra("quiz");
        int currentQuestion = getIntent().getIntExtra("currentQuestion", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

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
            if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked()) {
                if (radioButton1.isChecked()) {
                    if (quiz.getOptions().get(0).equals(quiz.getCorrect())) {
                        Toast.makeText(this, "Correct, Moving to next question", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Wrong, Moving to next question", Toast.LENGTH_SHORT).show();
                    }
                } else if (radioButton2.isChecked()) {
                    if (quiz.getOptions().get(1).equals(quiz.getCorrect())) {
                        Toast.makeText(this, "Correct, Moving to next question", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Wrong, Moving to next question", Toast.LENGTH_SHORT).show();
                    }
                } else if (radioButton3.isChecked()) {
                    if (quiz.getOptions().get(2).equals(quiz.getCorrect())) {
                        Toast.makeText(this, "Correct, Moving to next question", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Wrong, Moving to next question", Toast.LENGTH_SHORT).show();
                    }
                } else if (radioButton4.isChecked()) {
                    if (quiz.getOptions().get(3).equals(quiz.getCorrect())) {
                        Toast.makeText(this, "Correct, Moving to next question", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Wrong, Moving to next question", Toast.LENGTH_SHORT).show();
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finish();
            } else {
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            }
        });

    }
}