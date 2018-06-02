package com.example.amey.loginfirebase.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.amey.loginfirebase.R;

public class AuthActivity extends AppCompatActivity
{
    private Button button_login;
    private Button button_logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        button_login = (Button)findViewById(R.id.button_login);
        button_logout = (Button)findViewById(R.id.button_logout);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
