package alphabet.adviserconn.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import alphabet.adviserconn.ImaginarySpace.ChatBean;
import alphabet.adviserconn.InvitationFromDarkNet.bean.Season2ChatBean;
import alphabet.adviserconn.utils.Contants;


/**
 * 
 * 保存系统信息
 * @author song
 * @date 2015年11月4日
 */
public class SystemParams {

	private static SystemParams instance;
	private static SharedPreferences sharedPrederences = null;

	private SystemParams() {
	}

	//在Application初始化
	public static void init(Context context) {
		sharedPrederences = context.getSharedPreferences(Contants.APP_NAME, Context.MODE_PRIVATE);
	}
	public static SystemParams getInstance() {
		
		if(instance == null) {
			synchronized (SystemParams.class) {
				if(instance == null) {
					 instance = new SystemParams();
				}
			}
		}
		return instance;
	}
	
	/**get**/
	public int getInt(String key){
		return sharedPrederences.getInt(key, 0);
	}
	
	public int getInt(String key,int defValue){
		return sharedPrederences.getInt(key, defValue);
	}	

	public float getFloat(String key){
		return sharedPrederences.getFloat(key, 0);
	}
	
	public float getFloat(String key,float defValue) {
		return sharedPrederences.getFloat(key, defValue);
	}	
	
	public long getLong(String key){
		return sharedPrederences.getLong(key, 0);
	}
	
	public long getLong(String key,long defValue) {
		return sharedPrederences.getLong(key, defValue);
	}		

	public String getString(String key){
		return sharedPrederences.getString(key, null);
	}
	
	public String getString(String key,String defValue) {
		return sharedPrederences.getString(key, defValue);
	}	
	
	public boolean getBoolean(String key){
		return sharedPrederences.getBoolean(key, false);
	}
	
	public boolean getBoolean(String key,boolean defValue) {
		return sharedPrederences.getBoolean(key, defValue);
	}
	
	/**set**/
	public void setInt(String key,int value) {
		Editor editor = sharedPrederences.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public void setFloat(String key,float value) {
		Editor editor = sharedPrederences.edit();
		editor.putFloat(key, value);
		editor.commit();
	}
	
	public void setLong(String key,long value) {
		Editor editor = sharedPrederences.edit();
		editor.putLong(key, value);
		editor.commit();
	}
	
	public void setString(String key,String value) {
		Editor editor = sharedPrederences.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void setBoolean(String key,boolean value) {
		Editor editor = sharedPrederences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public void setSetString(String key,Set<String> values) {
		Editor editor = sharedPrederences.edit();
		editor.putStringSet(key, values);
		editor.commit();
	}


	public void setDataList(String key, ArrayList<ChatBean> chatBeen) {
		Editor editor = sharedPrederences.edit();
		Gson gson = new Gson();
		String json = gson.toJson(chatBeen);
		editor.putString(key, json);
		editor.commit();
	}

	public void setDataListSeason2(String key, ArrayList<Season2ChatBean> chatBeen) {
		Editor editor = sharedPrederences.edit();
		Gson gson = new Gson();
		String json = gson.toJson(chatBeen);
		editor.putString(key, json);
		editor.commit();
	}

	public ArrayList<ChatBean> getDataList(String key) {
		String json = sharedPrederences.getString(key, null);
		ArrayList<ChatBean> alterSamples = null;
		if (json != null) {
			Gson gson = new Gson();
			Type type = new TypeToken<List<ChatBean>>() {
			}.getType();
			alterSamples = gson.fromJson(json, type);
			if(alterSamples==null){
				return null;
			}

		}
		return alterSamples;
	}


	public ArrayList<Season2ChatBean> getDataListSeason2(String key) {
		String json = sharedPrederences.getString(key, null);
		ArrayList<Season2ChatBean> alterSamples = null;
		if (json != null) {
			Gson gson = new Gson();
			Type type = new TypeToken<List<Season2ChatBean>>() {
			}.getType();
			alterSamples = gson.fromJson(json, type);
			if(alterSamples==null){
				return null;
			}

		}
		return alterSamples;
	}

	public void remove(String key) {
		Editor editor = sharedPrederences.edit();
		editor.remove(key);
		editor.commit();
	}
	
	public void clear() {
		Editor editor = sharedPrederences.edit();
		editor.clear().commit();
	}
}
