package com.lida.cloud.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.CollectBean;
import com.lida.cloud.bean.GoodDetailBean;
import com.lida.cloud.fragment.FramengGoodComment;
import com.lida.cloud.fragment.FramengGoodWebDetail;
import com.lida.cloud.widght.dialog.DialogChooseColor;
import com.lida.cloud.widght.dialog.DialogShare;
import com.midian.base.api.ApiCallback;
import com.midian.base.app.AppManager;
import com.midian.base.base.BaseFragmentActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.Banner;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.ScrollViewWidthListener;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.view.RxToast;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情
 * Created by WeiQingFeng on 2017/8/25.
 */

public class ActivityGoodDetail extends BaseFragmentActivity implements DialogChooseColor.OnSelectButtonClickListener {
    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.slidingdrawer)
    SlidingDrawer slidingDrawer;
    @BindView(R.id.tvUpDown)
    TextView tvUpDown;
    @BindView(R.id.ivUpDown)
    ImageView ivUpDown;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    ScrollViewWidthListener scrollView;
    @BindView(R.id.viewFlag)
    View viewFlag;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvWelfare)
    TextView tvWelfare;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tvGoodName)
    TextView tvGoodName;
    @BindView(R.id.cbCollection)
    CheckBox cbCollection;
    @BindView(R.id.tvStock)
    TextView tvStock;
    @BindView(R.id.tvMarketPrice)
    TextView tvMarketPrice;
    @BindView(R.id.tvExpress)
    TextView tvExpress;
    @BindView(R.id.tvPosition)
    TextView tvPosition;
    @BindView(R.id.tvIntegral)
    TextView tvIntegral;
    @BindView(R.id.tvChooseColor)
    TextView tvChooseColor;
    @BindView(R.id.ivShop)
    ImageView ivShop;
    @BindView(R.id.tvShopName)
    TextView tvShopName;
    @BindView(R.id.btnInShop)
    Button btnInShop;
    @BindView(R.id.handle)
    LinearLayout handle;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.btnShop)
    Button btnShop;
    @BindView(R.id.btnShare)
    Button btnShare;
    @BindView(R.id.btnAddToShopCar)
    Button btnAddToShopCar;
    @BindView(R.id.btnBuyNow)
    Button btnBuyNow;
    @BindView(R.id.llGood)
    LinearLayout llGood;

    private static final String[] CHANNELS = new String[]{"商品详情", "商品评价"};
    private List<Fragment> fragments = new ArrayList<>();
    private GoodDetailBean data;
    private String goodId;
    private DialogChooseColor dialogChooseColor;
    private FramengGoodWebDetail framengGoodWebDetail;
    private FramengGoodComment framengGoodComment;

    private final int NULL = 10000;
    private int selectColor = NULL;
    private String selectCount = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gooddetail);
        ButterKnife.bind(this);
        initView();
        initBanner();
        goodId = mBundle.getString("id");
        AppUtil.getApiClient(ac).goodDetail(goodId, callback);
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
                if ("goodDetail".equals(tag)) {
                    data = (GoodDetailBean) res;
                    GoodDetailBean.DataBean bean = data.getData().get(0);
                    ArrayList imgs = new ArrayList();
                    if (bean.getGoods_images().size() > 0) {
                        imgs.addAll(bean.getGoods_images());
                    }
                    banner.setImages(imgs.toArray());
                    tvGoodName.setText(bean.getGoods_name());
                    tvPrice.setText("消费豆：" + bean.getCost());
                    tvMarketPrice.setText("市场参考价：¥" + bean.getMarket_price());
                    tvStock.setText("库存：" + bean.getStock());
                    tvShopName.setText(bean.getStore().getStore_name());
                    if (bean.getStore().getLogo() != null) {
                        ac.setImage(ivShop, bean.getStore().getLogo());
                    }
                    if(bean.isIs_collect()){
                        cbCollection.setChecked(true);
                    }else{
                        cbCollection.setChecked(false);
                    }
                    tvIntegral.setText("购买可得"+bean.getCredit()+"分");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",data);
                    bundle.putString("id",goodId);
                    framengGoodWebDetail = new FramengGoodWebDetail();
                    framengGoodComment = new FramengGoodComment();
                    framengGoodWebDetail.setArguments(bundle);
                    framengGoodComment.setArguments(bundle);
                    fragments.add(framengGoodWebDetail);
                    fragments.add(framengGoodComment);
                    viewPager.setAdapter(new FragmentPagerAdapter(fm) {
                        @Override
                        public Fragment getItem(int position) {
                            return fragments.get(position);
                        }

                        @Override
                        public int getCount() {
                            return fragments.size();
                        }
                    });
                }
                if("cartAdd".equals(tag)){
                    RxToast.success("添加成功！");
                    Intent intent = new Intent("android.intent.action.RefreshShopCar");
                    sendBroadcast(intent);
                }
                if("collectGood".equals(tag)){
                    RxToast.success("操作成功");
                    CollectBean bean = (CollectBean) res;
                    if(bean.getData().get(0).getState()==0){
                        cbCollection.setChecked(false);
                    }else{
                        cbCollection.setChecked(true);
                    }
                    Intent intent = new Intent("android.intent.action.RefreshCollectGood");
                    sendBroadcast(intent);
                }
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
            RxToast.error(_activity, "网络异常").show();
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
        }
    };

    private void initView() {
        topbar.setMode(BaseLibTopbarView.MODE_2);
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        topbar.setLeftText("商品详情", UIHelper.finish(_activity));
        topbar.getLeft_tv().setTextColor(Color.parseColor("#000000"));
        topbar.setTitle("");
        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                tvUpDown.setText("上拉查看商品详情");
                ivUpDown.setImageResource(R.drawable.icon_double_arrow_t);
            }
        });
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                tvUpDown.setText("下拉关闭商品详情");
                ivUpDown.setImageResource(R.drawable.icon_double_arrow_d);
            }
        });
        scrollView.setOnChildViewVisibilityChangedListener(new ScrollViewWidthListener.onChildViewVisibilityChangedListener() {
            @Override
            public void onChildViewVisibilityChanged(int index, View v, boolean becameVisible) {
                if (viewFlag == v) {
                    if (becameVisible) {
                        slidingDrawer.setVisibility(View.VISIBLE);
                    } else {
                        slidingDrawer.setVisibility(View.GONE);
                    }
                }
            }
        });
        cbCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getApiClient(ac).collectGood(_activity,goodId,callback);
            }
        });
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        magicIndicator.setBackgroundColor(Color.WHITE);
        CommonNavigator commonNavigator = new CommonNavigator(_activity);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return CHANNELS.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(CHANNELS[index]);
                simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#fe6559"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.parseColor("#fe6559"));
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initBanner() {
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(Banner.CENTER);
        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
    }

    private void showDialog() {
        if(dialogChooseColor==null){
            dialogChooseColor = new DialogChooseColor(_activity,data);
            dialogChooseColor.setOnSelectButtonClickListener(this);
        }
        dialogChooseColor.show();
    }

    @OnClick({R.id.tvChooseColor, R.id.btnInShop, R.id.btnShop, R.id.btnShare, R.id.btnAddToShopCar, R.id.btnBuyNow})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tvChooseColor:
                showDialog();
                break;
            case R.id.btnInShop:
                bundle.putString("selid",data.getData().get(0).getStore().getStore_id());
                UIHelper.jump(_activity,ActivityShopDetail.class,bundle);
                break;
            case R.id.btnShop:
                bundle.putString("selid",data.getData().get(0).getStore().getStore_id());
                UIHelper.jump(_activity,ActivityShopDetail.class,bundle);
                break;
            case R.id.btnShare:
                new DialogShare(_activity,"加入云众利，消费不再贵","泉州云众利网络科技有限公司（以下简称：云众利）由福建本土民营企业家黄文汉先生投资创建，于2017年4月在泉州工商局注册成立（目前注册资金为800万元）",
                        "http://www.baidu.com").show();
                break;
            case R.id.btnAddToShopCar:
                if("1".equals(data.getData().get(0).getIs_spec())){
                    if(selectColor == NULL){
                        RxToast.warning("请选择规格");
                        showDialog();
                        return;
                    }
                    String spec_id = data.getData().get(0).getSpec().get(selectColor).getSpec_id();
                    AppUtil.getApiClient(ac).cartAdd(goodId,selectCount, spec_id,callback);
                }else{
                    AppUtil.getApiClient(ac).cartAdd(goodId,selectCount,"",callback);
                }
                break;
            case R.id.btnBuyNow:
                String spec_id = "";
                if("1".equals(data.getData().get(0).getIs_spec())){
                    if(selectColor == NULL){
                        RxToast.warning("请选择规格");
                        showDialog();
                        return;
                    }
                    spec_id = data.getData().get(0).getSpec().get(selectColor).getSpec_id();
                }
                bundle.putString("goodsid",goodId);
                bundle.putString("total",selectCount);
                bundle.putString("specid",spec_id);
                UIHelper.jump(_activity,ActivityReadyToCommitOrder.class,bundle);
                break;
        }
    }

    /**
     * 选择规格、颜色之后的回调
     */
    @Override
    public void onSelectButtonClicked() {
        selectColor = dialogChooseColor.getSelectColor();
        selectCount = dialogChooseColor.getSelectCount();
        dialogChooseColor.dismiss();
    }
}
