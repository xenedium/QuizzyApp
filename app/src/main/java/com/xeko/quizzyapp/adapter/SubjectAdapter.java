package com.xeko.quizzyapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.xeko.quizzyapp.R;
import com.xeko.quizzyapp.models.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private final List<Subject> subjectList;

    public SubjectAdapter(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        holder.tvSubjectItem.setText(subject.getName());
        Picasso.get().load(subject.getImage()).into(holder.ivSubjectItem);

        holder.itemView.setOnClickListener(v -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("subjects").document(subject.getId());
            db.collection("quizzes")
                    .whereEqualTo("subject", docRef)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (task.getResult().isEmpty()) {
                                Toast.makeText(v.getContext(), "No quiz available for this subject", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(v.getContext(), "Loading questions...", Toast.LENGTH_SHORT).show();
                                ((Activity)v.getContext()).finish();
                            }
                        }
                    });
            // TODO: Open quiz activity for this subject
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubjectItem;
        ImageView ivSubjectItem;
        public ViewHolder(View v) {
            super(v);
            tvSubjectItem = v.findViewById(R.id.tvSubjectItem);
            ivSubjectItem = v.findViewById(R.id.ivSubjectItem);
        }
    }
}
