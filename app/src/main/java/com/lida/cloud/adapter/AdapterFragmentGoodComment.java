package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lida.cloud.R;
import com.midian.base.app.AppContext;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 商品评论
 * Created by WeiQingFeng on 2017/8/25.
 */

public class AdapterFragmentGoodComment extends BaseAdapter {

    private Context context;
    private AppContext ac;
    private List<String> pics;

    public AdapterFragmentGoodComment(Context context, List<String> pics) {
        this.context = context;
        this.pics = pics;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return pics==null?0:pics.size();
    }

    @Override
    public Object getItem(int position) {
        return pics==null?0:pics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_framentgoodcomment_image, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ac.setImage(viewHolder.iv,pics.get(position));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
