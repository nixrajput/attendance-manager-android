package co.in.nixlab.attendance_manager.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.Student;

public class StudentAttendanceAdapter extends RecyclerView.Adapter
        <StudentAttendanceAdapter.ViewHolder> {

    private final List<Student> studentList;
    private final int sessionId;

    public StudentAttendanceAdapter(List<Student> studentList, int sessionId) {
        this.studentList = studentList;
        this.sessionId = sessionId;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View customView = inflater.inflate(R.layout.add_student_attendance,
                parent, false);
        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StudentAttendanceAdapter.ViewHolder holder,
                                 int position) {
        Student student = studentList.get(position);
        final String[] attendanceStrings = new String[]{"P", "A"};

        TextView name = holder.nameTextView;
        Spinner attendanceSpinner = holder.attendanceSpinner;
        String fullName = student.getStudent_firstname() + " " + student.getStudent_lastname();
        name.setText(fullName);

        attendanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Attendance attendance = new Attendance();

                attendance.setAttendance_session_id(sessionId);
                attendance.setAttendance_student_roll(student.getStudent_roll());
                attendance.setAttendance_status(attendanceSpinner.getSelectedItem().toString());

                DBHandler handler = new DBHandler(holder.itemView.getContext());
                handler.addNewAttendance(attendance);
                Toast.makeText(holder.itemView.getContext(), "Saved",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter_role = new ArrayAdapter<>(holder.itemView.getContext(),
                android.R.layout.simple_spinner_item, attendanceStrings);
        adapter_role
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attendanceSpinner.setAdapter(adapter_role);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public Spinner attendanceSpinner;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.label_student);
            attendanceSpinner = itemView.findViewById(R.id.spinner_attendance);
        }
    }

}
