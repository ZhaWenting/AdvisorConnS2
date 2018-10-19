package alphabet.adviserconn.widget;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import alphabet.adviserconn.R;

public abstract class AdBannerDialog extends MyAlertDialog {
    protected AdBannerDialog(Context context, String title, String button1, String button2) {
        super(context, title, button1, button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdView adView = findViewById(R.id.ad_banner);
        adView.loadAd(new AdRequest.Builder().build());
    }



    @Override
    protected int getLayoutId() {
        return R.layout.dialog_ad_banner;
    }
}
