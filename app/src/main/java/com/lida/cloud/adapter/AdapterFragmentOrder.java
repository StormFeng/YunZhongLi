package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityApplyToReturn;
import com.lida.cloud.activity.ActivityGoodCommend;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.activity.ActivityLogistics;
import com.lida.cloud.activity.ActivityReturnDetail;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.OrderListBean;
import com.lida.cloud.fragment.FragmentOrder;
import com.lida.cloud.widght.BaseApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.app.AppManager;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.ListViewForScrollView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxImageUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的订单
 * Created by Administrator on 2017/8/10.
 */

public class AdapterFragmentOrder extends BaseAdapter {

    private Context context;
    private FragmentOrder fragmentOrder;
    private List<OrderListBean.DataBean.OrderBean> data;
    private AppContext ac;

    public AdapterFragmentOrder(Context context, FragmentOrder fragmentOrder, List<OrderListBean.DataBean.OrderBean> data) {
        this.context = context;
        this.data = data;
        this.fragmentOrder = fragmentOrder;
        ac = (AppContext) context.getApplicationContext();
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_fragmentorder, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final OrderListBean.DataBean.OrderBean bean = data.get(position);
        viewHolder.tvShopName.setText(bean.getShopname());
        if("-1".equals(bean.getStatus())){
            viewHolder.tvStatus.setText("已取消");
        }else{
            viewHolder.tvStatus.setText(bean.getStatestr());
            if("1".equals(bean.getRefund_status())){
                viewHolder.tvStatus.setText("退款/退货");
            }
        }
        if("0".equals(bean.getStatus())){
            viewHolder.btn0.setVisibility(View.GONE);
            viewHolder.btn1.setVisibility(View.VISIBLE);
            viewHolder.btn2.setVisibility(View.VISIBLE);
            viewHolder.btn1.setText("取消订单");
            viewHolder.btn2.setText("去付款");
        }else if("1".equals(bean.getStatus())){
            viewHolder.btn0.setVisibility(View.GONE);
            viewHolder.btn1.setVisibility(View.VISIBLE);
            viewHolder.btn1.setText("申请退款");
            if("1".equals(bean.getRemind())){
                viewHolder.btn2.setVisibility(View.VISIBLE);
                viewHolder.btn2.setText("提醒发货");
            }else{
                viewHolder.btn2.setVisibility(View.GONE);
            }
            if("1".equals(bean.getRefund_status())){
                viewHolder.btn1.setText("退款处理");
                viewHolder.btn2.setVisibility(View.GONE);
            }
        }else if("2".equals(bean.getStatus())){
            viewHolder.btn2.setVisibility(View.VISIBLE);
            viewHolder.btn1.setVisibility(View.VISIBLE);
            viewHolder.btn2.setVisibility(View.VISIBLE);
            viewHolder.btn1.setText("查看物流");
            viewHolder.btn2.setText("确认收货");
            if("1".equals(bean.getRefund_status())){
                viewHolder.btn1.setText("退款处理");
                viewHolder.btn0.setVisibility(View.GONE);
                viewHolder.btn2.setVisibility(View.GONE);
            }
        }else if("3".equals(bean.getStatus())){
            viewHolder.btn2.setVisibility(View.GONE);
            viewHolder.btn1.setVisibility(View.GONE);
            viewHolder.btn2.setVisibility(View.VISIBLE);
            viewHolder.btn2.setText("去评价");
        }else if("-1".equals(bean.getStatus())){
            viewHolder.btn2.setVisibility(View.GONE);
            viewHolder.btn1.setVisibility(View.GONE);
            viewHolder.btn2.setVisibility(View.GONE);
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn1:
                        if("0".equals(bean.getStatus())){
                            final RxDialogSureCancel dialogSureCancel = new RxDialogSureCancel(context);
                            dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                            dialogSureCancel.setContent("确定取消该订单？");
                            dialogSureCancel.setCancelListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogSureCancel.dismiss();
                                }
                            });
                            dialogSureCancel.setSureListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppUtil.getApiClient(ac).orderCancel(context,bean.getOrder_id(),new BaseApiCallback(){
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            super.onApiSuccess(res, tag);
                                            if(res.isOK()){
                                                RxToast.success("订单取消成功!");
                                                dialogSureCancel.dismiss();
                                                fragmentOrder.getData();
                                            }else{
                                                RxToast.error(res.getMessage());
                                                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                                                        ||"10003".equals(res.getErrorCode())){
                                                    ac.clearUserInfo();
                                                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                            dialogSureCancel.show();
                        }else if("1".equals(bean.getStatus())){
                            if("申请退款".equals(viewHolder.btn1.getText().toString())){
                                Bundle bundle = new Bundle();
                                bundle.putString("id",bean.getOrder_id());
                                bundle.putString("money",bean.getTotalprice());
                                bundle.putBoolean("click",false);
                                UIHelper.jumpForResult((Activity) context, ActivityApplyToReturn.class,bundle,1001);
                            }
                            if("退款处理".equals(viewHolder.btn1.getText().toString())){
                                Bundle bundle = new Bundle();
                                bundle.putString("id",bean.getOrder_id());
                                bundle.putString("refundid",bean.getRefundid());
                                UIHelper.jump((Activity) context, ActivityReturnDetail.class,bundle);
                            }
                        }else if("2".equals(bean.getStatus())){
                            if("查看物流".equals(viewHolder.btn1.getText().toString())){
                                Bundle bundle = new Bundle();
                                bundle.putString("id",bean.getOrder_id());
                                UIHelper.jump((Activity) context, ActivityLogistics.class,bundle);
                            }
                            if("退款处理".equals(viewHolder.btn1.getText().toString())){
                                Bundle bundle = new Bundle();
                                bundle.putString("id",bean.getOrder_id());
                                bundle.putString("refundid",bean.getRefundid());
                                UIHelper.jump((Activity) context, ActivityReturnDetail.class,bundle);
                            }
                        }else if("3".equals(bean.getStatus())){

                        }
                        break;
                    case R.id.btn2:
                        if("0".equals(bean.getStatus())){
                            final RxDialogSureCancel dialogSureCancel = new RxDialogSureCancel(context);
                            dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                            dialogSureCancel.setContent("确认支付？");
                            dialogSureCancel.setCancelListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogSureCancel.dismiss();
                                }
                            });
                            dialogSureCancel.setSureListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppUtil.getApiClient(ac).orderPay(context,bean.getOrder_sn(),new BaseApiCallback(){
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            super.onApiSuccess(res, tag);
                                            if(res.isOK()){
                                                RxToast.success("支付成功!");
                                                dialogSureCancel.dismiss();
                                                Intent intent = new Intent("android.intent.action.RefreshOrderList");
                                                context.sendBroadcast(intent);

                                                Intent var = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                                                context.sendBroadcast(var);
//                                                fragmentOrder.getData();
                                            }else{
                                                RxToast.error(res.getMessage());
                                                if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                                                        ||"10003".equals(res.getErrorCode())){
                                                    ac.clearUserInfo();
                                                    RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                                                }
                                            }
                                        }
                                    });
                                }
                            });
                            dialogSureCancel.show();
                        }
                        if("1".equals(bean.getStatus())){
                            AppUtil.getApiClient(ac).orderDetail(bean.getOrder_id(),new BaseApiCallback(){
                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(res.isOK()){
                                        RxToast.success("提醒成功");
                                        bean.setRemind("0");
                                        notifyDataSetChanged();
                                    }else{
                                        RxToast.error(res.getMessage());
                                    }
                                }
                            });
                        }
                        if("2".equals(bean.getStatus())){
                            final RxDialogSureCancel dialogSureCancel = new RxDialogSureCancel(context);
                            dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                            dialogSureCancel.setContent("确认收货？");
                            dialogSureCancel.setCancelListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogSureCancel.dismiss();
                                }
                            });
                            dialogSureCancel.setSureListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppUtil.getApiClient(ac).orderReceive(bean.getOrder_id(),new BaseApiCallback(){
                                        @Override
                                        public void onApiSuccess(NetResult res, String tag) {
                                            super.onApiSuccess(res, tag);
                                            if(res.isOK()){
                                                RxToast.success("已确认收货");
                                                dialogSureCancel.dismiss();
                                                Intent intent = new Intent("android.intent.action.RefreshOrderList");
                                                context.sendBroadcast(intent);
//                                                fragmentOrder.getData();
                                            }else{
                                                RxToast.error(res.getMessage());
                                            }
                                        }
                                    });
                                }
                            });
                            dialogSureCancel.show();
                        }
                        if("3".equals(bean.getStatus())){
                            Bundle bundle = new Bundle();
                            bundle.putString("order_id",bean.getOrder_id());
                            UIHelper.jump((Activity) context, ActivityGoodCommend.class,bundle);
                        }
                        break;
                    case R.id.btn0:
                        Bundle bundle = new Bundle();
                        bundle.putString("id",bean.getOrder_id());
                        bundle.putString("money",bean.getTotalprice());
                        bundle.putBoolean("click",true);
                        UIHelper.jump((Activity) context, ActivityApplyToReturn.class,bundle);
                        break;
                }
            }
        };
        viewHolder.btn0.setOnClickListener(listener);
        viewHolder.btn1.setOnClickListener(listener);
        viewHolder.btn2.setOnClickListener(listener);
        viewHolder.tvFreight.setText("共"+bean.getGoods().size()+"件商品");
        viewHolder.tvAllMoney.setText("合计：¥"+bean.getTotalprice());
        viewHolder.lvItem.setAdapter(new AdapterFragmentOrderChild(context,bean.getGoods()));
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tvShopName)
        TextView tvShopName;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.lvItem)
        ListViewForScrollView lvItem;
        @BindView(R.id.tvFreight)
        TextView tvFreight;
        @BindView(R.id.tvAllMoney)
        TextView tvAllMoney;
        @BindView(R.id.btn0)
        Button btn0;
        @BindView(R.id.btn1)
        Button btn1;
        @BindView(R.id.btn2)
        Button btn2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
