package com.xeko.quizzyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.xeko.quizzyapp.adapter.HistoryAdapter;
import com.xeko.quizzyapp.models.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ImageView backButton;

    RecyclerView historyRecyclerView;
    List<Result> historyList;
    TextView tvOverallPointsHistory, tvTotalQuestionsHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        // Get views
        backButton = findViewById(R.id.imageViewHistory);
        historyRecyclerView = findViewById(R.id.rvHistory);
        tvOverallPointsHistory = findViewById(R.id.tvOverallPointsHistory);
        tvTotalQuestionsHistory = findViewById(R.id.tvTotalQuestionsHistory);
        // Handle back button
        backButton.setOnClickListener(v -> finish());

        GetHistory getHistory = new GetHistory(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        getHistory.execute();
    }

    class GetHistory extends AsyncTask<Void, Void, Void> {
        private final String userEmail;
        ArrayList<Result> historyList = new ArrayList<>();
        public GetHistory(String userEmail) {
            this.userEmail = userEmail;
        }
        private boolean lockedThread = true;
        @Override
        protected Void doInBackground(Void... voids) {
            // Get history from database

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("results")
                    .whereEqualTo("email", userEmail)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            for (int i = 0; i < task.getResult().size(); i++)
                                historyList.add(task.getResult().getDocuments().get(i).toObject(Result.class));
                        lockedThread = false;
                    });

            while (lockedThread) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            int totalPoints = 0;
            int totalQuestions = historyList.size();
            for (Result result : historyList) totalPoints += Integer.parseInt(result.getEarned());
            tvOverallPointsHistory.setText(String.valueOf(totalPoints));
            tvTotalQuestionsHistory.setText(String.valueOf(totalQuestions));

            HistoryAdapter adapter = new HistoryAdapter(historyList);
            historyRecyclerView.setAdapter(adapter);
        }
    }
}