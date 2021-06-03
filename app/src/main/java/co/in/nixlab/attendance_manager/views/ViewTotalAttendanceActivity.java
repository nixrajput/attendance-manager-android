package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.context.AppContext;
import co.in.nixlab.attendance_manager.controllers.AttendanceAdapter;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.controllers.StudentAdapter;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.Student;

public class ViewTotalAttendanceActivity extends AppCompatActivity {

    ArrayList<Attendance> attendanceList;

    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recycler_view);

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText("Total Attendance");
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        this.attendanceList = ((AppContext) ViewTotalAttendanceActivity.this
                .getApplicationContext()).getAttendanceList();

        AttendanceAdapter adapter = new AttendanceAdapter(attendanceList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        ListView listView = findViewById(R.id.listview);
//        final ArrayList<String> attendanceList = new ArrayList<>();
//        attendanceList.add("Id | Student Name |  Status");
//
//        this.attendanceList = ((AppContext) this.getApplicationContext()).getAttendanceList();
//
//        for (Attendance attendance : this.attendanceList) {
//            String users;
//            if (attendance.getAttendance_session_id() != 0) {
//                DBHandler dbHandler = new DBHandler(ViewTotalAttendanceActivity.this);
//                Student student = dbHandler.getStudentById(attendance.getAttendance_student_id());
//                users = attendance.getAttendance_student_id() + ".     " +
//                        student.getStudent_firstname() + " " +
//                        student.getStudent_lastname() + "              " +
//                        attendance.getAttendance_status();
//            } else {
//                users = attendance.getAttendance_status();
//            }
//
//            attendanceList.add(users);
//
//        }
//
//        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.view_attendance_list, R.id.labelAttendance, attendanceList);
//        listView.setAdapter(listAdapter);

		/*listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {



				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewAttendanceByFacultyActivity.this);

				alertDialogBuilder.setTitle(getTitle()+"decision");
				alertDialogBuilder.setMessage("Are you sure?");

				alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						facultyList.remove(position);
						listAdapter.notifyDataSetChanged();
						listAdapter.notifyDataSetInvalidated();

						dbAdapter.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
						facultyBeanList=dbAdapter.getAllFaculty();

						for(FacultyBean facultyBean : facultyBeanList)
						{
							String users = " FirstName: " + facultyBean.getFaculty_firstname()+"\nLastname:"+facultyBean.getFaculty_lastname();
							facultyList.add(users);
							Log.d("users: ", users);

						}

					}

				});
				alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// cancel the alert box and put a Toast to the user
						dialog.cancel();
						Toast.makeText(getApplicationContext(), "You choose cancel",
								Toast.LENGTH_LONG).show();
					}
				});

				AlertDialog alertDialog = alertDialogBuilder.create();
				// show alert
				alertDialog.show();





				return false;
			}
		});
*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}