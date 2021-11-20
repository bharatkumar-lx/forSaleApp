package com.bharat.forsale.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bharat.forsale.databinding.FragmentSearchBinding;



public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";
    private FragmentSearchBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        Log.d("SearchFragment", "Search Fragment is started");
        return binding.getRoot();
    }




}
