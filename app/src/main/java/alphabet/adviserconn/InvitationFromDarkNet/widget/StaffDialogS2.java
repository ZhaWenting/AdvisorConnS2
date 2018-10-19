package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import alphabet.adviserconn.R;


public class StaffDialogS2 extends Dialog{

    private TextView restartTv;
    private TextView staffTv;
    private Context context;


    public StaffDialogS2(Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_staff_s2);
        this.setCanceledOnTouchOutside(true);
    }
}

