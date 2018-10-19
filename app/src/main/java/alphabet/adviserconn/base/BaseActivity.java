package alphabet.adviserconn.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import alphabet.adviserconn.InvitationFromDarkNet.widget.ChooseAvatarDialog;
import alphabet.adviserconn.InvitationFromDarkNet.widget.StartDialogS2;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.config.SystemParamsS2;
import alphabet.adviserconn.config.SystemParamsS2Virus;
import alphabet.adviserconn.widget.CustomInterstitialAd;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/11/9.
 */

public abstract class BaseActivity extends FragmentActivity {
    //    protected StartDialogS2 startDialogS2;
//    protected InterstitialAd mInterstitialAd;
    protected CustomInterstitialAd customInterstitialAd;
    protected SystemParamsS2 systemParamsS2;
    protected SystemParamsS2Virus systemParamsS2Virus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);
        systemParamsS2 = SystemParamsS2.getInstance();
        systemParamsS2Virus = SystemParamsS2Virus.getInstance();
//        startDialogS2 = new StartDialogS2(this);

        customInterstitialAd = CustomInterstitialAd.getInstance();
        customInterstitialAd.initAd(this);

//        mInterstitialAd = new InterstitialAd(this);
//        mInterstitialAd.setAdUnitId("ca-app-pub-9885585575069784/8547832806");
//
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                Log.e("mInterstitialAd", "onAdLoaded");
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                Log.e("mInterstitialAd", "onAdFailedToLoad -> " + errorCode);
//            }
//
//            @Override
//            public void onAdOpened() {
//                Log.e("mInterstitialAd", "onAdOpened");
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                Log.e("mInterstitialAd", "onAdLeftApplication");
//            }
//
//            @Override
//            public void onAdClosed() {
//                Log.e("mInterstitialAd", "onAdClosed");
//            }
//        });
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected abstract void initView();

    public abstract int getLayoutResID();

    //子线程或主线程中都可以使用toast
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }



//    public void dismissDialog(){
//        if(startDialogS2 !=null&& startDialogS2.isShowing()){
//            startDialogS2.dismiss();
//            startDialogS2 = null;
//        }
//    }

}
