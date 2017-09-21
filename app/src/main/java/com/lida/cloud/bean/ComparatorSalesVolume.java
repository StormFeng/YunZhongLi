package com.lida.cloud.bean;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Created by xkr on 2017/9/7.
 */

public class ComparatorSalesVolume implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        ActivityAgentCenterSVBean.DataBean.ListBean bean1 = (ActivityAgentCenterSVBean.DataBean.ListBean) o1;
        ActivityAgentCenterSVBean.DataBean.ListBean bean2 = (ActivityAgentCenterSVBean.DataBean.ListBean) o2;
        if (bean1.getPaydate() != null && bean2.getPaydate() != null)
        {
            return Collator.getInstance(Locale.CHINA).compare(bean1.getPaydate(), bean2.getPaydate());
        }
//        int flag=bean1.toString().compareTo(bean2.toString());
        return -1;
    }
}
