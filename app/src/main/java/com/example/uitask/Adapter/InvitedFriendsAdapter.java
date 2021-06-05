package com.example.uitask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitask.Model.Friend;
import com.example.uitask.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class InvitedFriendsAdapter extends RecyclerView.Adapter<InvitedFriendsAdapter.invitedHolder>{

    private Context context;
    private ArrayList<Friend> ifriends;

    public InvitedFriendsAdapter(Context context , ArrayList<Friend> iFriends){
        this.context = context;
        this.ifriends = iFriends;
    }

    @NonNull
    @Override
    public invitedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new invitedHolder(LayoutInflater.from(context).inflate(R.layout.invited_friend_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull invitedHolder holder, int position) {
      int dp = ifriends.get(position).dp;
      holder.image.setImageResource(dp);
    }

    public void change(ArrayList<Friend> cfriends){
        ifriends = cfriends;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ifriends.size();
    }

    static class invitedHolder extends RecyclerView.ViewHolder {
        public CircleImageView image;

        public invitedHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_invited_cell);
        }
    }
}
