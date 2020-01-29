package com.example.toolstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserInfoViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;

    public UserInfoAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        UserInfoViewHolder holder = new UserInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserInfoViewHolder holder, int position) {
        holder.tv_userName.setText(arrayList.get(position).getUserId());
        holder.tv_userID.setText(arrayList.get(position).getUserName());
        holder.tv_userCity.setText(arrayList.get(position).getUserCity());
        holder.tv_userZIPCode.setText(String.valueOf(arrayList.get(position).getUserZIPCode()));
        holder.tv_userContact.setText(String.valueOf(arrayList.get(position).getUserContact()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class UserInfoViewHolder extends RecyclerView.ViewHolder {
        TextView tv_userName, tv_userID, tv_userCity, tv_userZIPCode, tv_userContact;
        public UserInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);
            this.tv_userID = itemView.findViewById(R.id.tv_userID);
            this.tv_userCity = itemView.findViewById(R.id.tv_userCity);
            this.tv_userZIPCode = itemView.findViewById(R.id.tv_userZIPCode);
            this.tv_userContact = itemView.findViewById(R.id.tv_userContact);
        }
    }
}
