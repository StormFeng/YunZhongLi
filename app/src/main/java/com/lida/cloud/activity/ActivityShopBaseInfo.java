package com.lida.cloud.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.jph.takephoto.model.TResult;
import com.lida.cloud.R;
import com.lida.cloud.adapter.AdapterShopLogo;
import com.lida.cloud.adapter.AdapterShopPic;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.BusinessCategoryBean;
import com.lida.cloud.bean.BusinessDataBean;
import com.lida.cloud.widght.BaseTakePhotoActivity;
import com.lida.cloud.widght.InnerGridView;
import com.lida.cloud.widght.dialog.DialogSelectBusinessType;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxRegUtils;
import com.vondear.rxtools.view.RxToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商家基本资料
 * Created by WeiQingFeng on 2017/9/4.
 */

public class ActivityShopBaseInfo extends BaseTakePhotoActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.etShopName)
    EditText etShopName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etDes)
    EditText etDes;
    @BindView(R.id.gvPic)
    InnerGridView gvPic;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.shoptimeStart)
    TextView shoptimeStart;
    @BindView(R.id.shoptimeEnd)
    TextView shoptimeEnd;
    @BindView(R.id.etReturnname)
    EditText etReturnname;
    @BindView(R.id.etReturnmobile)
    EditText etReturnmobile;
    @BindView(R.id.etReturnaddress)
    EditText etReturnaddress;
    @BindView(R.id.gvLogo)
    InnerGridView gvLogo;

    private List<String> pics = new ArrayList<>();
    private AdapterShopPic adapter;

    private List<String> logos = new ArrayList<>();
    private AdapterShopLogo logoAdapter;

    String province = "";
    String city = "";
    String country = "";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

    BusinessCategoryBean businessCategoryBean = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopbaseinfo);
        ButterKnife.bind(this);
        topbar.setTitle("商户基本资料");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(this));
        pics.add("");
        adapter = new AdapterShopPic(this, pics);
        gvPic.setAdapter(adapter);
        logos.add("");
        logoAdapter = new AdapterShopLogo(this, logos);
        gvLogo.setAdapter(logoAdapter);
        AppUtil.getApiClient(ac).businessData(_activity, callback);
        AppUtil.getApiClient(ac).businessCategory(callback);
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
            if (res.isOK()) {
                if (tag.equals("businessData")) {
                    BusinessDataBean bean = (BusinessDataBean) res;
                    BusinessDataBean.DataBeanX.DataBean data = bean.getData().get(0).getData();
                    etShopName.setText(data.getSelshopname());
                    tvType.setText(data.getTypename());
                    tvType.setTag(data.getSelshoptype());
                    if(data.getProvince()!=null&&!data.getProvince().equals("")){
                        province = data.getProvince();
                        city = data.getCity();
                        country = data.getCounty();
                        tvPosition.setText(province + " " + city + " " + country);
                    }
                    etAddress.setText(data.getSelshopadd());
                    etPhone.setText(data.getSeltel());
                    if(data.getSelshoptime()!=null&&!data.getSelshoptime().equals("")&&data.getSelshoptime().contains("-")){
                        int index = data.getSelshoptime().indexOf("-");
                        shoptimeStart.setText(data.getSelshoptime().substring(0,index));
                        shoptimeEnd.setText(data.getSelshoptime().substring(index+1,data.getSelshoptime().length()));
                    }
                    etDes.setText(data.getSeldetail()==null?"":data.getSeldetail());
                    etReturnaddress.setText(data.getReturnaddress()==null?"":data.getReturnaddress());
                    etReturnmobile.setText(data.getReturnmobile()==null?"":data.getReturnmobile());
                    etReturnname.setText(data.getReturnname()==null?"":data.getReturnname());

                }
                else if(tag.equals("businessCategory")){
                    businessCategoryBean = (BusinessCategoryBean)res;
                }
                else if(tag.equals("businesshold")){
                    RxToast.success("操作成功");
                    finish();
                }

            } else {
                if (tag.equals("businessData")) {
                    RxToast.error(_activity, res.getMessage()).show();
                    if ("10001".equals(res.getErrorCode()) || "10002".equals(res.getErrorCode())
                            || "10003".equals(res.getErrorCode())) {
                        ac.clearUserInfo();
                        RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                    }
                }else {
                    RxToast.error(res.getMessage());
                }

            }

        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {

        }

        @Override
        public void onParseError(String tag) {

        }
    };


    private void save(){
        if(etShopName.getText().toString().trim().equals("")){
            UIHelper.t(_activity,"公司名称不能为空！");
            return;
        }
        if(tvType.getText().toString().trim().equals("")){
            UIHelper.t(_activity,"请选择行业！");
            return;
        }
        if(tvPosition.getText().toString().trim().equals("")){
            UIHelper.t(_activity,"请选择地区！");
            return;
        }
        if(etAddress.getText().toString().trim().equals("")){
            UIHelper.t(_activity,"请输入详细地址！");
            return;
        }
        if(etPhone.getText().toString().trim().equals("")){
            UIHelper.t(_activity,"手机号不能为空！");
            return;
        }
        if(shoptimeStart.getText().toString().equals("")||shoptimeEnd.getText().toString().equals("")){
            UIHelper.t(_activity,"请选择营业时间！");
            return;
        }
        if(!RxRegUtils.isMobileExact(etPhone.getText().toString())){
            UIHelper.t(_activity,"手机号码格式不正确！");
            return;
        }
        String cid = tvType.getTag().toString();
        String phone = etPhone.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String shoptime = "";
        if(!shoptimeStart.getText().toString().equals("")||!shoptimeEnd.getText().toString().equals("")){
            shoptime =  shoptimeStart.getText().toString()+"-"+shoptimeEnd.getText().toString();
        }
        String intro = etDes.getText().toString().trim();
        String returnaddress = etReturnaddress.getText().toString().trim();
        String returnname = etReturnname.getText().toString().trim();
        String returnmobile = etReturnmobile.getText().toString().trim();
        String shopname = etShopName.getText().toString().trim();

        AppUtil.getApiClient(ac).businesshold(cid,phone,province,city,country,address,shoptime,intro,pics,returnaddress,returnname,returnmobile,logos,shopname,_activity,callback);

    }

    @OnClick({R.id.tvType, R.id.btnSave, R.id.shoptimeStart, R.id.shoptimeEnd, R.id.tvPosition})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvType:
                if (businessCategoryBean != null && businessCategoryBean.getData() != null) {
                    final DialogSelectBusinessType dialog = new DialogSelectBusinessType(_activity, tvType, businessCategoryBean.getData());
                    dialog.setOnItemClickListener(new DialogSelectBusinessType.onItemClickListener() {
                        @Override
                        public void onItemClicked(int position) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else {
                    AppUtil.getApiClient(ac).businessCategory(callback);
                }
                break;
            case R.id.btnSave:
                save();
                break;
            case R.id.shoptimeStart:
                initTimeDialog(shoptimeStart);
                break;
            case R.id.shoptimeEnd:
                initTimeDialog(shoptimeEnd);
                break;
            case R.id.tvPosition:
                UIHelper.jumpForResult(_activity, ActivityProvince.class, 1100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1003 || requestCode == 1006 || requestCode == 1100) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == 1100) {
                province = data.getStringExtra("province");
                city = data.getStringExtra("city");
                country = data.getStringExtra("country");
                tvPosition.setText(province + " " + city + " " + country);
            }
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        if (!"".equals(result.getImage().getCompressPath())) {
            if (flag) {
                logos.add(logos.size() - 1, result.getImage().getCompressPath());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        logoAdapter.notifyDataSetChanged();
                    }
                });
            } else {
                pics.add(pics.size() - 1, result.getImage().getCompressPath());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }

    }

    private void initTimeDialog(final TextView view) {
        DatePickDialog dialog = new DatePickDialog(_activity);
        dialog.setTitle("选择时间");
        dialog.setType(DateType.TYPE_HM);
        //  dialog.setMessageFormat("yyyy-MM-dd");
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {

                String time = simpleDateFormat.format(date);
                view.setText(time);
            }
        });
        dialog.show();
    }

}
