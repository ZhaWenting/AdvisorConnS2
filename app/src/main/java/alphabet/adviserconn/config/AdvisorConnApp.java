package alphabet.adviserconn.config;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.ads.MobileAds;

import alphabet.adviserconn.utils.UnCeHandler;

/**
 * 类描述：全局控制类
 * 创建人：Administrator
 * 创建时间：2017/9/25 0025
 */

public class AdvisorConnApp extends Application {
    private final String TAG = this.getClass().toString();
    //单例，只生成一个app对象
    public static AdvisorConnApp instance;

    public static AdvisorConnApp getInstance() {
        return instance;
    }

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        SystemParams.init(this);
        SystemParamsS2.init(this);
        SystemParamsS2Virus.init(this);
        UnCeHandler catchExcep = new UnCeHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
        context = this.getApplicationContext();
        MobileAds.initialize(this, "ca-app-pub-9885585575069784~3957016956");

    }

}
