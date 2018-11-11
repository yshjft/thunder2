package com.example.thunder2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.ArrayList;




public class RcvAdapter_aboutPC extends RecyclerView.Adapter<RcvAdapter_aboutPC.ViewHolder> {

    private Context mContext;
    private ArrayList<DataForm_forPC> dataList;


    public RcvAdapter_aboutPC(Context mContext, ArrayList<DataForm_forPC> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aboutpc, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;


        public ViewHolder(View itemView) {
            super(itemView);


            ivIcon = (ImageView) itemView.findViewById(R.id.imageView2);
        }


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //holder.ivIcon.setImageDrawable();
    }



//    private void removeItem(int position) {
//        dataList.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, dataList.size());
//    }
}
