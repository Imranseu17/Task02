package com.example.task_02.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.task_02.Models.User;
import com.example.task_02.R;
import com.example.task_02.databinding.RecyclerCustomerItemBinding;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>  {

    private Context context;
    private List<User> userList;

    public UsersAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerCustomerItemBinding customerItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.recycler_customer_item,parent,false);
        return new ViewHolder(customerItemBinding);


    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder holder, int position) {

        final User user = userList.get(position);
        final String imageURLMale = "https://randomuser.me/api/portraits/men/"+user.getPhoto()+".jpg";
        final String imageURLFEMale = "https://randomuser.me/api/portraits/women/"+user.getPhoto()+".jpg";
        if(user.getGender().equals("female")){
            Glide.with(context).load(imageURLFEMale).into(holder.recyclerCustomerItemBinding.image);
        }else {
            Glide.with(context).load(imageURLMale).into(holder.recyclerCustomerItemBinding.image);
        }
        holder.recyclerCustomerItemBinding.firstName.setText(
                "First Name: "+user.getFirstName());
        holder.recyclerCustomerItemBinding.lastName.setText(
               "Last Name: " +user.getLastName());
        holder.recyclerCustomerItemBinding.mobileNumber.
                setText("Home Mobile Number: "+user.getPhones().getHome()
                +"\n"+"Mobile Number: "+user.getPhones().getMobile());


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    @Override
    public int getItemCount() {

        return userList != null ? userList.size() : 0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerCustomerItemBinding recyclerCustomerItemBinding;

        public ViewHolder(RecyclerCustomerItemBinding recyclerCustomerItemBinding) {
            super(recyclerCustomerItemBinding.getRoot());
           this.recyclerCustomerItemBinding = recyclerCustomerItemBinding;

        }

    }


}
