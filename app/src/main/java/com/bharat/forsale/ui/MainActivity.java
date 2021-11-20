package com.bharat.forsale.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bharat.forsale.R;
import com.bharat.forsale.adapter.SelectionPagerAdapter;
import com.bharat.forsale.databinding.ActivityMainBinding;
import com.bharat.forsale.viewModels.FirebaseViewModel;
import com.bharat.forsale.viewModels.FirebaseViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public FirebaseViewModel firebaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseViewModelFactory factory = new FirebaseViewModelFactory(getApplication());
        firebaseViewModel = new ViewModelProvider(this,factory).get(FirebaseViewModel.class);
    }

}