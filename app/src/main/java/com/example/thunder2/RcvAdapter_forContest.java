package com.example.thunder2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RcvAdapter_forContest extends RecyclerView.Adapter<RcvAdapter_forContest.ViewHolder> {

    private Context mContext;
    private ArrayList<DataForm_forContest> dataList;


    public RcvAdapter_forContest(Context mContext, ArrayList<DataForm_forContest> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forcontest, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.item_contestText_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(mContext, aboutContest.class);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(dataList.get(position).getContest_name());
    }

//    private void removeItem(int position) {
//        dataList.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeChanged(position, dataList.size());
//    }

}
