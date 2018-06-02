package com.kjsce.train.cia.Utilities.Backend;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Listeners.LoginListener;
import com.kjsce.train.cia.Listeners.LogoutListener;


public class LoginUtility {
    private Activity activity;
    private FirebaseAuth mAuth;
    boolean inputFlag, structureFlag, interfaceFlag = false;

    public void login(String emailId, String password, LoginListener listener) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("cia.kjsce@gmail.com", "ameymeher")
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user.getIdToken(true).toString());

                            inputFlag = true;
                            interfaceFlag = true;
                            structureFlag = true;

                        } else {
                            inputFlag = false;
                            interfaceFlag = false;
                            structureFlag = false;
                        }
                    }
                });
    }


    public void logout(LogoutListener listener)
    {

    }
}
