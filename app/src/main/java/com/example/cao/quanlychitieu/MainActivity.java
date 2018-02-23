package com.example.cao.quanlychitieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cao.quanlychitieu.User.Profile;
import com.example.cao.quanlychitieu.model.Group;
import com.example.cao.quanlychitieu.model.User;
import com.example.cao.quanlychitieu.viewcustom.BaiDangActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.cao.quanlychitieu.LoginActivity.GMAIL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tv_Diachi;
    TextView tv_Tenkhachhang;
    public final static String LINK = "https://quanlychitieu-9316c.firebaseio.com/";
    public final static String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();

        //getlntent();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            String username = tv_Tenkhachhang.getText().toString();
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if (id == R.id.nav_quanlychitieu) {
            Intent intent = new Intent(MainActivity.this, BaiDangActivity.class);
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            intent.putExtra(USERNAME, tv_Tenkhachhang.getText().toString());
            startActivity(intent);

        } else if (id == R.id.nav_quanlythongbao) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void init() {

        tv_Diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_Tenkhachhang = (TextView) findViewById(R.id.tv_tenkhachhang);

        //Khởi tạo SharedPreferences có tên "Profile"
        SharedPreferences share = getSharedPreferences("Profile", MODE_PRIVATE);
        //Lấy chuỗi String trong file SharedPreferences
        String gmail = share.getString("Gmail", "");
        String ten = share.getString("Username", "");

        tv_Diachi.setText(gmail);
        tv_Tenkhachhang.setText(ten);
    }

    public void getlntent() {
        Intent intent = this.getIntent();
        String Username = intent.getStringExtra(GMAIL);
        tv_Diachi.setText(Username);
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "User");


        Query query = ref.orderByChild("group_Title").equalTo(Username);
        DatabaseReference statusRef = query.getRef().child("user_Name");

        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = (User) dataSnapshot.getValue();
                tv_Tenkhachhang.setText(user.getUser_Name());
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
        //statusRef.setValue("COMPLETED");
    }
    public String getkey(String Username) {
        final Group[] group = {new Group()};
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReferenceFromUrl(LINK + "");
        Query query = ref.orderByChild("User_Gmail").equalTo(Username);
        DatabaseReference statusRef = query.getRef().child("ID");
        statusRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                group[0] = dataSnapshot.getValue(Group.class);
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
        return group[0].getID();
    }
}
