package co.in.nixlab.attendance_manager.views;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.ApplicationContext;
import co.in.nixlab.attendance_manager.controllers.DBAdapter;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.AttendanceSession;
import co.in.nixlab.attendance_manager.models.Faculty;
import co.in.nixlab.attendance_manager.models.Student;

public class AddAttendanceSessionActivity extends AppCompatActivity {

    private final String[] branchString = new String[]{"CSE", "ME", "EE", "CE", "ECE"};
    private final String[] SemString = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    private final String[] subjectFinal = new String[]{"M3", "DS", "M4", "CN", "M5", "NS"};
    Button submit;
    Button viewAttendance;
    Button viewTotalAttendance;
    Spinner spinnerBranch, spinnerSem, spinnerSubject;
    String branch = "CSE";
    String semester = "1st";
    String subject = "SC";
    AttendanceSession attendanceSessionBean;
    private int day;
    private int month;
    private int year;
    private EditText dateEditText;
    private final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @SuppressLint("SetTextI18n")
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_attendance);

        spinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        spinnerSem = (Spinner) findViewById(R.id.spinner_sem);
        spinnerSubject = (Spinner) findViewById(R.id.spinner_sub);

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branchString);
        adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(adapter_branch);
        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                branch = (String) spinnerBranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, SemString);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(adapter_year);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                semester = (String) spinnerSem.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_subject = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectFinal);
        adapter_subject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(adapter_subject);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                subject = (String) spinnerSubject.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        ImageButton date = (ImageButton) findViewById(R.id.DateImageButton);
        Calendar cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        dateEditText = (EditText) findViewById(R.id.DateEditText);
        date.setOnClickListener(arg0 -> showDialog(0));

        submit = (Button) findViewById(R.id.buttonsubmit);
        submit.setOnClickListener(arg0 -> {

            AttendanceSession attendanceSessionBean = new AttendanceSession();
            Faculty faculty = ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).getFaculty();

            attendanceSessionBean.setAttendance_session_faculty_id(faculty.getFaculty_id());
            attendanceSessionBean.setAttendance_session_department(branch);
            attendanceSessionBean.setAttendance_session_class(semester);
            attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
            attendanceSessionBean.setAttendance_session_subject(subject);

            DBAdapter dbAdapter = new DBAdapter(AddAttendanceSessionActivity.this);
            int sessionId = dbAdapter.addAttendanceSession(attendanceSessionBean);

            ArrayList<Student> studentBeanList = dbAdapter.getAllStudentByBranchSem(branch, semester);
            ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).setStudentList(studentBeanList);


            Intent intent = new Intent(AddAttendanceSessionActivity.this, AddAttendanceActivity.class);
            intent.putExtra("sessionId", sessionId);
            startActivity(intent);
        });

        viewAttendance = (Button) findViewById(R.id.viewAttendancebutton);
        viewAttendance.setOnClickListener(arg0 -> {

            AttendanceSession attendanceSessionBean = new AttendanceSession();
            Faculty bean = ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).getFaculty();

            attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
            attendanceSessionBean.setAttendance_session_department(branch);
            attendanceSessionBean.setAttendance_session_class(semester);
            attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
            attendanceSessionBean.setAttendance_session_subject(subject);

            DBAdapter dbAdapter = new DBAdapter(AddAttendanceSessionActivity.this);

            ArrayList<Attendance> attendanceBeanList = dbAdapter.getAttendanceBySessionID(attendanceSessionBean);
            ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).setAttendanceList(attendanceBeanList);

            Intent intent = new Intent(AddAttendanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
            startActivity(intent);

        });

        viewTotalAttendance = (Button) findViewById(R.id.viewTotalAttendanceButton);
        viewTotalAttendance.setOnClickListener(arg0 -> {
            AttendanceSession attendanceSessionBean = new AttendanceSession();
            Faculty bean = ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).getFaculty();

            attendanceSessionBean.setAttendance_session_faculty_id(bean.getFaculty_id());
            attendanceSessionBean.setAttendance_session_department(branch);
            attendanceSessionBean.setAttendance_session_class(semester);
            attendanceSessionBean.setAttendance_session_subject(subject);

            DBAdapter dbAdapter = new DBAdapter(AddAttendanceSessionActivity.this);

            ArrayList<Attendance> attendanceBeanList = dbAdapter.getTotalAttendanceBySessionID(attendanceSessionBean);
            ((ApplicationContext) AddAttendanceSessionActivity.this.getApplicationContext()).setAttendanceList(attendanceBeanList);

            Intent intent = new Intent(AddAttendanceSessionActivity.this, ViewAttendanceByFacultyActivity.class);
            startActivity(intent);

        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }
}