package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.controllers.FacultyListAdapter;
import co.in.nixlab.attendance_manager.models.Faculty;

public class ViewFacultyActivity extends AppCompatActivity {

    ArrayList<Faculty> facultyBeanList;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.FACULTY_LIST);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        this.facultyBeanList = dbHandler.getAllFaculty();

        FacultyListAdapter adapter = new FacultyListAdapter(facultyBeanList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}