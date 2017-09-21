package com.lida.cloud.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ActivityAgentCenterBean;
import com.lida.cloud.bean.ActivityAgentCenterSVBean;
import com.lida.cloud.bean.ComparatorSalesVolume;
import com.lida.cloud.widght.BaseApiCallback;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.UIHelper;
import com.midian.base.widget.BaseLibTopbarView;
import com.midian.base.widget.astickyheader.PinnedSectionListView;
import com.midian.base.widget.astickyheader.SimpleSectionedListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 代理中心-销售额列表
 * Created by xkr on 2017/9/7.
 */

public class ActivityAgentCenterSalesVolume extends BaseActivity {


    @BindView(R.id.topbar)
    BaseLibTopbarView topbar;
    @BindView(R.id.sum)
    TextView sum;
    @BindView(R.id.listView)
    PinnedSectionListView listView;



    private List<ActivityAgentCenterSVBean.DataBean.ListBean> data = null;
    private List<String> mHeaderNames = new ArrayList<>();
    private List<Integer> mHeaderPositions = new ArrayList<>();
    private ArrayList<SimpleSectionedListAdapter.Section> sections = new ArrayList<>();

    String starttime="";
    String endtime="";

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agentcenter_salesvolume);
        ButterKnife.bind(this);
        topbar.setTitle("销售额");
        topbar.setLeftImageButton(R.drawable.icon_back, UIHelper.finish(_activity));
        if(mBundle.getString("starttime")!=null){
            starttime = mBundle.getString("starttime");
        }
        if(mBundle.getString("endtime")!=null){
            endtime = mBundle.getString("endtime");
        }
        AppUtil.getApiClient(ac).agentsales(starttime,endtime,"","10000",callback);
    }

    BaseApiCallback callback = new BaseApiCallback(){
        @Override
        public void onApiStart(String tag) {
            super.onApiStart(tag);
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            super.onApiSuccess(res, tag);
            hideLoadingDlg();
            if(res.isOK()){

                    ActivityAgentCenterSVBean bean = (ActivityAgentCenterSVBean) res;
                if(bean!=null&&bean.getData()!=null&&bean.getData().size()>0) {
                    sum.setText(bean.getData().get(0).getSum()==null?"":bean.getData().get(0).getSum());
                    initData(bean);
                }
            }else{
                ac.handleErrorCode(_activity,res.getMessage());
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            super.onApiFailure(t, errorNo, strMsg, tag);
            hideLoadingDlg();
        }
    };


    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? null : data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(_activity).inflate(R.layout.item_sales_volume, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.money.setText(data.get(position).getMoney()==null?"":data.get(position).getMoney());
            viewHolder.selshopname.setText(data.get(position).getSelshopname()==null?"":data.get(position).getSelshopname());
            ac.setImage(viewHolder.image,data.get(position).getSelimage()==null?"":data.get(position).getSelimage());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.money)
            TextView money;
            @BindView(R.id.selshopname)
            TextView selshopname;
            @BindView(R.id.image)
            RoundedImageView image;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 初始化数据，排序
     */
    private void initData(ActivityAgentCenterSVBean bean) {

        data = bean.getData().get(0).getList();
        sections.clear();

        //清除之前的数据，搜索时下标越界
        mHeaderNames.clear();
        mHeaderPositions.clear();

        ComparatorSalesVolume comparatorCar = new ComparatorSalesVolume();
       // Collections.sort(data, comparatorCar); //排序
        for (int i = 0; i < data.size(); i++) {
            String firstFont = String.valueOf(data.get(i).getPaydate());
            if (!mHeaderNames.contains(firstFont)) {
                mHeaderNames.add(firstFont);
                mHeaderPositions.add(i);
            }
        }
        LogUtils.e(mHeaderNames);
        LogUtils.e(mHeaderPositions);

        for (int i = 0; i < mHeaderPositions.size(); i++) {
            sections.add(new SimpleSectionedListAdapter.Section(mHeaderPositions.get(i), mHeaderNames.get(i)));
        }
        adapter = new Adapter();
        SimpleSectionedListAdapter simpleSectionedGridAdapter = new SimpleSectionedListAdapter(this, adapter,
                R.layout.list_item_header, R.id.header);
        simpleSectionedGridAdapter.setSections(sections.toArray(new SimpleSectionedListAdapter.Section[0]));
        listView.setAdapter(simpleSectionedGridAdapter);
    }

}
