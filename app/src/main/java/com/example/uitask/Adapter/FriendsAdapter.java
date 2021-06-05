package com.example.uitask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitask.FrndsInviteInterface;
import com.example.uitask.Model.Friend;
import com.example.uitask.R;

import java.util.ArrayList;

public class FriendsAdapter extends  RecyclerView.Adapter<FriendsAdapter.frindsHolder>{

    public Context context;
    public ArrayList<Friend> friends;
    private FrndsInviteInterface frndsInviteInterface;


    public FriendsAdapter(Context context , ArrayList<Friend> friends , FrndsInviteInterface frndsInviteInterface){
        this.context = context;
        this.friends = friends;
         this.frndsInviteInterface = frndsInviteInterface;
    }

    @NonNull
    @Override
    public frindsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new frindsHolder(LayoutInflater.from(context).inflate(R.layout.invite_friend_cell,parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull frindsHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.name.setText(friend.name);
        holder.from.setText(friend.from);
        holder.dp.setImageResource(friend.dp);

    }
    public void  filter(ArrayList<Friend> filterFrnd){
        friends = filterFrnd;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class  frindsHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView from;
        public ImageView dp;
        public RelativeLayout invite;

        public frindsHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_frindName);
            from = itemView.findViewById(R.id.tv_from_cell);
            dp = itemView.findViewById(R.id.iv_dp_friend);
            invite = itemView.findViewById(R.id.rl_invite_cell);

            invite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frndsInviteInterface.onInviteClicked(getAbsoluteAdapterPosition());
                }
            });
        }


    }
}
