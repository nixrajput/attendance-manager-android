package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Attendance;

public class DashboardActivity extends AppCompatActivity {

    Button addStudent;
    Button addFaculty;
    Button viewStudent;
    Button viewFaculty;
    Button logout;
    Button attendancePerStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        addStudent = (Button) findViewById(R.id.add_student_btn);
        addFaculty = (Button) findViewById(R.id.add_faculty_btn);
        attendancePerStudent = (Button) findViewById(R.id.manage_attendance_btn);
        viewStudent = (Button) findViewById(R.id.view_students_btn);
        viewFaculty = (Button) findViewById(R.id.view_faculty_btn);
        logout = (Button) findViewById(R.id.logout_btn);

        addStudent.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddStudentActivity.class);
            startActivity(intent);
        });

        addFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, AddFacultyActivity.class);
            startActivity(intent);
        });

        viewFaculty.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ViewFacultyActivity.class);
            startActivity(intent);
        });

        viewStudent.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, ViewStudentActivity.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        attendancePerStudent.setOnClickListener(v -> {

            DBHandler dbHandler = new DBHandler(DashboardActivity.this);
            ArrayList<Attendance> attendanceBeanList = dbHandler.getAllAttendanceByStudent();
            ((AppContext) this.getApplicationContext()).setAttendanceList(attendanceBeanList);

            Intent intent = new Intent(DashboardActivity.this,
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