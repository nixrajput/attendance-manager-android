package co.in.nixlab.attendance_manager.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    private final List<User> userList;

    public UsersAdapter(List<User> users) {
        this.userList = users;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.user_item, parent, false);

        return new ViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.ViewHolder holder, int position) {
        User user = userList.get(position);

        TextView textViewId = holder.textViewId;
        TextView textViewName = holder.textViewName;
        TextView textViewUserType = holder.textViewUserType;
        ImageButton deleteBtn = holder.deleteBtn;
        String uid = user.get_uid() + ". ";
        String name = user.get_name();
        String userType = user.getUser_type();
        textViewId.setText(uid);
        textViewName.setText(name);
        textViewUserType.setText(userType);
        deleteBtn.setOnClickListener(v -> {
            DBHandler dbHandler = new DBHandler(holder.itemView.getContext());
            dbHandler.deleteUser(user);
            userList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewId, textViewName, textViewUserType;
        public ImageButton deleteBtn;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewId = itemView.findViewById(R.id.tv_id);
            textViewName = itemView.findViewById(R.id.tv_name);
            textViewUserType = itemView.findViewById(R.id.tv_user_type);
            deleteBtn = itemView.findViewById(R.id.delete_img_btn);
        }
    }
}
