package alphabet.adviserconn.InvitationFromDarkNet.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import alphabet.adviserconn.InvitationFromDarkNet.widget.StartDialogS2;
import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class DeskActivity extends BaseActivity {
    StartDialogS2 startDialogS2;

    @BindView(R.id.talk_icon)
    ImageView talkIcon;
    @BindView(R.id.mailbox_icon)
    ImageView mailboxIcon;
    @BindView(R.id.explorer_icon)
    ImageView explorerIcon;

    @Override
    protected void initView() {
//        startDialogS2 = new StartDialogS2(this);
//        if (customInterstitialAd.isLoaded()) {
//            customInterstitialAd.show();
//        }else
//            startDialogS2.show();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_desk_season2;
    }


    @OnClick({R.id.talk_icon, R.id.mailbox_icon, R.id.explorer_icon})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.talk_icon:
                startActivity(new Intent(this,XEAChatActivityS2.class));
                break;
            case R.id.mailbox_icon:
                startActivity(new Intent(this,MailActivity.class));
                break;
            case R.id.explorer_icon:
                startActivity(new Intent(this,ExplorerActivity.class));
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (startDialogS2 != null) {
            startDialogS2.dismiss();
        }
    }

}
