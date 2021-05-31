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

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBAdapter;
import co.in.nixlab.attendance_manager.models.Student;

public class AddStudentActivity extends AppCompatActivity {

    private final String[] branchString = new String[]{"CSE", "ME", "EE", "CE", "ECE"};
    private final String[] SemString = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    Button saveBtn;
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
        spinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        spinnerSem = (Spinner) findViewById(R.id.spinner_sem);
        studentFirstName = (EditText) findViewById(R.id.ed_first_name);
        studentLastName = (EditText) findViewById(R.id.ed_last_name);
        studentPhone = (EditText) findViewById(R.id.ed_student_phone);
        studentAddress = (EditText) findViewById(R.id.ed_student_address);
        saveBtn = (Button) findViewById(R.id.save_student_btn);

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
            String first_name = studentFirstName.getText().toString();
            String last_name = studentLastName.getText().toString();
            String phone_no = studentPhone.getText().toString();
            String address = studentAddress.getText().toString();

            if (TextUtils.isEmpty(first_name)) {
                studentFirstName.setError("Please enter firstname");
            } else if (TextUtils.isEmpty(last_name)) {
                studentLastName.setError("Please enter lastname");
            } else if (TextUtils.isEmpty(phone_no)) {
                studentPhone.setError("Please enter phone number");
            } else if (TextUtils.isEmpty(address)) {
                studentAddress.setError("Please enter address");
            } else {

                Student studentBean = new Student();

                studentBean.setStudent_firstname(first_name);
                studentBean.setStudent_lastname(last_name);
                studentBean.setStudent_mobile_number(phone_no);
                studentBean.setStudent_address(address);
                studentBean.setStudent_department(branch);
                studentBean.setStudent_class(semester);

                DBAdapter dbAdapter = new DBAdapter(AddStudentActivity.this);
                dbAdapter.addStudent(studentBean);

                Intent intent = new Intent(AddStudentActivity.this, DashboardActivity.class);
                startActivity(intent);
//                Snackbar.make(contextView, "Student added successfully", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}