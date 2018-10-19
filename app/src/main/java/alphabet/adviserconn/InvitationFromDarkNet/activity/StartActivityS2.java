package alphabet.adviserconn.InvitationFromDarkNet.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseActivity;
import butterknife.BindView;

public class StartActivityS2 extends BaseActivity {

    AlphaAnimation alphaAnimation;
    TranslateAnimation translateAnima;
    Handler animaHandler = new Handler();
    Runnable animaRunnable = new Runnable() {
        @Override
        public void run() {
            word.startAnimation(translateAnima);
            word.setVisibility(View.VISIBLE);
            loadingRl.startAnimation(alphaAnimation);

        }
    };

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(StartActivityS2.this,DeskActivity.class));
            finish();
        }
    };


    @BindView(R.id.word)
    ImageView word;
    @BindView(R.id.loading_rl)
    RelativeLayout loadingRl;

    @Override
    protected void initView() {
        word.setVisibility(View.INVISIBLE);
        translateAnima = (TranslateAnimation) AnimationUtils.loadAnimation(StartActivityS2.this, R.anim.loading_word1_chapter2);
        alphaAnimation = new AlphaAnimation(0.7f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(4);

        animaHandler.postDelayed(animaRunnable, 300);
        handler.postDelayed(runnable,5000);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.activity_loading_season2;

    }

    private void cancelAnima() {
        if (translateAnima != null)
            translateAnima.cancel();
        if (alphaAnimation != null)
            alphaAnimation.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        animaHandler.removeCallbacks(animaRunnable);
        handler.removeCallbacks(runnable);
        cancelAnima();
    }
}
