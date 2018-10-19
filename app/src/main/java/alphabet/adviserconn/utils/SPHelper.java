package alphabet.adviserconn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALPHABET on 2016/12/9.
 * SharedPreferences辅助工具类
 */

public class SPHelper {
    private String name;
    private Context context;
    private int mode = Context.MODE_PRIVATE;

    public SPHelper(Context context,String name) {
        this.name = name;
        this.context = context;
    }

    //分类存储键值对
    public void putValue(KeyVal... keyval) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor et = sp.edit();
        for (KeyVal keyVal : keyval) {
            save(et, keyVal);
        }
        et.commit();
    }


    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor editor = sp.edit();
        editor.remove(tag);
        editor.putString(tag, strJson);
        editor.commit();

    }




    private void save(Editor et, KeyVal keyVal) throws IllegalArgumentException {
        String key = keyVal.key;
        Object value = keyVal.value;
        if (value instanceof String) {
            et.putString(key, (String) value);
        } else if (value instanceof Integer) {
            et.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            et.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            et.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            et.putBoolean(key, (Boolean) value);
        } else if(value==null){
            et.putString(key,"");
        }else
            throw new IllegalArgumentException("非法数据类型,只允许Stringint,float,long,boolean");
    }

    public static class KeyVal {
        String key;
        Object value;

        public KeyVal(String key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    //获取键值对
    public String getString(String key) {
        return context.getSharedPreferences(key, mode).getString(key, "");
    }

    public Integer getInt(String key) {
        return context.getSharedPreferences(key, mode).getInt(key, -1);
    }

    public Float getFloat(String key) {
        return context.getSharedPreferences(key, mode).getFloat(key, -1);
    }

    public Long getLong(String key) {
        return context.getSharedPreferences(key, mode).getLong(key, -1);
    }

    public Boolean getBoolean(String key) {
        return context.getSharedPreferences(key, mode).getBoolean(key, false);
    }


    /**
     * 获取List
     * @param tag
     * @return
     */
    public <T> ArrayList<T> getDataList(String tag) {
        ArrayList<T> datalist=new ArrayList<T>();
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        String strJson = sp.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;

    }


    //清除数据
    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, mode);
        sharedPreferences.edit().clear().commit();
    }
}
