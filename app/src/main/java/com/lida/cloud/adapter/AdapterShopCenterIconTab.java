package com.lida.cloud.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAddress;
import com.lida.cloud.activity.ActivityBankCard;
import com.lida.cloud.activity.ActivityCollection;
import com.lida.cloud.activity.ActivityIntegralBuy;
import com.lida.cloud.activity.ActivityIntegralOut;
import com.lida.cloud.activity.ActivityIntegralStock;
import com.lida.cloud.activity.ActivityMyRecommend;
import com.lida.cloud.activity.ActivityPublicWelfare;
import com.lida.cloud.activity.ActivitySettlement;
import com.lida.cloud.activity.ActivityShopBaseInfo;
import com.lida.cloud.widght.dialog.DialogShare;
import com.midian.base.app.AppContext;
import com.midian.base.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商户中心
 * Created by Administrator on 2017/8/8.
 */

public class AdapterShopCenterIconTab extends BaseAdapter {

    private Context context;
    private List<Bean> data = new ArrayList<>();
    private AppContext ac;

    public AdapterShopCenterIconTab(Context context) {
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
        initData();
    }

    private void initData(){
        for (int i = 0; i < 5; i++) {
            Bean bean = new Bean();
            switch (i) {
                case 0:
                    bean.setTitle("商户详情");
                    bean.setImg(R.drawable.icon_transfer_detail);
                    bean.setTarget(ActivityShopBaseInfo.class);
                    break;
                case 1:
                    bean.setTitle("分享商户");
                    bean.setImg(R.drawable.icon_transfer_share);
                    break;
                case 2:
                    bean.setTitle("库存积分");
                    bean.setImg(R.drawable.icon_transfer_jifen);
                    bean.setTarget(ActivityIntegralStock.class);
                    break;
                case 3:
                    bean.setTitle("购买积分");
                    bean.setImg(R.drawable.icon_transfer_buy);
                    bean.setTarget(ActivityIntegralBuy.class);
                    break;
                case 4:
                    bean.setTitle("转出积分");
                    bean.setImg(R.drawable.icon_transfer_out);
                    bean.setTarget(ActivityIntegralOut.class);
                    break;
            }
            data.add(bean);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopcentericontab, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(data.get(position).getTitle());
        viewHolder.iv.setImageResource(data.get(position).getImg());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position==1){
                    new DialogShare(context,"加入云众利，消费不再贵","泉州云众利网络科技有限公司（以下简称：云众利）由福建本土民营企业家黄文汉先生投资创建，于2017年4月在泉州工商局注册成立（目前注册资金为800万元）",
                            "http://www.baidu.com").show();
                }
                if(data.get(position).getTarget()!=null){
                    UIHelper.jump((Activity) context,data.get(position).getTarget());
                }
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
