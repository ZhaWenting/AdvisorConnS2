package alphabet.adviserconn.InvitationFromDarkNet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import alphabet.adviserconn.InvitationFromDarkNet.bean.MailBean;
import alphabet.adviserconn.InvitationFromDarkNet.utils.TimeUtils;
import alphabet.adviserconn.InvitationFromDarkNet.widget.MailDialog;
import alphabet.adviserconn.R;
import alphabet.adviserconn.config.SystemParams;
import alphabet.adviserconn.config.SystemParamsS2;

/**
 * Created by Administrator on 2016/10/8.
 */

public class MailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MailBean> list;



    public MailAdapter(Context context, List<MailBean> list) {
        this.context = context;
        this.list = list;
    }

    public MailAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(
                context).inflate(R.layout.list_item_mail, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            SystemParamsS2 systemParamsS2 = SystemParamsS2.getInstance();
            long time = systemParamsS2.getLong("time");
            //设置邮件时间时为开启应用后的第position-1日
            if (position > 0) {
                String date = TimeUtils.addDay(time, position - 1);
                viewHolder.timeTv.setText(date);
                list.get(position).setTime(date);
            }else {
                viewHolder.timeTv.setText(list.get(position).getTime());
            }
            viewHolder.senderTv.setText(list.get(position).getSenderName());
            viewHolder.contentTv.setText(list.get(position).getContent());
            viewHolder.titleTv.setText(list.get(position).getTitle());
            viewHolder.listItemRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MailDialog mailDialog = new MailDialog(context, list.get(position));
                    mailDialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        return 0;
    }

    //  添加数据
    public void addData(int position, MailBean mail) {
//      在list中添加数据，并通知条目加入一条
        list.add(position, mail);
        notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarIv;
        TextView timeTv;
        TextView senderTv;
        TextView titleTv;
        TextView contentTv;
        RelativeLayout listItemRl;

        public ViewHolder(View itemView) {
            super(itemView);

            avatarIv = (ImageView) itemView.findViewById(R.id.avatar);
            timeTv = (TextView) itemView.findViewById(R.id.time);
            senderTv = (TextView) itemView.findViewById(R.id.sender);
            titleTv = (TextView) itemView.findViewById(R.id.title);
            contentTv = (TextView) itemView.findViewById(R.id.content);
            listItemRl = (RelativeLayout) itemView;
        }
    }


}
