package com.example.uitask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uitask.Model.RedeemDetails;
import com.example.uitask.R;

import java.util.ArrayList;

public class RedeemAdapter extends RecyclerView.Adapter<RedeemAdapter.redeemViewHolder>{

    private Context context;
    private ArrayList<RedeemDetails> redeemDetails;

    public RedeemAdapter(Context context , ArrayList<RedeemDetails> redeemDetails){
        this.context = context;
        this.redeemDetails = redeemDetails;
    }


    @NonNull
    @Override
    public redeemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new redeemViewHolder(LayoutInflater.from(context).inflate(R.layout.cell_redemption, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull redeemViewHolder holder, int position) {
        RedeemDetails redeemDetail = redeemDetails.get(position);
        holder.brandName.setText((CharSequence) redeemDetail.brandName);
        holder.brandPoint.setText((CharSequence) redeemDetail.points);
        holder.brandImage.setImageResource(redeemDetail.image);
    }

    @Override
    public int getItemCount() {
        return redeemDetails.size();
    }

    static class redeemViewHolder extends RecyclerView.ViewHolder {
        public ImageView brandImage;
        public TextView brandName;
        public TextView brandPoint;

        public redeemViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.tv_redeemBrandName_cell);
            brandPoint = itemView.findViewById(R.id.tv_redeemPoints_cell);
            brandImage = itemView.findViewById(R.id.iv_redeemBrand_cell);
        }
    }
}
