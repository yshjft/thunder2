package com.example.thunder2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<DataForm> dataList;


    public RcvAdapter(Context mContext, ArrayList<DataForm> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);


            tvName = (TextView) itemView.findViewById(R.id.item_tv_name);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(mContext, "click " + dataList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    Toast.makeText(mContext, "remove " + dataList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
//                    removeItem(getAdapterPosition());
//                    return false;
//                }
//            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvName.setText(dataList.get(position).getName());
    }

//    private void removeItem(int position) {
//        dataList.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, dataList.size());
//    }
}

