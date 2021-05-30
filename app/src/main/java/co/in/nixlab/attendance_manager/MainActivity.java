package co.in.nixlab.attendance_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.views.LoginUserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = (Button) findViewById(R.id.user_login_btn);

        loginBtn.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this, LoginUserActivity.class);
            startActivity(loginIntent);
        });
    }
}