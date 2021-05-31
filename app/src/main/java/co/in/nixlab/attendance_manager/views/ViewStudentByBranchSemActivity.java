package co.in.nixlab.attendance_manager.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBAdapter;
import co.in.nixlab.attendance_manager.models.Student;

public class ViewStudentByBranchSemActivity extends AppCompatActivity {

    ArrayList<Student> studentList;
    String branch;
    String semester;
    DBAdapter dbAdapter = new DBAdapter(this);
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listview);

        ListView listView = (ListView) findViewById(R.id.listview);
        final ArrayList<String> studentList = new ArrayList<>();

        branch = getIntent().getExtras().getString("branch");
        semester = getIntent().getExtras().getString("sem");

        this.studentList = dbAdapter.getAllStudentByBranchSem(branch, semester);

        for (Student studentBean : this.studentList) {
            String users = studentBean.getStudent_firstname() + "," + studentBean.getStudent_lastname();

            studentList.add(users);
        }

        listAdapter = new ArrayAdapter<>(this, R.layout.view_student_list, R.id.label, studentList);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener((arg0, arg1, position, arg3) -> {


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewStudentByBranchSemActivity.this);

            alertDialogBuilder.setTitle(getTitle() + " Decision!");
            alertDialogBuilder.setMessage("Are you sure want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    studentList.remove(position);
                    listAdapter.notifyDataSetChanged();
                    listAdapter.notifyDataSetInvalidated();

                    dbAdapter.deleteStudent(ViewStudentByBranchSemActivity.this.studentList.get(position).getStudent_id());
                    ViewStudentByBranchSemActivity.this.studentList = dbAdapter.getAllStudentByBranchSem(branch, semester);

                    for (Student studentBean : ViewStudentByBranchSemActivity.this.studentList) {
                        String users = " FirstName: " + studentBean.getStudent_firstname() + "\nLastname:" + studentBean.getStudent_lastname();
                        studentList.add(users);
                    }
                }

            });

            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(), "You choose cancel",
                            Toast.LENGTH_LONG).show();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}