package co.in.nixlab.attendance_manager.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.AttendanceSession;
import co.in.nixlab.attendance_manager.models.Faculty;
import co.in.nixlab.attendance_manager.models.Student;

public class AddAttendanceSessionActivity extends AppCompatActivity {

    private final String[] branchString = new String[]{"CSE", "ME", "EE", "CE", "ECE"};
    private final String[] SemString = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    private final String[] subjectFinal = new String[]{"M3", "DS", "M4", "CN", "M5", "NS"};
    Button addAttendanceBtn;
    Button viewAttendance;
    Button viewTotalAttendance;
    Button facultyLogoutBtn;
    Spinner spinnerBranch, spinnerSem, spinnerSubject;
    String branch = "CSE";
    String semester = "1st";
    String subject = "SC";
    DatePickerDialog datePickerDialog;
    private int day;
    private int month;
    private int year;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);

        spinnerBranch = findViewById(R.id.spinner_stu_branch);
        spinnerSem = findViewById(R.id.spinner_stu_sem);
        spinnerSubject = findViewById(R.id.spinner_sub);

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(adapter_branch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                branch = (String) spinnerBranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, SemString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(adapter_year);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                semester = (String) spinnerSem.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                subject = (String) spinnerSubject.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ImageButton date = findViewById(R.id.DateImageButton);
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        dateEditText = findViewById(R.id.DateEditText);
        date.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(
                    AddAttendanceSessionActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        String _date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        dateEditText.setText(_date);
                    }, year, month, day);
            datePickerDialog.show();
        });

        addAttendanceBtn = findViewById(R.id.add_attendance_btn);
        addAttendanceBtn.setOnClickListener(arg0 -> {

            String dateText = dateEditText.getText().toString();

            if (TextUtils.isEmpty(dateText)) {
                dateEditText.setError("Select a date");
                Toast.makeText(getApplicationContext(), "Select a date.",
                        Toast.LENGTH_LONG).show();
            } else {
                AttendanceSession attendanceSession = new AttendanceSession();
                Faculty faculty = ((AppContext) this.getApplicationContext()).getFaculty();

                attendanceSession.setAttendance_session_faculty_id(faculty.getFaculty_id());
                attendanceSession.setAttendance_session_branch(branch);
                attendanceSession.setAttendance_session_sem(semester);
                attendanceSession.setAttendance_session_date(dateEditText.getText().toString());
                attendanceSession.setAttendance_session_subject(subject);

                DBHandler dbHandler = new DBHandler(AddAttendanceSessionActivity.this);
                int sessionId = dbHandler.addAttendanceSession(attendanceSession);

                ArrayList<Student> studentList = dbHandler
                        .getAllStudentByBranchSem(branch, semester);
                ((AppContext) this.getApplicationContext()).setStudentList(studentList);

                Intent intent = new Intent(AddAttendanceSessionActivity.this,
                        AddAttendanceActivity.class);
                intent.putExtra("sessionId", sessionId);
                startActivity(intent);
            }
        });

        viewTotalAttendance = findViewById(R.id.view_total_attendance_btn);
        viewTotalAttendance.setOnClickListener(arg0 -> {
            AttendanceSession attendanceSession = new AttendanceSession();
            Faculty faculty = ((AppContext) this
                    .getApplicationContext()).getFaculty();

            attendanceSession.setAttendance_session_faculty_id(faculty.getFaculty_id());
            attendanceSession.setAttendance_session_branch(branch);
            attendanceSession.setAttendance_session_sem(semester);
            attendanceSession.setAttendance_session_subject(subject);

            DBHandler dbHandler = new DBHandler(AddAttendanceSessionActivity.this);

            ArrayList<Attendance> attendanceList = dbHandler
                    .getTotalAttendanceBySessionID(attendanceSession);
            ((AppContext) this.getApplicationContext())
                    .setAttendanceList(attendanceList);

            Intent intent = new Intent(AddAttendanceSessionActivity.this,
                    ViewTotalAttendanceActivity.class);
            startActivity(intent);

        });

        viewAttendance = findViewById(R.id.view_attendance_btn);
        viewAttendance.setOnClickListener(arg0 -> {

            AttendanceSession attendanceSession = new AttendanceSession();
            Faculty faculty = ((AppContext) this.getApplicationContext()).getFaculty();

            attendanceSession.setAttendance_session_faculty_id(faculty.getFaculty_id());
            attendanceSession.setAttendance_session_branch(branch);
            attendanceSession.setAttendance_session_sem(semester);
            attendanceSession.setAttendance_session_date(dateEditText.getText().toString());
            attendanceSession.setAttendance_session_subject(subject);

            DBHandler dbHandler = new DBHandler(AddAttendanceSessionActivity.this);

            ArrayList<Attendance> attendanceList = dbHandler
                    .getAttendanceBySessionID(attendanceSession);
            ((AppContext) this.getApplicationContext()).setAttendanceList(attendanceList);

            Intent intent = new Intent(AddAttendanceSessionActivity.this,
                    ViewTotalAttendanceActivity.class);
            startActivity(intent);

        });

        facultyLogoutBtn = findViewById(R.id.faculty_logout_btn);
        facultyLogoutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AddAttendanceSessionActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            SharedPreferences prefs = getSharedPreferences("my-prefs", MODE_PRIVATE);
            SharedPreferences.Editor prefsEdit = prefs.edit();
            prefsEdit.clear();
            prefsEdit.apply();
            Toast.makeText(getApplicationContext(), "Logged out successfully.",
                    Toast.LENGTH_LONG).show();
            startActivity(intent);
        });
    }
}