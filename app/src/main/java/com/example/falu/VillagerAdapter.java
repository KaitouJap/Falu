package com.example.falu;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VillagerAdapter extends BaseAdapter {

    private List<Villager> villagers;
    private Context context;

    public VillagerAdapter(List<Villager> villagers, Context context) {
        this.villagers = villagers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return villagers.size();
    }

    @Override
    public Villager getItem(int position) {
        return villagers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater
                    .from(context)
                    .inflate(R.layout.listview_item, parent, false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.nameTextView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        String name = getItem(position).getName();
        holder.name.setText(name);

        convertView.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(parent.getContext())
                        .setTitle("Delete Villager")
                        .setMessage("Are you sure you want to delete " + name + "?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            villagers.remove(position);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView name;
    }
}
