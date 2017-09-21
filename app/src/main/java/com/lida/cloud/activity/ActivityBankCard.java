package com.lida.cloud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.MyBankCardListBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 银行卡
 * Created by Administrator on 2017/8/9.
 */

public class ActivityBankCard extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.listView)
    ListView listView;

    private List<MyBankCardListBean.DataBean> data = new ArrayList<>();
    private Adapter adapter;
    private String flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
        if(mBundle!=null){
            flag = mBundle.getString("flag");
        }
        ButterKnife.bind(this);
        initView();
        AppUtil.getApiClient(ac).bankIndex(this,callback);
    }

    private void initView() {
        topbar.setTitle("银行卡");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        adapter = new Adapter();
        listView.setAdapter(adapter);
        listView.setDividerHeight(0);
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if(res.isOK()){
                MyBankCardListBean myBankCardListBean = (MyBankCardListBean) res;
                data.addAll(myBankCardListBean.getData());
                data.add(new MyBankCardListBean.DataBean());
                adapter.notifyDataSetChanged();
            }else{
                RxToast.error(_activity, res.getMessage()).show();
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent var) {
        super.onActivityResult(requestCode, resultCode, var);
        if(RESULT_OK == resultCode){
            data.clear();
            AppUtil.getApiClient(ac).bankIndex(this,callback);
        }
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data==null? 0:data.size();
        }

        @Override
        public Object getItem(int position) {
            return data==null? 0:data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_activitybankcard, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if(position==data.size()-1){
                viewHolder.rlCard.setVisibility(View.GONE);
                viewHolder.llAddCard.setVisibility(View.VISIBLE);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.jumpForResult(_activity,ActivityAddBankCard.class,1001);
                    }
                });
            }else{
                if(data.get(position).getB_bank_image()!=null){
                    ac.setImage(viewHolder.ivLogo,data.get(position).getB_bank_image());
                }
                if(data.get(position).getB_bg_image()!=null){
                    ac.setImage(viewHolder.ivBg,data.get(position).getB_bg_image());
                }
                viewHolder.rlCard.setVisibility(View.VISIBLE);
                viewHolder.llAddCard.setVisibility(View.GONE);
                viewHolder.tvName.setText(data.get(position).getB_bank_name());
                viewHolder.tvCardNum.setText(data.get(position).getB_number());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if("ActivityGetMoney".equals(flag)){
                            Intent intent = new Intent();
                            intent.putExtra("data",data.get(position));
                            setResult(RESULT_OK,intent);
                            _activity.finish();
                            return;
                        }
                    }
                });
                convertView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final RxDialogSureCancel dialogSureCancel = new RxDialogSureCancel(_activity);
                        dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                        dialogSureCancel.setContent("确定删除");
                        dialogSureCancel.setCancelListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogSureCancel.dismiss();
                            }
                        });
                        dialogSureCancel.setSureListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AppUtil.getApiClient(ac).bankRemove(data.get(position).getB_id(),new ApiCallback(){
                                    @Override
                                    public void onApiStart(String tag) {
                                        showLoadingDlg();
                                    }

                                    @Override
                                    public void onApiLoading(long count, long current, String tag) {
                                        showLoadingDlg();
                                    }

                                    @Override
                                    public void onApiSuccess(NetResult res, String tag) {
                                        hideLoadingDlg();
                                        if(res.isOK()){
                                            dialogSureCancel.dismiss();
                                            data.remove(position);
                                            notifyDataSetChanged();
                                            RxToast.success("删除成功！");
                                        }else{
                                            RxToast.error(res.getMessage());
                                            if("10001".equals(res.getErrorCode())||"10002".equals(res.getErrorCode())
                                                    ||"10003".equals(res.getErrorCode())){
                                                ac.clearUserInfo();
                                                RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
                                        hideLoadingDlg();
                                        RxToast.error("网络异常");
                                    }

                                    @Override
                                    public void onParseError(String tag) {
                                        hideLoadingDlg();
                                        RxToast.error("数据解析异常");
                                    }
                                });
                            }
                        });
                        dialogSureCancel.show();
                        return false;
                    }
                });
            }
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.rlCard)
            RelativeLayout rlCard;
            @BindView(R.id.llAddCard)
            LinearLayout llAddCard;
            @BindView(R.id.tvName)
            TextView tvName;
            @BindView(R.id.tvType)
            TextView tvType;
            @BindView(R.id.tvCardNum)
            TextView tvCardNum;
            @BindView(R.id.ivBg)
            RoundedImageView ivBg;
            @BindView(R.id.ivLogo)
            RoundedImageView ivLogo;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
