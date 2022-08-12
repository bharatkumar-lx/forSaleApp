package com.bharat.forsale.repository;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.bharat.forsale.R;
import com.bharat.forsale.model.Post;
import com.bharat.forsale.ui.SearchActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class AuthRepository{
    private final Application application;
    private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final String TAG = "Repository";



    public AuthRepository(Application application) {
        this.application = application;
        FirebaseApp.initializeApp(/*context=*/ application);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance());
    }

    public boolean signedUp(String email,String password){
        final boolean[] bool = {false};
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> bool[0] = task.isSuccessful());
        return bool[0];
    }


    public void login(String email,String password){
        final boolean[] bool = {false};
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Intent intent = new Intent(application.getApplicationContext(), SearchActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                application.startActivity(intent);
                Log.d("login","successfully logged in");
            }
        }).addOnFailureListener( message ->{
            Toast.makeText(application.getApplicationContext(),"authentication problem from firebase error",Toast.LENGTH_SHORT).show();
            Log.d("login","authentication problem from firebase error "+message);
        });
//        Log.d("login","authentication problem from firebase "+bool[0]);
    }

    public boolean isLoggedIn(){
        return firebaseAuth.getCurrentUser() != null;
    }

    public void signOut(){
        firebaseAuth.signOut();
    }

    public void uploadPost(byte[] image, String title, String description, String price, String country, String state_province, String city, String contact_email){
        final String postId = FirebaseDatabase.getInstance().getReference().push().getKey();
        final StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("posts/user/"+ firebaseAuth.getCurrentUser().getUid() + "/" + postId + "/post_image");
        Log.d(TAG,"postId is "+ postId);

        storageReference.putBytes(image).addOnSuccessListener( taskSnapshot -> {
            Toast.makeText(application, "Upload Success", Toast.LENGTH_SHORT).show();
            //insert download uri into firebase database
            Post post = new Post();
            final String[] photoLink = new String[1];
            Task<Uri> task = taskSnapshot.getMetadata().getReference().getDownloadUrl();
            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    photoLink[0] = uri.toString();
                    Log.d(TAG, "Inside task listener");
                    post.setImage(uri.toString());
                }
            });
            Log.d(TAG, "Outside task listener");

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            post.setPost_id(postId);
            post.setUser_id(firebaseAuth.getCurrentUser().getUid());
            post.setTitle(title);
            post.setDescription(description);
            post.setCity(city);
            post.setContact_email(contact_email);
            post.setCountry(country);
            post.setState_province(state_province);
            post.setPrice(price);
            databaseReference.child(application.getString(R.string.node_posts))
                    .child(postId)
                    .setValue(post);
        }).addOnFailureListener((e -> {
            Toast.makeText(application, "Upload Failed ", Toast.LENGTH_SHORT).show();
        }));
    }


}
