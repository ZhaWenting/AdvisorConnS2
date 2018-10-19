package alphabet.adviserconn.utils;

import android.icu.util.ValueIterator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/10 0010.
 */

public class AnimeUtil {

    public static void infoAlphaInAnime(final View view){
        view.setVisibility(View.VISIBLE);
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);
        view.setAnimation(animation);
    }


    public static void infoAlphaOutAnime(final View view){
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(animation);

    }

    //横向菜单展开
    public static void expand(final View view){
        ScaleAnimation animation = new ScaleAnimation(0f,1.0f,1.0f,1.0f);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                collapse(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(animation);
    }

    //横向菜单收缩
    public static void collapse(final View view){
        ScaleAnimation animation = new ScaleAnimation(1.0f,0f,1.0f,1.0f);
        animation.setDuration(500);
        animation.setStartOffset(10000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(animation);
    }

}
