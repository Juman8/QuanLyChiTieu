package com.example.cao.quanlychitieu.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cao.quanlychitieu.R;
import com.example.cao.quanlychitieu.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import static com.example.cao.quanlychitieu.MainActivity.LINK;

/**
 * Created by HP on 2/12/2018.
 */

public class Profile extends AppCompatActivity{
    TextView tvUsername;
    TextView tvNgaySinh;
    TextView tvGioitinh;
    TextView tvSodienthoai;
    TextView tvDiachiEmail;
    Button btnEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_layout);
        init();
        getdata();
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Profile.this,"Clicked",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent();
//                startActivity(intent);
            }
        });


    }
    private void init(){
        tvUsername = (TextView)findViewById(R.id.tv_username);
        tvNgaySinh = (TextView)findViewById(R.id.tv_ngaysinh);
        tvGioitinh = (TextView)findViewById(R.id.tv_gioitinh);
        tvSodienthoai = (TextView)findViewById(R.id.tv_numberphone);
        tvDiachiEmail = (TextView)findViewById(R.id.tv_emal);
        btnEdit = (Button)findViewById(R.id.btn_editpf);
    }
    private void getdata() {
        Intent intent = getIntent();
        final String username = intent.getStringExtra("USERNAME");
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "User");

        Query query = ref.orderByChild("User_Gmail").equalTo(username);
        DatabaseReference statusRef = query.getRef();
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                tvUsername.setText(user.getUser_Name());
                tvNgaySinh.setText(user.getUser_date());
                tvGioitinh.setText(user.getUser_gioitinh());
                tvSodienthoai.setText(user.getUser_NumberPhone());
                tvDiachiEmail.setText(user.getUser_Gmail());
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
