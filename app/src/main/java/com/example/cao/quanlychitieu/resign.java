package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.cao.quanlychitieu.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.password;

/**
 * Created by HP on 2/2/2018.
 */

public class resign extends AppCompatActivity {
    Button btn_DangKy;
    EditText edt_Name;
    EditText edt_Email;
    EditText edt_Pass;
    EditText edt_Confrim;
    public final static String ACCOUNT = "Account";
    public final static String USER = "User";
    private FirebaseAuth mAuth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky_layout);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        initwighet();
        setonclick();
    }

    public void initwighet() {
        btn_DangKy = (Button) findViewById(R.id.btn_dangky);
        edt_Email = (EditText) findViewById(R.id.edt_email);
        edt_Pass = (EditText) findViewById(R.id.edt_pass);
        edt_Confrim = (EditText) findViewById(R.id.edt_conf);
        edt_Name = (EditText) findViewById(R.id.edt_username);
    }

    private void check() {

        String email = edt_Email.getText().toString();
        String pass = edt_Pass.getText().toString().trim();
        String conf = edt_Confrim.getText().toString().trim();
        if (pass.isEmpty()) {
            Toast.makeText(resign.this, "Không được trống!", Toast.LENGTH_SHORT).show();
        } else if (conf.equals(pass)) {
            Toast.makeText(resign.this, "Xác nhận mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
        } else {
            DangKy();
        }
    }

    private void setonclick() {
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }

    private void DangKy() {
        final String username = edt_Name.getText().toString();
        final String email = edt_Email.getText().toString();
        String pass = edt_Pass.getText().toString();
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "ERR, try agains", Toast.LENGTH_SHORT).show();
        } else {
            final String keyID = FirebaseDatabase.getInstance().getReference().push().getKey();
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                User user = new User(keyID,null, username, null, null, email, null);
                                database.child(USER).push().setValue(user);
                                Toast.makeText(resign.this, "Đăng Ký Thành Công.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(resign.this,LoginActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(resign.this, "Có Lỗi Xảy Ra mời thử lại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
