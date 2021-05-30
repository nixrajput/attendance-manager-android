package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.User;

public class AddUserActivity extends AppCompatActivity {
    RadioGroup userTypeGroup;
    EditText name, email, uname, pass;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        contextView = findViewById(android.R.id.content).getRootView();

        name = (EditText) findViewById(R.id.edt_name);
        email = (EditText) findViewById(R.id.edt_email);
        uname = (EditText) findViewById(R.id.edt_uname);
        pass = (EditText) findViewById(R.id.edt_pass);
        userTypeGroup = (RadioGroup) findViewById(R.id.user_type_radio_grp);

        Button saveUserBtn = (Button) findViewById(R.id.save_user_btn);

        saveUserBtn.setOnClickListener(v -> saveUser());
    }

    private void saveUser() {
        DBHandler dbHandler = new DBHandler(this);
        User user;

        int userTypeSelectedId = userTypeGroup.getCheckedRadioButtonId();

        RadioButton userTypeBtn = (RadioButton) findViewById(userTypeSelectedId);

        if (name.getText().toString().matches("") ||
                email.getText().toString().matches("") ||
                uname.getText().toString().matches("") ||
                pass.getText().toString().matches("") ||
                userTypeSelectedId == -1) {
            Snackbar.make(contextView, "All fields are required.",
                    Snackbar.LENGTH_LONG).show();
        } else {
            user = new User(name.getText().toString(), email.getText().toString(),
                    uname.getText().toString(), pass.getText().toString(),
                    userTypeBtn.getText().toString().toUpperCase());
            dbHandler.addUser(user);
            Snackbar.make(contextView, "User Saved Successfully",
                    Snackbar.LENGTH_LONG).show();
        }

    }
}