package com.bharat.forsale.viewModels;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.bharat.forsale.repository.AuthRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirebaseViewModel extends ViewModel  {
    private final Application application;
    private final AuthRepository authRepository;
    private final String TAG = "FirebaseViewModel";



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public FirebaseViewModel(Application application) {
        this.application = application;
        authRepository = new AuthRepository(application);
    }

    public boolean signUp(String email,String password){
        if(validate(email) && password.length() >7){
            Log.d("Signup: ","signed up");
            return  authRepository.signedUp(email, password);
        }else{
            Log.d("Signup: ","wrong credentials");
            Toast.makeText(application.getBaseContext(),"Please Enter valid email and password",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public boolean logIn(String email,String password){
        if(validate(email) && password.length() >7){
            return  authRepository.login(email, password);
        }else{
//            Snackbar.make(application.getBaseContext(),"Please Enter valid email and password",Snackbar.LENGTH_LONG).show();
            Toast.makeText(application,"Please Enter valid email and password",Toast.LENGTH_LONG).show();
        }
        return false;
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

    public void post(String ...args){
        boolean isEmpty = false;
        for(String s : args){
            if(s.isEmpty()){
                isEmpty = true;
            }
        }
        if(!isEmpty){

        }
    }

    public byte[] bytesFromUri(Uri uri){
        final byte[][] bytes = new byte[1][1];
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(application.getContentResolver(),uri);
                    Log.d(TAG, "bytesFromBitmap: size before compression "+ bitmap.getByteCount()/1000000);
                    bytes[0] = bytesFromBitmap(bitmap,90);
                    Log.d(TAG, "bytesFromBitmap: size after compression "+ bytes[0].length/1000000);
                }catch (IOException e){
                    Log.d(TAG, "getBitmapFromUri: "+ e.getMessage());
                    bytes[0] = null;
                }
            }
        });
        return bytes[0];
    }

    public byte[] bytesFromBitmap(Bitmap bitmap,int quality){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,quality,out);

        return  out.toByteArray();
    }





}
