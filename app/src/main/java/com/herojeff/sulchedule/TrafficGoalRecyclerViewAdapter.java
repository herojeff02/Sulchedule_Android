package com.herojeff.sulchedule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TrafficGoalRecyclerViewAdapter extends RecyclerView.Adapter<TrafficGoalRecyclerViewAdapter.TrafficGoalRecyclerViewHolder> {
    @NonNull
    @Override
    public TrafficGoalRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View linearLayout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_goal_item, viewGroup, false);
        return new TrafficGoalRecyclerViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull TrafficGoalRecyclerViewHolder trafficGoalRecyclerViewHolder, int i) {
//        Movie movie = moviesList.get(position);
//        holder.title.setText(movie.getTitle());
//        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class TrafficGoalRecyclerViewHolder extends RecyclerView.ViewHolder{
        public TrafficGoalRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
