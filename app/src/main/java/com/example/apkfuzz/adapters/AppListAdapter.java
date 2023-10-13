package com.example.apkfuzz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apkfuzz.R;
import com.example.apkfuzz.entities.AppInfo;

import java.util.List;

public class AppListAdapter extends ArrayAdapter<AppInfo> {
    private final Context context;
    private final List<AppInfo> appList;

    public AppListAdapter(Context context, List<AppInfo> appList) {
        super(context, R.layout.listview_pattern_intent_fuzzing, appList); // set listview pattern id
        this.context = context;
        this.appList = appList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // set listview pattern id
        View rowView = inflater.inflate(R.layout.listview_pattern_intent_fuzzing, parent, false);

        // set listview items id
        TextView textView = (TextView) rowView.findViewById(R.id.app_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.app_icon);

        textView.setText(appList.get(position).getAppName());
        imageView.setImageDrawable(appList.get(position).getAppIcon());

        return rowView;
    }
}
