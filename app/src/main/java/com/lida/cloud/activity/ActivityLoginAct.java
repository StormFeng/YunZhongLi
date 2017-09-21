package com.lida.cloud.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.lida.cloud.MainActivity;
import com.lida.cloud.R;
import com.lida.cloud.app.AesEncryptionUtil;
import com.lida.cloud.app.AppUtil;
import com.lida.cloud.app.Constant;
import com.lida.cloud.bean.LoginBean;
import com.midian.base.api.ApiCallback;
import com.midian.base.base.BaseActivity;
import com.midian.base.bean.NetResult;
import com.midian.base.util.AnimatorUtils;
import com.midian.base.util.UIHelper;
import com.vondear.rxtools.RxActivityUtils;
import com.vondear.rxtools.RxAnimationUtils;
import com.vondear.rxtools.RxKeyboardUtils;
import com.vondear.rxtools.activity.AndroidBug5497Workaround;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityLoginAct extends BaseActivity {

    @BindView(R.id.logo)
    ImageView mLogo;
    @BindView(R.id.llLogo)
    LinearLayout llLogo;
    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.iv_clean_phone)
    ImageView mIvCleanPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.clean_password)
    ImageView mCleanPassword;
    @BindView(R.id.iv_show_pwd)
    ImageView mIvShowPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.regist)
    TextView mRegist;
    @BindView(R.id.forget_password)
    TextView mForgetPassword;
    @BindView(R.id.content)
    LinearLayout mContent;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.root)
    RelativeLayout mRoot;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private int height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ac.isLogin()){
            if("1".equals(ac.isagent)){
                RxActivityUtils.skipActivityAndFinish(_activity, ActivityAgentCenter.class);
                return;
            }
            RxActivityUtils.skipActivityAndFinish(_activity, MainActivity.class);
            return;
        }
        setContentView(R.layout.activity_login_act);
        ButterKnife.bind(this);
        if (isFullScreen(this)) {
            AndroidBug5497Workaround.assistActivity(this);
        }
        initView();
        initEvent();
    }

    private void initView() {
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3
    }

    private void initEvent() {
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
                    mIvCleanPhone.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mIvCleanPhone.setVisibility(View.GONE);
                }
            }
        });
        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
                    mCleanPassword.setVisibility(View.VISIBLE);
                } else if (TextUtils.isEmpty(s)) {
                    mCleanPassword.setVisibility(View.GONE);
                }
                if (s.toString().isEmpty())
                    return;
                if (!s.toString().matches("[A-Za-z0-9]+")) {
                    String temp = s.toString();
                    Toast.makeText(_activity, "请输入数字或字母", Toast.LENGTH_SHORT).show();
                    s.delete(temp.length() - 1, temp.length());
                    mEtPassword.setSelection(s.length());
                }
            }
        });
        /**
         * 禁止键盘弹起的时候可以滚动
         */
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = mContent.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        RxAnimationUtils.zoomIn(llLogo, 0.6f, dist);
                    }

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((mContent.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(mContent, "translationY", mContent.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        RxAnimationUtils.zoomOut(llLogo, 0.6f);
                    }
                }
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxKeyboardUtils.hideSoftInput(_activity);
                String name = mEtMobile.getText().toString();
                String pass = mEtPassword.getText().toString();
                if("".equals(name)){
                    AnimatorUtils.onVibrationView(mEtMobile);
                    RxToast.error(_activity,"请输入用户名").show();
                    return;
                }
                if("".equals(pass)){
                    AnimatorUtils.onVibrationView(mEtPassword);
                    RxToast.error(_activity,"请输入密码").show();
                    return;
                }
                AppUtil.getApiClient(ac).login(name,pass,callback);
            }
        });
    }

    public boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    @OnClick({R.id.iv_clean_phone, R.id.clean_password, R.id.iv_show_pwd, R.id.regist, R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_clean_phone:
                mEtMobile.setText("");
                break;
            case R.id.clean_password:
                mEtPassword.setText("");
                break;
            case R.id.iv_show_pwd:
                if (mEtPassword.getInputType() != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    mEtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_visuable);
                } else {
                    mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mIvShowPwd.setImageResource(R.drawable.pass_gone);
                }
                String pwd = mEtPassword.getText().toString();
                if (!TextUtils.isEmpty(pwd))
                    mEtPassword.setSelection(pwd.length());
                break;
            case R.id.regist:
                UIHelper.jump(_activity,ActivityRegister.class);
                break;
            case R.id.forget_password:
                UIHelper.jump(_activity,ActivityForgetPass.class);
                break;
        }
    }

    ApiCallback callback = new ApiCallback() {
        @Override
        public void onApiStart(String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiLoading(long count, long current, String tag) {
            showLoadingDlg();
        }

        @Override
        public void onApiSuccess(NetResult res, String tag) {
            hideLoadingDlg();
            if(res.isOK()){
                LoginBean bean = (LoginBean) res;
                String decrypt = AesEncryptionUtil.decrypt(bean.getData().get(0).getTokenInfo());
                LogUtils.e(decrypt);
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(decrypt);
                    ac.saveUserInfo(jsonObject.getInt("uid")+"",jsonObject.getString("access_token"),
                            jsonObject.getString("refresh_token"),jsonObject.getString("timestamp")+"000");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RxToast.success(_activity, "登录成功！").show();
//                if(RxActivityUtils.isExistActivity(_activity,"com.lida.cloud","MainActivity.class")){
//                    finish();
//                    return;
//                }
                ac.setIsagent(bean.getData().get(0).getIsagent());
                if("1".equals(bean.getData().get(0).getIsagent())){
                    RxActivityUtils.skipActivityAndFinish(_activity, ActivityAgentCenter.class);
                    return;
                }
                RxActivityUtils.skipActivityAndFinish(_activity, MainActivity.class);
            }else{
                RxToast.error(_activity, res.getMessage()).show();
                AnimatorUtils.onVibrationView(mBtnLogin);
            }
        }

        @Override
        public void onApiFailure(Throwable t, int errorNo, String strMsg, String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "网络异常").show();
            AnimatorUtils.onVibrationView(mBtnLogin);
        }

        @Override
        public void onParseError(String tag) {
            hideLoadingDlg();
            RxToast.error(_activity, "数据解析异常").show();
            AnimatorUtils.onVibrationView(mBtnLogin);
        }
    };
}
