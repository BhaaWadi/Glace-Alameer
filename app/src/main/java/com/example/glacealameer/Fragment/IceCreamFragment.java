package com.example.glacealameer.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.glacealameer.Activites.DetailsInsideActivity;
import com.example.glacealameer.R;
import com.example.glacealameer.databinding.FragmentIceCreamBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IceCreamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IceCreamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IceCreamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IceCreamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IceCreamFragment newInstance(String param1, String param2) {
        IceCreamFragment fragment = new IceCreamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentIceCreamBinding binding = FragmentIceCreamBinding.inflate(inflater, container, false);

         binding.cardSamll.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 movetoActivity(1);

             }
         });
//         binding.cardMedium.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 movetoActivity(2);
//
//             }
//         });

        binding.cardLarg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoActivity(3);

            }
        });
        binding.cardXllarg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoActivity(4);

            }
        });

        binding.cardSpSamll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoActivity(5);

            }
        });
        binding.cardSpMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoActivity(6);

            }
        });
        binding.cardSpLarg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetoActivity(7);

            }
        });





        return binding.getRoot();
    }



    public void movetoActivity(int id){

        Intent intent=new Intent(getContext(), DetailsInsideActivity.class);
        intent.putExtra("id",id);

        startActivity(intent);
    }
}