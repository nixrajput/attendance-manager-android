package co.in.nixlab.attendance_manager.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import co.in.nixlab.attendance_manager.R;

public class SearchStudentActivity extends AppCompatActivity {

    private final String[] branchString = new String[]{"CSE", "ME", "EE", "CE", "ECE"};
    private final String[] SemString = new String[]{"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    Spinner spinnerBranch, spinnerSem;
    String branch;
    String semester;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);

        spinnerBranch = findViewById(R.id.spinner_stu_branch);
        spinnerSem = findViewById(R.id.spinner_stu_sem);
        nextBtn = findViewById(R.id.next_btn);

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
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<String> adapter_year = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, SemString);
        adapter_year
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(adapter_year);

        nextBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SearchStudentActivity.this,
                    ViewStudentByBranchSemActivity.class);
            intent.putExtra("branch", branch);
            intent.putExtra("sem", semester);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}