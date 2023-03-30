package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.xeko.quizzyapp.adapter.SubjectAdapter;
import com.xeko.quizzyapp.models.Subject;

import java.util.ArrayList;

public class SubjectActivity extends AppCompatActivity {

    ImageView backButton;
    RecyclerView rvSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        // Get views
        backButton = findViewById(R.id.imageViewQuizOption);
        rvSubject = findViewById(R.id.rvSubject);

        // Handle back button
        backButton.setOnClickListener(v -> finish());

        GetSubjects getSubjects = new GetSubjects();
        getSubjects.execute();
    }

    class GetSubjects extends AsyncTask<Void, Void, Void> {

        ArrayList<Subject> subjectList = new ArrayList<>();
        public GetSubjects() {
        }
        private boolean lockedThread = true;
        @Override
        protected Void doInBackground(Void... voids) {
            // Get history from database

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("subjects")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            for (int i = 0; i < task.getResult().size(); i++)
                                subjectList.add(task.getResult().getDocuments().get(i).toObject(Subject.class));
                        lockedThread = false;
                    });
            while (lockedThread) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Set history list
            SubjectAdapter historyAdapter = new SubjectAdapter(subjectList);
            rvSubject.setAdapter(historyAdapter);
        }
    }
}