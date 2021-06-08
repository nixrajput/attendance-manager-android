package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Faculty;

public class LoginActivity extends AppCompatActivity {

    private final String[] userRoleString = new String[]{"ADMIN", "FACULTY"};
    Button login;
    EditText username, password;
    Spinner spinnerLoginAs;
    String userRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login = findViewById(R.id.login_btn);
        username = findViewById(R.id.edt_uname);
        password = findViewById(R.id.edt_pass);
        spinnerLoginAs = findViewById(R.id.spinnerLoginAs);

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
                    username.setError("Enter Username");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("Enter password");
                } else {
                    if (user_name.equals("admin") & pass_word.equals("pass")) {
                        SharedPreferences prefs = getSharedPreferences("my-prefs", MODE_PRIVATE);
                        SharedPreferences.Editor prefsEdit = prefs.edit();
                        prefsEdit.putBoolean("logged_in", true);
                        prefsEdit.putString("user_role", userRole);
                        prefsEdit.apply();
                        Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login successful.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Login failed",
                                Toast.LENGTH_LONG).show();
                    }
                }
            } else {

                if (TextUtils.isEmpty(user_name)) {
                    username.setError("Enter Username");
                } else if (TextUtils.isEmpty(pass_word)) {
                    password.setError("Enter password");
                }
                DBHandler dbHandler = new DBHandler(LoginActivity.this);
                Faculty faculty = dbHandler.validateFaculty(user_name, pass_word);

                if (faculty != null) {
                    SharedPreferences prefs = getSharedPreferences("my-prefs", MODE_PRIVATE);
                    SharedPreferences.Editor prefsEdit = prefs.edit();
                    prefsEdit.putBoolean("logged_in", true);
                    prefsEdit.putString("user_role", userRole);
                    prefsEdit.putInt("faculty_id", faculty.getFaculty_id());
                    prefsEdit.apply();
                    Intent intent = new Intent(LoginActivity.this,
                            AddAttendanceSessionActivity.class);
                    startActivity(intent);
                    ((AppContext) this.getApplicationContext()).setFaculty(faculty);
                    Toast.makeText(getApplicationContext(),
                            "Login successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Login failed", Toast.LENGTH_LONG).show();
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