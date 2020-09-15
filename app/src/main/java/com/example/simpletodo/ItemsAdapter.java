package com.example.simpletodo;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> todolist;
    OnLongClickListener olcl;

    public ItemsAdapter(List<String> start_items, OnLongClickListener olcl) {
        this.todolist = start_items;
        this.olcl = olcl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoview = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String atodo = todolist.get(position);
        holder.bind(atodo);
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String atodo) {
            tv_item.setText(atodo);
            tv_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    olcl.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
