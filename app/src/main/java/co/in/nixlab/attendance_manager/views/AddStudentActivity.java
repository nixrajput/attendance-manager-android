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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    private final String[] branchString = new String[]{"CSE", "ME", "EE", "CE", "ECE"};
    private final String[] SemString = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    Button saveBtn;
    EditText studentRoll;
    EditText studentFirstName;
    EditText studentLastName;
    EditText studentPhone;
    EditText studentAddress;
    Spinner spinnerBranch, spinnerSem;
    String branch;
    String semester;
    View contextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        contextView = findViewById(android.R.id.content).getRootView();
        spinnerBranch = findViewById(R.id.spinner_stu_branch);
        spinnerSem = findViewById(R.id.spinner_stu_sem);
        studentRoll = findViewById(R.id.ed_stu_roll_no);
        studentFirstName = findViewById(R.id.ed_stu_first_name);
        studentLastName = findViewById(R.id.ed_stu_last_name);
        studentPhone = findViewById(R.id.ed_stu_mob_no);
        studentAddress = findViewById(R.id.ed_stu_address);
        saveBtn = findViewById(R.id.save_student_btn);

        spinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                branch = (String) spinnerBranch.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_branch = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, branchString);
        adapter_branch
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBranch.setAdapter(adapter_branch);

        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {
                semester = (String) spinnerSem.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, SemString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(adapter_year);

        saveBtn.setOnClickListener(v -> {
            String roll_no = studentRoll.getText().toString();
            String first_name = studentFirstName.getText().toString();
            String last_name = studentLastName.getText().toString();
            String phone_no = studentPhone.getText().toString();
            String address = studentAddress.getText().toString();

            if (TextUtils.isEmpty(roll_no)) {
                studentRoll.setError("Please enter roll number");
            } else if (TextUtils.isEmpty(first_name)) {
                studentFirstName.setError("Please enter firstname");
            } else if (TextUtils.isEmpty(last_name)) {
                studentLastName.setError("Please enter lastname");
            } else if (TextUtils.isEmpty(phone_no)) {
                studentPhone.setError("Please enter phone number");
            } else if (TextUtils.isEmpty(address)) {
                studentAddress.setError("Please enter address");
            } else {

                Student student = new Student();

                student.setStudent_roll(roll_no);
                student.setStudent_firstname(first_name);
                student.setStudent_lastname(last_name);
                student.setStudent_mobile_number(phone_no);
                student.setStudent_address(address);
                student.setStudent_branch(branch);
                student.setStudent_sem(semester);

                DBHandler dbHandler = new DBHandler(AddStudentActivity.this);

                try {
                    dbHandler.addStudent(student);
                    Intent intent = new Intent(AddStudentActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Student added successfully",
                            Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_LONG).show();
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