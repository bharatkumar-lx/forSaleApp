package com.bharat.forsale.ui.dialogFragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bharat.forsale.databinding.DialogSelectPhotoBinding;

public class SelectPhotoDialog extends DialogFragment {
    private DialogSelectPhotoBinding dialogBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogBinding = DialogSelectPhotoBinding.inflate(inflater,container,false);
        return dialogBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    void showStorage(){
        Log.d("photoDialog", "showStorage: accessing phone memory");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    }


}
