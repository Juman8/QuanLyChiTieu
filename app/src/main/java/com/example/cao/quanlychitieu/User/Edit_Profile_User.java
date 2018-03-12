package com.example.cao.quanlychitieu.User;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cao.quanlychitieu.R;
import com.example.cao.quanlychitieu.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

import static com.example.cao.quanlychitieu.MainActivity.LINK;

/**
 * Created by HP on 2/26/2018.
 */

public class Edit_Profile_User extends AppCompatActivity {
    EditText tvUsername;
    EditText tvNgaySinh;
    EditText tvGioitinh;
    EditText tvSodienthoai;
    EditText tvDiachiEmail;
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

            }
        });
    }
    private void init(){
        tvUsername = (EditText) findViewById(R.id.tv_username);
        tvNgaySinh = (EditText)findViewById(R.id.tv_ngaysinh);
        tvGioitinh = (EditText)findViewById(R.id.tv_gioitinh);
        tvSodienthoai = (EditText)findViewById(R.id.tv_numberphone);
        btnEdit = (Button)findViewById(R.id.btn_editpf);
    }
    private void getdata() {
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences
        String username = share.getString("Gmail", "");
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "User");
        Query query = ref.orderByChild("User_Gmail").equalTo(username);
        DatabaseReference statusRef = query.getRef();
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                tvUsername.setHint(user.getUser_Name()+"");
                tvNgaySinh.setHint(user.getUser_date());
                tvGioitinh.setHint(user.getUser_gioitinh());
                tvSodienthoai.setHint(user.getUser_NumberPhone());
                tvDiachiEmail.setHint(user.getUser_Gmail());
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
    private void clicked(){
        String name = tvUsername.getText().toString().trim();
        String date = tvNgaySinh.getText().toString().trim();
        String gioitinh = tvGioitinh.getText().toString().trim();
        String sodienthoai = tvSodienthoai.getText().toString().trim();
        String gmail = tvDiachiEmail.getText().toString().trim();
   }
    private void writeNewPost(String name, String date, String gioitinh, String sodienthoai, String gmail) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences
        String key = share.getString("UsernameID", "");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        User post = new User(key,null, name, date, gioitinh,gmail,sodienthoai);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/" + key, postValues);

        ref.updateChildren(childUpdates);
    }
}
