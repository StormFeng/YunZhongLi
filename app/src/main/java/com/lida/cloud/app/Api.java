package com.lida.cloud.app;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.activity.ActivityAgentCenterSalesVolume;
import com.lida.cloud.activity.ActivityGoodDetail;
import com.lida.cloud.bean.ActivityAgentCenterBean;
import com.lida.cloud.bean.ActivityAgentCenterRevenueBean;
import com.lida.cloud.bean.ActivityAgentCenterSVBean;
import com.lida.cloud.bean.ActivityGoodListBean;
import com.lida.cloud.bean.ActivityPublicWelfareBannerBean;
import com.lida.cloud.bean.ActivityPublicWelfareBean;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBean;
import com.lida.cloud.bean.ActivityReadyToCommitOrderBeanCopy;
import com.lida.cloud.bean.ActivityShopDetailBean;
import com.lida.cloud.bean.AddressListBean;
import com.lida.cloud.bean.BalanceListBean;
import com.lida.cloud.bean.BalanceYesterdayBean;
import com.lida.cloud.bean.BeanRecordBean;
import com.lida.cloud.bean.BusinessCategoryBean;
import com.lida.cloud.bean.BusinessDataBean;
import com.lida.cloud.bean.BusinessHoldBean;
import com.lida.cloud.bean.BusinessListBean;
import com.lida.cloud.bean.CheckIsShopMember;
import com.lida.cloud.bean.CityBean;
import com.lida.cloud.bean.CollectBean;
import com.lida.cloud.bean.CollectGoodsBean;
import com.lida.cloud.bean.CollectShopBean;
import com.lida.cloud.bean.CountryBean;
import com.lida.cloud.bean.CreditRatioBean;
import com.lida.cloud.bean.ExpressBean;
import com.lida.cloud.bean.GetPhoneBean;
import com.lida.cloud.bean.GoodCommentBean;
import com.lida.cloud.bean.GoodDetailBean;
import com.lida.cloud.bean.IndexBean;
import com.lida.cloud.bean.LoginBean;
import com.lida.cloud.bean.LogisticsBean;
import com.lida.cloud.bean.MemberCreditLogBean;
import com.lida.cloud.bean.MemberIsrealBean;
import com.lida.cloud.bean.MemberQrcodeBean;
import com.lida.cloud.bean.MyBankCardListBean;
import com.lida.cloud.bean.MyRecommendBean;
import com.lida.cloud.bean.NewsListBean;
import com.lida.cloud.bean.OrderCreateBean;
import com.lida.cloud.bean.OrderEvaluateBean;
import com.lida.cloud.bean.OrderListBean;
import com.lida.cloud.bean.PayBean;
import com.lida.cloud.bean.PersonalInfoBean;
import com.lida.cloud.bean.ProvinceBean;
import com.lida.cloud.bean.RefundBean;
import com.lida.cloud.bean.RefundingBean;
import com.lida.cloud.bean.ShopCarListBean;
import com.lida.cloud.bean.ShopGoodBean;
import com.lida.cloud.bean.SignBean;
import com.lida.cloud.bean.SignMonthBean;
import com.lida.cloud.bean.SpecBean;
import com.lida.cloud.bean.SupportBankCardListBean;
import com.midian.base.afinal.http.AjaxParams;
import com.midian.base.api.ApiCallback;
import com.midian.base.api.BaseApiClient;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.RxDataUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;


/**
 * Created by WeiQingFeng on 2017/5/16.
 */

public class Api extends BaseApiClient {
    public Api(AppContext ac) {
        super(ac);
    }
    static public void init(AppContext appcontext) {
        if (appcontext == null)
            return;
        appcontext.api.addApiClient(new Api(appcontext));
    }

    /**
     * 首页数据
     * @param callback
     */
    public void index(String city,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("city",city);
        get(callback, Constant.INDEX, params, IndexBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取验证码
     * @param mobile
     * @param type 注册传register,忘记密码传forget,解绑传relieve,绑定传bind
     * @param callback
     */
    public void getMobilecode(String mobile,String type,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("mobile",mobile);
        params.put("type",type);
        get(callback, Constant.GETMOBILECODE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 注册
     * @param name
     * @param password
     * @param mobile
     * @param tname
     * @param callback
     */
    public void register(String name,String password,String mobile,String tname,String code,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        params.put("password",password);
        params.put("mobile",mobile);
        params.put("tname",tname);
        params.put("code",code);
        post(callback, Constant.REGISTER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 店铺分类
     * @param callback
     */
    public void businessCategory(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        get(callback, Constant.BUSINESSCATEGORY, params, BusinessCategoryBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 店铺列表
     * @param lat
     * @param lng
     * @param cid
     * @param keyword
     * @param p
     * @return
     * @throws Exception
     */
    public BusinessListBean businessList(String lat,String lng,String cid,String keyword,String p) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("lat",lat);
        params.put("lng",lng);
        params.put("cid",cid);
        params.put("keyword",keyword);
        params.put("p",p);
        return (BusinessListBean) getSync(Constant.BUSINESSLIST, params, BusinessListBean.class);
    }

    /**
     * 商品列表
     * @param goods_name
     * @param cate_id
     * @param store_id
     * @param sort
     * @param type
     * @param callback
     */
    public void goodList(String goods_name,String cate_id,String store_id,
                         String sort,String type,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("cate_id",cate_id);
        if(!RxDataUtils.isNullString(store_id)){
            params.put("store_id",store_id);
        }
        if(!RxDataUtils.isNullString(goods_name)){
            params.put("goods_name",goods_name);
        }
        params.put("limit","1000");
        params.put("sort",sort);
        params.put("type",type);
        get(callback, Constant.GOODLIST, params, ActivityGoodListBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商品详情
     * @param id
     * @param callback
     */
    public void goodDetail(String id,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("id",id);
        params.put("member_id",ac.memid);
        get(callback, Constant.GOODSDETAIL, params, GoodDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商品评价
     * @param id
     */
    public GoodCommentBean goodComment(String id) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("id",id);
        params.put("page","1");
        params.put("limit","10000");
        return (GoodCommentBean) getSync(Constant.GOODSCOMMENT, params, GoodCommentBean.class);
    }

    /**
     * 店铺详情
     * @param id
     * @param memid
     */
    public void businessDetail(String id,String memid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("id",id);
        params.put("memid",memid);
        get(callback, Constant.BUSINESSDETAIL, params, ActivityShopDetailBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 店铺商品
     * @param id
     */
    public void businessStoregoods(String id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("id",id);
        get(callback, Constant.BUSINESSSTOREGOODS, params, ShopGoodBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 登录
     * @param name
     * @param password
     */
    public void login(String name,String password,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("name",name);
        params.put("password",password);
        post(callback, Constant.LOGIN, params, LoginBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 找回密码
     * @param mobile
     * @param pwd
     * @param code
     * @param callback
     */
    public void forget(String mobile,String pwd,String code,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("mobile",mobile);
        params.put("pwd",pwd);
        params.put("code",code);
        post(callback, Constant.FORGET, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 个人中心
     * @param context
     * @param memid
     * @param callback
     */
    public void getPersonalInfo(Context context,String memid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        LogUtils.e(AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.CENTER, params, PersonalInfoBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 获取手机号
     * @param memid
     * @param callback
     */
    public void getPhone(Context context,String memid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.UNBUNDLING, params, GetPhoneBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交验证码
     * @param context
     * @param code
     * @param callback
     */
    public void commitCode(Context context, String code, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("code",code);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.UNBUNDLING, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 绑定手机号
     * @param code
     * @param callback
     */
    public void bindPhone(String code,String mobile,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("code",code);
        params.put("mobile",mobile);
        params.put("sign",AesEncryptionUtil.getBaseSign(ac));
        post(callback, Constant.BIND, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 更新令牌
     * @param memid
     * @param callback
     */
    public void token(String memid,String refresh_token,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("refresh_token",refresh_token);
        post(callback, Constant.TOKEN, params, SignBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 消费豆记录
     * @param memid
     * @param type
     */
    public BeanRecordBean beanRecord(String memid, String type) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("type",type);
        return (BeanRecordBean) getSync(Constant.BEANSRECORD, params, BeanRecordBean.class);
    }

    /**
     * 收藏的店铺
     * @param memid
     * @param callback
     */
    public void collectShop(Context context,String memid,String lng,String lat,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("lng",lng);
        params.put("lat",lat);
        params.put("limit","10000");
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.COLLECTSHOP, params, CollectShopBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 收藏的商品
     * @param callback
     */
    public void collectGoods(Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("limit","10000");
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.COLLECTGOODS, params, CollectGoodsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 我的二维码
     * @param memid
     * @param callback
     */
    public void memberQrcode(String memid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        get(callback, Constant.MEMBERQRCODE, params, MemberQrcodeBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改昵称
     * @param memid
     * @param nickname
     * @param callback
     */
    public void memberNickname(String memid, String nickname, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("nickname",nickname);
        post(callback, Constant.MEMBERNICKNAME, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改性别
     * @param memid
     * @param gender
     * @param callback
     */
    public void memberGender(String memid, String gender, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("gender",gender);
        post(callback, Constant.MEMBERGENDER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改头像
     * @param callback
     */
    public void memberAvatar(File img, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.setHasFile(true);
        try {
            params.put("img",img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        post(callback, Constant.MEMBERAVATAR, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改省市区
     * @param callback
     */
    public void memberProvincialcity(String province, String city, String county, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("province",province);
        params.put("city",city);
        params.put("county",county);
        post(callback, Constant.MEMBERPROVINCIALCITY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改详细地址
     * @param callback
     */
    public void memberAddress(String address, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("address",address);
        post(callback, Constant.MEMBERADDRESS, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改密码
     * @param callback
     */
    public void memberModify(Context context,String mobile,String opwd,String npwd,String repwd,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("mobile",mobile);
        params.put("opwd",opwd);
        params.put("npwd",npwd);
        params.put("repwd",repwd);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.MEMBERMODIFY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 查找转账账号
     * @param context
     * @param memid
     * @param tar_member
     * @param callback
     */
    public void balanceSearch(Context context, String memid, String tar_member, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("tar_member",tar_member);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e(jsonTypeSign);
        String sign = AesEncryptionUtil.encrypt(context,jsonTypeSign.toString());
        params.put("sign",sign);
        post(callback, Constant.BALANCESEARCH, params, SignBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 余额记录
     * @param memid
     */
    public BalanceListBean balance(String memid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",memid);
        params.put("limit","10000");
        return (BalanceListBean) getSync(Constant.BALANCE, params, BalanceListBean.class);
    }

    /**
     * 余额转账
     * @param context
     * @param tar_id
     * @param amount
     * @param remarks
     * @param callback
     */
    public void balanceTransfer(Context context, String tar_id,String amount, String remarks, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("tar_id",tar_id);
            jsonTypeSign.put("amount",amount);
            jsonTypeSign.put("remarks",remarks);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.BALANCETRANSFER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 余额提现申请
     * @param callback
     */
    public void balanceWithdrawals(Context context,String bankcard_id, String amount, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("bankcard_id",bankcard_id);
            jsonTypeSign.put("amount",amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.BALANCEWITHDRAWALS, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 余额充值
     * @param context
     * @param amount
     * @param callback
     */
    public void balanceRecharge(Context context, String amount, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("amount",amount);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.BALANCERECHARGE, params, SignBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }
    /**
     * 消费豆充值
     * @param callback
     */
    public void beansRecharge(String num, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("num",num);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.BEANSRECHARGE, params, SignBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /*我的银行卡列表*/
    public void bankIndex(Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.BANKINDEX, params, MyBankCardListBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加银行卡
     * @param context
     * @param cardnumber
     * @param name
     * @param bank
     * @param callback
     */
    public void bankBind(Context context,String cardnumber,String name,String bank,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("cardnumber",cardnumber);
            jsonTypeSign.put("name",name);
            jsonTypeSign.put("bank",bank);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.BANKBIND, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 支持绑定的银行列表
     * @return
     * @throws Exception
     */
    public SupportBankCardListBean getSupportBankCardList() throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        return (SupportBankCardListBean) getSync(Constant.BANKLIST, params, SupportBankCardListBean.class);
    }

    /**
     * 添加收货地址
     * @param name
     * @param mobile
     * @param province
     * @param city
     * @param county
     * @param address
     * @param callback
     */
    public void addAddress(String name,String mobile,String province,String city,
                           String county,String address,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("name",name);
        params.put("mobile",mobile);
        params.put("province",province);
        params.put("city",city);
        params.put("county",county);
        params.put("address",address);
        post(callback, Constant.ADDRESSADD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 设置默认收货地址
     * @param callback
     */
    public void addressDefault(String id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        post(callback, Constant.ADDRESSDEFAULT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除收货地址
     * @param callback
     */
    public void addressRemove(String id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        post(callback, Constant.ADDRESSREMOVE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改收货地址
     * @param callback
     */
    public void editAddress(String name,String mobile,String province,String city,
                           String county,String address,String id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("name",name);
        params.put("mobile",mobile);
        params.put("province",province);
        params.put("city",city);
        params.put("county",county);
        params.put("address",address);
        params.put("id",id);
        post(callback, Constant.ADDRESSADD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


    /**
     * 收货地址列表
     * @param callback
     */
    public void getAddressList(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        get(callback, Constant.ADDRESSLIST, params, AddressListBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 判断商家认证状态
     * @param callback
     */
    public void businessCheckApplyStatus(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        get(callback, Constant.BUSINESSCHECKAPPLYSTATUS, params, CheckIsShopMember.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 省
     */
    public ProvinceBean province() throws Exception {
        AjaxParams params=new AjaxParams();
        return (ProvinceBean) getSync(Constant.MEMBERPROVINCE, params, ProvinceBean.class);
    }

    /**
     * 市
     */
    public CityBean city(String pid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("pid",pid);
        return (CityBean) getSync(Constant.MEMBERCITY, params, CityBean.class);
    }

    /**
     * 区
     */
    public CountryBean county(String cid) throws Exception {
        AjaxParams params=new AjaxParams();
        params.put("cid",cid);
        return (CountryBean) getSync(Constant.MEMBERCOUNTY, params, CountryBean.class);
    }

    /**
     * 新闻资讯
     */
    public NewsListBean newsList() throws Exception {
        AjaxParams params=new AjaxParams();
        return (NewsListBean) getSync(Constant.NEWSLIST, params, NewsListBean.class);
    }

    /**
     * 签到
     * @param callback
     */
    public void sign(Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.SIGN, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 签到
     * @param callback
     */
    public void signDetail(Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.SIGN, params, SignMonthBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消与收藏店铺
     * @param context
     * @param id
     * @param callback
     */
    public void collectShop(Context context,String id,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.COLLECT, params, CollectBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消与收藏商品
     * @param context
     * @param id
     * @param callback
     */
    public void collectGood(Context context,String id,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.COLLECT, params, CollectBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 添加购物车
     * @param callback
     */
    public void cartAdd(String goodsid,String total,String specid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("goodsid",goodsid);
        params.put("total",total);
        params.put("specid",specid);
        post(callback, Constant.CARTADD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 购物车列表
     * @param callback
     */
    public void cartList(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        get(callback, Constant.CARTLIST, params, ShopCarListBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除购物车商品
     * @param callback
     */
    public void cartRemove(String ids,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("ids",ids);
        post(callback, Constant.CARTREMOVE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 购物车产品规格列表
     * @param callback
     */
    public void cartSpec(String goodsid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("goodsid",goodsid);
        get(callback, Constant.CARTSPEC, params, SpecBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 变更购物车产品规格
     * @param callback
     */
    public void cartUpdate(String id,String specid,String total,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        params.put("specid",specid);
        params.put("total",total);
        post(callback, Constant.CARTUPDATE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 勾选要下单的购物车产品
     * @param callback
     */
    public void cartDefault(String id,String select,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        params.put("select",select);
        post(callback, Constant.CARTDEFAULT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除收藏店铺记录
     * @param callback
     */
    public void collectRemoveshop(Context context,String ids, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("ids",ids);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.COLLECTREMOVESHOP, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 删除收藏商品记录
     * @param callback
     */
    public void collectRemovegoods(Context context,String ids, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("ids",ids);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.COLLECTREMOVEGOODS, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 确认订单（购物车进来）
     * @param callback
     */
    public void orderConfirm(Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.ORDERCONFIRM, params, ActivityReadyToCommitOrderBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 确认订单（立即购买到确定订单）
     * @param callback
     */
    public void orderConfirmCopy(Context context,String goodsid,String total,String specid,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",goodsid);
        params.put("total",total);
        if(!"".equals(specid)){
            params.put("specid",specid);
        }
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.ORDERCONFIRM, params, ActivityReadyToCommitOrderBeanCopy.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商家认证
     * @param context
     * @param callback
     */
    public void businessApply(Context context, String name, String phone, String number,
                              String shopname, List<String> pics,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("name",name);
        params.put("phone",phone);
        params.put("number",number);
        params.put("shopname",shopname);
        params.setHasFile(true);
        try {
            for (int i = 0; i < pics.size(); i++) {
                File file = new File(pics.get(i));
                params.put("image[]",file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.BUSINESSAPPLY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     *
     * @param context
     * @param callback
     */
    public void businessData(Context context,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.BUSINESSDATA, params, BusinessDataBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 库存积分
     * @param context
     */
    public BusinessHoldBean businessCredit(Context context) throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        return (BusinessHoldBean) getSync(Constant.BUSINESSCREDIT, params, BusinessHoldBean.class);
    }

    /**
     * 库存积分充值
     */
    public void businessCredit(String money,ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("money",money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.BUSINESSCREDIT, params, SignBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 商家转出库存积分
     * @param context
     * @param callback
     */
    public void businessTransfer(Context context,String mem_name,String credit,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("mem_name", mem_name);
            jsonTypeSign.put("credit", credit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.BUSINESSTRANSFER, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交下单（购物车进来的）
     * @param context
     */
    public void orderCreate(Context context,List<String> remarks, String addressid, ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        if(remarks!=null){
            for (int i = 0; i < remarks.size(); i++) {
                params.put("remark[]", remarks.get(i));
            }
        }
        params.put("addressid", addressid);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.ORDERCREATE, params, OrderCreateBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交下单（商品详情进来的）
     * @param context
     */
    public void orderCreate(Context context, String goodsid, String total, String specid,
                            String addressid, String remark, ApiCallback callback){
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("id", goodsid);
        params.put("total", total);
        params.put("specid", specid);
        params.put("addressid", addressid);
        params.put("remark", remark);
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.ORDERCREATE, params, OrderCreateBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 代理中心
     * @param callback
     */
    public void agent(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        get(callback, Constant.AGENT, params, ActivityAgentCenterBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 销售额
     * @param starttime
     * @param endtime
     * @param page
     * @param limit
     * @param callback
     */
    public void agentsales(String starttime,String endtime,String page,String limit,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("starttime",starttime);
        params.put("endtime",endtime);
        params.put("page",page);
        params.put("limit",limit);
        get(callback, Constant.AGENTSALES, params, ActivityAgentCenterSVBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 收入
     * @param page
     * @param limit
     * @param callback
     */
    public void agentincome(String page,String limit,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("page",page);
        params.put("limit",limit);
        get(callback, Constant.AGENTINCOME, params, ActivityAgentCenterRevenueBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 我的推荐
     * @param type
     * @return
     * @throws Exception
     */
    public MyRecommendBean myrecommend(String type) throws Exception{
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("limit","10000");
        params.put("type",type);
        return (MyRecommendBean) getSync(Constant.MYRECOMMEND, params, MyRecommendBean.class);
    }

    /**
     * 订单列表
     * @param context
     * @param status
     * @param callback
     */
    public void orderList(Context context,String status,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        if(!"".equals(status)){
            params.put("status",status);
        }
        params.put("limit","10000");
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        get(callback, Constant.ORDERLIST, params, OrderListBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 取消订单
     * @param context
     * @param callback
     */
    public void orderCancel(Context context,String order_id,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("order_id",order_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.ORDERCANCEL, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }
    /**
     * 粉丝公益列表
     * @return
     * @throws Exception
     */
    public ActivityPublicWelfareBean welfare(String page,String limit) throws Exception{
        AjaxParams params=new AjaxParams();
        params.put("page",page);
        params.put("limit",limit);
        return (ActivityPublicWelfareBean) getSync(Constant.WELFARE, params, ActivityPublicWelfareBean.class);
    }

    /**
     * 粉丝公益轮播图
     * @param callback
     */
    public void welfarebanner(ApiCallback callback){
        AjaxParams params=new AjaxParams();
        get(callback, Constant.WELFAREBANNER, params, ActivityPublicWelfareBannerBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 订单支付
     * @param callback
     */
    public void orderPay(Context context,String order_sn,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(context);
        try {
            jsonTypeSign.put("order_sn",order_sn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(context,jsonTypeSign.toString()));
        post(callback, Constant.ORDERPAY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提醒发货
     * @param id
     * @param callback
     */
    public void orderDetail(String id, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("id",id);
        get(callback, Constant.ORDERREMIND, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 修改商户资料
     * @param cid
     * @param phone
     * @param province
     * @param city
     * @param county
     * @param address
     * @param shoptime
     * @param intro
     * @param image
     * @param returnaddress
     * @param returnname
     * @param returnmobile
     * @param logo
     * @param shopname
     * @param context
     * @param callback
     */
    public void businesshold(String cid,String phone,String province,String city,String county,String address,
                             String shoptime,String intro, List<String> image,String returnaddress,String returnname,String returnmobile,
                             List<String> logo,String shopname,Context context,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        LogUtils.e(logo);
        LogUtils.e(image);
        params.put("memid",ac.memid);
        params.put("cid",cid);
        params.put("phone",phone);
        params.put("province",province);
        params.put("city",city);
        params.put("county",county);
        params.put("address",address);
        params.put("shoptime",shoptime);
        params.put("intro",intro);
        params.put("returnaddress",returnaddress);
        params.put("returnname",returnname);
        params.put("returnmobile",returnmobile);
        params.put("shopname",shopname);
        params.setHasFile(true);
        try {
            for (int i = 0; i < image.size(); i++) {
                if(image.get(i)!=null&&!image.get(i).equals("")) {
                    File file = new File(image.get(i));
                    params.put("image[]", file);
                }
            }
            for(int i=0;i<logo.size();i++){
                if(logo.get(i)!=null&&!logo.get(i).equals("")) {
                    File file = new File(logo.get(i));
                    params.put("logo", file);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.getBaseSign(context));
        post(callback, Constant.BUSINESSHOLD, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 付款页面
     * @param callback
     */
    public void orderBuy(String credit,String selid, ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("selid",selid);
            jsonTypeSign.put("credit",credit);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.BUSINESSBUY, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 实名认证
     * @param callback
     */
    public void memberAuthentication(String realname,String cardnum,List<String> pics,ApiCallback callback){
        AjaxParams params=new AjaxParams();
        params.put("memid",ac.memid);
        params.put("realname",realname);
        params.put("cardno",cardnum);
        params.setHasFile(true);
        File file1 = new File(pics.get(0));
        File file2 = new File(pics.get(1));
        File file3 = new File(pics.get(2));
        try {
            params.put("positive",file1);
            params.put("negative",file2);
            params.put("handheld",file3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        params.put("sign",AesEncryptionUtil.getBaseSign(ac));
        post(callback, Constant.MEMBERAUTHENTICATION, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 实名认证
     * @param callback
     */
    public void memberIsreal(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        post(callback, Constant.MEMBERISREAL, params, MemberIsrealBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 查看物流
     * @param callback
     */
    public void orderLogistics(String id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("id", id);
        get(callback, Constant.ORDERLOGISTICS, params, LogisticsBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 确认收货
     * @param callback
     */
    public void orderReceive(String order_id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("order_id", order_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.ORDERRECEIVE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 举报商家
     * @param callback
     */
    public void businessReport(String selid,String con,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("selid", selid);
        params.put("con", con);
        post(callback, Constant.BUSINESSREPORT, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 提交评价
     * @param callback
     */
    public void orderEvaluateWrite(String order_id, String comment, List<String> pics, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("order_id",order_id);
            jsonTypeSign.put("comment",comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.setHasFile(true);
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        for (int i = 0; i < pics.size(); i++) {
            String[] split = pics.get(i).split("\\!\\$\\!");
            LogUtils.e(split);
            File file = new File(split[1]);
            try {
                params.put(split[0],file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        post(callback, Constant.ORDEREVALUATEWRITE, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 评价商品信息
     * @param callback
     */
    public void orderEvaluate(String order_id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("order_id",order_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        get(callback, Constant.ORDEREVALUATE, params, OrderEvaluateBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 累计结算
     */
    public BalanceYesterdayBean balanceYesterday() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("limit", "10000");
        return (BalanceYesterdayBean) getSync(Constant.BALANCEYESTERDAY, params, BalanceYesterdayBean.class);
    }

    /*信用积分记录*/
    public MemberCreditLogBean memberCredit_log() throws Exception {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("limit", "10000");
        return (MemberCreditLogBean) getSync(Constant.MEMBERCREDIT_LOG, params, MemberCreditLogBean.class);
    }

    /**
     * 支付宝支付
     * @param callback
     */
    public void payAlipay(String pay_sn,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("pay_sn", pay_sn);
        get(callback, Constant.PAYALIPAY, params, PayBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 库存积分兑换比例
     * @param callback
     */
    public void creditRatio(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.CREDITRATIO, params, CreditRatioBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 申请退款
     */
    public void orderRefund(String type,String content,String order_id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("type",type);
            jsonTypeSign.put("content",content);
            jsonTypeSign.put("order_id",order_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.ORDERREFUND, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 填写退货信息
     * @param callback
     */
    public void orderRefundInfo(String order_id, String express_sn,String express_code, ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("order_id",order_id);
            jsonTypeSign.put("express_sn",express_sn);
            jsonTypeSign.put("express_code",express_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        post(callback, Constant.ORDERREFUNDINFO, params, NetResult.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 快递公司列表
     * @param callback
     */
    public void orderRefundExpress(ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        get(callback, Constant.ORDERREFUNDEXPRESS, params, ExpressBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 退货地址
     * @param callback
     */
    public void orderRefund(String order_id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        JSONObject jsonTypeSign = AesEncryptionUtil.getJsonTypeSign(ac);
        try {
            jsonTypeSign.put("order_id",order_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        params.put("sign", AesEncryptionUtil.encrypt(ac,jsonTypeSign.toString()));
        get(callback, Constant.ORDERREFUND, params, RefundBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }

    /**
     * 退款详情
     * @param callback
     */
    public void orderRefunding(String order_id,ApiCallback callback) {
        AjaxParams params = new AjaxParams();
        params.put("memid", ac.memid);
        params.put("refundid",order_id);
        get(callback, Constant.ORDERREFUNDING, params, RefundingBean.class,
                getMethodName(Thread.currentThread().getStackTrace()));
    }


}








