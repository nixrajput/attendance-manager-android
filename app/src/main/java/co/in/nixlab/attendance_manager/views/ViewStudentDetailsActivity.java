package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Student;

public class ViewStudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_student_details);

        String roll_no = getIntent().getExtras().getString("roll_no");

        TextView rollTextView = findViewById(R.id.lbl_stu_roll_no);
        TextView nameTextView = findViewById(R.id.lbl_stu_name);
        TextView branchTextView = findViewById(R.id.lbl_stu_branch);
        TextView semTextView = findViewById(R.id.lbl_stu_sem);
        TextView phoneTextView = findViewById(R.id.lbl_stu_mob_no);
        TextView addressTextView = findViewById(R.id.lbl_stu_address);

        DBHandler dbHandler = new DBHandler(this);

        try {
            Student student = dbHandler.getStudentByRollNo(roll_no);

            String roll = "ROLL NO : " + student.getStudent_roll();
            rollTextView.setText(roll);

            String name = "NAME : " + student.getStudent_firstname() + " " + student.getStudent_lastname();
            nameTextView.setText(name);

            String branch = "BRANCH : " + student.getStudent_branch();
            branchTextView.setText(branch);

            String sem = "SEMESTER : " + student.getStudent_sem();
            semTextView.setText(sem);

            String phone = "PHONE : " + student.getStudent_mobile_number();
            phoneTextView.setText(phone);

            String address = "ADDRESS : " + student.getStudent_address();
            addressTextView.setText(address);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}