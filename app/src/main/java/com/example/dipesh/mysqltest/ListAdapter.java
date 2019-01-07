package com.example.dipesh.mysqltest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
    BookingFragment main;

    ListAdapter(BookingFragment main)
    {
        this.main = main;
    }

    @Override
    public int getCount() {
        return  main.bookings.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView Username;
        TextView StartTime;
        TextView EndTime;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);

            holder.Username = (TextView) convertView.findViewById(R.id.username);
            holder.StartTime = (TextView) convertView.findViewById(R.id.startTime);
            holder.EndTime = (TextView) convertView.findViewById(R.id.endTime);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }


        holder.Username.setText(this.main.bookings.get(position).username);
        holder.StartTime.setText(this.main.bookings.get(position).startTime);
        holder.EndTime.setText((this.main.bookings.get(position).endTime));

        return convertView;
    }
}
