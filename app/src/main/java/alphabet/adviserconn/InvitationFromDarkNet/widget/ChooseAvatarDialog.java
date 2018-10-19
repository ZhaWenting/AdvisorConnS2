package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import alphabet.adviserconn.InvitationFromDarkNet.activity.XEAChatActivityS2;
import alphabet.adviserconn.InvitationFromDarkNet.activity.VirusChatActivity;
import alphabet.adviserconn.R;

public class ChooseAvatarDialog extends Dialog {
    private Context context;
    ImageView virus;
    ImageView xea;
    String className;

    public ChooseAvatarDialog(Context paramContext, String className) {
        super(paramContext, R.style.DialogTheme);
        this.context = paramContext;
        this.className = className;
    }

    protected int getLayoutId() {
        return R.layout.dialog_choose_avatar;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(getLayoutId());
        setCanceledOnTouchOutside(true);
        this.virus = findViewById(R.id.virus);
        virus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!className.equals("VirusChatActivity"))
                    context.startActivity(new Intent(context, VirusChatActivity.class));
                dismiss();
            }
        });
        this.xea = findViewById(R.id.xea);
        xea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!className.equals("XEAChatActivityS2"))
                    context.startActivity(new Intent(context, XEAChatActivityS2.class));
                dismiss();
            }
        });
    }
}
