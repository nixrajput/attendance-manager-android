package co.in.nixlab.attendance_manager.views;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.models.Faculty;

public class ViewFacultyActivity extends AppCompatActivity {

    ArrayList<Faculty> facultyBeanList;
    DBHandler dbHandler = new DBHandler(this);
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_listview);

        ListView listView = findViewById(R.id.listview);
        final ArrayList<String> facultyList = new ArrayList<>();

        facultyBeanList = dbHandler.getAllFaculty();

        for (Faculty facultyBean : facultyBeanList) {
            String users = facultyBean.getFaculty_firstname() + " " + facultyBean.getFaculty_lastname();
            facultyList.add(users);
        }

        listAdapter = new ArrayAdapter<>(this, R.layout.view_faculty_list, R.id.labelF, facultyList);
        listView.setAdapter(listAdapter);

        listView.setOnItemLongClickListener((arg0, arg1, position, arg3) -> {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ViewFacultyActivity.this);

            alertDialogBuilder.setTitle(getTitle());
            alertDialogBuilder.setMessage("Are you sure want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", (dialog, id) -> {

                facultyList.remove(position);
                listAdapter.notifyDataSetChanged();
                listAdapter.notifyDataSetInvalidated();

                dbHandler.deleteFaculty(facultyBeanList.get(position).getFaculty_id());
                facultyBeanList = dbHandler.getAllFaculty();

                for (Faculty facultyBean : facultyBeanList) {
                    String users = facultyBean.getFaculty_firstname() + " " + facultyBean.getFaculty_lastname();
                    facultyList.add(users);
                }
            });
            alertDialogBuilder.setNegativeButton("No", (dialog, id) ->
                    dialog.cancel()
            );

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