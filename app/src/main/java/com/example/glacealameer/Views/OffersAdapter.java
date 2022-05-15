package com.example.glacealameer.Views;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.Offer;
import com.example.glacealameer.databinding.CaustomOffersBinding;
import com.example.glacealameer.databinding.CustomItemBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ItemHolder> {
    FirebaseFirestore fb;
    private List<Offer> Offer;
    Context context;
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public OffersAdapter( List<com.example.glacealameer.Model.Offer> offer, Context context) {

        Offer = offer;
        this.context = context;
    }

    public List<com.example.glacealameer.Model.Offer> getOffer() {
        return Offer;
    }

    public void setOffer(List<com.example.glacealameer.Model.Offer> offer) {
        Offer = offer;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fb = FirebaseFirestore.getInstance();
        CaustomOffersBinding binding = CaustomOffersBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Offer offer = Offer.get(position);
        holder.offer = offer;


        holder.binding.title.setText(offer.getTitle());
        holder.binding.subTitle.setText(offer.getSubtitle());
        Picasso.get().load(offer.getImage()).into(holder.binding.mgOffer);
        String uid = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.USER_UID_KEY, null);
//
    }

    @Override
    public int getItemCount() {
        return Offer.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        CaustomOffersBinding  binding;
        Offer offer;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = CaustomOffersBinding.bind(itemView);

//
        }
    }
}
