package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import alphabet.adviserconn.R;


public abstract class MyAlertDialog extends Dialog implements View.OnClickListener {

    private TextView buttonOneTv;
    private TextView buttonTwoTv;
    private TextView titleTv;
    private Context context;
    String title;
    String button1;
    String button2;


    protected MyAlertDialog(Context context, String title, String button1, String button2) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.title = title;
        this.button1 = button1;
        this.button2 = button2;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        titleTv = (TextView) findViewById(R.id.title);
        buttonOneTv = (TextView) findViewById(R.id.buttonOne);
        buttonOneTv.setOnClickListener(this);
        buttonTwoTv = (TextView) findViewById(R.id.buttonTwo);
        buttonTwoTv.setOnClickListener(this);
        this.setCanceledOnTouchOutside(true);

        titleTv.setText(title);
        buttonOneTv.setText(button1);
        buttonTwoTv.setText(button2);
    }

    protected int getLayoutId() {
        return R.layout.dialog_end;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOne:
                buttonOne();
                dismiss();
                break;
            case R.id.buttonTwo:
                buttonTwo();
                dismiss();
                break;
        }
    }

    public abstract void buttonOne();

    public abstract void buttonTwo();
}

