package alphabet.adviserconn.InvitationFromDarkNet.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import alphabet.adviserconn.InvitationFromDarkNet.fragment.ReceiveFragment;
import alphabet.adviserconn.InvitationFromDarkNet.fragment.SendFragment;
import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class MailActivity extends BaseActivity {


    @BindView(R.id.button1)
    RadioButton button1;
    @BindView(R.id.button2)
    RadioButton button2;
    @BindView(R.id.select_group)
    RadioGroup selectGroup;
    @BindView(R.id.avatar_ll)
    LinearLayout avatarLl;
    @BindView(R.id.content)
    FrameLayout content;

    ReceiveFragment fragment1;
    SendFragment fragment2;

    private FragmentManager fragmentManager;

    @Override
    protected void initView() {

        fragmentManager = getSupportFragmentManager();
        button1.performClick();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_mail;
    }


    @OnClick({R.id.button1, R.id.button2,R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                setTabSelection(0);
                break;
            case R.id.button2:
                setTabSelection(1);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void setTabSelection(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = new ReceiveFragment();
                    transaction.add(R.id.content, fragment1);
                } else
                    transaction.show(fragment1);
                break;
            case 1:
                if (fragment2 == null) {
                    fragment2 = new SendFragment();
                    transaction.add(R.id.content, fragment2);
                } else
                    transaction.show(fragment2);
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (fragment1 != null)
            transaction.hide(fragment1);
        if (fragment2 != null)
            transaction.hide(fragment2);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment1 == null && fragment instanceof ReceiveFragment) {
            fragment1 = (ReceiveFragment) fragment;
        } else if (fragment2 == null && fragment instanceof SendFragment) {
            fragment2 = (SendFragment) fragment;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (customInterstitialAd.isLoaded()) {
            customInterstitialAd.show();
        }
    }
}
