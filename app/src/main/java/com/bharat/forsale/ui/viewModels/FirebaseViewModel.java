package com.bharat.forsale.ui.viewModels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.bharat.forsale.ui.repository.AuthRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirebaseViewModel extends ViewModel {
    final private Application application;
    private final AuthRepository authRepository;


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



}
