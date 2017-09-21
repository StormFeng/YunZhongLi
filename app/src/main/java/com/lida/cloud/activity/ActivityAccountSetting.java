package com.lida.cloud.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.MemberIsrealBean;
import com.lida.cloud.bean.PersonalInfoBean;
import com.lida.cloud.widght.dialog.DialogCode;
import com.lida.cloud.widght.dialog.SexChooserDialog;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxFileUtils;
import com.vondear.rxtools.RxPhotoUtils;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogChooseImage;
import com.vondear.rxtools.view.dialog.RxDialogEditSureCancel;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 账户设置
 * Created by Administrator on 2017/8/9.
 */

public class ActivityAccountSetting extends BaseActivity {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.ivHead)
    RoundedImageView ivHead;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.llCode)
    LinearLayout llCode;
    @BindView(R.id.tvNick)
    TextView tvNick;
    @BindView(R.id.llNick)
    LinearLayout llNick;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.llSex)
    LinearLayout llSex;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.llArea)
    LinearLayout llArea;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.llPhone)
    LinearLayout llPhone;
    @BindView(R.id.llPass)
    LinearLayout llPass;
    @BindView(R.id.llRealName)
    LinearLayout llRealName;
    @BindView(R.id.tvAccountId)
    TextView tvAccountId;
    @BindView(R.id.tvAddress)
    TextView tvAddress;

    private Uri uri;
    private PersonalInfoBean personalInfoBean;
    private String province;
    private String city;
    private String country;
    private String address;
    private String phone;
    private RxDialogEditSureCancel rxDialogEditSureCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsetting);
        ButterKnife.bind(this);
        topbar.setTitle("账户设置");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        getData();

    }

    private void getData() {
        AppUtil.getApiClient(ac).getPersonalInfo(_activity, ac.memid, callback);
    }

    @OnClick({R.id.llHead, R.id.llCode, R.id.llNick, R.id.llSex, R.id.llArea, R.id.llPhone,
            R.id.llPass, R.id.llRealName, R.id.tvAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llHead:
                RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(this, RxDialogChooseImage.LayoutType.TITLE);
                dialogChooseImage.show();
                break;
            case R.id.llCode:
                DialogCode dialogCode = new DialogCode(_activity, personalInfoBean);
                dialogCode.show();
                break;
            case R.id.llNick:
                UIHelper.jumpForResult(_activity, ActivityChangeNick.class, 1001);
                break;
            case R.id.llSex:
                SexChooserDialog sexChooserDialog = new SexChooserDialog(_activity, R.style.bottom_dialog);
                sexChooserDialog.setListener(new SexChooserDialog.OnSexChoosed() {
                    @Override
                    public void onSexChoosed(String s) {
                        AppUtil.getApiClient(ac).memberGender(ac.memid, s, callback);
                    }
                });
                sexChooserDialog.show();
                break;
            case R.id.llArea:
                UIHelper.jumpForResult(_activity, ActivityProvince.class, 1002);
                break;
            case R.id.tvAddress:
                if(rxDialogEditSureCancel==null){
                    rxDialogEditSureCancel = new RxDialogEditSureCancel(_activity);
                    rxDialogEditSureCancel.getTvTitle().setVisibility(View.GONE);
                    rxDialogEditSureCancel.getEditText().setHint("请输入详细地址");
                    rxDialogEditSureCancel.getTvCancel().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rxDialogEditSureCancel.dismiss();
                        }
                    });
                    rxDialogEditSureCancel.getTvSure().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            address = rxDialogEditSureCancel.getEditText().getText().toString();
                            if("".equals(address)){
                                RxToast.error("请输入详细地址");
                                AnimatorUtils.onVibrationView(rxDialogEditSureCancel.getEditText());
                                return;
                            }
                            AppUtil.getApiClient(ac).memberAddress(address,callback);
                        }
                    });
                }
                rxDialogEditSureCancel.show();
                break;
            case R.id.llPhone:
                UIHelper.jump(_activity, ActivityBindNewPhoneFirstStep.class);
                break;
            case R.id.llPass:
                Bundle bundle = new Bundle();
                bundle.putString("phone",phone);
                UIHelper.jump(_activity, ActivityChangePass.class,bundle);
                break;
            case R.id.llRealName:
                AppUtil.getApiClient(ac).memberIsreal(callback);
                break;

        }
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
                if ("getPersonalInfo".equals(tag)) {
                    personalInfoBean = (PersonalInfoBean) res;
                    PersonalInfoBean.DataBean data = personalInfoBean.getData().get(0);
                    if (data.getMem_tx() != null) {
                        ac.setImage(ivHead, data.getMem_tx());
                    }
                    if(data.getQrcode()!=null){
                        ac.setImage(ivCode, data.getQrcode());
                    }
                    tvAccountId.setText(data.getMemid());
                    tvNick.setText(data.getMem_nc());
                    tvSex.setText(data.getMem_sex());
                    tvArea.setText(data.getProvince() + "  " +
                            data.getCity() + "  " +
                            data.getCounty());
                    tvAddress.setText(data.getMem_add());
                    phone = data.getMem_tel();

                }
                if ("memberGender".equals(tag)) {
                    RxToast.success("修改成功");
                    getData();
                }
                if ("memberAvatar".equals(tag)) {
                    RxToast.success("头像上传成功");
                    getData();
                    Intent intent = new Intent("android.intent.action.PersonalInfoRefreshBroadCast");
                    sendBroadcast(intent);
                }
                if ("memberProvincialcity".equals(tag)) {
                    RxToast.success("所在地区修改成功");
                    tvArea.setText(province + "  " + city + "  " + country);
                }
                if("memberAddress".equals(tag)){
                    RxToast.success("详细地址修改成功");
                    rxDialogEditSureCancel.dismiss();
                    tvAddress.setText(address);
                }
                if("memberIsreal".equals(tag)){
                    MemberIsrealBean bean = (MemberIsrealBean) res;
                    MemberIsrealBean.DataBean data = bean.getData().get(0);
                    if(data.getStatus()==0){
                        UIHelper.jump(_activity,ActivityAuth.class);
                    }else if(data.getStatus()==1){
                        RxToast.warning("认证申请已提交，正在审核中");
                    }else if(data.getStatus()==2){
                        RxToast.warning("实名认证已通过");
                    }else if(data.getStatus()==3){
                        RxToast.warning("实名认证不通过，请重新提交");
                        UIHelper.jump(_activity,ActivityAuth.class);
                    }
                }
            } else {
                RxToast.error(_activity, res.getMessage()).show();
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
            RxToast.error(_activity, "网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RxPhotoUtils.GET_IMAGE_FROM_PHONE:
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    RxPhotoUtils.cropImage(_activity, uri);
                }
                break;
            case RxPhotoUtils.GET_IMAGE_BY_CAMERA:
                if (resultCode == RESULT_OK) {
                    uri = RxPhotoUtils.imageUriFromCamera;
                    RxPhotoUtils.cropImage(_activity, uri);
                }
                break;
            case RxPhotoUtils.CROP_IMAGE:
                File img = RxFileUtils.getFileFromUri(_activity, uri);
                AppUtil.getApiClient(ac).memberAvatar(img, callback);
                break;
            case 1001://修改头像
                if (RESULT_OK == resultCode) {
                    getData();
                }
                break;
            case 1002://修改地区
                if (RESULT_OK == resultCode) {
                    province = data.getStringExtra("province");
                    city = data.getStringExtra("city");
                    country = data.getStringExtra("country");
                    AppUtil.getApiClient(ac).memberProvincialcity(province, city, country, callback);
                }
                break;
        }
    }
}
