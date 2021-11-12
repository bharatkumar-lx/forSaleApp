package com.bharat.forsale.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bharat.forsale.databinding.FragmentSignUpBinding;
import com.bharat.forsale.ui.MainActivity;
import com.bharat.forsale.ui.viewModels.FirebaseViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment{

    private FragmentSignUpBinding binding;
    private FirebaseViewModel firebaseViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseViewModel = ((MainActivity)getActivity()).firebaseViewModel;
        binding.signUpButton.setOnClickListener(view1 -> {
            sign(view);
        });
    }


    private boolean sign(View view){
        String email = binding.signUpId.getText().toString();
        String password = binding.signUpPassword.getText().toString();
        String confirmPassword = binding.signUpConfirmPassword.getText().toString();
        if(password.equals(confirmPassword)){
            firebaseViewModel.signUp(email,password);
            Snackbar.make(view,"sign up success",Snackbar.LENGTH_LONG).show();

            return true;
        }else{
            Snackbar.make(view,"Password mismatch",Snackbar.LENGTH_LONG).show();
        }
        return false;
    }




}

