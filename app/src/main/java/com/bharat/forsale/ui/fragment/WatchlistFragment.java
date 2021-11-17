package com.bharat.forsale.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bharat.forsale.databinding.FragmentWatchlistBinding;

public class WatchlistFragment extends Fragment {

    private FragmentWatchlistBinding binding ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWatchlistBinding.inflate(inflater,container,false);
        Log.d("WatchListFragment", "WatchList Fragment is started");
        return binding.getRoot();
    }
}
