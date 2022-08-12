package com.bharat.forsale.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bharat.forsale.R;
import com.bharat.forsale.databinding.FragmentPostBinding;
import com.bharat.forsale.ui.dialogFragment.SelectPhotoDialog;
import com.bharat.forsale.util.forsaleUtilites;
import com.bharat.forsale.viewModels.FirebaseViewModel;
import com.bharat.forsale.viewModels.FirebaseViewModelFactory;
import com.bumptech.glide.Glide;


public class PostFragment extends Fragment {
    private ActivityResultLauncher<String[]> permissionLauncher;
    private FragmentPostBinding binding ;
    private FirebaseViewModel firebaseViewModel;
    private byte[] imageByteArray;
//    private final String TAG = "PostFragment";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater,container,false);
        setPermissionLauncher();
        assert getActivity()  != null;
        FirebaseViewModelFactory factory = new FirebaseViewModelFactory(getActivity().getApplication());
        firebaseViewModel = new ViewModelProvider(this,factory).get(FirebaseViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.postImage.setOnClickListener(this::onClick);
        binding.btnPost.setOnClickListener(view1 -> {
            post();
            showProgressBar();
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
    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
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


    private void setPermissionLauncher(){
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
    }

    //Fixme : image is processing on background thread but ui thread gets update until  background done it's work
    private void onClick(View buttonView) {
        requestPermissions();
        SelectPhotoDialog s = new SelectPhotoDialog();
        s.show(getChildFragmentManager(), getString(R.string.photo_dialog));
        //gets result from child photoDialog Fragment

        getChildFragmentManager().setFragmentResultListener("1", getViewLifecycleOwner(),
                (requestKey, result) -> {
                   if(result.get("image") != null){
                       imageByteArray = result.getByteArray("image");
                       BitmapFactory.Options options = new BitmapFactory.Options();
                       options.inMutable = true;
                       Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length, options);
                       Glide.with(binding.getRoot()).load(bmp).into(binding.postImage);
                   }
                });
    }

    private void post(){
        setText();
        firebaseViewModel.post(imageByteArray,
                binding.inputTitle.getText().toString(),
                binding.inputDescription.getText().toString(),
                binding.inputPrice.getText().toString(),
                binding.inputCountry.getText().toString(),
                binding.inputStateProvince.getText().toString(),
                binding.inputCity.getText().toString(),
                binding.inputEmail.getText().toString());
    }
    private void setText(){
                binding.inputTitle.setText("title");
                binding.inputDescription.setText("des");
                binding.inputPrice.setText("price");
                binding.inputCountry.setText("country");
                binding.inputStateProvince.setText("province");
                binding.inputCity.setText("city");
                binding.inputEmail.setText("email");
    }
}
