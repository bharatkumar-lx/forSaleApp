package com.bharat.forsale.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FirebaseViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public FirebaseViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass) {
        return (T) new FirebaseViewModel(application);
    }
}
