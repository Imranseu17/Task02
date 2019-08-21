package com.example.task_02.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.task_02.Models.Data;
import com.example.task_02.R;
import com.example.task_02.adapter.UsersAdapter;
import com.example.task_02.databinding.ActivityMainBinding;
import com.example.task_02.presenter.UserPresenter;
import com.example.task_02.view.UserView;

public class MainActivity extends AppCompatActivity implements UserView {

    private UserPresenter mPresenter;
    private UsersAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("User List");

        mPresenter = new UserPresenter(this);

        viewConfig();

        getUserList();
    }

    private void getUserList() {
        mPresenter.getUsers();
    }

    private void viewConfig(){
       mainBinding.recyclerList.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mainBinding.recyclerList.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onSuccess(Data data) {
        if (data.getUsers() != null) {
            if (data.getUsers().size() > 0) {
                mAdapter =  new UsersAdapter(this,data.getUsers());
                mainBinding.recyclerList.setAdapter(mAdapter);
            } else Toast.makeText(this,"No data found",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onError(String error, int code) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();

    }
}
