package com.lida.cloud.widght;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.lida.cloud.R;

import java.util.List;

/**
 * Created by mengwei on 2016/7/20.
 */
public class MarqueeTextView extends ViewFlipper
{

    private Context mContext;
    private boolean isSetAnimDuration = false;
    private int interval = 3000;
    /**
     * 动画时间
     */
    private int animDuration = 500;

    public MarqueeTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    private void init(Context context)
    {
        this.mContext = context;
        setFlipInterval(interval);
        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);
        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }


    /**
     * 设置循环滚动的View数组
     *
     * @param views
     */
    public void setViews(final List<View> views)
    {
        if (views == null || views.size() == 0) return;
        removeAllViews();
        for (int i = 0; i < views.size(); i++)
        {
            View view = views.get(i);
            //设置监听回调
            final int finalI = i;
            view.setOnClickListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (onItemClickListener != null)
                    {
                        onItemClickListener.onItemClick(v, finalI);
                    }
                }
            });
            addView(view);
        }
        startFlipping();
    }

    /**
     * 点击
     */
    private OnItemClickListener onItemClickListener;

    /**
     * 设置监听接口
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item_view的接口
     */
    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
