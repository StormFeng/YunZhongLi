package com.lida.cloud.widght.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lida.cloud.R;

public class SexChooserDialog extends Dialog implements View.OnClickListener {

	public interface OnSexChoosed{
		void onSexChoosed(String s);
	}

	private Context context;

	private OnSexChoosed listner;

	public SexChooserDialog(Context context) {
		super(context);
		init(context);
	}

	public SexChooserDialog(Context context, int theme) {
		super(context, theme);
		init(context);
	}

	private void init(Context context) {
		this.context = context;

		Window w = this.getWindow();
		WindowManager.LayoutParams lp = w.getAttributes();
		lp.gravity = Gravity.BOTTOM;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		w.setAttributes(lp);
		this.setCanceledOnTouchOutside(true);

		View contentView = View.inflate(context, R.layout.dialog_sex_chooser, null);
		this.setContentView(contentView);
		contentView.findViewById(R.id.fromCamera).setOnClickListener(this);
		contentView.findViewById(R.id.fromGallery).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		String temp="";
		if (id == R.id.fromCamera) {
			temp="男";
			dismiss();
		} else if (id == R.id.fromGallery) {
			temp="女";
			dismiss();
		}
		if(listner!=null){
			listner.onSexChoosed(temp);
		}
	}

	public SexChooserDialog setListener(OnSexChoosed listner) {
		this.listner = listner;
		return this;
	}
}
