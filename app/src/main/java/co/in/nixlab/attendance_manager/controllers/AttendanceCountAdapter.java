package co.in.nixlab.attendance_manager.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.models.Attendance;
import co.in.nixlab.attendance_manager.models.Student;

public class AttendanceCountAdapter extends RecyclerView.Adapter
        <AttendanceCountAdapter.ViewHolder> {

    private final ArrayList<Attendance> attendanceList;

    public AttendanceCountAdapter(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    @NonNull
    @NotNull
    @Override
    public AttendanceCountAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View customView = inflater.inflate(R.layout.view_attendance_list,
                parent, false);
        return new AttendanceCountAdapter.ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AttendanceCountAdapter.ViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);

        TextView roll_no = holder.rollTextView;
        TextView stu_name = holder.nameTextView;
        TextView status = holder.statusTextView;

        DBHandler dbHandler = new DBHandler(holder.itemView.getContext());
        Student student = dbHandler.getStudentByRollNo(attendance.getAttendance_student_roll());
        String name = student.getStudent_firstname() + " " + student.getStudent_lastname();

        roll_no.setText(attendance.getAttendance_student_roll());
        stu_name.setText(name);
        status.setText(String.valueOf(attendance.getAttendance_session_id()));
    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rollTextView;
        public TextView nameTextView;
        public TextView statusTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            rollTextView = itemView.findViewById(R.id.label_stu_roll);
            nameTextView = itemView.findViewById(R.id.label_stu_name);
            statusTextView = itemView.findViewById(R.id.label_stu_status);
        }
    }
}
