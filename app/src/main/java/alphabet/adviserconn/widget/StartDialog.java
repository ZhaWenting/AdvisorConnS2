package alphabet.adviserconn.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import alphabet.adviserconn.R;

/**
 * Created by Administrator on 2016/9/26.
 */
public class StartDialog extends Dialog {


    //window的颜色是黑色的
    public StartDialog(Context context) {
        super(context, R.style.StartDialogTheme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView iv = new ImageView(getContext());
        iv.setImageResource(R.mipmap.start);
        setContentView(iv);
    }

    @Override
    public void show() {
        super.show();
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (StartDialog.this != null && StartDialog.this.isShowing()) {
//                    dialogListener.next();
                    dismiss();
                }
            }
        }.start();
    }


}
