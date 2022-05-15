package com.example.glacealameer.Views;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.News;
import com.example.glacealameer.Model.Offer;
import com.example.glacealameer.databinding.CaustomNewsBinding;
import com.example.glacealameer.databinding.CaustomOffersBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ItemHolder> {
    FirebaseFirestore fb;
    private List<News> news;
    Context context;
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public NewsAdapter(List<News> news, Context context) {
        this.news = news;
        this.context = context;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fb = FirebaseFirestore.getInstance();
        CaustomNewsBinding binding = CaustomNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        News newss = news.get(position);
        holder.news = newss;


        holder.binding.title.setText(newss.getTitle());
        holder.binding.subTitle.setText(newss.getSubtitle());
        Picasso.get().load(newss.getImage()).into(holder.binding.newsMg);
        String uid = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.USER_UID_KEY, null);
//
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        CaustomNewsBinding  binding;
        News news;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = CaustomNewsBinding.bind(itemView);

//
        }
    }
}
