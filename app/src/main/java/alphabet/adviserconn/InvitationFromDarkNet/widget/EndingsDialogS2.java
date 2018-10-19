package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alphabet.adviserconn.R;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.config.SystemParamsS2;
import alphabet.adviserconn.utils.SPUtil;


public class EndingsDialogS2 extends Dialog {

    TextView textView1;
    private TextView endingContentTv;


    SystemParamsS2 systemParams;


    public EndingsDialogS2(Context context, SystemParamsS2 systemParams) {
        super(context, R.style.DialogTheme);
        this.systemParams = systemParams;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ending_text);
        textView1 = findViewById(R.id.fall_down);
        if (systemParams.getBoolean("ending_falldown", false))
            textView1.setVisibility(View.VISIBLE);


        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void show() {
        super.show();
    }


}

