package com.example.thunder2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RcvAdapter_forPC extends RecyclerView.Adapter<RcvAdapter_forPC.ViewHolder> {
    private Context mContext;
    private ArrayList<DTOaboutPC> dataList;

    public RcvAdapter_forPC(Context mContext, ArrayList<DTOaboutPC> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forpc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView location;
        CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);


            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);
            location=(TextView)itemView.findViewById(R.id.location);
            cardview=(CardView) itemView.findViewById(R.id.cardview);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,aboutPC.class);
                    intent.putExtra("stringLocation",dataList.get(getAdapterPosition()).getLocation());
                    intent.putExtra("stringSeatkind", dataList.get(getAdapterPosition()).getSeatKind());
                    intent.putExtra("intSeatTotal", dataList.get(getAdapterPosition()).getSeat_total());
                    intent.putExtra("stringSpec", dataList.get(getAdapterPosition()).getSpec());
                    intent.putExtra("stringName", dataList.get(getAdapterPosition()).getName());
                    intent.putExtra("stringNotice", dataList.get(getAdapterPosition()).getNotice());
                    intent.putExtra("stringUID",dataList.get(getAdapterPosition()).getUID());
                    intent.putExtra("stringImage",dataList.get(getAdapterPosition()).getImage());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(dataList.get(position).getName());
        holder.location.setText(dataList.get(position).getLocation());
    }
}

