package co.in.nixlab.attendance_manager.controllers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.models.Faculty;
import co.in.nixlab.attendance_manager.views.ViewFacultyDetailsActivity;

public class FacultyListAdapter extends RecyclerView.Adapter
        <FacultyListAdapter.ViewHolder> {

    private final List<Faculty> facultyList;

    public FacultyListAdapter(List<Faculty> studentList) {
        this.facultyList = studentList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View customView = inflater.inflate(R.layout.view_student_list,
                parent, false);
        return new ViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FacultyListAdapter.ViewHolder holder,
                                 int position) {
        Faculty faculty = facultyList.get(position);

        TextView roll_no = holder.rollTextView;
        TextView name = holder.nameTextView;

        roll_no.setText(String.valueOf(faculty.getFaculty_id()));
        String fullName = faculty.getFaculty_firstname() + " " + faculty.getFaculty_lastname();
        name.setText(fullName);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(),
                    ViewFacultyDetailsActivity.class);
            intent.putExtra("faculty_id", faculty.getFaculty_id());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog
                    .Builder(holder.itemView.getContext());

            alertDialogBuilder.setTitle("Delete Faculty");
            alertDialogBuilder.setMessage("Are you sure want to delete?");

            alertDialogBuilder.setPositiveButton("Yes", (dialog, id) -> {

                facultyList.remove(position);
                notifyDataSetChanged();

                DBHandler dbHandler = new DBHandler(holder.itemView.getContext());
                dbHandler.deleteFaculty(faculty.getFaculty_id());
            });

            alertDialogBuilder.setNegativeButton("No", (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return false;
        });

    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView rollTextView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            rollTextView = itemView.findViewById(R.id.label_student_roll);
            nameTextView = itemView.findViewById(R.id.label_student_name);

        }
    }

}
