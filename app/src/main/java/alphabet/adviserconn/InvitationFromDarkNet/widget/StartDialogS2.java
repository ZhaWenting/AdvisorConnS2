package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import alphabet.adviserconn.R;

/**
 * Created by Administrator on 2016/9/26.
 */
public class StartDialogS2 extends Dialog {

    AlphaAnimation alphaAnimation;
    TranslateAnimation translateAnima;
    ImageView word;
    RelativeLayout loadingRl;

    Context context;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            word.startAnimation(translateAnima);
            word.setVisibility(View.VISIBLE);
            loadingRl.startAnimation(alphaAnimation);
        }
    };

    //window的颜色是黑色的
    public StartDialogS2(Context context) {
        super(context, R.style.StartDialogTheme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_season2);
        word = findViewById(R.id.word);
        loadingRl = findViewById(R.id.loading_rl);
        word.setVisibility(View.INVISIBLE);

        translateAnima = (TranslateAnimation) AnimationUtils.loadAnimation(context, R.anim.loading_word1_chapter2);
        alphaAnimation = new AlphaAnimation(0.7f, 1.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatCount(4);

        handler.postDelayed(runnable,300);
    }

    @Override
    public void show() {
        super.show();
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dismiss();
                cancelAnima();
                handler.removeCallbacks(runnable);

            }
        }.start();
    }

    private void cancelAnima() {
        if (translateAnima != null)
            translateAnima.cancel();
        if (alphaAnimation != null)
            alphaAnimation.cancel();
    }


}
