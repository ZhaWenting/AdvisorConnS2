package alphabet.adviserconn.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import alphabet.adviserconn.ImaginarySpace.ImaginarySpaceActivity;
import alphabet.adviserconn.config.AdvisorConnApp;
import alphabet.adviserconn.config.SystemParams;

/**
 * Created by user on 2017/12/9.
 */

public class UnCeHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static final String TAG = "CatchExcep";
    AdvisorConnApp application;


    public UnCeHandler(AdvisorConnApp application){
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex) && mDefaultHandler != null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        }else{

            Intent intent = new Intent(application.getApplicationContext(), ImaginarySpaceActivity.class);
            PendingIntent restartIntent = PendingIntent.getActivity(
                    application.getApplicationContext(), 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            SystemParams systemParams = SystemParams.getInstance();
            systemParams.setInt("count", 0);
            systemParams.setInt("position", 0);
            systemParams.getString("saveChapter", "");
            systemParams.setDataList("saveList", null);
            systemParams.setBoolean("isBranch", false);

            systemParams.setBoolean("clue1", false);
            systemParams.setBoolean("clue2", false);
            systemParams.setBoolean("clue3", false);
            systemParams.setBoolean("clue4", false);
            systemParams.setBoolean("clue5", false);
            systemParams.setBoolean("clue6", false);
            //退出程序
            AlarmManager mgr = (AlarmManager)application.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                    restartIntent); // 1秒钟后重启应用

        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        Log.e(TAG, "handleException: ",ex );
        //使用Toast来显示异常信息
//        new Thread(){
//            @Override
//            public void run() {
//                Looper.prepare();
//                Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.",
//                        Toast.LENGTH_SHORT).show();
//                Looper.loop();
//            }
//        }.start();
        return true;
    }
}
