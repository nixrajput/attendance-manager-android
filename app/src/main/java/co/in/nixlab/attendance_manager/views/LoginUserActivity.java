package co.in.nixlab.attendance_manager.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.User;

public class LoginUserActivity extends AppCompatActivity {

    Button loginBtn;
    EditText editTextUname, editTextPass;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        DBHandler dbHandler = new DBHandler(this);

        contextView = findViewById(android.R.id.content).getRootView();

        loginBtn = (Button) findViewById(R.id.login_btn);
        editTextUname = (EditText) findViewById(R.id.edt_uname);
        editTextPass = (EditText) findViewById(R.id.edt_pass);

        loginBtn.setOnClickListener(v -> {

            try {
                User currentUser = dbHandler.loginUser(
                        editTextUname.getText().toString(),
                        editTextPass.getText().toString());
                if(currentUser != null) {
                    Intent dashboardIntent = new Intent(LoginUserActivity.this, DashboardActivity.class);
                    startActivity(dashboardIntent);
                }
                else {
                    Snackbar.make(contextView, "Username or password is wrong. Please check and try again.",
                            Snackbar.LENGTH_LONG).show();
                }
            }catch (SQLiteException ex) {
                Snackbar.make(contextView, Objects.requireNonNull(ex.getMessage()),
                        Snackbar.LENGTH_LONG).show();
            }

        });
    }
}