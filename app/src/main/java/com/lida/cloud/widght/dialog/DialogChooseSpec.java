package com.lida.cloud.widght.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.lida.cloud.R;
import com.lida.cloud.bean.SpecBean;
import com.midian.base.app.AppContext;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择商品颜色
 * Created by Administrator on 2016/10/27 0027.
 */

public class DialogChooseSpec extends Dialog {

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tagColor)
    TagFlowLayout tagColor;
    @BindView(R.id.btnOk)
    Button btnOk;

    private Context context;
    private SpecBean data;
    private AppContext ac;
    private OnSelectButtonClickListener listener;

    public DialogChooseSpec(Context context, SpecBean data) {
        super(context, R.style.bottom_dialog);
        this.data = data;
        init(context);
    }

    public DialogChooseSpec(Context context, int themeResId) {
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
        View v = View.inflate(context, R.layout.dialog_choosespec, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        tagColor.setAdapter(new TagAdapter<SpecBean.DataBean>(data.getData()) {
            @Override
            public View getView(FlowLayout parent, int position,SpecBean.DataBean o) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.item_tagcolor,null);
                tv.setText(o.getSpec_name());
                return tv;
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

    public ArrayList<String> getSelectSpec(){
        ArrayList arr = new ArrayList();
        Object[] objects = tagColor.getSelectedList().toArray();
        int selectId = (int) objects[0];
        arr.add(data.getData().get(selectId).getSpec_id());
        arr.add(data.getData().get(selectId).getSpec_name());
        return arr;
    }
}
