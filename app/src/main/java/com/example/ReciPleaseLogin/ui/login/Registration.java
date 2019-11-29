package com.example.ReciPleaseLogin.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ReciPleaseLogin.R;

import com.example.ReciPleaseLogin.data.DB;
import com.example.ReciPleaseLogin.data.RUser;
import com.example.ReciPleaseLogin.data.UserProfile;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Registration extends AppCompatActivity {
    private FirebaseAuth mAuth;


    //account
    private String email, password;
    //profile
    private String name, dname, occ,exp;
    boolean over15;

    //connection to ui
    private Button bCreateUser;
    private CheckBox age;
    private EditText emailbox, passbox,namebox,dispbox,occbox,expbox;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        bCreateUser =  findViewById(R.id.register);
        emailbox = findViewById(R.id.rEmail);
        passbox = findViewById(R.id.rPassword);
        namebox = findViewById(R.id.rrealName);
        dispbox = findViewById(R.id.rDisplayName);
        occbox = findViewById(R.id.rOcc);
        expbox = findViewById(R.id.rExperience);
        age =findViewById(R.id.rAge);


        bCreateUser.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Do something in response to button click
                if(validate_info()){
                    create_user();
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        submit_profile();
                    }
                }
                else{


                }
            }
        });
    }


private void create_user() {

    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "createUserWithEmail:success");
                       // FirebaseUser user = mAuth.getCurrentUser();

                       // create_profile(user);
                      //  LoginActivity.updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
//                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
  //                      Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                      //          Toast.LENGTH_SHORT).show();
                      //  LoginActivity.updateUI(null);

                    }

                    // ...
                }
            });
}


private void submit_profile(){
    Intent intent;
    RUser user= RUser.getInstance();
    UserProfile profile =new UserProfile(); //initialize userprofile object

     profile.username=name;
     profile.uuid=user.getUid();
     profile.who_are_you=dname;
     profile.cooking_experience=exp;
     profile.what_do_you_do=occ;
     profile.picture_link=null;
     profile.over15=over15;

    user.db.getInstance().push(profile);

    Toast.makeText(Registration.this, "Account Created!", Toast.LENGTH_SHORT).show();
    intent = new Intent(Registration.this, LoginActivity.class);
    startActivity(intent);
}



private boolean validate_info(){
        boolean valid=true;

        if (emailbox.getText().length()==0){
        valid=false;
        emailbox.setError("Email is required");
        }
        else{
        email=emailbox.getText().toString();

    }

        if (passbox.getText().length()<6){
        //error
        valid=false;
        passbox.setError("Password is required and minimum length of 6");

    }else
        {
         password=passbox.getText().toString();
        }

        if (namebox.getText().length()==0){
        valid=false;
        namebox.setError("Name is required");
    }else
    {
        name=namebox.getText().toString();

    }


        if (dispbox.getText().length()==0){
        dispbox.setError("Preferred Display Name is required");
        valid=false;
    }else
    {
        dname=dispbox.getText().toString();
    }


        if (occbox.getText().length()==0){
        occbox.setError("Please tell us what do you do?");
        valid=false;
    }else
    {
        occ=occbox.getText().toString();

    }


        if (expbox.getText().length()==0){
        expbox.setError("Please Enter your cooking experience");
        valid=false;
    }else
    {
        exp=expbox.getText().toString();

    }

    if (!age.isChecked()){
        age.setError("Come Back when you are at least 15 years old");
        valid=false;
    }else
    {
        over15=true;

    }

 return valid;
}




}












