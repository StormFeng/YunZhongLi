package com.lida.cloud.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.LubanOptions;
import com.lida.cloud.R;
import com.lida.cloud.widght.BaseTakePhotoActivity;
import com.lida.cloud.widght.dialog.DialogChoosePicType;
import com.midian.base.util.UIHelper;
import com.vondear.rxtools.RxDataUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公司图片上传
 * Created by WeiQingFeng on 2017/4/17.
 */

public class AdapterShopPic extends BaseAdapter {

    private BaseTakePhotoActivity context;
    private DialogChoosePicType dialog;
    private List<String> pics=new ArrayList<>();

    public AdapterShopPic(BaseTakePhotoActivity context, List<String> pics) {
        this.context = context;
        this.pics = pics;
    }



    DialogChoosePicType.onTypeSelectedListener listener=new DialogChoosePicType.onTypeSelectedListener() {
        @Override
        public void onOpenCamera() {
            TakePhoto takePhoto = context.getTakePhoto();
            File file=new File(Environment.getExternalStorageDirectory(), "/activity_chooseposition/"+System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists())file.getParentFile().mkdirs();
            Uri imageUri = Uri.fromFile(file);
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromCapture(imageUri);
        }

        @Override
        public void onOpenPic() {
            TakePhoto takePhoto = context.getTakePhoto();
            CompressConfig config;
            LubanOptions option=new LubanOptions.Builder()
                    .setMaxHeight(1080)
                    .setMaxWidth(1920)
                    .setMaxSize(512000)
                    .create();
            config=CompressConfig.ofLuban(option);
            takePhoto.onEnableCompress(config,false);
            takePhoto.onPickFromGallery();
        }
    };

    @Override
    public int getCount() {
        LogUtils.e(pics);
        if(pics.size()==4){
            pics.remove(3);
        }else if(pics.size()==3){

        }else if(!"".equals(pics.get(pics.size()-1))){
            pics.add("");
        }
//        else if(!"".equals(pics.get(pics.size()-1))){
//            pics.add("");
//        }
        return pics.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pic, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position==pics.size()-1){
            if(RxDataUtils.isNullString(pics.get(position))){
                viewHolder.ivAdd.setVisibility(View.VISIBLE);
                viewHolder.ivDel.setVisibility(View.GONE);
                viewHolder.ivSource.setVisibility(View.GONE);
            }else{
                viewHolder.ivAdd.setVisibility(View.GONE);
                viewHolder.ivSource.setVisibility(View.VISIBLE);
                viewHolder.ivDel.setVisibility(View.VISIBLE);
                viewHolder.ivSource.setImageBitmap(BitmapFactory.decodeFile(pics.get(position)));
            }
        }else{
            viewHolder.ivAdd.setVisibility(View.GONE);
            viewHolder.ivSource.setVisibility(View.VISIBLE);
            viewHolder.ivDel.setVisibility(View.VISIBLE);
            viewHolder.ivSource.setImageBitmap(BitmapFactory.decodeFile(pics.get(position)));
        }
        viewHolder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position>2){
                    UIHelper.t(context,"最多只能上传三张图片");
                    return;
                }
                context.flag = false;
                dialog = new DialogChoosePicType(context);
                dialog.setTypeSelectedListener(listener);
                dialog.show();
            }
        });
        viewHolder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e(position);
                pics.remove(position);
                LogUtils.e(pics);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.ivSource)
        ImageView ivSource;
        @BindView(R.id.ivAdd)
        ImageView ivAdd;
        @BindView(R.id.ivDel)
        ImageView ivDel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
