package com.iabug.wecareforyou;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetpasswordActivity extends AppCompatActivity {


    private EditText reset_email;
    private Button reset_details_button;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        reset_email = (EditText) findViewById(R.id.resetemailidInput);
        reset_details_button = (Button) findViewById(R.id.ResetpasswordButton);

        auth = FirebaseAuth.getInstance();


        reset_details_button.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {

                                                        String email = reset_email.getText().toString().trim();

                                                        if (TextUtils.isEmpty(email)) {
                                                            Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                                                            return;
                                                        }


                                                        auth.sendPasswordResetEmail(email)
                                                                .addOnCompleteListener(new OnCompleteListener() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task task) {
                                                                        if (task.isSuccessful()) {
                                                                            Toast.makeText(ResetpasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                                                        } else {
                                                                            Toast.makeText(ResetpasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }

                                                                });

                                                    }
                                                }
        );
    }
}