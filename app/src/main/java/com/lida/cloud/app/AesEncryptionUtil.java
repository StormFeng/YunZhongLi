package com.lida.cloud.app;

import android.content.Context;

import com.apkfuns.logutils.LogUtils;
import com.midian.base.app.AppContext;
import com.vondear.rxtools.RxTimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

public class AesEncryptionUtil {

    // /** 算法/模式/填充 **/
    private static final String CipherMode = "AES/CBC/PKCS5Padding";

    // /** 创建密钥 **/
    private static SecretKeySpec createKey(String key) {
        byte[] data = null;
        if (key == null) {
            key = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(key);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data, "AES");
    }

    private static IvParameterSpec createIV(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(16);
        sb.append(password);
        while (sb.length() < 16) {
            sb.append("0");
        }
        if (sb.length() > 16) {
            sb.setLength(16);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new IvParameterSpec(data);
    }

    // /** 加密字节数据 **/
    public static byte[] encrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // /** 加密(结果为16进制字符串) **/

    /**
     * 加密指定的字符串
     * @param context
     * @param content
     * @return
     */
    public static String encrypt(Context context,String content) {
        LogUtils.e(content);
        AppContext ac = (AppContext) context.getApplicationContext();
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, ac.key, Constant.IV);
        String result = ToHexUtil.byte2hex(data);
        return result;
    }

    // /** 解密字节数组 **/
    public static byte[] decrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key, createIV(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // /** 解密 **/
    public static String decrypt(String content) {
        byte[] data = null;
        try {
            data = ToHexUtil.hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, Constant.KEY, Constant.IV);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LogUtils.e(result);
        return result;
    }

    /**
     * 获得基本的json对象
     * @param context
     * @return
     */
    public static JSONObject getJsonTypeSign(Context context){
        AppContext ac = (AppContext) context.getApplicationContext();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("memid",ac.memid);
            jsonObject.put("token",ac.access_token.substring(4,20));
            jsonObject.put("timestamp", String.valueOf(RxTimeUtils.getCurTimeMills()).substring(0,10));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e(ac.access_token);
        LogUtils.e("加密秘钥："+ac.key);
        LogUtils.e(jsonObject);
        return jsonObject;
    }

    /**
     * 获得基本的json对象加密字符串
     * @param context
     * @return
     */
    public static String getBaseSign(Context context){
        return encrypt(context,getJsonTypeSign(context).toString());
    }
}