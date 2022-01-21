 package com.example.localmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.localmarket.ui.home.HomeFragment;
import com.example.localmarket.ui.home.HomeViewModel;

 public class WelcomeScreen extends AppCompatActivity {
private Button button;
private TextView tvRegister;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        button = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

         tvRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent registerIntent = new Intent(getApplicationContext(), Register.class);
                 startActivity(registerIntent);
             }
         });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(loginIntent);
            }

        });

    }
}