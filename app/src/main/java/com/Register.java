package com.example.localmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private static final String pattern_password = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    private static Pattern PASSWORD_PATTERN =
            Pattern.compile(pattern_password);

    private TextView textViewLogin;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputFName;
    private TextInputEditText textInputLName;
    private TextInputEditText textInputNumber;
    private TextInputEditText textInputPassword;
    private TextInputEditText textInputConfirmPwd;
    private Button btRegister;

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String uid;
    private FirebaseUser currentUser;
    private ProgressBar progressBar;


    public static void setPasswordPattern(Pattern passwordPattern) {
        PASSWORD_PATTERN = passwordPattern;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mAuth = FirebaseAuth.getInstance();
        //assign Variables
        textInputEmail = findViewById(R.id.inputEmail);
        textInputFName = findViewById(R.id.inputFName);
        textInputLName = findViewById(R.id.inputLName);
        textInputNumber = findViewById(R.id.inputPhoneNo);
        btRegister = (Button) findViewById(R.id.btnRegister);
        textViewLogin = (TextView) findViewById(R.id.tvLogin);
        textInputPassword = findViewById(R.id.inputPassword);
        textInputConfirmPwd = findViewById(R.id.inputConfirm);
        progressBar = findViewById(R.id.progressBar);

//updateUI(currentUser);


        //--------------------------------------------------------------------------

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerIntent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(registerIntent);
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLogin = new Intent(getApplicationContext(), LoginScreen.class);
                registerUser();
                //startActivity(goToLogin);

            }

        });

    }


    private void registerUser() {
        String emailAddress = textInputEmail.getEditableText().toString().trim();
        String confirmedPassword = textInputConfirmPwd.getEditableText().toString().trim();
        String fName = textInputFName.getEditableText().toString().trim();
        String lName = textInputLName.getEditableText().toString().trim();
        String number = textInputNumber.getEditableText().toString().trim();
        String password = textInputPassword.getEditableText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        String error_message = getString(R.string.error_message);
        String success_message = getString(R.string.success_message);

        //Validate Email-------------------------------------------------------------------
        if (emailAddress.isEmpty()) {
            textInputEmail.setError(getString(R.string.email_required_field));

        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            textInputEmail.setError(getString(R.string.validemailerror));
        }
        else{
            textInputEmail.setError(null);
        }

        //Validate Password-------------------------------------------------------------------
        if (password.isEmpty()) {
            textInputPassword.setError(getString(R.string.pass_field_required));

        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            textInputPassword.setError(getString(R.string.password_constraints));
        }
        else{
            textInputPassword.setError(null);
        }

        //Confirm Password--------------------------------------------------------
        if (password.isEmpty()) {
            textInputConfirmPwd.setError(getString(R.string.pass_field_required));

        } else if (!confirmedPassword.matches(password)) {
            textInputConfirmPwd.setError(getString(R.string.match_password));

        } else {
            textInputConfirmPwd.setError(null);
        }

        //Validate Names-----------------------------------------------------------
        if (fName.isEmpty()) {
            textInputFName.setError(getString(R.string.required_field));
        } else {
            textInputFName.setError(null);
        }

        if (lName.isEmpty()) {
            textInputLName.setError(getString(R.string.required_field));

        } else {
            textInputLName.setError(null);
        }

        //Validate Number---------------------------------------------------------------
        if (number.isEmpty()) {
            textInputNumber.setError(getString(R.string.required_field));

        } else if (number.charAt(0) != '0' || number.length() != 10) {
            textInputNumber.setError(getString(R.string.validNumber));

        } else {
            textInputNumber.setError(null);

        }



        mAuth.signInWithEmailAndPassword(emailAddress, confirmedPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(emailAddress, fName, lName, number);
                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), success_message, Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }
}

