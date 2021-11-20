package com.bharat.forsale.ui.dialogFragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.bharat.forsale.databinding.DialogSelectPhotoBinding;

public class SelectPhotoDialog extends DialogFragment {
    private DialogSelectPhotoBinding dialogBinding;
    private ActivityResultLauncher<Intent> imageLauncher;
    private ActivityResultLauncher takePhoto;
    public Uri imageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogBinding = DialogSelectPhotoBinding.inflate(inflater,container,false);
        return dialogBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent i = result.getData();
                        imageUri = i.getData();
                        dismiss();
                    }
                });

        takePhoto = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), result -> {
            dismiss();
        });
        dialogBinding.dialogOpenStorage.setOnClickListener(view1 -> showStorage());
        dialogBinding.dialogOpenCamera.setOnClickListener(view1 -> openCamera());
    }


    void showStorage(){
        Log.d("PhotoDialog", "onViewCreated: openStorage Clicked");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("Image/*");
        imageLauncher.launch(intent);
    }

    void openCamera(){
        takePhoto.launch(null);
    }

}
