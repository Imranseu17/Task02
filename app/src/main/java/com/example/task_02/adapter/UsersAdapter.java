package com.example.task_02.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.task_02.Models.User;
import com.example.task_02.R;
import com.example.task_02.databinding.RecyclerCustomerItemBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>  {

    private Context context;
    private List<User> userList;
    OkHttpClient client = new OkHttpClient();

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
        if(user.getGender().equals("female")){
            Request request = new Request.Builder()
                    .url("https://randomuser.me/api/portraits/women/"+user.getPhoto()+".png")
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("request failed: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    response.body().byteStream();
                    response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    System.out.println("request Success Women");
                }
            });
        }else {

            Request request = new Request.Builder()
                    .url("https://randomuser.me/api/portraits/men/"+user.getPhoto()+".png")
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("request failed: " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    response.body().byteStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    System.out.println("request Success men");


                }
            });
        }

        holder.recyclerCustomerItemBinding.firstName.setText(
                "First Name: "+user.getFirstName());
        holder.recyclerCustomerItemBinding.lastName.setText(
               "Last Name: " +user.getLastName());
        holder.recyclerCustomerItemBinding.mobileNumber.
                setText("Home Mobile Number: " +user.getPhones().getHome()
                +"\n"+"Mobile Number: " +user.getPhones().getMobile());


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
