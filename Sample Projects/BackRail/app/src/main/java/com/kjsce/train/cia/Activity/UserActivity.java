package com.kjsce.train.cia.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kjsce.train.cia.Entities.UserEntity;
import com.kjsce.train.cia.Listener.AddUserListener;
import com.kjsce.train.cia.Listener.DeleteUserListener;
import com.kjsce.train.cia.Listener.GetUserListListener;
import com.kjsce.train.cia.Listener.GetUserListener;
import com.kjsce.train.cia.Listener.SetUserListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.UserUtility;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void addUser(View view)
    {
        UserUtility userUtility = new UserUtility();
        userUtility.addUser(new UserEntity("Amey", "InspectionOfficer", "0122"), "8655633463", new AddUserListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(),"User added!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteUser(View view)
    {
        UserUtility userUtility = new UserUtility();
        userUtility.deleteUser("8655633463", new DeleteUserListener() {
            @Override
            public void onCompleteTask(String result) {
                Toast.makeText(getApplicationContext(), "User Deleted!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getUser(View view)
    {
        UserUtility userUtility = new UserUtility();
        userUtility.getUser("8888888888", new GetUserListener() {
            @Override
            public void onCompleteTask(UserEntity userEntity) {
                Toast.makeText(getApplicationContext(), userEntity.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setUser(View view)
    {
        UserUtility userUtility = new UserUtility();
        userUtility.setUser(new UserEntity("BP", "InspectionOfficer", "0122"), "8888888888", new SetUserListener() {
            @Override
            public void onCompleteTask(UserEntity userEntity) {
                Toast.makeText(getApplicationContext(),"User updated!", Toast.LENGTH_LONG).show();
            }

        });
    }

    public void getUserList(View view)
    {
        UserUtility userUtility = new UserUtility();
        userUtility.getUserList(new GetUserListListener() {
            @Override
            public void onCompleteTask(List<String> userList) {
                Toast.makeText(getApplicationContext(),userList.toString(),Toast.LENGTH_LONG).show();
                System.out.println("userlist: "+userList);
            }
        });
    }

}
