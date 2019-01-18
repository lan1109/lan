package com.example.lan.my_kaoshi_lan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lan.my_kaoshi_lan.MainActivity;
import com.example.lan.my_kaoshi_lan.R;
import com.example.lan.my_kaoshi_lan.beans.Bean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.IdentityHashMap;
import java.util.List;

public class MyAdapter  extends XRecyclerView.Adapter{
    private List<Bean.RecentBean> mRecent;
    private Context mContext;

    public MyAdapter(Context  context, List<Bean.RecentBean> recent) {
        this.mContext = context;
        this.mRecent = recent;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_a, null, false);
            MyViewHolderA myViewHolderA = new MyViewHolderA(inflate);
            return  myViewHolderA;
        }else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_b, null, false);
            MyViewHolderB myViewHolderB = new MyViewHolderB(inflate);
            return  myViewHolderB;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0){
            MyViewHolderA myViewHolderA = (MyViewHolderA) holder;
            myViewHolderA.mTv.setText(mRecent.get(position).getTitle());
            Glide.with(mContext).load(mRecent.get(position).getThumbnail()).into(myViewHolderA.mImg);
        }else {
            MyViewHolderB myViewHolderB = (MyViewHolderB) holder;
            myViewHolderB.mTv.setText(mRecent.get(position).getTitle());
            Glide.with(mContext).load(mRecent.get(position).getThumbnail()).into(myViewHolderB.mImg);
        }
    }

    @Override
    public int getItemCount() {
        return mRecent.size();
    }

    class MyViewHolderA extends  XRecyclerView.ViewHolder {

        private  ImageView mImg;
        private  TextView mTv;

        public MyViewHolderA(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    class MyViewHolderB extends  XRecyclerView.ViewHolder {

        private  ImageView mImg;
        private  TextView mTv;

        public MyViewHolderB(View itemView) {
            super(itemView);
            mImg = itemView.findViewById(R.id.img);
            mTv = itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2 == 0){
            return 0;
        }else {
            return 1;
        }
    }
}
