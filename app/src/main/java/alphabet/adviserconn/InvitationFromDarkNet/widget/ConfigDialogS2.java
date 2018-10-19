package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alphabet.adviserconn.R;


public abstract class ConfigDialogS2 extends Dialog implements View.OnClickListener {

    private TextView restartTv;
    private TextView staffTv;
    private TextView endingsTv;
//    private TextView tipsTv;
    private TextView speed1Tv;
    private TextView speed2Tv;
    private TextView speed3Tv;
    private Context context;
    int textSpeed;
    boolean isEnd;
    boolean isOtherInterface;


    public ConfigDialogS2(Context context, int textSpeed, boolean isEnd) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.textSpeed = textSpeed;
        this.isEnd = isEnd;
    }

    public ConfigDialogS2(Context context, int textSpeed, boolean isEnd,boolean isOtherInterface) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.textSpeed = textSpeed;
        this.isEnd = isEnd;
        this.isOtherInterface = isOtherInterface;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_config_s2);
        restartTv = (TextView) findViewById(R.id.new_charpter);
        if(isOtherInterface)
            restartTv.setVisibility(View.GONE);
        restartTv.setOnClickListener(this);
        staffTv = (TextView) findViewById(R.id.imaginary_space);
        staffTv.setOnClickListener(this);
        endingsTv = (TextView) findViewById(R.id.endings);
        endingsTv.setOnClickListener(this);
        speed1Tv = (TextView) findViewById(R.id.speed1);
        speed2Tv = (TextView) findViewById(R.id.speed2);
        speed3Tv = (TextView) findViewById(R.id.speed3);
//        tipsTv = (TextView) findViewById(R.id.tips);
//        tipsTv.setOnClickListener(this);
        switch (textSpeed) {
            case 1:
                speed1Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed2Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed3Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                break;
            case 2:
                speed2Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed1Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed3Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                break;
            case 3:
                speed3Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed2Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed1Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                break;
            default:
                textSpeed = 1;
                speed1Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed2Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed3Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                break;
        }
//        if (isEnd) {
//            tipsTv.setVisibility(View.VISIBLE);
//        } else
//            tipsTv.setVisibility(View.GONE);
        speed1Tv.setOnClickListener(this);
        speed2Tv.setOnClickListener(this);
        speed3Tv.setOnClickListener(this);

        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_charpter:
                restart();
                dismiss();
                break;
            case R.id.imaginary_space:
                staff();
                break;
            case R.id.endings:
                endings();
                break;
            case R.id.speed1:
                textSpeed = 1;
                speed1Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed2Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed3Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                changeTextSpeed(textSpeed);
                break;
            case R.id.speed2:
                textSpeed = 2;
                speed2Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed1Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed3Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                changeTextSpeed(textSpeed);
                break;
            case R.id.speed3:
                textSpeed = 3;
                speed3Tv.setTextColor(context.getResources().getColor(R.color.navigationBarColor));
                speed2Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                speed1Tv.setTextColor(context.getResources().getColor(R.color.textColorPrimary));
                changeTextSpeed(textSpeed);
                break;
//            case R.id.tips:
//                tips();
//                dismiss();
//                break;
        }
    }

//    public abstract void tips();

    public abstract void restart();

    public abstract void endings();

    public abstract void staff();

    public abstract void changeTextSpeed(int textSpeed);
}

