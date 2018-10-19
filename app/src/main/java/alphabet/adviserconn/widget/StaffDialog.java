package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alphabet.adviserconn.R;


public class StaffDialog extends Dialog{

    private TextView restartTv;
    private TextView staffTv;
    private Context context;


    public StaffDialog(Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_info);
        this.setCanceledOnTouchOutside(true);
    }
}

