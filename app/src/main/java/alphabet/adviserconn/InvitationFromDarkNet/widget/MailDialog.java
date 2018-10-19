package alphabet.adviserconn.InvitationFromDarkNet.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import alphabet.adviserconn.InvitationFromDarkNet.bean.MailBean;
import alphabet.adviserconn.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MailDialog extends Dialog {

    TextView title;
    ImageView avatar;
    TextView sender;
    TextView receiver;
    TextView time;
    TextView content;
    TextView attachment;

    private Context context;
    private MailBean mailBean;


    public MailDialog(Context context, MailBean mailBean) {
        super(context, R.style.DialogTheme);
        this.context = context;
        this.mailBean = mailBean;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.setCanceledOnTouchOutside(true);

        title = findViewById(R.id.title);
        sender = findViewById(R.id.sender);
        avatar = findViewById(R.id.avatar);
        receiver = findViewById(R.id.receiver);
        time = findViewById(R.id.time);
        content = findViewById(R.id.content);
        attachment = findViewById(R.id.attachment);

        title.setText(mailBean.getTitle());
        sender.setText("发件人："+mailBean.getSenderName());
        receiver.setText("收件人："+mailBean.getReceiverName());

        time.setText("收件时间："+mailBean.getTime());
        content.setText(mailBean.getContent());
        attachment.setText("附件："+mailBean.getAttachment());
    }

    protected int getLayoutId() {
        return R.layout.dialog_mail;
    }

}

