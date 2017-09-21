package com.lida.cloud.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.bean.ShopCarListBean;
import com.lida.cloud.bean.SpecBean;
import com.lida.cloud.widght.BaseApiCallback;
import com.lida.cloud.widght.NumberWidget;
import com.lida.cloud.widght.SmoothCheckBox;
import com.lida.cloud.widght.dialog.DialogChooseSpec;
import com.midian.base.app.AppContext;
import com.midian.base.bean.NetResult;
import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogSureCancel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购物车
 * Created by Administrator on 2016/10/27 0027.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ShopCarListBean goodBean;
    private UpdateView updateViewListener;
    private AppContext ac;
    private RxDialogSureCancel dialogSureCancel;
    private double allMoney;
    private int allCount;
    private int n;
    private int m;
    private int isSelect;
    int groupId = 0;
    int childId = 0;
    int childSize = 0;
    int groupPosition = 0;
    private SmoothCheckBox checkBox;

    public ExpandableListAdapter(Context context, ShopCarListBean goodBean) {
        this.context = context;
        this.goodBean = goodBean;
        ac = (AppContext) context.getApplicationContext();
    }

    @Override
    public int getGroupCount() {
        return goodBean.getData()==null ? 0:goodBean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return goodBean.getData().get(groupPosition).getList()==null?
                0:goodBean.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return goodBean.getData()==null?null:goodBean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return goodBean.getData().get(groupPosition).getList()==null?
                null: goodBean.getData().get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopingcargroup, parent, false);
            holder = new GroupViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.cbGroupItem.setTag(groupPosition);
        holder.cbGroupItem.setOnClickListener(listener);
        holder.tvPosition.setText(goodBean.getData().get(groupPosition).getSelshopname());
        if (goodBean.getData().get(groupPosition).isSelected()) {
            if (!holder.cbGroupItem.isChecked()) {
                holder.cbGroupItem.setChecked(true);
            }
        } else {
            holder.cbGroupItem.setChecked(false);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_shopingcarchild, parent, false);
            holder = new ChildViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        String tag = groupPosition + "," + childPosition;
        holder.cbItem.setTag(tag);
        holder.ivDelete.setTag(tag);
        holder.cbItem.setOnClickListener(listener);
        holder.ivDelete.setOnClickListener(listener);
        holder.numEdit.setLimit(Integer.valueOf(goodBean.getData().get(groupPosition).getList().get(childPosition).getStock()));
        holder.numEdit.setNumber(goodBean.getData().get(groupPosition).getList().get(childPosition).getTotal()+"");
        holder.numEdit.setOnBtnClickedListener(new NumberWidget.OnBtnClickedListener() {
            @Override
            public void onBtnClicked(final int i, final boolean isAdd) {

                AppUtil.getApiClient(ac).cartUpdate(goodBean.getData().get(groupPosition).getList().get(childPosition).getId(),
                        "",i+"",new BaseApiCallback(){
                            @Override
                            public void onApiStart(String tag) {
                                super.onApiStart(tag);
                                allMoney = goodBean.getAllMoney();
                                holder.numEdit.setCanClick(false);
                            }

                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                holder.numEdit.setCanClick(true);
                                if(res.isOK()){
                                    goodBean.getData().get(groupPosition).getList().get(childPosition).setTotal(i+"");
//                                    if (goodBean.getData().get(groupPosition).getList().get(childPosition).isSelected()) {
                                    if (goodBean.getData().get(groupPosition).getList().get(childPosition).getIsselect()==1) {
                                        if(isAdd){
                                            allMoney += Double.valueOf(goodBean.getData().get(groupPosition).getList().get(childPosition).getCost());
                                        }else{
                                            allMoney -= Double.valueOf(goodBean.getData().get(groupPosition).getList().get(childPosition).getCost());
                                        }
                                    }
                                    updateViewListener.update(goodBean.isAllSelect(), goodBean.getAllCount(), allMoney);
                                    notifyDataSetChanged();
                                    goodBean.setAllMoney(allMoney);
                                }
                            }
                        });
            }
        });
        if (goodBean.getData().get(groupPosition).getList().get(childPosition).getIsselect()==1) {
            holder.cbItem.setChecked(true);
        } else {
            holder.cbItem.setChecked(false);
        }
        if (goodBean.getData().get(groupPosition).getList().get(childPosition).isEdit()) {
            holder.llEdit.setVisibility(View.VISIBLE);
            holder.llNomal.setVisibility(View.GONE);
            holder.ivDelete.setVisibility(View.VISIBLE);
        } else {
            holder.llEdit.setVisibility(View.GONE);
            holder.llNomal.setVisibility(View.VISIBLE);
            holder.ivDelete.setVisibility(View.GONE);
        }
        holder.tvSpec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtil.getApiClient(ac).cartSpec(goodBean.getData().get(groupPosition).getList().get(childPosition).getGoodsid(),
                        new BaseApiCallback(){
                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                SpecBean bean = (SpecBean) res;
                                if(res.isOK()){
                                    final DialogChooseSpec dialogChooseSpec = new DialogChooseSpec(context, bean);
                                    dialogChooseSpec.setOnSelectButtonClickListener(new DialogChooseSpec.OnSelectButtonClickListener() {
                                        @Override
                                        public void onSelectButtonClicked() {
                                            final ArrayList<String> selectSpec = dialogChooseSpec.getSelectSpec();
                                            AppUtil.getApiClient(ac).cartUpdate(goodBean.getData().get(groupPosition).getList().get(childPosition).getId(),
                                                    selectSpec.get(0),"",new BaseApiCallback(){
                                                        @Override
                                                        public void onApiSuccess(NetResult res, String tag) {
                                                            super.onApiSuccess(res, tag);
                                                            if(res.isOK()){
                                                                dialogChooseSpec.dismiss();
                                                                goodBean.getData().get(groupPosition).getList().get(childPosition).setSpecid(selectSpec.get(0));
                                                                goodBean.getData().get(groupPosition).getList().get(childPosition).setSpec_name(selectSpec.get(1));
                                                                notifyDataSetChanged();
                                                            }else{
                                                                RxToast.error(res.getMessage());
                                                            }
                                                        }
                                                    });
                                        }
                                    });
                                    dialogChooseSpec.show();
                                }else{
                                    RxToast.error(res.getMessage());
                                }
                            }
                        });
            }
        });
        holder.tvGoodName.setText(goodBean.getData().get(groupPosition).getList().get(childPosition).getSp_name());
        holder.tvSpec.setText(goodBean.getData().get(groupPosition).getList().get(childPosition).getSpec_name());
        holder.tvPrice.setText("￥" + goodBean.getData().get(groupPosition).getList().get(childPosition).getCost());
        holder.tvCount.setText("x" + goodBean.getData().get(groupPosition).getList().get(childPosition).getTotal());
        holder.numEdit.setNumber(goodBean.getData().get(groupPosition).getList().get(childPosition).getTotal()+"");
        String sp_image = goodBean.getData().get(groupPosition).getList().get(childPosition).getSp_image();
        if(sp_image!=null){
            ac.setImage(holder.ivGood, sp_image);
        }
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String tag = v.getTag().toString();
            String[] split;
            groupId = 0;
            childId = 0;
            childSize = 0;
            groupPosition = 0;
            allCount = goodBean.getAllCount();
            allMoney = goodBean.getAllMoney();
            if (tag.contains(",")) {
                split = tag.split(",");
                groupId = Integer.parseInt(split[0]);
                childId = Integer.parseInt(split[1]);
            } else {
                groupPosition = Integer.parseInt(tag);
                childSize = goodBean.getData().get(groupPosition).getList().size();
            }
            LogUtils.e("tag:"+tag);
            LogUtils.e("groupPosition:"+groupPosition);
            LogUtils.e("childId:"+childId);
            switch (v.getId()) {
                case R.id.cb_GroupItem:
                    checkBox = (SmoothCheckBox) v;
                    if(goodBean.getData().get(groupPosition).isSelected()){
                        isSelect=0;
                    }else{
                        isSelect=1;
                    }
                    for (int i = 0; i < goodBean.getData().get(groupPosition).getList().size(); i++) {
                        final int finalI = i;
                        AppUtil.getApiClient(ac).cartDefault(goodBean.getData().get(groupPosition).getList().get(i).getId(),
                                isSelect+"",new BaseApiCallback(){
                                    @Override
                                    public void onApiStart(String tag) {
                                        super.onApiStart(tag);
                                        checkBox.setChecked(false);
                                    }

                                    @Override
                                    public void onApiSuccess(NetResult res, String tag) {
                                        super.onApiSuccess(res, tag);
                                        checkBox.setChecked(true);
                                        if(res.isOK()){
                                            if(finalI == goodBean.getData().get(groupPosition).getList().size()-1){
                                                goodBean.getData().get(groupPosition).setSelected(!checkBox.isChecked());
                                                if (!checkBox.isChecked()) {
                                                    for (int i = 0; i < childSize; i++) {
//                                                        if (!goodBean.getData().get(groupPosition).getList().get(i).isSelected()) {
                                                        if (goodBean.getData().get(groupPosition).getList().get(i).getIsselect()!=1) {
                                                            allCount++;
                                                            goodBean.getData().get(groupPosition).getList().get(i).setIsselect(1);
//                                                            goodBean.getData().get(groupPosition).getList().get(i).setSelected(!checkBox.isChecked());
                                                            allMoney += Integer.valueOf(goodBean.getData().get(groupPosition).getList().get(i).getTotal())
                                                                    * Double.valueOf(goodBean.getData().get(groupPosition).getList().get(i).getCost());
                                                        }
                                                    }
                                                } else {
                                                    allCount -= childSize;
                                                    for (int i = 0; i < childSize; i++) {
                                                        goodBean.getData().get(groupPosition).getList().get(i).setIsselect(0);
//                                                        goodBean.getData().get(groupPosition).getList().get(i).setSelected(!checkBox.isChecked());
                                                        allMoney -= Integer.valueOf(goodBean.getData().get(groupPosition).getList().get(i).getTotal())
                                                                * Double.valueOf(goodBean.getData().get(groupPosition).getList().get(i).getCost());
                                                    }
                                                }
                                                int cm = 0;
                                                //判断是否所有的父item都被选中，决定全选按钮状态
                                                for (int i = 0; i < goodBean.getData().size(); i++) {
                                                    if (goodBean.getData().get(i).isSelected()) {
                                                        cm++;
                                                    }
                                                }
                                                if (cm == goodBean.getData().size()) {
                                                    goodBean.setAllSelect(true);
                                                } else {
                                                    goodBean.setAllSelect(false);
                                                }
                                                LogUtils.e("allMoney:"+allMoney);
                                                goodBean.setAllCount(allCount);
                                                goodBean.setAllMoney(allMoney);
                                                notifyDataSetChanged();
                                                updateViewListener.update(goodBean.isAllSelect(), allCount, allMoney);
                                            }
                                        }else{
                                            RxToast.error(res.getMessage());
                                        }
                                    }
                                });
                    }

                    break;
                //单个子项item被点击
                case R.id.cb_Item:
                    checkBox = (SmoothCheckBox) v;
                    n = 0;
                    m = 0;
//                    if(goodBean.getData().get(groupId).getList().get(childId).isSelected()){
                    if(goodBean.getData().get(groupId).getList().get(childId).getIsselect()==1){
                        isSelect=0;
                    }else{
                        isSelect=1;
                    }
                    final int finalGroupId1 = groupId;
                    final int finalChildId1 = childId;
                    AppUtil.getApiClient(ac).cartDefault(goodBean.getData().get(groupId).getList().get(childId).getId(),
                            isSelect+"",new BaseApiCallback(){
                                @Override
                                public void onApiStart(String tag) {
                                    super.onApiStart(tag);
                                    checkBox.setClickable(false);
                                }

                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    checkBox.setClickable(true);
                                    if(res.isOK()){
                                        goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).setIsselect(isSelect);
//                                        goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).setSelected(!checkBox.isChecked());
                                        //遍历父item所有数据，统计被选中的item数量
                                        for (int i = 0; i < goodBean.getData().get(finalGroupId1).getList().size(); i++) {
//                                            if (goodBean.getData().get(finalGroupId1).getList().get(i).isSelected()) {
                                            if (goodBean.getData().get(finalGroupId1).getList().get(i).getIsselect()==1) {
                                                n++;
                                            }
                                        }
                                        //判断是否所有的子item都被选中，决定父item状态
                                        if (n == goodBean.getData().get(finalGroupId1).getList().size()) {
                                            goodBean.getData().get(finalGroupId1).setSelected(true);
                                        } else {
                                            goodBean.getData().get(finalGroupId1).setSelected(false);
                                        }
                                        //判断是否所有的父item都被选中，决定全选按钮状态
                                        for (int i = 0; i < goodBean.getData().size(); i++) {
                                            if (goodBean.getData().get(i).isSelected()) {
                                                m++;
                                            }
                                        }
                                        if (m == goodBean.getData().size()) {
                                            goodBean.setAllSelect(true);
                                        } else {
                                            goodBean.setAllSelect(false);
                                        }
                                        //判断子item状态，更新结算总商品数和合计Money
                                        if (!checkBox.isChecked()) {
                                            allCount++;
                                            allMoney += Integer.valueOf(goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).getTotal())
                                                    * Double.valueOf(goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).getCost());
                                        } else {
                                            allCount--;
                                            allMoney -= Integer.valueOf(goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).getTotal())
                                                    * Double.valueOf(goodBean.getData().get(finalGroupId1).getList().get(finalChildId1).getCost());
                                        }
                                        goodBean.setAllCount(allCount);
                                        goodBean.setAllMoney(allMoney);
                                        notifyDataSetChanged();
                                        updateViewListener.update(goodBean.isAllSelect(), allCount, allMoney);
                                    }else{
                                        RxToast.error(res.getMessage());
                                    }
                                }
                            });
                    break;
                case R.id.iv_Delete:
                    dialogSureCancel = new RxDialogSureCancel(context);
                    dialogSureCancel.getTvTitle().setVisibility(View.GONE);
                    dialogSureCancel.setContent("确定删除？");
                    final int finalChildId = childId;
                    final int finalGroupId = groupId;
                    LogUtils.e(goodBean);
                    LogUtils.e("finalGroupId:"+finalGroupId);
                    LogUtils.e("finalChildId:"+finalChildId);
                    dialogSureCancel.setSureListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String sp_id = goodBean.getData().get(finalGroupId).getList().get(finalChildId).getId();
                            AppUtil.getApiClient(ac).cartRemove(sp_id, new BaseApiCallback(){
                                @Override
                                public void onApiSuccess(NetResult res, String tag) {
                                    super.onApiSuccess(res, tag);
                                    if(res.isOK()){
                                        RxToast.success("删除成功！");
                                        dialogSureCancel.dismiss();
                                        goodBean.getData().get(finalGroupId).getList().remove(finalChildId);
                                        if(goodBean.getData().get(finalGroupId).getList().size()==0){
                                            goodBean.getData().remove(finalGroupId);
                                        }
                                        notifyDataSetChanged();
                                    }else{
                                        RxToast.error(res.getMessage());
                                    }
                                }
                            });
                        }
                    });
                    dialogSureCancel.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogSureCancel.dismiss();
                        }
                    });
                    dialogSureCancel.show();
                    break;
            }
        }
    };

    public void setChangedListener(UpdateView listener) {
        if (updateViewListener == null) {
            this.updateViewListener = listener;
        }
    }

    public void clearStatus(){
        for (int i = 0; i < goodBean.getData().size(); i++) {
            goodBean.getData().get(i).setSelected(false);
            for (int j = 0; j < goodBean.getData().get(i).getList().size(); j++) {
                final int finalJ = j;
                final int finalI = i;
                AppUtil.getApiClient(ac).cartDefault(goodBean.getData().get(i).getList().get(j).getId(),
                        "0",new BaseApiCallback(){
                            @Override
                            public void onApiSuccess(NetResult res, String tag) {
                                super.onApiSuccess(res, tag);
                                if(res.isOK()){
                                    if(finalJ ==goodBean.getData().get(finalI).getList().size()-1){
                                        goodBean.setAllSelect(false);
                                        for (int i = 0; i < goodBean.getData().size(); i++) {
                                            goodBean.getData().get(i).setSelected(false);
                                            for (int n = 0; n < goodBean.getData().get(i).getList().size(); n++) {
                                                goodBean.getData().get(i).getList().get(n).setIsselect(0);
                                            }
                                        }
                                        allCount = 0;
                                        allMoney = 0;
                                        goodBean.setAllMoney(allMoney);
                                        goodBean.setAllCount(allCount);
                                        updateViewListener.update(goodBean.isAllSelect(), allCount, allMoney);
                                        notifyDataSetChanged();
                                    }
                                }
                            }
                        });
            }
        }
    }


    static class GroupViewHolder {
        @BindView(R.id.cb_GroupItem)
        SmoothCheckBox cbGroupItem;
        @BindView(R.id.tv_Position)
        TextView tvPosition;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.cb_Item)
        SmoothCheckBox cbItem;
        @BindView(R.id.tv_GoodName)
        TextView tvGoodName;
        @BindView(R.id.tvSpec)
        TextView tvSpec;
        @BindView(R.id.tv_LimitCount)
        TextView tvLimitCount;
        @BindView(R.id.tv_Price)
        TextView tvPrice;
        @BindView(R.id.tv_Count)
        TextView tvCount;
        @BindView(R.id.iv_Delete)
        ImageView ivDelete;
        @BindView(R.id.ivGood)
        ImageView ivGood;
        @BindView(R.id.ll_Nomal)
        LinearLayout llNomal;
        @BindView(R.id.llEdit)
        LinearLayout llEdit;
        @BindView(R.id.numEdit)
        NumberWidget numEdit;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
