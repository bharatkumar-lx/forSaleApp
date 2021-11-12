package com.bharat.forsale.ui.repository;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository{
    private Application application;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    public AuthRepository(Application application) {
        this.application = application;
    }

    public boolean signedUp(String email,String password){
        final boolean[] bool = {false};
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> bool[0] = task.isSuccessful());
        return bool[0];
    }


    public boolean login(String email,String password){
        final boolean[] bool = {false};
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> bool[0] = task.isSuccessful());
        return bool[0];
    }

    public boolean isLoggedIn(){
        return firebaseAuth.getCurrentUser() != null;
    }

}
