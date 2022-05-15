package com.example.glacealameer.ui.Favorite;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.glacealameer.Database.DatabaseFavorite;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Favorite_1;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.FavorityAdapter;
import com.example.glacealameer.Views.ItemListAdapter;
import com.example.glacealameer.Views.OnFavoriteClickListener;
import com.example.glacealameer.Views.OnItemClickListener;
import com.example.glacealameer.databinding.FragmentDashboardBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    FirebaseFirestore  fb;
    String  uid;
    FavorityAdapter adapter;
    ArrayList<Item> items;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DatabaseFavorite databaseFavorite = new DatabaseFavorite(requireContext());

        items = databaseFavorite.getAllItem();
        adapter = new FavorityAdapter(items, requireContext(), new OnFavoriteClickListener() {
            @Override
            public void onFavoriteClick(String  ItemID) {
                if (databaseFavorite.deleteFurniture(ItemID)){
                    Toast.makeText(getContext(), "تمت ازارلة من المفضلة", Toast.LENGTH_SHORT).show();
                    items = databaseFavorite.getAllItem();
                    adapter.setItems(items);
                    adapter.notifyDataSetChanged();
                }

            }
        });

        binding.fFavorite.setAdapter(adapter);
        binding.fFavorite.setHasFixedSize(true);
        binding.fFavorite.setLayoutManager(new LinearLayoutManager(getContext()));


//        fb = FirebaseFirestore.getInstance();
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getContext());
//        uid = sp.getString(Constants.USER_UID_KEY,null);
//
//        fb.collection("Favorite").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                Favorite_1 fav = value.toObject(Favorite_1.class);
//                ArrayList<Item> items = fav.getItems();
//                populateDataIntoRV(items);
//            }
//        });

//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
    private void populateDataIntoRV(List<Item> items) {
        ItemListAdapter adapter = new ItemListAdapter(items,getContext());
        adapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {

            }

            @Override
            public void onFavoriteClick(Item item, ImageView view) {
                String uid = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(Constants.USER_UID_KEY, null);
                fb.collection("Favorite").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Favorite_1 f = documentSnapshot.toObject(Favorite_1.class);
                        if (item.isFavorite()) {
                            for (int i = 0; i < f.getItems().size(); i++) {
                                if (f.getItems().get(i).getDocId().equals(item.getDocId())) {
                                    f.getItems().remove(i);
                                }
                            }
                            fb.collection("Favorite").document(uid).set(f).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    view.setBackgroundResource(R.drawable.ic_favorite);
                                }
                            });
                        } else {
                            f.getItems().add(item);
                            fb.collection("Favorite").document(uid).set(f).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    view.setBackgroundResource(R.drawable.ic_favorite_borde);
                                }
                            });
                        }
                    }
                });
            }
        });
        binding.fFavorite.setAdapter(adapter);
        binding.fFavorite.setHasFixedSize(true);
        binding.fFavorite.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}