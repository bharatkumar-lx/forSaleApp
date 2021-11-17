package com.bharat.forsale.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bharat.forsale.databinding.FragmentPostBinding;
import com.bharat.forsale.ui.dialogFragment.SelectPhotoDialog;
import com.bharat.forsale.util.forsaleUtilites;

public class PostFragment extends Fragment {
    private ActivityResultLauncher<String[]> permissionLauncher;
    private FragmentPostBinding binding ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater,container,false);
        Log.d("PostFragment", "Post Fragment is started");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(),
                result -> {
                    if(!forsaleUtilites.hasPermission(getContext())) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Permission Required")
                                .setMessage("Storage and camera permission require to work " +
                                        "click on ok to grant permission from setting")
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                                        intent.setData(uri);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("Cancel", ((dialogInterface, i) -> {
                                    dialogInterface.cancel();
                                })).show();
                    }
                }
        );
        binding.postImage.setOnClickListener(view1 -> {
            requestPermissions();

        });
    }

    private void resetFields(){
        binding.inputTitle.setText("");
        binding.inputDescription.setText("");
        binding.inputPrice.setText("");
        binding.inputCountry.setText("");
        binding.inputStateProvince.setText("");
        binding.inputCity.setText("");
        binding.inputEmail.setText("");
    }

    private void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }


    private void requestPermissions(){
        String[] permissions = {
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if(!forsaleUtilites.hasPermission(getContext())){
            permissionLauncher.launch(permissions);
        }
    }

}
