package com.lida.cloud;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lida.cloud.activity.ActivityLoginAct;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.SignBean;
import com.lida.cloud.fragment.main.FragmentHome;
import com.lida.cloud.fragment.main.FragmentPersonal;
import com.lida.cloud.fragment.main.FragmentShop;
import com.lida.cloud.fragment.main.FragmentShopCar;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.dialog.DialogGoSetting;
import com.lida.cloud.widght.grandienttab.GradientTabStrip;
import com.lida.cloud.widght.grandienttab.GradientTabStripAdapter;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxPermissionTool;
import com.vondear.rxtools.RxTimeUtils;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseFragmentActivity implements DialogGoSetting.onButtonClickListener {

    @BindView(R.id.gts_vp_fragments)
    ViewPager vpFragments;
    @BindView(R.id.gradientTabStrip)
    GradientTabStrip tabStrip;

    private List<Fragment> fragments = new ArrayList<>();
    private GradientTabStripAdapter adapter;
    private LocationClient mLocationClient = null;
    private BDLocationListener myListener = new MyLocationListener();
    public String province = "";//省
    public String city = "";//市
    public String district = "";//区
    public String lon = "";//
    public String lat = "";//
    private DialogGoSetting dialogGoSetting;

    private Intent refreshTokenIntent;
    private long waitTime = 2000;
    private long touchTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                RxPermissionTool.with(_activity).addPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                        .addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .addPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .addPermission(Manifest.permission.CALL_PHONE)
                        .addPermission(Manifest.permission.READ_LOGS)
                        .addPermission(Manifest.permission.READ_PHONE_STATE)
                        .addPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .addPermission(Manifest.permission.SET_DEBUG_APP)
                        .addPermission(Manifest.permission.SYSTEM_ALERT_WINDOW)
                        .addPermission(Manifest.permission.GET_ACCOUNTS)
                        .addPermission(Manifest.permission.WRITE_APN_SETTINGS)
                        .initPermission();
            }else{
                refreshToken();
            }
        }else{
            refreshToken();
        }
        dialogGoSetting = new DialogGoSetting(_activity);
        dialogGoSetting.setOnButtonClickListener(this);
    }

    private void refreshToken(){
        long now = RxTimeUtils.getCurTimeMills();
        long loginTime = Long.parseLong(ac.timestamp);
        final long dTime = now - loginTime;
        LogUtils.e("现在时刻："+RxTimeUtils.milliseconds2String(now));
        LogUtils.e("现在时刻："+now);
        LogUtils.e("登录时刻："+RxTimeUtils.milliseconds2String(loginTime));
        LogUtils.e("登录时刻："+loginTime);
        LogUtils.e("差值："+dTime);
        if(dTime > Constant.REFRESHTIME){
            AppUtil.getApiClient(ac).token(ac.memid,ac.refresh_token,new BaseApiCallback(){
                @Override
                public void onApiSuccess(NetResult res, String tag) {
                    super.onApiSuccess(res, tag);
                    if(res.isOK()){
                        SignBean bean = (SignBean) res;
                        String tokenInfo = bean.getData().get(0).getTokenInfo();
                        String decrypt = AesEncryptionUtil.decrypt(tokenInfo);
                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(decrypt);
                            ac.saveUserInfo(jsonObject.getInt("uid")+"",jsonObject.getString("access_token"),
                                    jsonObject.getString("refresh_token"),jsonObject.getString("timestamp")+"000");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        initView();
                        initLocation();
                    }else{
                        RxToast.error(ac, res.getMessage()).show();
                        ac.clearUserInfo();
                        RxActivityUtils.skipActivityAndFinishAll(AppManager.getAppManager().currentActivity(), ActivityLoginAct.class);
                    }
                }
            });
        }else{
            initView();
            initLocation();
        }
    }

    private void initView() {
        fragments.add(new FragmentHome());
        fragments.add(new FragmentShop());
        fragments.add(new FragmentShopCar());
        fragments.add(new FragmentPersonal());
        adapter = new GradientTabStripAdapter(getSupportFragmentManager(),fragments);
        vpFragments.setAdapter(adapter);
        tabStrip.setAdapter(adapter);
        tabStrip.bindViewPager(vpFragments);
        vpFragments.setOffscreenPageLimit(3);
        refreshTokenIntent = new Intent(this, RefreshService.class);
        startService(refreshTokenIntent);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        int span = 5000;
        option.setScanSpan(span);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        option.setIsNeedLocationDescribe(true);
        option.setIgnoreKillProcess(false);
        option.SetIgnoreCacheException(false);
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(refreshTokenIntent!=null){
            stopService(refreshTokenIntent);
        }
    }

    @Override
    public void onGoSettingClick() {
        dialogGoSetting.dismiss();
        Uri packageURI = Uri.parse("package:" + _activity.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        startActivityForResult(intent,1001);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            int locType = location.getLocType();
            LogUtils.e(location.getLocationDescribe());
            ac.setProperty("position", location.getLocationDescribe());
            if (locType == BDLocation.TypeNetWorkLocation) {
                String addrStr = location.getAddrStr();// 获取反地理编码(文字描述的地址)
                LogUtils.e("文字描述的地址:" + addrStr);
            }
            province = location.getProvince();
            city = location.getCity();
            district = location.getDistrict();
            lat = String.valueOf(location.getLatitude());
            lon = String.valueOf(location.getLongitude());
            ac.saveLocation(lon,lat);
            ac.saveArea(province,city,district);
            LogUtils.e("省:" + province + ", 市:" + city + ", 区:" + district);
            LogUtils.e("lat:" + lat + ", lon:" + lon);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_DENIED){
            RxToast.warning(_activity,"请在权限设置中为云众利开启定位权限").show();
            dialogGoSetting.show();
        }else{
            refreshToken();
        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            super.onBackPressed();
        }
    }
}
