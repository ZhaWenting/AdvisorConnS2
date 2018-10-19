package alphabet.adviserconn.widget;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/7/26 0026
 */
public class CustomInterstitialAd {
    private static CustomInterstitialAd inserstitalAd = null;
    private InterstitialAd mInterstitialAd;
    private Context mContext;

    private CustomInterstitialAd(){}
    public static CustomInterstitialAd getInstance(){
        if(inserstitalAd==null){
            inserstitalAd = new CustomInterstitialAd();
        }
        return inserstitalAd;
    }

    public CustomInterstitialAd getInserstitalAd() {
        return inserstitalAd;
    }
    public boolean isLoaded(){
        return mInterstitialAd.isLoaded();
    }

    public void show(){
        mInterstitialAd.show();
    }

    public void initAd(Context context){
        mContext = context;
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-9885585575069784/8547832806");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e("mInterstitialAd", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e("mInterstitialAd", "onAdFailedToLoad -> " + errorCode);
            }

            @Override
            public void onAdOpened() {
                Log.e("mInterstitialAd", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                Log.e("mInterstitialAd", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.e("mInterstitialAd", "onAdClosed");
            }
        });
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

    }


}
