package com.xeko.quizzyapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.xeko.quizzyapp.R;
import com.xeko.quizzyapp.models.Result;

import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    private List<Result> historyList;


    public HistoryAdapter(List<Result> historyList) {
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = historyList.get(position);

        holder.tvSubject.setText(result.getSubject());
        holder.tvEarned.setText(result.getEarned());
        // convert date to a readable format DD/MM/YYYY
        holder.tvDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(result.getDate()));

        holder.cvParent.setOnClickListener(v -> {
            // TODO: Open result details
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvSubject, tvEarned, tvDate;
        public CardView cvParent;
        public ViewHolder(View v) {
            super(v);
            tvSubject = v.findViewById(R.id.tvSubject);
            tvEarned = v.findViewById(R.id.tvEarned);
            tvDate = v.findViewById(R.id.tvDate);
            cvParent = v.findViewById(R.id.cvItemHistory);
        }
    }
}
