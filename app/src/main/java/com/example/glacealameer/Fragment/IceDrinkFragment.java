package com.example.glacealameer.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Views.ItemListAdapter;
import com.example.glacealameer.Views.OnFragmentInteractionListener;
import com.example.glacealameer.Views.OnItemClickListener;
import com.example.glacealameer.databinding.FragmentItemListBinding;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class IceDrinkFragment extends Fragment {
    private static final String CAT_NAME = "cat_name";
    private String catName;
    private OnFragmentInteractionListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;

        }
    }

    public IceDrinkFragment() {
        // Required empty public constructor
    }

    public static IceDrinkFragment newInstance(String catName) {
        IceDrinkFragment fragment = new IceDrinkFragment();
        Bundle args = new Bundle();
        args.putString(CAT_NAME, catName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            catName = getArguments().getString(CAT_NAME);
        }
    }

    FragmentItemListBinding binding;
    FirebaseFirestore fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentItemListBinding.inflate(inflater,container,false);
        fb = FirebaseFirestore.getInstance();

         fb.collection("Items").whereEqualTo("categoryName", catName).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                            if (value!=null&&!value.isEmpty()) {
                                ArrayList<Item> items = (ArrayList<Item>) value.toObjects(Item.class);

                                populateDataIntoRV(items);
                }
            }
        });

        return binding.getRoot();
    }

    private void populateDataIntoRV(List<Item> items) {
        ItemListAdapter adapter = new ItemListAdapter(items, requireContext());
        adapter.setListener(new OnItemClickListener() {
            //            @Override
//            public void onItemClick(Item item, int value) {
//                listener.onItemSend(item, value);
//            }
            @Override
            public void onItemClick(Item item) {
                //  listener.onItemSend(item);
                listener.onItemSend(item,60);


            }

            @Override
            public void onFavoriteClick(Item item, ImageView view) {



//                String uid = PreferenceManager.getDefaultSharedPreferences(requireContext()).getString(Constants.USER_UID_KEY, null);
//                fb.collection("Favorite").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        Favorite_1 f = documentSnapshot.toObject(Favorite_1.class);
//                        if (item.isFavorite()) {
//                            for (int i = 0; i < f.getItems().size(); i++) {
//                                if (f.getItems().get(i).getDocId().equals(item.getDocId())) {
//                                    f.getItems().remove(i);
//                                }
//                            }
//                            fb.collection("Favorite").document(uid).set(f).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    //view.setBackgroundResource(R.drawable.fav_off);
//                                }
//                            });
//                        } else {
//                            f.getItems().add(item);
//                            fb.collection("Favorite").document(uid).set(f).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                   // view.setBackgroundResource(R.drawable.fav_on);
//                                }
//                            });
//                        }
//                    }
//                });
         }
        });
        binding.itemListRV.setAdapter(adapter);
        binding.itemListRV.setHasFixedSize(true);
        binding.itemListRV.setLayoutManager(new LinearLayoutManager(getContext()));

    }


}