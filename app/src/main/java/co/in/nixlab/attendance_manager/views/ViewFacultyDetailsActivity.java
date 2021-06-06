package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Faculty;

public class ViewFacultyDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_faculty_details);

        int facultyId = getIntent().getExtras().getInt("faculty_id");

        TextView idTextView = findViewById(R.id.lbl_fac_id);
        TextView nameTextView = findViewById(R.id.lbl_fac_name);
        TextView phoneTextView = findViewById(R.id.lbl_fac_mob_no);
        TextView addressTextView = findViewById(R.id.lbl_fac_address);
        TextView usernameTextView = findViewById(R.id.lbl_fac_username);

        DBHandler dbHandler = new DBHandler(this);

        try {
            Faculty faculty = dbHandler.getFacultyById(facultyId);

            String id = "ID : " + String.valueOf(faculty.getFaculty_id());
            idTextView.setText(id);

            String name = "NAME : " + faculty.getFaculty_firstname() + " " + faculty.getFaculty_lastname();
            nameTextView.setText(name);

            String phone = "PHONE : " + faculty.getFaculty_mobile_number();
            phoneTextView.setText(phone);

            String address = "ADDRESS : " + faculty.getFaculty_address();
            addressTextView.setText(address);

            String uname = "USERNAME : " + faculty.getFaculty_username();
            usernameTextView.setText(uname);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}