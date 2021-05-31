package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.ApplicationContext;
import co.in.nixlab.attendance_manager.controllers.DBAdapter;
import co.in.nixlab.attendance_manager.models.Faculty;

public class LoginActivity extends AppCompatActivity {

    private final String[] userRoleString = new String[]{"ADMIN", "FACULTY"};
    Button login;
    EditText username, password;
    Spinner spinnerLoginAs;
    String userRole;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);
        login = (Button) findViewById(R.id.login_btn);
        username = (EditText) findViewById(R.id.edt_uname);
        password = (EditText) findViewById(R.id.edt_pass);
        spinnerLoginAs = (Spinner) findViewById(R.id.spinnerLoginAs);
        contextView = findViewById(android.R.id.content).getRootView();

        spinnerLoginAs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                userRole = (String) spinnerLoginAs.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_role = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, userRoleString);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoginAs.setAdapter(adapter_role);

        login.setOnClickListener(v -> {

            String user_name = username.getText().toString();
            String pass_word = password.getText().toString();
            if (userRole.equals("ADMIN")) {

                if (TextUtils.isEmpty(user_name)) {
                    username.setError("Invalid Username");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("Enter password");
                } else {
                    if (user_name.equals("admin") & pass_word.equals("pass")) {
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        Snackbar.make(contextView, "Login successful", Snackbar.LENGTH_LONG).show();
                    } else {
                        Snackbar.make(contextView, "Login failed", Snackbar.LENGTH_LONG).show();
                    }
                }
            } else {

                if (TextUtils.isEmpty(user_name)) {
                    username.setError("Invalid Username");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("Enter password");
                }
                DBAdapter dbAdapter = new DBAdapter(LoginActivity.this);
                Faculty faculty = dbAdapter.validateFaculty(user_name, pass_word);

                if (faculty != null) {
                    Intent intent = new Intent(LoginActivity.this, AddAttendanceSessionActivity.class);
                    startActivity(intent);
                    ((ApplicationContext) LoginActivity.this.getApplicationContext()).setFaculty(faculty);
                    Snackbar.make(contextView, "Login successful", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(contextView, "Login failed", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}