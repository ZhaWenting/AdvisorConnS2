package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alphabet.adviserconn.R;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.utils.SPUtil;


public class EndingsDialog extends Dialog {

    private TextView endingInfinityIv;
    private TextView endingChaosIv;
    private TextView endingWakeupIv;
    private TextView endingRebirthIv;
    private TextView endingCutoffIv;
    private TextView endingAmnesiaIv;
    private Context context;
    private TextView endingContentTv;

    SPUtil spUtilSaveNum;
    private int lastSave = 0;
    String charpter = "";

    SystemParams systemParams;


    public EndingsDialog(Context context, SystemParams systemParams) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.systemParams = systemParams;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ending);
        endingContentTv = (TextView) findViewById(R.id.ending_content);
        endingInfinityIv = (TextView) findViewById(R.id.ending_infinity);
        endingInfinityIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("被关在无限循环的地图中，新主角将诞生");
            }
        });
//        restartTv.setOnClickListener(this);
        endingChaosIv = (TextView) findViewById(R.id.ending_chaos);
        endingChaosIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("小唯脱离服务器控制，AI取代原型？");
            }
        });
        endingWakeupIv = (TextView) findViewById(R.id.ending_wakeup);
        endingWakeupIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("从植物人状态中苏醒，小唯AI被删除");
            }
        });
        endingRebirthIv = (TextView) findViewById(R.id.ending_rebirth);
        endingRebirthIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("恢复所有记忆，帮助了小唯，与贾唯重聚");
            }
        });
        endingCutoffIv = (TextView) findViewById(R.id.ending_cutoff);
        endingCutoffIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("与服务器断开连接，主角资料损坏");
            }
        });
        endingAmnesiaIv = (TextView) findViewById(R.id.ending_amnesia);
        endingAmnesiaIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endingContentTv.setText("放弃探索真相，被管理员清除记忆");
            }
        });

        if (systemParams.getBoolean("ending_rebirth", false))
            endingRebirthIv.setEnabled(true);
        if (systemParams.getBoolean("ending_wakeup", false))
            endingWakeupIv.setEnabled(true);
        if (systemParams.getBoolean("ending_infinity", false))
            endingInfinityIv.setEnabled(true);
        if (systemParams.getBoolean("ending_chaos", false))
            endingChaosIv.setEnabled(true);
        if (systemParams.getBoolean("ending_amnesia", false))
            endingAmnesiaIv.setEnabled(true);
        if (systemParams.getBoolean("ending_cutoff", false))
            endingCutoffIv.setEnabled(true);



//        spUtilSaveNum = new SPUtil(context, "SaveNum");
//        lastSave = spUtilSaveNum.getInt("lastSave", 0);

//        Log.e("lastSave: ",lastSave+"" );

//        if ("true".equals(spUtilSaveNum.getString("ending_rebirth", "false"))){
//            endingRebirthIv.setVisibility(View.VISIBLE);
//        }
//        if ("true".equals(spUtilSaveNum.getString("ending_wakeup", "false"))){
//            endingWakeupIv.setVisibility(View.VISIBLE);
//        }
//        if ("true".equals(spUtilSaveNum.getString("ending_chaos", "false"))){
//            endingChaosIv.setVisibility(View.VISIBLE);
//        }
//        if ("true".equals(spUtilSaveNum.getString("ending_collapse", "false"))){
//            endingInfinityIv.setVisibility(View.VISIBLE);
//        }
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
    }


}

