package com.bharat.forsale.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bharat.forsale.databinding.FragmentViewPostBinding;

public class ViewPostFragment extends Fragment {

    private FragmentViewPostBinding binding ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewPostBinding.inflate(inflater,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
