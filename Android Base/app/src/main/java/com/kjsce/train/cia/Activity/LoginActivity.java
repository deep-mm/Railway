package com.kjsce.train.cia.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kjsce.train.cia.Activity.Inspection.InspectionMenuActivity;
import com.kjsce.train.cia.Activity.Maintainence.TrainSearchActivity;
import com.kjsce.train.cia.Entity.UserEntity;
import com.kjsce.train.cia.Listeners.GetUserListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.Backend.UserUtility;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    ImageButton submit;
    String get_username,get_password;
    SharedData sd;
    Boolean success;
    private FirebaseAuth mAuth;
    MaterialDialog materialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Validate the username and password for null and other validations
                get_username = username.getText().toString() + "@email.com";
                get_password = password.getText().toString();

                if (get_username.equalsIgnoreCase("") || get_password.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be left empty", Toast.LENGTH_SHORT).show();
                } else {
                    materialDialog = new MaterialDialog.Builder(LoginActivity.this)
                            .title("Logging In")
                            .content("Please Wait")
                            .progress(true, 0)
                            .show();
                    progressStart();
                    signIn(get_username,get_password);
                }
            }
        });
    }

    public void initialize(){
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        submit = (ImageButton) findViewById(R.id.submit);
        sd = new SharedData(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();
    }

    public void progressStart(){
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void progressStop(){
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void checkType(FirebaseUser user){

        UserUtility userUtility = new UserUtility();
        userUtility.getUser(user.getUid(), new GetUserListener() {
            @Override
            public void onCompleteTask(UserEntity userEntity) {
                Intent i;
                if(userEntity.getType().equals("inspection")){
                    i = new Intent(getApplicationContext(), InspectionMenuActivity.class);
                }
                else{
                    i = new Intent(getApplicationContext(), TrainSearchActivity.class);
                }
                sd.setUserEntity(userEntity);
                startActivity(i);
            }
        });
    }

    public void checkLoggedIn(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            checkType(currentUser);
        }
    }

    public void signIn(String username,String password){
        System.out.println("username: " + username + " " + password);
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("Exception " + task.getException());
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            checkType(user);
                        } else {
                            new MaterialDialog.Builder(LoginActivity.this)
                                    .title("Incorrect")
                                    .content("Invalid Credentials")
                                    .positiveText("OK")
                                    .negativeText("EXIT")
                                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                        }
                                    })
                                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                                        @Override
                                        public void onClick(MaterialDialog dialog, DialogAction which) {
                                            finishAffinity();
                                        }
                                    })
                                    .show();
                        }
                        materialDialog.hide();
                        progressStop();
                    }
                });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkLoggedIn();
    }
}
