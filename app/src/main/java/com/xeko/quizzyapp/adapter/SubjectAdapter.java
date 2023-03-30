package com.xeko.quizzyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.ivSubjectItem.setOnClickListener(v -> {
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
