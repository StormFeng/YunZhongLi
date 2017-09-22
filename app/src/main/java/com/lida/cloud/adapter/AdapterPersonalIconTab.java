package com.lida.cloud.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAddress;
import com.lida.cloud.activity.ActivityAgentCenter;
import com.lida.cloud.activity.ActivityBankCard;
import com.lida.cloud.activity.ActivityCollection;
import com.lida.cloud.activity.ActivityMyRecommend;
import com.lida.cloud.activity.ActivityPublicWelfare;
import com.lida.cloud.activity.ActivitySettlement;
import com.lida.cloud.activity.ActivityShopAuth;
import com.lida.cloud.activity.ActivityShopCenter;
import com.lida.cloud.activity.ActivityUpdate;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CheckIsShopMember;
import com.lida.cloud.fragment.main.FragmentPersonal;
import com.lida.cloud.widght.dialog.DialogShare;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.base.BaseActivity;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人中心
 * Created by Administrator on 2017/8/8.
 */

public class AdapterPersonalIconTab extends BaseAdapter {

    private BaseFragmentActivity context;
    private List<Bean> data = new ArrayList<>();
    private AppContext ac;
    private FragmentPersonal fragmentPersonal;

    public AdapterPersonalIconTab(FragmentPersonal fragmentPersonal,Context context) {
        this.fragmentPersonal = fragmentPersonal;
        this.context = (BaseFragmentActivity) context;
        ac = (AppContext) context.getApplicationContext();
        initData();
    }

    private void initData(){
        for (int i = 0; i < 11; i++) {
            Bean bean = new Bean();
            switch (i) {
                case 0:
                    bean.setTitle("昨日结算");
                    bean.setImg(R.drawable.icon_personal_tab1);
                    bean.setTarget(ActivitySettlement.class);
                    break;
                case 1:
                    bean.setTitle("会员升级");
                    bean.setImg(R.drawable.icon_personal_tab2);
                    bean.setTarget(ActivityUpdate.class);
                    break;
                case 2:
                    bean.setTitle("我的收藏");
                    bean.setImg(R.drawable.icon_personal_tab3);
                    bean.setTarget(ActivityCollection.class);
                    break;
                case 3:
                    bean.setTitle("银行卡");
                    bean.setImg(R.drawable.icon_personal_tab4);
                    bean.setTarget(ActivityBankCard.class);
                    break;
                case 4:
                    bean.setTitle("商户中心");
                    bean.setImg(R.drawable.icon_personal_tab5);
                    break;
                case 5:
                    bean.setTitle("我的推荐");
                    bean.setImg(R.drawable.icon_personal_tab6);
                    bean.setTarget(ActivityMyRecommend.class);
                    break;
                case 6:
                    bean.setTitle("收货地址");
                    bean.setImg(R.drawable.icon_personal_tab7);
                    bean.setTarget(ActivityAddress.class);
                    break;
                case 7:
                    bean.setTitle("粉丝公益");
                    bean.setImg(R.drawable.icon_personal_tab8);
                    bean.setTarget(ActivityPublicWelfare.class);
                    break;
                case 8:
                    bean.setTitle("信呗");
                    bean.setImg(R.drawable.icon_personal_tab9);
                    break;
                case 9:
                    bean.setTitle("代理中心");
                    bean.setImg(R.drawable.icon_personal_tab10);
                    bean.setTarget(ActivityAgentCenter.class);
                    break;
                case 10:
                    bean.setTitle("分享");
                    bean.setImg(R.drawable.icon_personal_tab10);
                    break;
            }
            data.add(bean);
        }
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            context.showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            context.showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            context.hideLoadingDlg();
            if(res.isOK()){
                if("businessCheckApplyStatus".equals(tag)){
                    CheckIsShopMember bean = (CheckIsShopMember) res;
                    if(bean.getData().get(0).getStatus()==-1){
                        RxToast.warning("请先提交认证审核资料");
                        UIHelper.jump(context, ActivityShopAuth.class);
                        return;
                    }
                    if(bean.getData().get(0).getStatus()==0){
                        RxToast.warning("正在审核中，请耐心等待");
                        return;
                    }
                    if(bean.getData().get(0).getStatus()==1){
                        UIHelper.jump(context,ActivityShopCenter.class);
                        return;
                    }
                    if(bean.getData().get(0).getStatus()==2){
                        RxToast.warning("审核不通过，请联系客服咨询");
                        return;
                    }
                }
            }else{
                RxToast.error(res.getMessage());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            context.hideLoadingDlg();
            RxToast.error("网络异常");
        }

        @Override
        public void onParseError(String tag) {
            context.hideLoadingDlg();
            RxToast.error("数据解析异常");
        }
    };

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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_homeicontab, null);
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
                if(position==4){
                    if("0".equals(ac.memState)||"1".equals(ac.memState)||"2".equals(ac.memState)){
                        RxToast.warning("商户中心仅对商家用户开放，其他用户请先提交资料申请成为商户");
                        UIHelper.jump(context,ActivityShopAuth.class);
                        return;
                    }
                    AppUtil.getApiClient(ac).businessCheckApplyStatus(callback);
                    return;
                }
                if(data.get(position).getTarget()!=null){
                    if(position==1){
                        Bundle bundle = new Bundle();
                        bundle.putString("head",fragmentPersonal.head);
                        bundle.putString("name",fragmentPersonal.tvName.getText().toString());
                        UIHelper.jump(context,data.get(position).getTarget(),bundle);
                        return;
                    }
                    if(position==9){
                        if(!"1".equals(ac.isagent)){
                            RxToast.error("代理中心仅供代理使用");
                            return;
                        }
                    }
                    UIHelper.jump(context,data.get(position).getTarget());
                }else{
                    new DialogShare(context,"加入云众利，消费不再贵","加入云众利，消费不再贵",
                            fragmentPersonal.qCode).show();
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
