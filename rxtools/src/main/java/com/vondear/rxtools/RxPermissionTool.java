//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.vondear.rxtools;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RxPermissionTool {
    public RxPermissionTool() {
    }

    public static RxPermissionTool.Builder with(Activity activity) {
        return new RxPermissionTool.Builder(activity);
    }

    public static class Builder {
        private Activity mActivity;
        private List<String> permissionList;

        public Builder(@NonNull Activity activity) {
            this.mActivity = activity;
            this.permissionList = new ArrayList();
        }

        public RxPermissionTool.Builder addPermission(@NonNull String permission) {
            if(!this.permissionList.contains(permission)) {
                this.permissionList.add(permission);
            }

            return this;
        }

        public List<String> initPermission() {
            ArrayList list = new ArrayList();
            Iterator var2 = this.permissionList.iterator();

            while(var2.hasNext()) {
                String permission = (String)var2.next();
                if(ActivityCompat.checkSelfPermission(this.mActivity, permission) != 0) {
                    list.add(permission);
                }
            }

            if(list.size() > 0) {
                ActivityCompat.requestPermissions(this.mActivity, (String[])list.toArray(new String[list.size()]), 1);
            }

            return list;
        }
    }
}
