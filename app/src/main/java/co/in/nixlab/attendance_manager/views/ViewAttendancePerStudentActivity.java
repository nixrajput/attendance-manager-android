package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.Student;

public class ViewAttendancePerStudentActivity extends AppCompatActivity {

    ArrayList<Attendance> attendanceBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listview);

        ListView listView = findViewById(R.id.listview);
        final ArrayList<String> attendanceList = new ArrayList<>();
        attendanceList.add("Present Count Per Student");
        attendanceBeanList = ((AppContext) this.getApplicationContext()).getAttendanceList();

        for (Attendance attendanceBean : attendanceBeanList) {
            String users;

            DBHandler dbHandler = new DBHandler(ViewAttendancePerStudentActivity.this);
            Student studentBean = dbHandler.getStudentByRollNo(attendanceBean.getAttendance_student_roll());
            users = attendanceBean.getAttendance_student_roll() + ".     " +
                    studentBean.getStudent_firstname() + " " + studentBean.getStudent_lastname() +
                    "                  " + attendanceBean.getAttendance_session_id();
            attendanceList.add(users);
        }

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.view_attendance_list_per_student, R.id.labelAttendancePerStudent, attendanceList);
        listView.setAdapter(listAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}