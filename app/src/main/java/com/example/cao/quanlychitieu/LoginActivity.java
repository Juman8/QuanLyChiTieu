package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cao.quanlychitieu.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * Created by HP on 1/30/2018.
 */

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView tvResign;
    TextView tv_ChangePass;
    EditText edt_Pass;
    EditText edt_ID;

    public final static String LINKS = "https://quanlychitieu-9316c.firebaseio.com/User";
    public final static String GMAIL = "Gmail";

    private FirebaseAuth mAuth;
    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        result = false;
        checkLogin();

        if (result == false) {
            setContentView(R.layout.activity_login);
            initwighet();
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String email = edt_ID.getText().toString().trim();
                    String password = edt_Pass.getText().toString().trim();
                    DangNhap2(email, password);
                    finish();

                }
            });
        }

    }

    private void checkLogin() {
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        String name = share.getString("Gmail", "");
        String pass = share.getString("Passwords", "");
        DangNhap(name, pass);
    }

    public void initwighet() {
        tvResign = (TextView) findViewById(R.id.tv_resign);
        tv_ChangePass = (TextView) findViewById(R.id.tv_changepasswords);
        btnLogin = (Button) findViewById(R.id.btn_login);
        edt_Pass = (EditText) findViewById(R.id.edt_passeword);
        edt_ID = (EditText) findViewById(R.id.edt_username);
    }

    private void DangNhap(final String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            Toast.makeText(LoginActivity.this, "Welcome back " + email, Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            finish();
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");\

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            result = false;
                            Toast.makeText(LoginActivity.this, "Co Loi xay ra, moi ban dang nhap lai.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    private void DangNhap2(final String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Cac truong khong duoc trong", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(LoginActivity.this, "Nhap Thanh Cong", Toast.LENGTH_LONG).show();
                                getkey(email);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                //Log.d(TAG, "signInWithEmail:success");\
                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Co Loi xay ra, moi ban thu lai.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            // ...
                        }
                    });
        }
    }

    public void getkey(String Username) {

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINKS);
        Query query = ref.orderByChild("User_Gmail").equalTo(Username);
        DatabaseReference statusRef = query.getRef();
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                //Đẩy 2 chuỗi lấy từ 2 editext ở file SharedPreferences
                editor.putString("Gmail", edt_ID.getText().toString());
                editor.putString("Passwords", edt_Pass.getText().toString());
                editor.putString("Username", user.getUser_Name());
                editor.putString("UsernameID", user.getID());
                editor.commit();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
