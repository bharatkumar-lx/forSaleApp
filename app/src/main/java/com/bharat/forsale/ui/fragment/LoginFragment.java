package com.bharat.forsale.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bharat.forsale.R;
import com.bharat.forsale.databinding.FragmentLoginBinding;
import com.bharat.forsale.ui.MainActivity;
import com.bharat.forsale.ui.SearchActivity;
import com.bharat.forsale.viewModels.FirebaseViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseViewModel firebaseViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseViewModel = ((MainActivity)getActivity()).firebaseViewModel;
        binding.loginButton.setOnClickListener(view1 -> {
            Log.d("LogInFragment","log in button pressed");
            logIn();
        });

        binding.signUpButton.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signUpFragment);
        });

        binding.loginForgetButton.setOnClickListener(view1 ->{
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }

    private void logIn(){
        Log.d("LogInFragment","log in status "+ firebaseViewModel.isLoggedIn());
        String email = binding.loginIdText.getText().toString();
        String password = binding.loginPassword.getText().toString();
        firebaseViewModel.logIn(email,password);
    }



}