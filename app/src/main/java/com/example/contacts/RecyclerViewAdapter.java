package com.example.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.MyViewHolder> {

    private List<ContactModel> list;
    private MyRecyclerViewItemClickListener myRecyclerViewItemClickListener;

    public RecyclerViewAdapter(List<ContactModel> list, MyRecyclerViewItemClickListener myRecyclerViewItemClickListener) {
        this.list = list;
        this.myRecyclerViewItemClickListener = myRecyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(constraintLayout);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRecyclerViewItemClickListener.onItemClicked(list.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewSingleId.setText(String.valueOf(list.get(position).getId()));
        holder.textViewSingleMobile.setText(list.get(position).getMobile());
        holder.textViewSingleFName.setText(list.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {
        //return 0;
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSingleId;
        TextView textViewSingleMobile;
        TextView textViewSingleFName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSingleId = itemView.findViewById(R.id.textViewSingleId);
            textViewSingleMobile = itemView.findViewById(R.id.textViewSingleMobile);
            textViewSingleFName = itemView.findViewById(R.id.textViewSingleFName);
        }
    }

    public interface MyRecyclerViewItemClickListener {
        void onItemClicked(ContactModel contactModel);
    }
}
