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

public class AttendanceAdapter extends RecyclerView.Adapter
<AttendanceAdapter.ViewHolder>{

    private final ArrayList<Attendance> attendanceList;

    public AttendanceAdapter(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rollTextView;
        public TextView nameTextView;
        public TextView statusTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            rollTextView = itemView.findViewById(R.id.label_roll);
            nameTextView = itemView.findViewById(R.id.label_student_name);
            statusTextView = itemView.findViewById(R.id.label_status);
        }
    }

    @NonNull
    @NotNull
    @Override
    public AttendanceAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View customView = inflater.inflate(R.layout.view_attendance,
                parent, false);
        return new AttendanceAdapter.ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AttendanceAdapter.ViewHolder holder, int position) {
        Attendance attendance = attendanceList.get(position);

        TextView roll_no = holder.rollTextView;
        TextView stu_name = holder.nameTextView;
        TextView status = holder.statusTextView;

        if(attendance.getAttendance_session_id() != 0) {
            DBHandler dbHandler = new DBHandler(holder.itemView.getContext());
            Student student = dbHandler.getStudentById(attendance.getAttendance_student_id());
            String name = student.getStudent_firstname() + " " + student.getStudent_lastname();

            roll_no.setText(String.valueOf(attendance.getAttendance_student_id()));
            stu_name.setText(name);
        }
        else {
            roll_no.setText(attendance.getAttendance_status());
            stu_name.setText(attendance.getAttendance_status());
        }
        status.setText(attendance.getAttendance_status());

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }
}
