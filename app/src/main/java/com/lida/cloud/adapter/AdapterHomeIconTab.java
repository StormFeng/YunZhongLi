package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityGoodList;
import com.lida.cloud.bean.IndexBean;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8.
 */

public class AdapterHomeIconTab extends BaseAdapter {

    private Context context;
//    private List<Bean> data = new ArrayList<>();
    private List<IndexBean.DataBean.CategoryBean> data = new ArrayList<>();
    private AppContext ac;

    public AdapterHomeIconTab(Context context, List<IndexBean.DataBean.CategoryBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    private void initData(){
        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean();
            switch (i) {
                case 0:
                    bean.setTitle("食品饮料");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 1:
                    bean.setTitle("数码家电");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 2:
                    bean.setTitle("洗护美妆");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 3:
                    bean.setTitle("服装鞋帽");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 4:
                    bean.setTitle("箱包饰品");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 5:
                    bean.setTitle("母婴产品");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 6:
                    bean.setTitle("家装建材");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 7:
                    bean.setTitle("办公家居");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 8:
                    bean.setTitle("户外运动");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
                case 9:
                    bean.setTitle("更多商品");
                    bean.setImg(R.drawable.icon_tab5);
                    break;
            }
        }
    }

    @Override
    public int getCount() {
        return data==null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data==null?0:data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_homeicontab, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(data.get(position).getCate_name());
        if(data.get(position).getLogo()!=null){
            ac.setImage(viewHolder.iv,data.get(position).getLogo());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cate_id",data.get(position).getCate_id()+"");
                bundle.putString("title",data.get(position).getCate_name());
                UIHelper.jump((Activity) context, ActivityGoodList.class,bundle);
            }
        });
        return convertView;
    }

    class Bean {
        String title;
        Class target;
        int img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class getTarget() {
            return target;
        }

        public void setTarget(Class target) {
            this.target = target;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "title='" + title + '\'' +
                    ", target=" + target +
                    ", img=" + img +
                    '}';
        }
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        @BindView(R.id.iv)
        ImageView iv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
