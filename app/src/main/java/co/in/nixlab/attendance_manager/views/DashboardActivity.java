package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button addUserBtn = (Button) findViewById(R.id.add_user_btn);
        Button viewUserBtn = (Button) findViewById(R.id.view_users_btn);

        addUserBtn.setOnClickListener(v -> {
            Intent addUserIntent = new Intent(DashboardActivity.this, AddUserActivity.class);
            startActivity(addUserIntent);
        });

        viewUserBtn.setOnClickListener(v -> {
            Intent viewUsersIntent = new Intent(DashboardActivity.this, ViewUsersActivity.class);
            startActivity(viewUsersIntent);
        });
    }
}