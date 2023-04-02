package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xeko.quizzyapp.models.GlobalState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class ResultActivity extends AppCompatActivity {

    TextView tvWellDone, tvCorrect, tvIncorrect, tvEarned, tvDate, tvSubject, tvTotalQuestions;
    Button btnFinishQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvTotalQuestions = findViewById(R.id.tvTotalQuestions);
        tvSubject = findViewById(R.id.tvSubject);
        tvWellDone = findViewById(R.id.tvWellDone);
        tvCorrect = findViewById(R.id.tvCorrect);
        tvIncorrect = findViewById(R.id.tvIncorrect);
        tvEarned = findViewById(R.id.tvEarned);
        tvDate = findViewById(R.id.tvDate);
        btnFinishQuiz = findViewById(R.id.btnFinishQuiz);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String name = Objects.requireNonNull(auth.getCurrentUser()).getEmail();

        int correct = GlobalState.getInstance().getScore();
        int incorrect = GlobalState.getInstance().getTotalQuestions() - correct;
        int earned = correct * 10;

        tvTotalQuestions.setText(String.valueOf(GlobalState.getInstance().getTotalQuestions()));
        tvSubject.setText(GlobalState.getInstance().getSubjectName());
        tvWellDone.setText("Well done " + name + "!");
        tvCorrect.setText(String.valueOf(correct));
        tvIncorrect.setText(String.valueOf(incorrect));
        tvEarned.setText(String.valueOf(earned));
        tvDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));

        DocumentReference subjectRef = FirebaseFirestore.getInstance().collection("subjects").document(GlobalState.getInstance().getSubjectId());
        btnFinishQuiz.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("results").add(new HashMap<String, Object>() {{
                put("date", new Date());
                put("earned", String.valueOf(earned));
                put("email", name);
                put("subject", subjectRef);
            }});
            GlobalState.getInstance().reset();
            finish();
        });
    }
}