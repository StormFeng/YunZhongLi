package com.lida.cloud.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.activity.ActivityAddAddress;
import com.lida.cloud.activity.ActivityAddress;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.AddressListBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收货地址
 * Created by Administrator on 2017/8/10.
 */

public class AdapterActivityAddress extends BaseAdapter {

    private ActivityAddress context;
    private List<AddressListBean.DataBean.ListBean> data;
    private AppContext ac;

    public AdapterActivityAddress(ActivityAddress context, List<AddressListBean.DataBean.ListBean> data) {
        this.context = context;
        this.data = data;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data == null ? 0 : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_activityaddress, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(context);
                rxDialogSureCancel.getTvTitle().setVisibility(View.GONE);
                rxDialogSureCancel.setContent("确定删除？");
                rxDialogSureCancel.getTvSure().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppUtil.getApiClient(ac).addressRemove(data.get(position).getId(),new BaseApiCallback(){
                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                if(res.isOK()){
                                    RxToast.success("删除成功！");
                                    context.getData();
                                    rxDialogSureCancel.dismiss();
                                }else{
                                    RxToast.error(res.getMessage());
                                }
                            }
                        });
                    }
                });
                rxDialogSureCancel.getTvCancel().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSureCancel.dismiss();
                    }
                });
                rxDialogSureCancel.show();
            }
        });
        viewHolder.tvName.setText(data.get(position).getName());
        viewHolder.tvPhone.setText(data.get(position).getMobile());
        viewHolder.tvAddress.setText(data.get(position).getProvince()+" "+
            data.get(position).getCity()+" "+data.get(position).getCountry()+
            data.get(position).getDetail());
        if("0".equals(data.get(position).getIsdefault())){
            viewHolder.cbDef.setChecked(false);
        }else{
            viewHolder.cbDef.setChecked(true);
        }
        viewHolder.cbDef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AppUtil.getApiClient(ac).addressDefault(data.get(position).getId(), new BaseApiCallback() {
                    @Override
                    public void onApiSuccess(NetResult res, String tag) {
                        if(res.isOK()){
                            RxToast.success("设置成功！");
                            context.getData();
                        }else{
                            RxToast.error(res.getMessage());
                        }
                    }
                });
            }
        });
        viewHolder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",data.get(position));
                UIHelper.jumpForResult(context, ActivityAddAddress.class, bundle, 1001);
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(context.flag)){
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("data",data.get(position));
                context.setResult(Activity.RESULT_OK,intent);
                context.finish();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhone)
        TextView tvPhone;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.cbDef)
        CheckBox cbDef;
        @BindView(R.id.tvEdit)
        TextView tvEdit;
        @BindView(R.id.tvDel)
        TextView tvDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
