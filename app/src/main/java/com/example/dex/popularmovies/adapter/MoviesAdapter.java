package com.example.dex.popularmovies.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dex.popularmovies.R;
import com.example.dex.popularmovies.eventlistener.ListItemClickListener;
import com.example.dex.popularmovies.model.data.MovieStub;
import com.example.dex.popularmovies.util.Helper;
import com.example.dex.popularmovies.util.MovieDbApi.MovieDbApiImageHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> implements ListItemClickListener {
    private ArrayList<MovieStub> movies;
    private ListItemClickListener itemClickListener;

    public MoviesAdapter(ArrayList<MovieStub> movies, ListItemClickListener itemClickListener) {
        this.movies = movies;
        this.itemClickListener = itemClickListener;
    }

    public ArrayList<MovieStub> getMovies() {
        return this.movies;
    }

    public void addMovie(MovieStub movieStub) {
        this.movies.add(movieStub);
        this.notifyItemInserted(this.getItemCount() - 1);
    }

    public void removeMovie(long movieId) {
        int position = -1;
        int totalItems = this.getItemCount();
        for (int i = 0; i < totalItems; i++) {
            if (this.movies.get(i).getId() == movieId) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            this.movies.remove(position);
            this.notifyItemRemoved(position);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AppCompatImageView movieImage = (AppCompatImageView)
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item_recycler_view_movies, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieImage, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int imageWidth = Helper.getScreenWidth(holder.imageItem) / 2;

        String imageUrl = MovieDbApiImageHelper.getImageUrl(
                this.movies.get(position).getPosterPath(),
                imageWidth
        );

        Picasso
                .with(holder.imageItem.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_poster)
                .error(R.drawable.ic_cloud_off)
                .into(holder.imageItem);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    @Override
    public void onItemClick(int position) {
        this.itemClickListener.onItemClick(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements AppCompatImageView.OnClickListener {
        private AppCompatImageView imageItem;
        private MoviesAdapter adapter;

        public ViewHolder(AppCompatImageView imageItem, MoviesAdapter adapter) {
            super(imageItem);
            this.imageItem = imageItem;
            this.adapter = adapter;
            this.imageItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            this.adapter.onItemClick(this.getAdapterPosition());
        }
    }
}
