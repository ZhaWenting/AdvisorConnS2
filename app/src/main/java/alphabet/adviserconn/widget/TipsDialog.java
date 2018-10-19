package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import alphabet.adviserconn.R;


public class TipsDialog extends Dialog{


    public TipsDialog(Context context) {
        super(context, R.style.DialogTheme);
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tips);
        this.setCanceledOnTouchOutside(true);
    }
}

