package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alphabet.adviserconn.R;


public class InfoDialog extends Dialog implements View.OnClickListener {

    private TextView buttonOneTv;
    private TextView titleTv;
    private Context context;
    String title;
    int imgRes;

    public InfoDialog(Context context) {
        super(context, R.style.DialogTheme);
        this.context = context;
    }

    public InfoDialog(Context context, String title,int imgRes) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.title = title;
        this.imgRes = imgRes;
    }

    public void setContent(String title,int imgRes){
        this.title = title;
        this.imgRes = imgRes;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        titleTv =  findViewById(R.id.title);
        buttonOneTv = findViewById(R.id.buttonOne);
        buttonOneTv.setOnClickListener(this);
        this.setCanceledOnTouchOutside(true);
        titleTv.setText(title);
        ImageView imgView = findViewById(R.id.img);
        imgView.setImageResource(imgRes);
    }

    protected int getLayoutId() {
        return R.layout.dialog_tip_info;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOne:
                dismiss();
                break;
        }
    }


}

