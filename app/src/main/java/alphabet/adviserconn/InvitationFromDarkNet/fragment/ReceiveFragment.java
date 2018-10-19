package alphabet.adviserconn.InvitationFromDarkNet.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import alphabet.adviserconn.InvitationFromDarkNet.adapter.MailAdapter;
import alphabet.adviserconn.InvitationFromDarkNet.bean.MailBean;
import alphabet.adviserconn.R;
import alphabet.adviserconn.base.BaseFragment;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.config.SystemParamsS2;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveFragment extends BaseFragment {
    Context context;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    MailAdapter mailAdapter;
    List<MailBean> list = new ArrayList<>();
    List<MailBean> mailList = new ArrayList<>();

    public ReceiveFragment() {

    }

    @Override
    protected void iniView() {
        context = getActivity();
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        recyclerview.setItemAnimator(defaultItemAnimator);
        recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerview.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        MailBean mailBean = new MailBean();
        mailBean.setAttachment("");
        mailBean.setContent("欢迎使用云顾问邮箱！");
        mailBean.setReceiverName("default728");
        mailBean.setSenderName("Administrator");
        long time = systemParamsS2.getLong("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(time);
        mailBean.setTime(format.substring(4,6)+"月"+format.substring(6,8)+"日");
        mailBean.setTitle("欢迎！");

        list.add(mailBean);
        mailAdapter = new MailAdapter(context, list);
        recyclerview.setAdapter(mailAdapter);


        InputStream inputStream = getResources().openRawResource(R.raw.season2_mail_receive);
        String mailReceive = getString(inputStream);
        Gson gson = new Gson();
        mailList = gson.fromJson(mailReceive, new TypeToken<List<MailBean>>() {
        }.getType());
    }

    @Override
    public void onResume() {
        super.onResume();
        if(systemParamsS2.getBoolean("mail1")) {
            list.add(mailList.get(0));
            //If player receive the mail, the restart flag will be set to be true
            systemParamsS2.setBoolean("mail1Read",true);
        }
        if(systemParamsS2.getBoolean("mail2")) {
            list.add(mailList.get(1));
            //If player receive the mail, the restart flag will be set to be true
            systemParamsS2.setBoolean("mail2Read",true);
        }
        if(systemParamsS2.getBoolean("mail3")) {
            list.add(mailList.get(2));
            //If player receive the mail, the restart flag will be set to be true
            systemParamsS2.setBoolean("mail3Read",true);
        }
        if(systemParamsS2.getBoolean("mail4")) {
            list.add(mailList.get(3));
            //If player receive the mail, the restart flag will be set to be true
            systemParamsS2.setBoolean("mail4Read",true);
        }
        if(systemParamsS2.getBoolean("mail5")) {
            list.add(mailList.get(4));
            //If player receive the mail, the restart flag will be set to be true
            systemParamsS2.setBoolean("mail5Read",true);
        }
        mailAdapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_receive;
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
