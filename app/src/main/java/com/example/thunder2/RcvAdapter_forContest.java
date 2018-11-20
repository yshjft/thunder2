package com.example.thunder2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RcvAdapter_forContest extends RecyclerView.Adapter<RcvAdapter_forContest.ViewHolder> {

    private Context mContext;
    private ArrayList<DTOaboutContest> dataList;


    public RcvAdapter_forContest(Context mContext, ArrayList<DTOaboutContest> dataList) {
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
                    intent.putExtra("stringDate",dataList.get(getAdapterPosition()).getDate());
                    intent.putExtra("stringDeadline", dataList.get(getAdapterPosition()).getDeadline());
                    intent.putExtra("stringETC", dataList.get(getAdapterPosition()).getETC());
                    intent.putExtra("intEvent", dataList.get(getAdapterPosition()).getEvent());
                    intent.putExtra("stringHost", dataList.get(getAdapterPosition()).getHost());
                    intent.putExtra("stringHow", dataList.get(getAdapterPosition()).getHow());
                    intent.putExtra("stringLocation", dataList.get(getAdapterPosition()).getLocation());
                    intent.putExtra("stringName", dataList.get(getAdapterPosition()).getName());
                    intent.putExtra("stringPrize", dataList.get(getAdapterPosition()).getPrize());
                    intent.putExtra("stringQualification", dataList.get(getAdapterPosition()).getQuali());
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
