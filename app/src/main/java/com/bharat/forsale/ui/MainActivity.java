package com.bharat.forsale.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.bharat.forsale.databinding.ActivityMainBinding;
import com.bharat.forsale.ui.viewModels.FirebaseViewModel;
import com.bharat.forsale.ui.viewModels.FirebaseViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public FirebaseViewModel firebaseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseViewModelFactory factory = new FirebaseViewModelFactory(this.getApplication());
        firebaseViewModel = new ViewModelProvider(this,factory).get(FirebaseViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}