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
import com.kjsce.train.cia.Listener.OnUserListChangeListener;
import com.kjsce.train.cia.Listener.SetUserListener;
import com.kjsce.train.cia.R;
import com.kjsce.train.cia.Utilities.UserUtility;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserActivity extends AppCompatActivity {

    private UserUtility userUtility = new UserUtility(new OnUserListChangeListener() {
        @Override
        public void OnDataChanged(List<String> newUserList) {
            Toast.makeText(getApplicationContext(), "User List   "+newUserList.toString(), Toast.LENGTH_LONG).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void addUser(View view) {
        userUtility.addUser(new UserEntity("Parakh", "CRP", "1225", "6556334638"));
    }

    public void deleteUser(View view)
    {
        userUtility.deleteUser("8888888888");
    }

    public void getUserList(View view)
    {
        List<String> userList =  userUtility.getUserList();
        Toast.makeText(getApplicationContext(), "User LIST: "+userList.toString(), Toast.LENGTH_LONG).show();

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



}
