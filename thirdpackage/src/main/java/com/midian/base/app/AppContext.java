package com.midian.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.bishilai.thirdpackage.R;
import com.midian.base.afinal.http.AjaxParams;
import com.midian.base.api.ApiClient;
import com.midian.base.configlib.ServerConstant;
import com.midian.base.util.EncryptionUtil;
import com.midian.base.util.ResourceUtil;
import com.midian.base.util.UIHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Created by XuYang on 15/4/13.
 */
public class AppContext extends Application {

    public String memid = "";// 用户id
    public String access_token = "";// 访问token
    public String refresh_token = "";
    public String timestamp = "";
    public String key = "";
    public String memState = "";
    public String province = "";
    public String city = "";
    public String district = "";
    public String isagent = "0";

    public boolean ischange = false;
    public DisplayImageOptions options;
    public ImageLoader imageLoader;// 图片加载类
    public ApiClient api;// 网络请求帮助类
    private Class login;
    public String device_token ;// 设备号4335567824607072747
    private ResourceUtil resourceUtil;
    public String lat = "23.129178";// 当前经纬度
    public String lon = "113.405107";// 当前经纬度
    public boolean isClosePush = false;// 是否接收推送
    public boolean isHasNewVersion = false;// 是否接收推送
    public String city_name = "";//选择城市的值
    public String city_id = "";//城市的id
    public String area_id = "";//区的id
    public String area_name = "";//区的name
    public String loc_citys = "";//百度定位的城市
    public String loc_city = "";//百度定位的城市城市的区域
    public String loc_city_id = "";//百度定位的城市城市区id值
    public String loc_city_level = "";//百度定位的城市等级
    public static Context _context;
    public boolean isFirst = false;


    @Override
    public void onCreate() {
        super.onCreate();
        _context = getApplicationContext();
        api = new ApiClient(this);
        resourceUtil = new ResourceUtil(this);
        initData();
        initUImageLoader();

    }


    private void initData() {
        memid = getProperty("memid");
        access_token = getProperty("access_token");
        refresh_token = getProperty("refresh_token");
        timestamp = getProperty("timestamp");
        province = getProperty("province");
        city = getProperty("city");
        district = getProperty("district");
        isagent = getProperty("isagent");
        key = getProperty("key");

        city_name = getProperty("city_name");
        city_id = getProperty("city_id");
        area_id = getProperty("area_id");
        area_name = getProperty("area_name");
        loc_city = getProperty("loc_city");
        loc_citys = getProperty("loc_citys");
        loc_city_id = getProperty("loc_city_id");
        loc_city_level = getProperty("loc_city_level");
        lon = getProperty("lon");
        lat = getProperty("lat");
        isClosePush = getBoolean("isClosePush");
        isHasNewVersion = getBoolean("isHasNewVersion");
        device_token = getProperty("device_token");

        isFirst = getBoolean("isFirst");

    }

    /**
     * 是否第一次启动
     * @param isFirst
     */
    public void isFirst(boolean isFirst) {
        this.isFirst = isFirst;
        setBoolean("isFirst", isFirst);
    }
    public boolean isLogin() {
        return !TextUtils.isEmpty(access_token);
    }

    /**
     * ClientKey规定
     *
     * @return
     */
    public String getClientKey() {
        return EncryptionUtil.getEncryptionStr();
    }

    public void setImage(ImageView iv, int id) {
        imageLoader.displayImage("drawable://" + id, iv);
    }


    public void setImage(final ImageView iv, String url) {
        if (url.contains("http://") || url.contains("https://")) {

        } else {
            url = ServerConstant.BASEFILEURL + url;
        }

//        Glide.with(this).load(url).placeholder(R.drawable.icon_default).error(R.drawable.icon_default).into(iv);
        imageLoader.displayImage(url, iv, new SimpleImageLoadingListener() {

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                // iv.setImageBitmap(arg2);
            }

        });
    }


    public String getFileUrl(String url) {
        if (url.contains(ServerConstant.BASEFILEURL) || url.contains("https://") || url.contains("http://")) {

        } else {
            url = ServerConstant.BASEFILEURL + url;
        }
        return url;
    }

    public static synchronized AppContext context() {
        return (AppContext) _context;
    }

    /**
     * 设置推送id
     *
     * @param device_token
     */
    public void saveDeviceToken(String device_token) {
        this.device_token = device_token;
        setProperty("device_token", device_token);
    }

    /**
     * 设置是否接收
     */
    public void isClosePush(boolean isClosePush) {
        this.isClosePush = isClosePush;
        setBoolean("isClosePush", isClosePush);
    }

    /**
     * 有新版本
     */
    public void isHasNewVersion(boolean isHasNewVersion) {
        this.isHasNewVersion = isHasNewVersion;
        setBoolean("isHasNewVersion", isHasNewVersion);
    }

    /**
     * 保存经纬度
     * @param lon
     * @param lat
     */
    public void saveLocation(String lon, String lat) {
        this.lat = lat;
        this.lon = lon;
        setProperty("lat", lat);
        setProperty("lon", lon);
    }

    public void saveArea(String province, String city, String district) {
        this.province = province;
        this.city = city;
        this.district = district;
        setProperty("province", province);
        setProperty("city", city);
        setProperty("district", district);
    }

    /**
     * 保存选择的城市
     *
     * @param city_name
     * @param city_id
     */
    public void saveCityName(String city_name, String city_id) {
        this.city_name = city_name;
        this.city_id = city_id;
        setProperty("city_name", city_name);
        setProperty("city_id", city_id);
    }

    /**
     * 定位的城市
     */
    public void saveLocCityName(String loc_city, String loc_city_id, String loc_city_level) {
        this.loc_city = loc_city;
        this.loc_city_id = loc_city_id;
        this.loc_city_level = loc_city_level;
        setProperty("loc_city", loc_city);
        setProperty("loc_city_id", loc_city_id);
        setProperty("loc_city_level", loc_city_level);
    }


    /**
     * 获取通用的请求参数
     *
     * @return
     */
    public AjaxParams getCAjaxParams() {
        AjaxParams params = new AjaxParams();
        if (!TextUtils.isEmpty(memid))
            params.put("memberid", memid + "");
       /* if (!TextUtils.isEmpty(access_token))
            params.put("access_token", access_token + "");*/
        params.put("client_key", getClientKey());

        System.out.println(params.getParamString());
        return params;
    }

    public AjaxParams getClientKeyParams() {
        AjaxParams params = new AjaxParams();
        params.put("client_key", getClientKey());
        return params;
    }


    private void initUImageLoader() {
        try {
            Options decodingOptions = new Options();
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.drawable.c_loading)
                    .showImageForEmptyUri(R.drawable.c_loading_failure)
                    .showImageOnFail(R.drawable.c_loading_failure)
                    .decodingOptions(decodingOptions).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                    getApplicationContext()).defaultDisplayImageOptions(options)
                    .threadPriority(Thread.NORM_PRIORITY - 2)
                    .denyCacheImageMultipleSizesInMemory()
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                    .diskCacheSize(50 * 1024 * 1024)
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .writeDebugLogs()
                    .build();
            ImageLoader.getInstance().init(config);
            imageLoader = ImageLoader.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 是否存在access_token
     *
     * @return
     */
    public boolean isAccess() {
        if (TextUtils.isEmpty(access_token)) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 是否存在user_id【用户id】
     *
     * @return
     */
    public boolean isUserIdExsit() {
        if ("".equals(memid)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否包含指定key的配置信息
     *
     * @param key
     * @return
     */
    public boolean containsProperty(String key) {
        SharedPreferences sp = AppConfig.getSharedPreferences(this);
        return sp.contains(key);
    }

    /**
     * 设置指定key的配置信息
     *
     * @param key
     * @param value
     */
    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 设置指定key的配置信息
     *
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 设置指定key的配置信息s
     *
     * @param key
     * @param value
     */
    public void setInteger(String key, int value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    /**
     * 获得指定key的配置信息
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    /**
     * 获得指定key的配置信息
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return AppConfig.getAppConfig(this).getBoolean(key);
    }

    /**
     * 获得指定key的配置信息
     *
     * @param key
     * @return
     */
    public int getInteger(String key) {
        return AppConfig.getAppConfig(this).getInteger(key);
    }

    /**
     * 刪除指定key的配置信息
     *
     * @param key
     */
    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 保存对象
     *
     * @param ser
     * @param file
     * @throws IOException
     */
    public boolean saveObject(Serializable ser, String file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput(file, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ser);
            oos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                oos.close();
            } catch (Exception e) {
            }
            try {
                fos.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * 读取对象
     *
     * @param file
     * @return
     * @throws IOException
     */
    public Serializable readObject(String file) {
        if (!isExistDataCache(file))
            return null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(file);
            ois = new ObjectInputStream(fis);
            return (Serializable) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (Exception e) {
            e.printStackTrace();
            // 反序列化失败 - 删除缓存文件
            if (e instanceof InvalidClassException) {
                File data = getFileStreamPath(file);
                data.delete();
            }
        } finally {
            try {
                ois.close();
            } catch (Exception e) {
            }
            try {
                fis.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 判断缓存是否存在
     *
     * @param cachefile
     * @return
     */
    public boolean isExistDataCache(String cachefile) {
        boolean exist = false;
        File data = getFileStreamPath(cachefile);
        if (data.exists())
            exist = true;
        return exist;
    }

    public void setIsagent(String isagent) {
        this.isagent = isagent;
        setProperty("isagent", isagent);
    }

    /**
     * 保存用户信息
     */
    public void saveUserInfo(String memid,String access_token,String refresh_token,String timestamp) {
        ischange = true;
        this.memid = memid;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.timestamp = timestamp;
        setProperty("memid", memid);
        setProperty("access_token", access_token);
        setProperty("timestamp", timestamp);
        setProperty("refresh_token", refresh_token);
        saveKey(access_token);
    }

    /**
     * 刷新access_token
     * @param access_token
     */
    public void saveKey(String access_token){
        this.key = access_token.substring(4,20);
        setProperty("key", key);
    }

    /**
     * 保存用户状态
     * @param memState
     */
    public void saveMemState(String memState){
        this.memState = memState;
        setProperty("memState", memState);
    }

    /**
     * 清除用户信息
     */
    public void clearUserInfo() {
        memid = "";
        access_token = "";
        refresh_token = "";
        timestamp = "";
        key = "";
        province = "";
        city = "";
        district = "";
        isagent = "";
        removeProperty("memid");
        removeProperty("access_token");
        removeProperty("refresh_token");
        removeProperty("timestamp");
        removeProperty("key");
        removeProperty("province");
        removeProperty("city");
        removeProperty("district");
        removeProperty("isagent");
    }

    /**
     * 要求登录
     *
     * @param context
     * @return
     */
    public boolean isRequireLogin(final Activity context) {
        if (TextUtils.isEmpty(memid)) {
            if (login != null)
                UIHelper.jump(context, login);
            return false;
        } else {
            return true;
        }
    }

    public String getUrl(String url) {
        if (url.contains(ServerConstant.BASEURL) || url.contains("http://")
                || url.contains("https://")) {

        } else {
            url = ServerConstant.BASEURL + url;
        }
        return url;

    }

    /**
     * 设置登录页面
     *
     * @param context
     */
    public void setLogin(Class context) {
        this.login = context;
    }

    /**
     * 处理后台返回的错误码
     *
     * @param context
     * @param errorcode
     */
    public void handleErrorCode(final Context context, final String errorcode) {

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                if (TextUtils.isEmpty(errorcode)) {
                    return;
                }
                // 登录过期
                if ("token_error".equals(errorcode)
                        || "tokenTimeLimit".equals(errorcode)
                        || "tokenError".equals(errorcode)) {
                    UIHelper.t(context, "您的账号已经在其他设备登陆，请重新登录");
                    clearUserInfo();
                    // logout();
                    // BroadcastManager.sendLogoutBroadcast(context);
                    UIHelper.jump((Activity) context, login);
//					Intent intent = new Intent(context, login);
//					Bundle bundle = new Bundle();
//					intent.putExtras(bundle);
//
//					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
//							| Intent.FLAG_ACTIVITY_NEW_TASK);
//
//					context.startActivity(intent);


                } else {
                    UIHelper.t(context, resourceUtil.getString(errorcode));
                }
            }
        };
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(runnable);
        } else if (context instanceof AppContext) {
            new Handler().post(runnable);
        }

    }
}
