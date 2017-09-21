package com.lida.cloud.widght.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lida.cloud.R;
import com.lida.cloud.bean.BusinessCategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 右弹窗选择行业
 * Created by xkr on 2017/9/8.
 */

public class DialogSelectBusinessType extends Dialog {

    @BindView(R.id.listView)
    ListView listView;

    private Context context;
    private TextView textView;
    private List<BusinessCategoryBean.DataBean> data;
    private onItemClickListener listener;

    public DialogSelectBusinessType(Context context, TextView textView, List<BusinessCategoryBean.DataBean> data) {
        super(context, R.style.right_dialog);
        this.textView = textView;
        this.data = data;
        init(context);
    }

    public DialogSelectBusinessType(Context context, int themeResId) {
        super(context, R.style.right_dialog);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        Window w = this.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.gravity = Gravity.RIGHT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        w.setAttributes(lp);
        this.setCanceledOnTouchOutside(true);
        View v = View.inflate(context, R.layout.dialog_right_list, null);
        this.setContentView(v);
        ButterKnife.bind(this, v);
        listView.setAdapter(new Adapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(data.get(position).getName());
                textView.setTag(data.get(position).getId());
                if(listener!=null){
                    listener.onItemClicked(position);
                }
                dismiss();
            }
        });
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public Object getItem(int position) {
            return data == null ? 0 : position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_business_type, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(data.get(position).getName());
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.tv)
            TextView tv;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    public interface onItemClickListener{
        void onItemClicked(int position);
    }
}
