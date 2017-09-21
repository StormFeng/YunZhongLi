package com.lida.cloud.widght.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.R;
import com.lida.cloud.bean.GoodDetailBean;
import com.lida.cloud.widght.NumberWidget;
import com.makeramen.roundedimageview.RoundedImageView;
import com.midian.base.app.AppContext;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择商品颜色
 * Created by Administrator on 2016/10/27 0027.
 */

public class DialogChooseColor extends Dialog {

    @BindView(R.id.ivGood)
    RoundedImageView ivGood;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvStock)
    TextView tvStock;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tagColor)
    TagFlowLayout tagColor;
    @BindView(R.id.etCount)
    NumberWidget etCount;
    @BindView(R.id.btnOk)
    Button btnOk;

    private Context context;
    private GoodDetailBean data;
    private AppContext ac;
    private OnSelectButtonClickListener listener;
    private GoodDetailBean.DataBean bean;

    public DialogChooseColor(Context context, GoodDetailBean data) {
        super(context, R.style.bottom_dialog);
        this.data = data;
        init(context);
    }

    public DialogChooseColor(Context context, int themeResId) {
        super(context, R.style.bottom_dialog);
        init(context);
    }


    private void init(final Context context) {
        this.context = context;
        ac = (AppContext) context.getApplicationContext();
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_choosecolor, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        bean = data.getData().get(0);
        if(bean.getGoods_image()!=null){
            ac.setImage(ivGood, bean.getGoods_image());
        }
        tvPrice.setText("消费豆：" + bean.getCost());
        tvStock.setText("库存：" + bean.getStock()+"件");
        etCount.setLimit(Integer.valueOf(bean.getStock()));
        tagColor.setAdapter(new TagAdapter<GoodDetailBean.DataBean.SpecBean>(bean.getSpec()) {
            @Override
            public View getView(FlowLayout parent, int position, GoodDetailBean.DataBean.SpecBean o) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tagcolor,null);
                tv.setText(o.getSpec_name());
                return tv;
            }
        });
        tagColor.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if(selectPosSet.size()==0){
                    tvPrice.setText("消费豆：" + bean.getCost());
                    tvStock.setText("库存：" + bean.getStock()+"件");
                    etCount.setLimit(Integer.valueOf(bean.getStock()));
                }else{
                    Iterator<Integer> iterator = selectPosSet.iterator();
                    int next = iterator.next();
                    tvPrice.setText("消费豆：" + bean.getSpec().get(next).getSpec_cost());
                    tvStock.setText("库存：" + bean.getSpec().get(next).getSpec_stock()+"件");
                    etCount.setLimit(Integer.valueOf(bean.getSpec().get(next).getSpec_stock()));
                }
            }
        });
    }

    @OnClick({R.id.ivClose, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                dismiss();
                break;
            case R.id.btnOk:
                if(listener!=null){
                    listener.onSelectButtonClicked();
                }
                break;
        }
    }

    public interface OnSelectButtonClickListener{
        void onSelectButtonClicked();
    }

    public void setOnSelectButtonClickListener(OnSelectButtonClickListener listener){
        this.listener = listener;
    }

    public int getSelectColor(){
        Object[] objects = tagColor.getSelectedList().toArray();
        LogUtils.e(objects);
        return objects.length==0 ? 0:(int) objects[0];
    }

    public String getSelectCount(){
        return etCount.getNumber();
    }
}
