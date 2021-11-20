package com.bharat.forsale.repository;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;

public class AuthRepository{
    private Application application;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


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

    public void signOut(){
        firebaseAuth.signOut();
    }

}
