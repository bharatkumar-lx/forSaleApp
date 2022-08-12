package com.bharat.forsale.viewModels;

import static com.bharat.forsale.util.forsaleUtilites.validate;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.bharat.forsale.repository.AuthRepository;

import java.io.ByteArrayOutputStream;

public class FirebaseViewModel extends ViewModel  {
    private final Application application;
    private final AuthRepository authRepository;
    private final String TAG = "FirebaseViewModel";


    public FirebaseViewModel(Application application) {
        this.application = application;
        authRepository = new AuthRepository(application);
    }

    public void signUp(String email,String password){
        if(validate(email) && password.length() >7){
            Log.d("Signup: ","signed up");
            authRepository.signedUp(email, password);
        }else{
            Log.d(TAG +"Signup: ","wrong credentials");
            Toast.makeText(application.getBaseContext(),"Please Enter valid email and password",Toast.LENGTH_LONG).show();
        }
    }

    public void logIn(String email,String password){
        if(validate(email) && password.length() >7){
              authRepository.login(email, password);
        }else{
            Toast.makeText(application,"Please Enter valid email and password",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isLoggedIn(){
        return authRepository.isLoggedIn();
    }

    public void signOut(){
        if(isLoggedIn()) {
            authRepository.signOut();
            Toast.makeText(application, "Signed out", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(application,"Log in first",Toast.LENGTH_LONG).show();
        }
    }

    public void post(byte[] image, String title, String description, String price, String country, String state_province, String city, String contact_email){
        authRepository.uploadPost(image,title,description,price,country,state_province,city,contact_email);
    }
/*
//    public byte[] bytesFromUri(Uri uri){
//        final byte[][] bytes = new byte[1][1];
//        //Ui thread working up-til here
////        final ExecutorService executorService = Executors.newSingleThreadExecutor();
////        executorService.execute(new Runnable() {
////            @Override
////            public void run() {
////                try {
////                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(application.getContentResolver(),uri);
////                    Log.d(TAG, "BytesFromBitmap: size before compression "+ bitmap.getByteCount()/1000000);
////                    bytes[0] = bytesFromBitmap(bitmap,90);
////                    Log.d(TAG, "BytesFromBitmap: size after compression "+ bytes[0].length/1000000);
////                }catch (IOException e){
////                    Log.d(TAG, "BytesFromBitmap: "+ e.getMessage());
////                    bytes[0] = null;
////                }
////            }
////        });
//
//
//
//        return bytes[0];
//    }

 */

    public byte[] bytesFromBitmap(Bitmap bitmap,int quality){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,quality,out);
        return  out.toByteArray();
    }





}
