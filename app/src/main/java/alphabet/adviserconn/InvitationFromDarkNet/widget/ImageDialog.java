package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import alphabet.adviserconn.R;

public class ImageDialog extends Dialog {
    private Context context;
    ImageView image;
    int resourceId;

    public ImageDialog(Context paramContext, int paramInt) {
        super(paramContext, R.style.DialogTheme);
        this.context = paramContext;
        this.resourceId = paramInt;
    }

    protected int getLayoutId() {
        return R.layout.dialog_image_s2;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(getLayoutId());
        setCanceledOnTouchOutside(true);
        this.image = findViewById(R.id.image);
        this.image.setImageResource(this.resourceId);
    }
}
