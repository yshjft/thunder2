package com.example.thunder2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signin;
    private Button signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();


        email = (EditText)findViewById(R.id.sign_in_email);
        password = (EditText)findViewById(R.id.sign_in_pwd);
        signin = (Button)findViewById(R.id.sign_in_btn);
        signup = (Button)findViewById(R.id.sign_up_btn);

        // 로그인 버튼을 누르면 이메일, 비밀번호를 확인하고 다음 화면으로 이동한다.
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinEvent();
            }
        });

        // 회원가입 버튼을 누르면 SignUpActivity가 실행된다.
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });

        // 로그인 인터페이스 리스너, 로그인되었거나 로그아웃 되었는지 따른 상태가 변하면 인터페이서 리스너가 실행된다.

    }

    // 로그인이 정상적으로 되었는지 확인해준다.
    void signinEvent() {
       try {
            firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignIn.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(SignIn.this, setting_market.class);
                                Toast.makeText(SignIn.this, "로그인", Toast.LENGTH_SHORT).show();
                                try {
                                    intent.putExtra("stringUID", firebaseAuth.getCurrentUser().getUid());
                                }catch(Exception e){}
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
        }catch(Exception e){
           Toast.makeText(getApplicationContext(), "입력이 완료되지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}