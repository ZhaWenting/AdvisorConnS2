package alphabet.adviserconn.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

import alphabet.adviserconn.BuildConfig;

/**
 * Created by Administrator on 2016/9/27.
 */
public class LogUtil {

    static boolean isSaveFile = false;

    static {
        new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AdviserConn/").mkdirs();
    }

    public static void d(Object tag,Object msg) {
        if (msg == null) {
            LogUtil.d(tag,"null");
            return;
        }

        if (BuildConfig.DEBUG) {
            Log.d("AdviserConn", tag+":" + msg.toString());
            if (isSaveFile) {
                FileUtils.writeString(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AdviserConn/log.txt", msg.toString());
            }
        }
    }


    public static void e(Object tag,Object msg) {
        if (msg == null) {
            LogUtil.e(tag,"null");
            return;
        }
        if (BuildConfig.DEBUG) {
            Log.e("AdviserConn", tag+":" + msg.toString());
            if (isSaveFile) {
                FileUtils.writeString(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AdviserConn/log.txt", msg.toString());
            }
        }
    }

//    public static void e(String tag,Object msg) {
//        if (msg == null) {
//            LogUtil.e(tag,"null");
//            return;
//        }
//        if (BuildConfig.DEBUG) {
//            Log.e("AdviserConn", tag+":"  + msg.toString());
//            if (isSaveFile) {
//                FileUtils.writeString(Environment.getExternalStorageDirectory().getAbsolutePath() + "/AdviserConn/log.txt", msg.toString());
//            }
//
//        }
//    }
}
