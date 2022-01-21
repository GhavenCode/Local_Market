package com.example.localmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class HelpScreen extends AppCompatActivity {

    TextView textViewLogin;
    ImageButton btnImageFb;
    ImageButton btnImageTw;
    private TextInputEditText inputEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        textViewLogin = (TextView) findViewById(R.id.tvLogin);
        inputEmail = (TextInputEditText) findViewById(R.id.editTextEmailAddress);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(registerIntent);
            }
        });

        btnImageFb = (ImageButton) findViewById(R.id.imageFacebook);
        btnImageFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseFacebook = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/GhavGraphics"));
                startActivity(browseFacebook);
            }
        });

        btnImageTw = (ImageButton) findViewById(R.id.imageTwitter);
        btnImageTw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browseTwitter = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.Twitter.com"));
                startActivity(browseTwitter);
            }
        });
    }

    private boolean validateEmail()
    {
        String emailInput = inputEmail.getEditableText().toString().trim();

        if (emailInput.isEmpty()){
            inputEmail.setError("Email Field cannot be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            inputEmail.setError(getString(R.string.validemailerror));
            return false;
        }
        else
        {
            inputEmail.setError(null);
            return true;
        }
    }
}