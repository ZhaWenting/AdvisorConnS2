package alphabet.adviserconn;

import android.content.Intent;
import android.view.View;

import alphabet.adviserconn.ImaginarySpace.ImaginarySpaceActivity;
import alphabet.adviserconn.InvitationFromDarkNet.activity.StartActivityS2;
import alphabet.adviserconn.base.ActivityCollector;
import alphabet.adviserconn.base.BaseActivity;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public final String TAG = this.getClass().toString();

    @Override
    protected void initView() {
        //获取当前日期
        long time = systemParamsS2.getLong("time", 0);
        if (time == 0) {
            time = System.currentTimeMillis();
            systemParamsS2.setLong("time", time);
        }
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.main_select1, R.id.main_select2, R.id.main_select3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_select1:
                startActivity(new Intent(this, ImaginarySpaceActivity.class));
                break;
            case R.id.main_select2:
                if (customInterstitialAd.isLoaded())
                    customInterstitialAd.show();
                startActivity(new Intent(MainActivity.this, StartActivityS2.class));
//                startActivity(new Intent(MainActivity.this, DeskActivity.class));
                break;
            case R.id.main_select3:
                System.exit(0);
                ActivityCollector.finishAll();
                break;

        }
    }
}
