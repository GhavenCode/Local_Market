package com.example.localmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginScreen extends MainActivity {
    TextView textViewReg;
    TextView textViewHelp;
    private Button btLogin;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewReg = (TextView) findViewById(R.id.tvRegister);
        textViewHelp = (TextView) findViewById(R.id.tvHelp);
        btLogin = (Button) findViewById(R.id.buttonLogin);
        textInputEmail = findViewById(R.id.textInputEditText);
        textInputPassword = findViewById(R.id.editTextPassword);

        textViewReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), Register.class);
                startActivity(registerIntent);
            }
        });


        textViewHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(getApplicationContext(), HelpScreen.class);
                startActivity(helpIntent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent loginIntent = new Intent(getApplicationContext(),MainActivity.class);

                if (!validatePassword() | !validateEmail())
                {
                    validatePassword();
                    validateEmail();
                }
                else
                {
                    startActivity(loginIntent);
                }
            }

        });

    }
    private boolean validateEmail()
    {
        String emailInputTxt = textInputEmail.getEditableText().toString().trim();

        if (emailInputTxt.isEmpty()){
            textInputEmail.setError("Email Field cannot be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInputTxt).matches())
        {
            textInputEmail.setError("Please enter a valid Email");
            return false;
        }
        else
        {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword()
    {
        String passwordInput = textInputPassword.getEditableText().toString().trim();

        if (passwordInput.isEmpty())
        {
            textInputPassword.setError("Password field cannot be empty");
            return false;
        }
        else
        {
            textInputPassword.setError(null);
            return true;
        }
    }
}
