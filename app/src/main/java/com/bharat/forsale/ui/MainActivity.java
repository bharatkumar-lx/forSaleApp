package com.bharat.forsale.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

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
        if(firebaseViewModel.isLoggedIn()){
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
        }
    }

}