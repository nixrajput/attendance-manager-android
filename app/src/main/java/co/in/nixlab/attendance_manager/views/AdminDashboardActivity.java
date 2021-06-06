package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Attendance;

public class AdminDashboardActivity extends AppCompatActivity {

    Button addStudent;
    Button addFaculty;
    Button viewStudent;
    Button viewFaculty;
    Button logout;
    Button attendancePerStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        addStudent = findViewById(R.id.add_student_btn);
        addFaculty = findViewById(R.id.add_faculty_btn);
        attendancePerStudent = findViewById(R.id.manage_attendance_btn);
        viewStudent = findViewById(R.id.view_students_btn);
        viewFaculty = findViewById(R.id.view_faculty_btn);
        logout = findViewById(R.id.logout_btn);

        addStudent.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        addFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddFacultyActivity.class);
            startActivity(intent);
        });

        viewFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ViewFacultyActivity.class);
            startActivity(intent);
        });

        viewStudent.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, SearchStudentActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SharedPreferences prefs = getSharedPreferences("my-prefs", MODE_PRIVATE);
            SharedPreferences.Editor prefsEdit = prefs.edit();
            prefsEdit.clear();
            prefsEdit.apply();
            Toast.makeText(getApplicationContext(), "Logged out successfully.",
                    Toast.LENGTH_LONG).show();
            startActivity(intent);
        });

        attendancePerStudent.setOnClickListener(v -> {

            DBHandler dbHandler = new DBHandler(AdminDashboardActivity.this);
            ArrayList<Attendance> attendanceBeanList = dbHandler.getAllAttendanceByStudent();
            ((AppContext) this.getApplicationContext()).setAttendanceList(attendanceBeanList);

            Intent intent = new Intent(AdminDashboardActivity.this,
                    ViewAttendancePerStudentActivity.class);
            startActivity(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}