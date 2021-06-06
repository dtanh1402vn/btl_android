package com.example.dangtuananh_e17cn2_btlandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegsiterActivity extends AppCompatActivity {
    EditText edName, edEmail, edPassword;
    Button btnReg;
    TextView loginBtn;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        btnReg = findViewById(R.id.btnRegister);
        loginBtn = findViewById(R.id.tvLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = edEmail.getText().toString().trim();
                String ps = edPassword.getText().toString().trim();
                if (TextUtils.isEmpty(em)) {
                    edEmail.setError("Chưa điền email");
                    return;
                }
                if (TextUtils.isEmpty(ps)) {
                    edPassword.setError("Chưa điền mật khẩu");
                    return;
                }
                if (edPassword.length() < 6) {
                    edPassword.setError("Mật khẩu phải nhiều hơn 6 kí tự");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(em,ps).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegsiterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else{
                            Toast.makeText(RegsiterActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}