package com.glriverside.xgqin.ggnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private List<News> mNewsData;
    private Context mContext;
    private int resourceId;

    public NewsAdapter(Context context, int resourceId, List<News> data) {
        super(context, resourceId, data);
        this.mContext = context;
        this.mNewsData = data;
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view ;

        final ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvTitle  = view.findViewById(R.id.tv_title);
            viewHolder.tvSource = view.findViewById(R.id.tv_subtitle);
            viewHolder.ivImage = view.findViewById(R.id.iv_image);
            viewHolder.ivDelete = view.findViewById(R.id.iv_delete);
            viewHolder.tvPublishTime = view.findViewById(R.id.tv_publish_time);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(news.getTitle());
        viewHolder.tvSource.setText(news.getSource());
        viewHolder.ivDelete.setTag(position);
        viewHolder.tvPublishTime.setText(news.getDate());

        Glide.with(mContext).load(news.getPicUrl()).into(viewHolder.ivImage);

        return view;
    }

    class ViewHolder {
        TextView tvTitle;
        TextView tvSource;
        ImageView ivImage;
        TextView tvPublishTime;

        ImageView ivDelete;
    }
}
