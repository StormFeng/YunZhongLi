package com.lida.cloud.app;

/**
 * Created by Administrator on 2017/8/23.
 */

public class Constant {

    public static final String IV = "5efd3f6060e20889";
    public static final String KEY = "10bd4db6bca25b9d";
    public static final String BASEURL = "http://shop.qzyzl.com";
    public static final int REFRESHTIME = 7000 * 1000;
    /*首页*/
    public static final String INDEX = BASEURL + "/api/index";

    /*获取验证码*/
    public static final String GETMOBILECODE = BASEURL + "/api/Msm/getmobilecode";

    /*注册*/
    public static final String REGISTER = BASEURL + "/api/Login/register";

    /*店铺分类*/
    public static final String BUSINESSCATEGORY = BASEURL + "/api/business/category";

    /*店铺列表*/
    public static final String BUSINESSLIST = BASEURL + "/api/business/list";

    /*店铺详情*/
    public static final String BUSINESSDETAIL = BASEURL + "/api/business/detail";

    /*店铺的商品*/
    public static final String BUSINESSSTOREGOODS = BASEURL + "/api/business/storegoods";

    /*商品列表*/
    public static final String GOODLIST = BASEURL + "/api/goods/list";

    /*商品详情*/
    public static final String GOODSDETAIL = BASEURL + "/api/goods/detail";

    /*商品评价*/
    public static final String GOODSCOMMENT = BASEURL + "/api/goods/comment";

    /*登录*/
    public static final String LOGIN = BASEURL + "/api/login/login";

    /*忘记密码*/
    public static final String FORGET = BASEURL + "/api/login/forget";

    /*个人中心*/
    public static final String CENTER = BASEURL + "/api/member/center";

    /*获取手机号*/
    public static final String UNBUNDLING = BASEURL + "/api/member/unbundling";

    /*绑定手机号*/
    public static final String BIND = BASEURL + "/api/member/bind";

    /*我的二维码*/
    public static final String MEMBERQRCODE = BASEURL + "/api/member/qrcode";

    /*修改昵称*/
    public static final String MEMBERNICKNAME = BASEURL + "/api/member/nickname";

    /*修改性别*/
    public static final String MEMBERGENDER = BASEURL + "/api/member/gender";

    /*修改头像*/
    public static final String MEMBERAVATAR = BASEURL + "/api/member/avatar";

    /*修改密码*/
    public static final String MEMBERMODIFY = BASEURL + "/api/member/modify";

    /*刷新Token*/
    public static final String TOKEN = BASEURL + "/api/token";

    /*消费豆明细*/
    public static final String BEANSRECORD = BASEURL + "/api/pay_beans/record";

    /*收藏的店铺*/
    public static final String COLLECTSHOP = BASEURL + "/api/collect/shop";

    /*收藏的商品*/
    public static final String COLLECTGOODS = BASEURL + "/api/collect/goods";

    /*取消与收藏店铺 取消与收藏商品*/
    public static final String COLLECT = BASEURL + "/api/collect/collect";

    /*删除收藏店铺记录*/
    public static final String COLLECTREMOVESHOP = BASEURL + "/api/collect/removeshop";

    /*删除收藏商品记录*/
    public static final String COLLECTREMOVEGOODS = BASEURL + "/api/collect/removegoods";

    /*支持的银行卡列表*/
    public static final String BANKLIST = BASEURL + "/api/bank/list";

    /*我的银行卡列表*/
    public static final String BANKINDEX = BASEURL + "/api/bank/index";

    /*添加银行卡*/
    public static final String BANKBIND = BASEURL + "/api/bank/bind";

    /*查找转账账号*/
    public static final String BALANCESEARCH = BASEURL + "/api/balance/search";

    /*余额记录*/
    public static final String BALANCE = BASEURL + "/api/balance";

    /*余额转账*/
    public static final String BALANCETRANSFER = BASEURL + "/api/balance/transfer";

    /*余额提现申请*/
    public static final String BALANCEWITHDRAWALS = BASEURL + "/api/balance/withdrawals";

    /*余额充值*/
    public static final String BALANCERECHARGE = BASEURL + "/api/balance/recharge";

    /*消费豆充值*/
    public static final String BEANSRECHARGE = BASEURL + "/api/pay_beans/recharge";

    /*添加收货地址*/ /*修改收货地址*/
    public static final String ADDRESSADD = BASEURL + "/api/address/add";

    /*收货地址列表*/
    public static final String ADDRESSLIST = BASEURL + "/api/address/list";

    /*收货地址详情*/
    public static final String ADDRESSDETAIL = BASEURL + "/api/address/detail";

    /*删除收货地址*/
    public static final String ADDRESSREMOVE = BASEURL + "/api/address/remove";

    /*设置默认收货地址*/
    public static final String ADDRESSDEFAULT = BASEURL + "/api/address/default";

    /*判断商家认证状态*/
    public static final String BUSINESSCHECKAPPLYSTATUS = BASEURL + "/api/business/checkApplyStatus";

    /*省*/
    public static final String MEMBERPROVINCE = BASEURL + "/api/member/province";

    /*市*/
    public static final String MEMBERCITY = BASEURL + "/api/member/city";

    /*区*/
    public static final String MEMBERCOUNTY = BASEURL + "/api/member/county";

    /*新闻资讯*/
    public static final String NEWSLIST = BASEURL + "/api/news/list";

    /*签到*/
    public static final String SIGN = BASEURL + "/api/sign";

    /*添加购物车*/
    public static final String CARTADD = BASEURL + "/api/cart/add";

    /*购物车产品列表*/
    public static final String CARTLIST = BASEURL + "/api/cart/list";

    /*删除购物车产品*/
    public static final String CARTREMOVE = BASEURL + "/api/cart/remove";

    /*勾选要下单的购物车产品*/
    public static final String CARTDEFAULT = BASEURL + "/api/cart/default";

    /*修改购物车产品数量  变更购物车产品规格*/
    public static final String CARTUPDATE = BASEURL + "/api/cart/update";

    /*购物车产品规格列表*/
    public static final String CARTSPEC = BASEURL + "/api/cart/spec";

    public static final String ADDRESSUPDATE = BASEURL + "/api/address/update";

    public static final String ADDRESSSPEC = BASEURL + "/api/address/spec";

    /*个人资料修改省市区*/
    public static final String MEMBERPROVINCIALCITY = BASEURL + "/api/member/provincialcity";

    /*修改详细地址*/
    public static final String MEMBERADDRESS = BASEURL + "/api/member/address";

    /*确定订单（购物车进来）*/
    public static final String ORDERCONFIRM = BASEURL + "/api/order/confirm";

    /*商家认证*/
    public static final String BUSINESSAPPLY = BASEURL + "/api/business/apply";

    /*商户资料详情*/
    public static final String BUSINESSDATA = BASEURL + "/api/business/data";

    /*商家库存积分记录*/
    public static final String BUSINESSCREDIT = BASEURL + "/api/business/credit";

    /*商家转出库存积分*/
    public static final String BUSINESSTRANSFER = BASEURL + "/api/business/transfer";

    /*行业类型*/
    public static final String BUSINESSTYPE = BASEURL + "/api/business/type";

    /*提交下单（购物车进来的）*/
    public static final String ORDERCREATE = BASEURL + "/api/order/create";

    /*订单列表*/
    public static final String ORDERLIST = BASEURL + "/api/order/list";

    /*取消订单*/
    public static final String ORDERCANCEL = BASEURL + "/api/order/cancel";

    /*订单支付*/
    public static final String ORDERPAY = BASEURL + "/api/order/pay";

    /*提醒发货*/
    public static final String ORDERREMIND = BASEURL + "/api/order/remind";

    /*付款页面*/
    public static final String BUSINESSBUY = BASEURL + "/api/business/buy";

    /*实名认证*/
    public static final String MEMBERAUTHENTICATION = BASEURL + "/api/member/authentication";

    /*实名认证状态*/
    public static final String MEMBERISREAL = BASEURL + "/api/member/isreal";

    /*查看物流*/
    public static final String ORDERLOGISTICS = BASEURL + "/api/order/logistics";

    /*确认收货*/
    public static final String ORDERRECEIVE = BASEURL + "/api/order/receive";

    /*举报商家*/
    public static final String BUSINESSREPORT = BASEURL + "/api/business/report";

    /*商品评价*/
    public static final String ORDEREVALUATEWRITE = BASEURL + "/api/order/evaluate/write";

    /*评价商品信息*/
    public static final String ORDEREVALUATE = BASEURL + "/api/order/evaluate";

    /*昨日结算*/
    public static final String BALANCEYESTERDAY = BASEURL + "/api/balance/yesterday";

    /*支付宝支付*/
    public static final String PAYALIPAY = BASEURL + "/api/pay/alipay";

    /*库存积分兑换比例*/
    public static final String CREDITRATIO = BASEURL + "/api/business/credit/ratio";

    /*申请退款*/
    public static final String ORDERREFUND = BASEURL + "/api/order/refund";

    /*填写退货信息*/
    public static final String ORDERREFUNDINFO = BASEURL + "/api/order/refund/info";

    /*退货快递方式*/
    public static final String ORDERREFUNDEXPRESS = BASEURL + "/api/order/refund/express";

    /*退款详情*/
    public static final String ORDERREFUNDING = BASEURL + "/api/order/refunding";

    /*信用积分记录*/
    public static final String MEMBERCREDIT_LOG = BASEURL + "/api/member/credit_log";

    /*删除银行卡*/
    public static final String BANKREMOVE = BASEURL + "/api/bank/remove";

    /**
     * 代理中心
     */
    public static final String AGENT = BASEURL+"/api/agent";

    /**
     * 我的推荐
     */
    public static final String  MYRECOMMEND= BASEURL+"/api/member/my_recommend";

    /**
     * 销售额
     */
    public static final String AGENTSALES = BASEURL+"/api/agent/sales";


    /**
     * 收入
     */
    public static final String AGENTINCOME = BASEURL+"/api/agent/income";


    /**
     * 粉丝公益列表
     */
    public static final String WELFARE = BASEURL+"/api/welfare";

    /**
     * 粉丝公益轮播图
     */
    public static final String WELFAREBANNER = BASEURL+"/api/welfare/banner";


    /**
     * 修改商户资料
     */
    public static final String BUSINESSHOLD = BASEURL+"/api/business/hold";
}
