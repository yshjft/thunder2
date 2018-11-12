package com.example.thunder2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RcvAdapter_correctionContest extends RecyclerView.Adapter<RcvAdapter_correctionContest.ViewHolder>{
    private Context mContext;
    private ArrayList<DataForm_correctionContest> dataList;


    public RcvAdapter_correctionContest(Context mContext, ArrayList<DataForm_correctionContest> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_correctioncontest, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        //ImageView ivIcon;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.item_contest_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(mContext, setting_market_contestManage_correction.class);
                    mContext.startActivity(intent);
                }
            });
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
