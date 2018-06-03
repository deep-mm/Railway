package com.example.amey.loginfirebase.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.amey.loginfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public Intent intent;
    boolean inputFlag,structureFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


    }

    public void toInput(View view){
        if(inputFlag){
            intent = new Intent(this,InputActivity.class);
            startActivity(intent);
        }
        else{
            signIn();
        }
    }

    public void toStructure(View view){
        if(structureFlag){
            intent = new Intent(this,StructureActivity.class);
            startActivity(intent);
        }
        else{
            signIn();
        }
    }




    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            structureFlag = true;
            inputFlag = true;
        }
    }

    public void signIn(){
        mAuth.signInWithEmailAndPassword("test11@email.com", "password")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Log in successful",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(user.getIdToken(true).toString());

                            inputFlag = true;
                            structureFlag = true;

                        } else {
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                            inputFlag = false;
                            structureFlag = false;
                        }
                    }
                });
    }
}
