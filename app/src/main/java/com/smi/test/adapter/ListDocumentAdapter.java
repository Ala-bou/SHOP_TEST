package com.smi.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.smi.test.R;
import com.smi.test.SecondeActivity;
import com.smi.test.entity.Brands;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.smi.test.MainActivity.brands_choisen;


public class ListDocumentAdapter extends ArrayAdapter<Brands> {

    private Context mContext;
    private int layoutResourceId;
    private List<Brands> mListData;
    public static Brands ent_choisen;

    public ListDocumentAdapter(Context mContext, int layoutResourceId, List<Brands> mListData) {
        super(mContext, layoutResourceId, mListData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mListData = mListData;
    }

    public void setListData(List<Brands> mListData) {
        this.mListData = mListData;
    }

    @Nullable
    @Override
    public Brands getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        final Brands item = mListData.get(position);
        holder = new ViewHolder(row);







        holder.textViewDescription.setText(item.getDisplayName());
        holder.textViewDescription.setTextSize(16);
        holder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(mContext)
                .load(item.getPic())
                .fitCenter()
                .into(holder.imageViewBackground);




        holder.rel_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                brands_choisen=item;
                mContext.startActivity(new Intent(mContext, SecondeActivity.class));

            }
        });
        return row;
    }

    static class ViewHolder {

        ViewHolder(View view) {

            ButterKnife.bind(this, view);
        }


        @BindView(R.id.iv_auto_image_slider)
        ImageView imageViewBackground;


        @BindView(R.id.iv_gif_container)
        ImageView imageGifContainer;

        @BindView(R.id.tv_auto_image_slider)
        TextView textViewDescription;


        @BindView(R.id.rel_item)
        RelativeLayout rel_item;

    }

}
