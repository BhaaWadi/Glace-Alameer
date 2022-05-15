package com.example.glacealameer.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.glacealameer.Activites.DashbordActivity;
import com.example.glacealameer.Activites.NewsActivity;
import com.example.glacealameer.Activites.OffersActivity;
import com.example.glacealameer.Activites.OutsideActivity;
import com.example.glacealameer.databinding.FragmentHomeBinding;
import com.example.glacealameer.Activites.insideOrderActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public OnItemClick getListener() {
        return listener;
    }

    public void setListener(OnItemClick listener) {
        this.listener = listener;
    }

    OnItemClick listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//               // textView.setText(s);
//            }
//        });

        binding.cardInside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),insideOrderActivity.class);

                  getActivity().startActivity(intent);
            }
        });

        binding.cardOutside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), OutsideActivity.class);


                startActivity(intent);
            }
        });
        binding.cardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), DashbordActivity.class);


                startActivity(intent);
            }
        });

        binding.cardOffers.setOnClickListener(v -> {

            Intent intent=new Intent(getContext(), OffersActivity.class);


            startActivity(intent);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public  interface OnItemClick{

        void oClick();
    }
}