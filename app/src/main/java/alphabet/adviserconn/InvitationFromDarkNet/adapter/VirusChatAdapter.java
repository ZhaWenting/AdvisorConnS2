package alphabet.adviserconn.InvitationFromDarkNet.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alphabet.adviserconn.InvitationFromDarkNet.bean.Season2ChatBean;
import alphabet.adviserconn.InvitationFromDarkNet.utils.ConvertString;
import alphabet.adviserconn.InvitationFromDarkNet.widget.ImageDialog;
import alphabet.adviserconn.R;
import alphabet.adviserconn.utils.LogUtil;
import alphabet.adviserconn.widget.MyAlertDialog;

/**
 * Created by Administrator on 2016/10/8.
 */

public class VirusChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final String TAG = this.getClass().toString();
    private Context context;
    Activity activity;
    private ArrayList<Season2ChatBean> list;
    MyAlertDialog myAlertDialog;

    public VirusChatAdapter(Context context, Activity activity, ClickTextListener clickTextListener, LinkedTextListener linkedTextListener) {
        this.context = context;
        this.activity = activity;
        this.clickTextListener = clickTextListener;
        this.linkedTextListener = linkedTextListener;
    }


    public VirusChatAdapter(Context context, ArrayList<Season2ChatBean> list, Activity activity, ClickTextListener clickTextListener, LinkedTextListener linkedTextListener) {
        this.context = context;
        this.list = list;
        this.activity = activity;
        this.clickTextListener = clickTextListener;
        this.linkedTextListener = linkedTextListener;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                TextViewHolder holder = new TextViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_text_s2, parent,
                        false));
                return holder;
            case 2:
                holder = new TextViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_player_s2, parent,
                        false));
                return holder;
            case 3:
                SelectionViewHolder selectionHolder = new SelectionViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_selection_s2, parent,
                        false));
                return selectionHolder;
            case 4:
                ImageViewHolder imageViewHolder = new ImageViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_image_s2, parent,
                        false));
                return imageViewHolder;
            case 5:
                LinkedViewHolder linkedViewHolder = new LinkedViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_linked_text_season2, parent,
                        false));
                return linkedViewHolder;
            case 6:
                DateViewHolder dateViewHolder = new DateViewHolder(LayoutInflater.from(
                        context).inflate(R.layout.virus_date_s2, parent,
                        false));
                return dateViewHolder;

            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).tv.setText(list.get(position).getContent());
        } else if (holder instanceof SelectionViewHolder) {
            ((SelectionViewHolder) holder).tv.setText(list.get(position).getContent());
        } else if (holder instanceof LinkedViewHolder) {
            ((LinkedViewHolder) holder).tv.setText(list.get(position).getContent());
        } else if (holder instanceof ImageViewHolder) {
//            int convert = ConvertString.convert(list.get(position).getHero_image());
//            LogUtil.d(TAG,"ImageViewHolder"+convert);
            final int imageRes = ConvertString.convert(list.get(position).getHero_image());
            ((ImageViewHolder) holder).iv.setImageResource(imageRes);
            ((ImageViewHolder) holder).iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new ImageDialog(context, imageRes).show();
                }
            });
        } else if (holder instanceof DateViewHolder) {
            ((DateViewHolder) holder).tv.setText(list.get(position).getContent());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            if (holder instanceof TextViewHolder) {
                ((TextViewHolder) holder).tv.setText(list.get(position).getContent());
            } else if (holder instanceof SelectionViewHolder) {
                ((SelectionViewHolder) holder).tv.setText(list.get(position).getContent());
            } else if (holder instanceof LinkedTextListener) {
                ((LinkedViewHolder) holder).tv.setText(list.get(position).getContent());
            } else if (holder instanceof ImageViewHolder) {
                final int imageRes = ConvertString.convert(list.get(position).getHero_image());
                ((ImageViewHolder) holder).iv.setImageResource(imageRes);
                ((ImageViewHolder) holder).iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new ImageDialog(context, imageRes).show();
                    }
                });
            } else if (holder instanceof DateViewHolder) {
                ((DateViewHolder) holder).tv.setText(list.get(position).getContent());
            }
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
        switch (list.get(position).getType()) {
            case "hero_text":
            case "hero_answer_multi":
                return 1;
            case "player_text":
            case "player_single_branch":
            case "player_select_multi":
                return 2;
            case "player_clickable":
                return 3;
            case "hero_image":
                return 4;
            case "hero_text_linked":
                return 5;
            case "date":
                return 6;
        }
        return 0;
    }

    public void addDataNoAnim(int position, Season2ChatBean chatBean) {
//      在list中添加数据，并通知条目加入一条
        list.add(position, chatBean);
    }


    public void addData(int position, Season2ChatBean chatBean) {
//      在list中添加数据，并通知条目加入一条
        list.add(position, chatBean);
        notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAll() {
        list.clear();
        for (int i = 0; i < list.size(); i++) {
            list.remove(list.size() - 1);
        }
        notifyDataSetChanged();
    }

    public void setList(ArrayList<Season2ChatBean> list) {
        this.list = list;
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TextViewHolder(final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }
    }

    class DateViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public DateViewHolder(final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }
    }

    class LinkedViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public LinkedViewHolder(final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtil.d(TAG, "linkedViewHolder:" + list.get(getAdapterPosition()).getLinked_word());
                    linkedTextListener.clickText(list.get(getAdapterPosition()).getLinked_word());
                }
            });
        }
    }

    class SelectionViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public SelectionViewHolder(final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myAlertDialog == null) {
                        myAlertDialog = new MyAlertDialog(context, "是否回溯时间？", "确定", "取消") {
                            @Override
                            public void buttonOne() {
//                                LogUtil.d(TAG, "playerClickLoad:" + list.get(getAdapterPosition()).getSelection_num());
                                clickTextListener.clickText(Integer.parseInt(list.get(getAdapterPosition()).getSelection_num()));
                            }

                            @Override
                            public void buttonTwo() {

                            }
                        };
                    }
                    myAlertDialog.show();
                }
            });
        }
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image);
        }
    }

    public interface ClickTextListener {
        void clickText(int selectNum);
    }

    ClickTextListener clickTextListener;

    public interface LinkedTextListener {
        void clickText(String linkedText);
    }

    LinkedTextListener linkedTextListener;
}
