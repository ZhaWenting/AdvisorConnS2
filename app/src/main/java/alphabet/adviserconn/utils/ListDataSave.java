package alphabet.adviserconn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import alphabet.adviserconn.ImaginarySpace.ChatBean;

/**
 * Created by user on 2017/7/23.
 */

public class ListDataSave {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public void setDataList(String tag, ArrayList<ChatBean> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        Log.e("strJson", strJson );
        editor.remove(tag);
        editor.putString(tag, strJson);
        editor.commit();

    }


//    public <T> void setDataList(String tag, ArrayList<T> datalist) {
//        if (null == datalist || datalist.size() <= 0)
//            return;
//
//        Gson gson = new Gson();
//        //转换成json数据，再保存
//        String strJson = gson.toJson(datalist);
//        Log.e("strJson", strJson );
//        editor.remove(tag);
//        editor.putString(tag, strJson);
//        editor.commit();
//
//    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public  ArrayList<ChatBean> getDataList(String tag) {
        ArrayList<ChatBean> datalist=new ArrayList<>();
        String strJson = preferences.getString(tag, null);
//        Log.e("strJson", strJson);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<ChatBean>>() {
        }.getType());
        return datalist;

    }
//    public <T> ArrayList<T> getDataList(String tag) {
//        ArrayList<T> datalist=new ArrayList<T>();
//        String strJson = preferences.getString(tag, null);
//        if (null == strJson) {
//            return datalist;
//        }
//        Gson gson = new Gson();
//        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
//        }.getType());
//        return datalist;
//
//    }
}