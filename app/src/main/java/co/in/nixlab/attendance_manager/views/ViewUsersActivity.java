package co.in.nixlab.attendance_manager.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.in.nixlab.attendance_manager.R;
import co.in.nixlab.attendance_manager.controllers.DBHandler;
import co.in.nixlab.attendance_manager.controllers.UsersAdapter;
import co.in.nixlab.attendance_manager.models.User;

public class ViewUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        DBHandler dbHandler = new DBHandler(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rcv_users);

        List<User> userList = dbHandler.getAllUsers();

        UsersAdapter usersAdapter = new UsersAdapter(userList);
        recyclerView.setAdapter(usersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}